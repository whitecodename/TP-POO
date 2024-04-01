package ui;

import core.Contact;
import core.Repertoire;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainFrmApplication extends JFrame {
    private Repertoire repertoire;
    private Connection connection;
    
    public MainFrmApplication(Connection connection, Repertoire repertoire) {
        this.repertoire = repertoire;
        this.connection = connection;
        
        setTitle("Gestionnaire de Contacts");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton createContactButton = new JButton("Créer un contact");
        createContactButton.addActionListener((ActionEvent e) -> {
            ContactsFactory contactsFactory = new ContactsFactory(this.repertoire);
            contactsFactory.setVisible(true);
        });

        JButton showContactsButton = new JButton("Afficher les contacts");
        showContactsButton.addActionListener((ActionEvent e) -> {
            ContactsDialog contactsDialog = new ContactsDialog(this.repertoire);
            contactsDialog.setVisible(true);
        });

        JPanel panel = new JPanel();
        panel.add(createContactButton);
        panel.add(showContactsButton);
        add(panel);
        
        // Création du menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fichierMenu = new JMenu("Fichier");
        JMenuItem enregistrerMenuItem = new JMenuItem("Enregistrer");
        enregistrerMenuItem.addActionListener((ActionEvent e) -> {
            enregistrerDansBD(repertoire, connection);
        });
        fichierMenu.add(enregistrerMenuItem);
        menuBar.add(fichierMenu);
        setJMenuBar(menuBar);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(() -> {
            // Connexion à la base de données
            try {
                String url = "jdbc:mysql://localhost:3306/ContactsDB";
                String user = "root";
                String password = "";
                
                Connection connection = DriverManager.getConnection(url, user, password);
                Repertoire repertoire = new Repertoire();
                
                MainFrmApplication mainFrame = new MainFrmApplication(connection, repertoire);
                mainFrame.setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    
    /**
     * Enregistre les contacts du répertoire dans la base de données.
     * @param repertoire Le répertoire contenant les contacts.
     * @param connection La connexion à la base de données.
     */
    public void enregistrerDansBD(Repertoire repertoire, Connection connection) {
        System.out.println("test");
        
        if (repertoire == null) {
            return;
        }
        
        for (Contact contact : repertoire.getContacts()) {
            contact.insererDansBD(this.connection);
        }
        
        // Affichage un message de confirmation
        JOptionPane.showMessageDialog(null, "Enregistrement effectué avec succès !");
    }
}