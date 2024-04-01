/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FTAB TECH
 */
public class Repertoire {
    private List<Contact> contacts = new ArrayList<>();
    
    public List<Contact> getContacts() {
        return contacts;
    }
    
    public List<Contact> getContacts(String type) {
        List<Contact> filteredContacts = new ArrayList<>();

        switch (type.toLowerCase()) {
            case "etudiant" -> {
                for (Contact contact : contacts) {
                    if (contact instanceof Etudiant) {
                        filteredContacts.add(contact);
                    }
                }
            }
            case "agent" -> {
                for (Contact contact : contacts) {
                    if (contact instanceof Agent) {
                        filteredContacts.add(contact);
                    }
                }
            }
            case "enseignant" -> {
                for (Contact contact : contacts) {
                    if (contact instanceof Enseignant) {
                        filteredContacts.add(contact);
                    }
                }
            }
            default -> {}
        }
        
        return filteredContacts;
    }

    public void ajouterContact(Contact contact) {
        contacts.add(contact);
    }

    public void supprimerContact(Contact contact) {
        contacts.remove(contact);
    }

    public void modifierContact(Contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact existingContact = contacts.get(i);
        
            if (existingContact.getCode().equals(contact.getCode())) {
                // Mettre à jour les informations du contact existant avec celles du contact passé en paramètre
                existingContact.setNom(contact.getNom());
                existingContact.setDateNaissance(contact.getDateNaissance());
                existingContact.setAdresse(contact.getAdresse());
                existingContact.setEmail(contact.getEmail());
                existingContact.setTelNumber(contact.getTelNumber());
                
                break; // Arrête la boucle une fois que le contact est modifié
            }
        }
    }

    public List<Contact> rechercherContact(String nom) {
        List<Contact> contactsTrouves = new ArrayList<>();
        
        for (Contact contact : contacts) {
            if (contact.getNom().equals(nom)) {
                contactsTrouves.add(contact);
            }
        }
        
        return contactsTrouves;
    }
    
    public void afficherContacts() {
        System.out.println("Liste des contacts :");
 
        for (int i = 0, size = contacts.size(); i < size; i++) {
            Contact contact = contacts.get(i);
            System.out.print((i + 1) + ". ");
        
            contact.afficherContact();
        }
    }
}




