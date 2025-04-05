

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Ristorante {
    int numPers = 0;
    float conto = 0;
    int totAlimenti =0;

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox_1;
    private final float[] prezziCibo = {5.0f, 0.0f, 7.0f, 8.0f, 10.0f, 9.0f, 6.0f, 7.0f}; 
    private final float[] prezziBevande = {1.5f, 2.0f, 0.0f, 2.5f, 2.5f, 2.5f, 15.0f, 15.0f, 15.0f, 50.0f};
    private JTextField textField_2;
    private JTextField textFieldTableNumber;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ristorante window = new Ristorante();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Ristorante() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(63, 63, 63));
        frame.setBounds(100, 100, 505, 541);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(111, 111, 111));
        panel.setBounds(10, 0, 331, 449);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"margherita", "niente", "bufala", "capricciosa", "michaeltonsy", "4 stagioni", "panino con hamburgus", "mac chicken"}));
        comboBox.setBounds(10, 102, 150, 22);
        panel.add(comboBox);

        JLabel lblNewLabel = new JLabel("Coperto per persona 1.00€");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(10, 11, 180, 36);
        panel.add(lblNewLabel);

        JButton btnAddPerson = new JButton("Aggiungi Persona");
        btnAddPerson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numPers++;
                conto++;
                textField.setText("" + numPers);
            }
        });
        btnAddPerson.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAddPerson.setBounds(181, 19, 140, 23);
        panel.add(btnAddPerson);

        JLabel lblTotalPeople = new JLabel("Totale Persone:");
        lblTotalPeople.setBounds(48, 58, 100, 14);
        panel.add(lblTotalPeople);

        textField = new JTextField();
        textField.setBounds(140, 58, 50, 20);
        panel.add(textField);
        textField.setColumns(10);

        JButton btnAddFood = new JButton("Aggiungi Alimento");
        btnAddFood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBox.getSelectedIndex();
                conto += prezziCibo[selectedIndex];
                totAlimenti++;
                textField_2.setText("" + totAlimenti);
            }
        });
        btnAddFood.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        btnAddFood.setBounds(171, 102, 150, 23);
        panel.add(btnAddFood);

        JButton btnAddDrink = new JButton("Aggiungi Bevanda");
        btnAddDrink.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBox_1.getSelectedIndex();
                conto += prezziBevande[selectedIndex];
            }
        });
        btnAddDrink.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        btnAddDrink.setBounds(171, 155, 150, 23);
        panel.add(btnAddDrink);

        textField_1 = new JTextField();
        textField_1.setBounds(69, 270, 200, 30);
        panel.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Vuoi bere qualcosa?");
        lblNewLabel_2.setBounds(10, 130, 150, 14);
        panel.add(lblNewLabel_2);

        comboBox_1 = new JComboBox<>();
        comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] {"acqua", "sprite", "niente", "cocacola", "coca zero", "coca zero zuccheri", "vino rosso", "vino bianco", "vino rosé", "champagne"}));
        comboBox_1.setBounds(10, 155, 150, 22);
        panel.add(comboBox_1);

        JButton btnShowBill = new JButton("Mostra Conto");
        btnShowBill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_1.setText("" + conto + "€");
            }
        });
        btnShowBill.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        btnShowBill.setBounds(96, 311, 150, 23);
        panel.add(btnShowBill);

        JLabel lblNewLabel_2_1 = new JLabel("tot alimenti :");
        lblNewLabel_2_1.setBounds(171, 136, 76, 14);
        panel.add(lblNewLabel_2_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(236, 136, 50, 14);
        panel.add(textField_2);

        JButton btnSaveToFile = new JButton("Salva su File");
        btnSaveToFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveToFile(textFieldTableNumber.getText());
            }
        });
        btnSaveToFile.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        btnSaveToFile.setBounds(200, 460, 115, 30);
        frame.getContentPane().add(btnSaveToFile);

        JLabel lblTableNumber = new JLabel("Numero Tavolo:");
        lblTableNumber.setBounds(10, 230, 100, 14);
        panel.add(lblTableNumber);

        textFieldTableNumber = new JTextField();
        textFieldTableNumber.setBounds(95, 227, 65, 20);
        panel.add(textFieldTableNumber);
        textFieldTableNumber.setColumns(10);
        
        JRadioButton rdbConsegna = new JRadioButton("domicilio");
        rdbConsegna.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Date d = new Date();
        		long millis = d.getTime(); //numero di millisecondi dal 1 gennaio 1970
        		millis += 60*60*1000; //aggiungo un'ora
        		d.setTime(millis); //l'oggetto rappresenta l'orario tra un'ora
        		conto +=15;
        		JOptionPane.showMessageDialog(null,"la pizza sarà pronta per le :" +d);
        	}
        	
        });
        buttonGroup.add(rdbConsegna);
        rdbConsegna.setBounds(6, 393, 331, 23);
        panel.add(rdbConsegna);
        
        JRadioButton rdbtnasporto = new JRadioButton("portare via");
        rdbtnasporto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null,"hai selezionato da asporto" );
        	}
        });
        buttonGroup.add(rdbtnasporto);
        rdbtnasporto.setBounds(6, 367, 331, 23);
        panel.add(rdbtnasporto);
        
        JRadioButton rdbtntavolo = new JRadioButton("consumazione diretta");
        rdbtntavolo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		conto +=1;
        	}
        });
        buttonGroup.add(rdbtntavolo);
        rdbtntavolo.setBounds(6, 341, 331, 23);
        panel.add(rdbtntavolo);
        
        JButton btnAzzera = new JButton(" elimina conto ");
        btnAzzera.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	conto = 0;
        		JOptionPane.showMessageDialog(null,"conto azzerato" );
                textField_1.setText("" + conto + "€");
        	}
        });
        btnAzzera.setBounds(215, 423, 106, 23);
        panel.add(btnAzzera);

        JButton btnExit = new JButton("Esci");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnExit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        btnExit.setBounds(360, 460, 115, 30);
        frame.getContentPane().add(btnExit);
    }

    private void saveToFile(String tableNumber) {
        try (PrintWriter writer = new PrintWriter(new File("ristorante.txt"))) {
            writer.println("Numero di tavolo: " + tableNumber);
            writer.println("Data e ora: " + LocalDateTime.now());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
