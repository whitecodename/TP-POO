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
public class Agent extends Contact {
    private double salaire;
    private double indiceSalaire;
    private String statut;
    private String categorie;
    private String occupation;

    public Agent(String code, String nom, Date dateNaissance, String adresse, String email, String telNumber, double salaire, String statut, String categorie, double indiceSalaire, String occupation) {
        super(code, nom, dateNaissance, adresse, email, telNumber);
        this.salaire = salaire;
        this.statut = statut;
        this.categorie = categorie;
        this.indiceSalaire = indiceSalaire;
        this.occupation = occupation;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    public double getIndiceSalaire() {
        return indiceSalaire;
    }
    
    public void setIndiceSalaire(double indiceSalaire) {
        this.indiceSalaire = indiceSalaire;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    
    public void afficherContact() {
        super.afficherContact();
       
        String str = salaire + ", " + indiceSalaire + ", " + statut + ", " + categorie + ", " + occupation + ")";
        System.out.println(str);
    }

    @Override
    public void insererDansBD(Connection connection) {
        // Requête SQL pour insérer un agent dans la table Agent
        String sql = "INSERT INTO Agent (code, nom, dateNaissance, adresse, email, telNumber, salaire, statut, categorie, indiceSalaire, occupation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            preparedStatement.setDouble(7, getSalaire());
            preparedStatement.setString(8, getStatut());
            preparedStatement.setString(9, getCategorie());
            preparedStatement.setDouble(10, getIndiceSalaire());
            preparedStatement.setString(11, getOccupation());
            
            // Exécuter la requête d'insertion
            preparedStatement.executeUpdate();
            
            // Fermer le PreparedStatement
            preparedStatement.close();
            
            System.out.println("Agent inséré avec succès dans la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Object[] toArray(){
        Object[] array1 = super.toArray();
        Object[] array2 = new Object[]{salaire, statut, categorie, indiceSalaire, occupation};
        
        return concatArrays(array1, array2);
    }
}