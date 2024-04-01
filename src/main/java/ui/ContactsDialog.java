package ui;

import core.Agent;
import core.Contact;
import core.Enseignant;
import core.Etudiant;
import core.Repertoire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

class ContactsDialog extends JFrame {
    private JComboBox<String> typeComboBox;

    public ContactsDialog(Repertoire repertoire) {
        setTitle("Liste des Contacts");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Création de la JComboBox pour choisir le type de contact à afficher
        typeComboBox = new JComboBox<>(new String[]{"Etudiant", "Agent", "Enseignant"});
        typeComboBox.addActionListener((ActionEvent e) -> {
            String selectedType = (String) typeComboBox.getSelectedItem();
            updateContacts(repertoire, selectedType);
        });
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.add(new JLabel("Type de contact:"));
        comboBoxPanel.add(typeComboBox);
        add(comboBoxPanel, BorderLayout.NORTH);

        // Initialisation avec le premier type de contact
        updateContacts(repertoire, "Etudiant");

        setLocationRelativeTo(null);
    }

    // Méthode pour mettre à jour les contacts affichés en fonction du type sélectionné
    private void updateContacts(Repertoire repertoire, String type) {
        // Supprimer le tableau précédent s'il existe
        getContentPane().removeAll();

        // Logique pour afficher la liste des contacts dans un tableau (JTable)
        Object[][] data = null;

        switch (type) {
            case "Etudiant" -> {
                List<Contact> etudiants = repertoire.getContacts("etudiant");
                data = new Object[etudiants.size()][];
                
                for (int i = 0; i < etudiants.size(); i++) {
                    Etudiant etudiant = (Etudiant)etudiants.get(i);
                    data[i] = etudiant.toArray();
                }
            }
            case "Agent" -> {
                List<Contact> agents = repertoire.getContacts("agent");
                data = new Object[agents.size()][];
                
                for (int i = 0; i < agents.size(); i++) {
                    Agent agent = (Agent)agents.get(i);
                    data[i] = agent.toArray();
                }
            }
            case "Enseignant" -> {
                List<Contact> enseignants = repertoire.getContacts("enseignant");
                data = new Object[enseignants.size()][];
                
                for (int i = 0; i < enseignants.size(); i++) {
                    Enseignant enseignant = (Enseignant)enseignants.get(i);
                    data[i] = enseignant.toArray();
                }
            }
            
            default -> {}
        }

        // Créer le tableau avec les données récupérées
        if (data != null) {
            // Créer des noms de colonnes dynamiques en fonction du type de contact
            String[] columnNames = getColumnNames(type);

            JTable table = new JTable(data, columnNames);
            
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);
        }

        // Actualiser l'affichage
        revalidate();
        repaint();
    }

    // Méthode pour obtenir les noms de colonnes en fonction du type de contact
    private String[] getColumnNames(String type) {
        switch (type) {
            case "Etudiant":
                return new String[]{"Code", "Nom", "Date de naissance", "Adresse", "Email", "Téléphone", "Cycle", "Niveau"};
            case "Agent":
                return new String[]{"Code", "Nom", "Date de naissance", "Adresse", "Email", "Téléphone", "Salaire", "Statut", "Catégorie", "Indice de salaire", "Occupation"};
            case "Enseignant":
                return new String[]{"Code", "Nom", "Date de naissance", "Adresse", "Email", "Téléphone", "Statut"};
            default:
                return null;
        }
    }
}