/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author FTAB TECH
 */
public class Etudiant extends Contact {
    private String cycle;
    private String niveau;

    public Etudiant(String code, String nom, Date dateNaissance, String adresse, String email, String telNumber, String cycle, String niveau) {
        super(code, nom, dateNaissance, adresse, email, telNumber);
        this.cycle = cycle;
        this.niveau = niveau;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
    
    public void afficherContact() {
        super.afficherContact();
        
        String str = cycle + ", " + niveau + ")";
        System.out.println(str);
    }

    @Override
    public void insererDansBD(Connection connection) {
        // Requête SQL pour insérer un étudiant dans la table Etudiant
        String sql = "INSERT INTO Etudiant (code, nom, dateNaissance, adresse, email, telNumber, cycle, niveau) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Créer un objet PreparedStatement avec la requête SQL
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            // Définir les valeurs des paramètres de la requête
            preparedStatement.setString(1, getCode());
            preparedStatement.setString(2, getNom());
            preparedStatement.setDate(3, new java.sql.Date(getDateNaissance().getTime()));
            preparedStatement.setString(4, getAdresse());
            preparedStatement.setString(5, getEmail());
            preparedStatement.setString(6, getTelNumber());
            preparedStatement.setString(7, getCycle());
            preparedStatement.setString(8, getNiveau());
            
            // Exécuter la requête d'insertion
            preparedStatement.executeUpdate();
            
            // Fermer le PreparedStatement
            preparedStatement.close();
            
            System.out.println("Étudiant inséré avec succès dans la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Object[] toArray(){
        Object[] array1 = super.toArray();
        Object[] array2 = new Object[]{cycle, niveau};
        
        return concatArrays(array1, array2);
    }
}