package main;

import core.Agent;
import core.Contact;
import core.Enseignant;
import core.Etudiant;
import core.Repertoire;
import java.util.Date;
import java.util.List;

public class MainApplication {

    /**
     * Méthode principale qui démarre l'application.
     * Elle crée des contacts (étudiant, enseignant, agent), les ajoute au répertoire,
     */
    public static void main(String[] args) {
        // Création des contacts de test
        Etudiant etudiant = new Etudiant("ET001", "Jean Dupont", new Date(), "123 rue de Paris", "jean.dupont@example.com", "123456789", "Licence", "L3");
        Enseignant enseignant = new Enseignant("ENS001", "Marie Durand", new Date(), "456 avenue du Campus", "marie.durand@example.com", "987654321", "Permanent");
        Agent agent = new Agent("AG001", "Paul Martin", new Date(), "789 chemin de la Plage", "paul.martin@example.com", "456123789", 2500.0, "Permanent", "Secrétaire", 3.5, "Occupation");

        // Création du repertoire
        System.out.println("STARTING TESTS\n");
        
        Repertoire repertoire = new Repertoire();

        // Test Ajout
        System.out.println("TEST AJOUT...");
        
        repertoire.ajouterContact(etudiant);
        repertoire.ajouterContact(enseignant);
        repertoire.ajouterContact(agent);
        
        repertoire.afficherContacts();
        
        // Test Recherche
        System.out.println("\nTEST RECHERCHE...");
        
        List<Contact> contacts = repertoire.rechercherContact("Paul Martin");
        afficherContacts(contacts);
        
        // Test Modification
        System.out.println("\nTEST MODIFICATION...");
        
        etudiant.setNom("Jean Duclaire");
        repertoire.modifierContact(etudiant);
        
        repertoire.afficherContacts();
        
        // Test Suppression
        System.out.println("\nTEST SUPPRESSION...");
        
        repertoire.supprimerContact(agent);
        repertoire.supprimerContact(etudiant);
        repertoire.supprimerContact(enseignant);
        
        repertoire.afficherContacts();
        
        System.out.println("\nENDING TESTS\n");
    }
    
    public static void afficherContacts(List<Contact> contacts) {
        for(Contact contact : contacts) {
            contact.afficherContact();
        }
    }
}