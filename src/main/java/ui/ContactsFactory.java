package ui;

import core.Agent;
import core.Enseignant;
import core.Etudiant;
import core.Repertoire;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContactsFactory extends JFrame {
    private final JPanel fieldsPanel;
    private final JComboBox<String> contactTypeComboBox;
    private JLabel[] labels, specificLabels;
    private Object[] fields, specificFields;
    private int nRows;
    private final GridLayout layout;

    public ContactsFactory(Repertoire repertoire) {
        setTitle("Contacts Factory");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Création du GridLayout avec 1 ligne et 2 colonnes
        layout = new GridLayout(7, 2);

        // Création du JPanel avec le GridLayout
        fieldsPanel = new JPanel(layout);

        // Création de la liste déroulante pour choisir le type de contact
        fieldsPanel.add(new JLabel("Type de contact:"));
        contactTypeComboBox = new JComboBox<>(new String[]{"Etudiant", "Agent", "Enseignant"});
        fieldsPanel.add(contactTypeComboBox);
        
        labels = convertToJLabel(new String[]{"Code", "Nom", "Date de naissance", "Adresse", "Email", "telNumber"});
        
        int numFields = 6;
        fields = new Object[numFields];

        // Création et initialisation des JTextField dans le tableau
        for (int i = 0; i < numFields; i++) {
            fields[i] = new JTextField(20);
            ((JTextField)fields[i]).setPreferredSize(new Dimension(200, 35));
        }
        
        addFields(labels, fields);

        // Écouteur pour le changement de sélection dans la liste déroulante
        contactTypeComboBox.addActionListener((ActionEvent e) -> {
            String selectedItem = (String) contactTypeComboBox.getSelectedItem();
            selectedItem = selectedItem.toLowerCase();
            
            setNewLayout(selectedItem);
            
            removeFields(specificLabels, specificFields);
            
            specificLabels = getSpecificLabels(selectedItem);
            specificFields = getSpecificFields(selectedItem);
            
            // Ajout des champs de saisie spécifiques pour le nouveau type de contact sélectionné
            addFields(specificLabels, specificFields);
            
            // Rafraîchissement l'affichage
            revalidate();
            repaint();
        });

        // Création du bouton Enregistrer
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton createButton = new JButton("Create");
        createButton.addActionListener((ActionEvent e) -> {
            // Récupération du type de contact sélectionné
            String selectedContactType = (String) contactTypeComboBox.getSelectedItem();

            // Récupération des valeurs génériques des champs de saisie
            String code = ((JTextField) fields[0]).getText();
            String nom = ((JTextField) fields[1]).getText();
            String dateNaissance = ((JTextField) fields[2]).getText();
            String adresse = ((JTextField) fields[3]).getText();
            String email = ((JTextField) fields[4]).getText();
            String telNumber = ((JTextField) fields[5]).getText();
            
            Date date = getDate(dateNaissance);
            
            repertoire.ajouterContact(switch (selectedContactType.toLowerCase()) {
                case "etudiant" -> {
                    String cycle = ((JTextField) specificFields[0]).getText();
                    String niveau = ((JTextField) specificFields[1]).getText();
                    yield new Etudiant(code, nom, date, adresse, email, telNumber, cycle, niveau);
                }
                case "enseignant" -> {
                    String statut = ((JTextField) specificFields[0]).getText();
                    yield new Enseignant(code, nom, date, adresse, email, telNumber, statut);
                }
                case "agent" -> {
                    String salaire = ((JTextField) specificFields[0]).getText();
                    String statut = ((JTextField) specificFields[1]).getText();
                    String categorie = ((JTextField) specificFields[2]).getText();
                    String indiceSalaire = ((JTextField) specificFields[3]).getText();
                    String occupation = ((JTextField) specificFields[4]).getText();
                    yield new Agent(code, nom, date, adresse, email, telNumber, Double.parseDouble(salaire), statut, categorie, Double.parseDouble(indiceSalaire), occupation);
                }
                default -> null;
            });
            
            // Affichage un message de confirmation
            JOptionPane.showMessageDialog(null, "Contact créé avec succès !");
        });
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener((ActionEvent e) -> {
            clear();
        });

        buttonPanel.add(createButton);
        buttonPanel.add(clearButton);

        // Ajout du panel des champs de saisie à la fenêtre
        add(fieldsPanel, BorderLayout.CENTER);

        // Ajout du panel du bouton Enregistrer au bas de la fenêtre
        add(buttonPanel, BorderLayout.SOUTH);

        // Ajustement automatique de la taille de la fenêtre
        pack();

        // Centrage de la fenêtre sur l'écran
        setLocationRelativeTo(null);
        
        // Sélection par défaut
        contactTypeComboBox.setSelectedItem("Etudiant");
    }
    
    private void clear() {
        if (fields == null || specificFields == null) {
            return;
        }
        
        // Réinitialisation des champs de saisie génériques
        for (Object field : fields) {
            ((JTextField) field).setText("");
        }

        // Réinitialisation des champs de saisie spécifiques
        for (Object specificField : specificFields) {
            ((JTextField) specificField).setText("");
        }
    }
    
    private void setNewLayout(String contactType) {
        nRows = switch(contactType) {
            case "etudiant" -> 9;
            case "enseignant" -> 8;
            case "agent" -> 12;
            default -> 0;
        };
        
        layout.setRows(nRows);
        fieldsPanel.setLayout(layout);
    }
    
    private Date getDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = dateFormat.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        JOptionPane.showMessageDialog(null, "Erreur sur la date. Utilisez ce format : DD/MM/YYYY");
        
        return null;
    }

    // Méthode pour ajouter les champs de saisie spécifiques au type de contact sélectionné
    private JLabel[] getSpecificLabels(String contactType) {
        return convertToJLabel(switch (contactType) {
            case "etudiant" -> new String[]{"Cycle", "Niveau"};
            case "enseignant" -> new String[]{"Statut"};
            case "agent" -> new String[]{"Salaire", "Statut", "Categorie", "IndiceSalaire", "Occupation"};
            default -> null;
        });
    }
    
    private JLabel[] convertToJLabel(String[] strings) {
        if (strings == null) {
            return null;
        }
        
        int n = strings.length;
        JLabel[] jLabels = new JLabel[n];

        for (int i = 0; i < n; i++) {
            jLabels[i] = new JLabel(strings[i]);
        }

        return jLabels;
}

    
    private Object[] getSpecificFields(String contactType) {
        return switch (contactType) {
            case "etudiant" -> new Object[]{new JTextField(20), new JTextField(20)};
            case "enseignant" -> new Object[]{new JTextField(20)};
            case "agent" -> new Object[]{new JTextField(20), new JTextField(20), new JTextField(20), new JTextField(20), new JTextField(20)};
            default -> null;
        };
    }
    
    private void addFields(JLabel[] labels, Object[] fields) {
        if (labels == null || fields == null) {
            return;
        }
        
        for (int i = 0; i < labels.length; i++) {
            fieldsPanel.add(labels[i]);
            fieldsPanel.add((JTextField)fields[i]);
        }
    }
    
    private void removeFields(JLabel[] labels, Object[] fields) {
        if (labels == null || fields == null) {
            return;
        }
        
        for (int i = 0; i < labels.length; i++) {
            fieldsPanel.remove(labels[i]);
            fieldsPanel.remove((JTextField) fields[i]);
        }
    }

}