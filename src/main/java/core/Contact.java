/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author FTAB TECH
 */

public abstract class Contact {
    private String code;
    private String nom;
    private Date dateNaissance;
    private String adresse;
    private String email;
    private String telNumber;

    public Contact(String code, String nom, Date dateNaissance, String adresse, String email, String telNumber) {
        this.code = code;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.email = email;
        this.telNumber = telNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void afficherContact() {
        String str = getClass().getSimpleName() + "(";
        str += (code + ", " + nom + ", " + dateNaissance.toString());
        str += (", " + adresse + ", " + email + ", " + telNumber + ", ");
                
        System.out.print(str);
    }
    
    public Object[] toArray() {
        return new Object[]{code, nom, dateNaissance.toString(), adresse, email, telNumber};
    }
    
    public static Object[] concatArrays(Object[] array1, Object[] array2) {
        // Créer un nouveau tableau avec une taille égale à la somme des tailles des deux tableaux d'origine
        Object[] result = Arrays.copyOf(array1, array1.length + array2.length);

        // Copier les éléments du deuxième tableau dans le nouveau tableau à partir de la position de la fin du premier tableau
        System.arraycopy(array2, 0, result, array1.length, array2.length);

        return result;
    }
    
    public abstract void insererDansBD(Connection connection);
}