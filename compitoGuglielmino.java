package compito;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class compitoGuglielmino {

    private JFrame frame;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField textFieldOre;
    private JLabel risultatoLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    compitoGuglielmino window = new compitoGuglielmino();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public compitoGuglielmino() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(128, 128, 128));
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnEsci = new JButton("Exit");
        btnEsci.setBackground(new Color(128, 255, 255));
        btnEsci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnEsci.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnEsci.setBounds(450, 300, 120, 45);
        frame.getContentPane().add(btnEsci);

        JLabel lblTitolo = new JLabel("Gestione Parcheggio");
        lblTitolo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        lblTitolo.setBounds(10, 10, 200, 25);
        frame.getContentPane().add(lblTitolo);

        JRadioButton rdbtnSconto = new JRadioButton("Sconto 10%");
        rdbtnSconto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hai selezionato sconto");

        	}
        });
        rdbtnSconto.setBackground(new Color(128, 128, 128));
        buttonGroup.add(rdbtnSconto);
        rdbtnSconto.setBounds(300, 50, 150, 30);
        frame.getContentPane().add(rdbtnSconto);

        JRadioButton rdbtnNoSconto = new JRadioButton("No sconto");
        rdbtnNoSconto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hai selezionato no sconto");

        	}
        });
        rdbtnNoSconto.setBackground(new Color(128, 128, 128));
        buttonGroup.add(rdbtnNoSconto);
        rdbtnNoSconto.setBounds(300, 90, 150, 30);
        frame.getContentPane().add(rdbtnNoSconto);

        JComboBox<String> comboBoxMezzo = new JComboBox<>();
        comboBoxMezzo.setModel(new DefaultComboBoxModel<>(new String[]{"Macchina 3€", "Moto 2€", "Bici 1€"}));
        comboBoxMezzo.setBounds(10, 50, 150, 25);
        frame.getContentPane().add(comboBoxMezzo);

        JLabel lblOre = new JLabel("Inserisci ore:");
        lblOre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOre.setBounds(10, 90, 100, 20);
        frame.getContentPane().add(lblOre);

        textFieldOre = new JTextField();
        textFieldOre.setBounds(10, 120, 150, 25);
        frame.getContentPane().add(textFieldOre);
        textFieldOre.setColumns(10);

        JButton btnCalcola = new JButton("Calcola");
        btnCalcola.setBackground(new Color(128, 255, 255));
        btnCalcola.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Recupera il veicolo selezionato
                    String mezzo = (String) comboBoxMezzo.getSelectedItem();
                    int ore = Integer.parseInt(textFieldOre.getText());
                    double tariffa = 0;

                    if (mezzo.contains("Macchina")) {
                        tariffa = 3;
                    } else if (mezzo.contains("Moto")) {
                        tariffa = 2;
                    } else if (mezzo.contains("Bici")) {
                        tariffa = 1;
                    }

                    double prezzo = ore * tariffa;

                    // Applica sconto se selezionato
                    if (rdbtnSconto.isSelected()) {
                        prezzo *= 0.9;
                    }

                    risultatoLabel.setText("Prezzo totale: " + String.format("%.2f", prezzo) + "€");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Inserisci un numero valido di ore!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCalcola.setBounds(10, 160, 100, 30);
        frame.getContentPane().add(btnCalcola);

        risultatoLabel = new JLabel("");
        risultatoLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        risultatoLabel.setBounds(10, 200, 400, 30);
        frame.getContentPane().add(risultatoLabel);
    }
}
