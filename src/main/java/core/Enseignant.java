/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author FTAB TECH
 */
public class Enseignant extends Contact {
    private String statut;

    public Enseignant(String code, String nom, Date dateNaissance, String adresse, String email, String telNumber, String statut) {
        super(code, nom, dateNaissance, adresse, email, telNumber);
        this.statut = statut;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public void afficherContact() {
        super.afficherContact();
        
        String str = statut + ")";
        System.out.println(str);
    }

    @Override
    public void insererDansBD(Connection connection) {
        // Requête SQL pour insérer un enseignant dans la table Enseignant
        String sql = "INSERT INTO Enseignant (code, nom, dateNaissance, adresse, email, telNumber, statut) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
            preparedStatement.setString(7, getStatut());
            
            // Exécuter la requête d'insertion
            preparedStatement.executeUpdate();
            
            // Fermer le PreparedStatement
            preparedStatement.close();
            
            System.out.println("Enseignant inséré avec succès dans la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Object[] toArray(){
        Object[] array1 = super.toArray();
        Object[] array2 = new Object[]{statut};
        
        return concatArrays(array1, array2);
    }
}