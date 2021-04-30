package com.example.agence_location;

public class VoitureVedue {
    private String Vid;
    private String Matricule;
    private String Style;
    private String Marque;
    private String Corburant;
    private String Model;
    private String  Couleurs;
    private String prix;

    public VoitureVedue() {

    }

    public VoitureVedue(String vid, String matricule, String style, String marque, String corburant, String model, String couleurs, String prix) {
        Vid = vid;
        Matricule = matricule;
        Style = style;
        Marque = marque;
        Corburant = corburant;
        Model = model;
        Couleurs = couleurs;
        this.prix = prix;
    }

    public String getVid() {
        return Vid;
    }

    public void setVid(String vid) {
        Vid = vid;
    }

    public String getMatricule() {
        return Matricule;
    }

    public void setMatricule(String matricule) {
        Matricule = matricule;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String marque) {
        Marque = marque;
    }

    public String getCorburant() {
        return Corburant;
    }

    public void setCorburant(String corburant) {
        Corburant = corburant;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getCouleurs() {
        return Couleurs;
    }

    public void setCouleurs(String couleurs) {
        Couleurs = couleurs;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
