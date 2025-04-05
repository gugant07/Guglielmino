import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RubricaApp extends JFrame {
    private List<Contatto> contatti; // Lista per gestire i contatti
    private DefaultTableModel tableModel; // Modello per la tabella dei contatti

    public RubricaApp() {
        contatti = new ArrayList<>();
        setTitle("Rubrica Telefonica");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Creazione della tabella dei contatti
        tableModel = new DefaultTableModel(new String[]{"Niome", "Cognome", "Telefono", "Email"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Pannello per i pulsanti
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        // Pulsanti per gestire i contatti
        JButton addButton = new JButton("Aggiungi Contatto");
        JButton deleteButton = new JButton("Rimuovi Contatto");
        JButton saveButton = new JButton("Salva");
        JButton loadButton = new JButton("Carica");

        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(saveButton);
        
        JButton btnEsci = new JButton("ESCI");
        btnEsci.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        panel.add(btnEsci);
        panel.add(loadButton);

        // Azione per aggiungere un contatto
        addButton.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Nome:");
            String cognome = JOptionPane.showInputDialog("Cognome:");
            String telefono = JOptionPane.showInputDialog("Telefono:");
            String email = JOptionPane.showInputDialog("Email:");
            Contatto contatto = new Contatto(nome, cognome, telefono, email);
            contatti.add(contatto); // Aggiunge il nuovo contatto alla lista
            tableModel.addRow(new Object[]{nome, cognome, telefono, email}); // Aggiunge il contatto alla tabella
        });

        // Azione per rimuovere un contatto
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); // Ottiene la riga selezionata dalla tabella
            if (selectedRow >= 0) {
                contatti.remove(selectedRow); // Rimuove il contatto dalla lista
                tableModel.removeRow(selectedRow); // Rimuove il contatto dalla tabella
            } else {
                JOptionPane.showMessageDialog(this, "Seleziona un contatto da rimuovere.");
            }
        });

        // Azione per salvare i contatti su Desktop in formato leggibile
        saveButton.addActionListener(e -> {
            String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "rubrica.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(desktopPath))) {
                for (Contatto contatto : contatti) {
                    writer.write(contatto.getNome() + "," + contatto.getCognome() + "," +
                                 contatto.getTelefono() + "," + contatto.getEmail());
                    writer.newLine(); // Vai a capo dopo ogni contatto
                }
                JOptionPane.showMessageDialog(this, "Rubrica salvata sul Desktop in formato leggibile.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Azione per caricare i contatti dal file sul Desktop
        loadButton.addActionListener(e -> {
            String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "rubrica.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(desktopPath))) {
                String line;
                contatti.clear(); // Pulisce la lista dei contatti
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(","); // Divide la riga per i separatori
                    if (parts.length == 4) {
                        Contatto contatto = new Contatto(parts[0], parts[1], parts[2], parts[3]);
                        contatti.add(contatto); // Aggiunge il contatto alla lista
                    }
                }
                tableModel.setRowCount(0); // Pulisce la tabella
                for (Contatto contatto : contatti) {
                    tableModel.addRow(new Object[]{contatto.getNome(), contatto.getCognome(), contatto.getTelefono(), contatto.getEmail()});
                }
                JOptionPane.showMessageDialog(this, "Rubrica caricata dal Desktop in formato leggibile.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RubricaApp app = new RubricaApp();
            app.setVisible(true); // Mostra la finestra dell'applicazione
        });
    }
}
