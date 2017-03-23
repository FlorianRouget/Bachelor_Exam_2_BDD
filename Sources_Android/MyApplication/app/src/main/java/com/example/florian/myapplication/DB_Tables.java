package com.example.florian.myapplication;

/**
 * Created by florian on 23/03/2017.
 */

public class DB_Tables {

    public static class User{

        private int Id_User;
        private String Login;
        private String Password;

        public String getLogin(){return Login;}

        public void setLogin(String nv){ this.Login = nv;}

        public String getPassword(){return Password;}

        public void setPassword(String nv){ this.Password = nv;}

        public Integer getIdUser(){return Id_User;}

        public void setIdUser(int nv){ this.Id_User = nv;}

    }

    public static class Projet{

        private int Id_Projet;
        private String NomProjet;
        private String DescProjet;
        private String Type;
        private String Langage;
        private Double Budget;
        private int Id_Client;

        public int getIdProjet(){return Id_Projet;}

        public void setIdProjet(int nv){ this.Id_Projet = nv;}

        public String getNomProjet(){return NomProjet;}

        public void setNomProjet(String nv){ this.NomProjet = nv;}

        public String getDescProjet(){return DescProjet;}

        public void setDescProjet(String nv){ this.DescProjet = nv;}

        public String getType(){return Type;}

        public void setType(String nv){ this.Type = nv;}

        public String getLangage(){return Langage;}

        public void setLangage(String nv){ this.Langage = nv;}

        public Double getBudget(){return Budget;}

        public void setBudget(Double nv){ this.Budget = nv;}

        public int getIdClient(){return Id_Client;}

        public void setIdClient(int nv){ this.Id_Client = nv;}

    }

    public static class Client{

        private int Id_Client;
        private String Nom;
        private String Prenom;
        private String Societe;
        private String Tel;
        private String Mail;

        public String getNom(){return Nom;}

        public void setNom(String nv){ this.Nom = nv;}

        public String getPrenom(){return Prenom;}

        public void setPrenom(String nv){ this.Prenom = nv;}

        public String getSociete(){return Societe;}

        public void setSociete(String nv){ this.Societe = nv;}

        public String getTel(){return Tel;}

        public void setTel(String nv){ this.Tel = nv;}

        public String getMail(){return Mail;}

        public void setMail(String nv){ this.Mail = nv;}

        public int getIdClient(){return Id_Client;}

        public void setIdClient(int nv){ this.Id_Client = nv;}

    }

    public static class Participe{

        private int User;
        private int Projet;
        private String Fonction;

        public String getFonction(){return Fonction;}

        public void setFonction(String nv){ this.Fonction = nv;}

        public int getUser(){return User;}

        public void setUser(int nv){ this.User = nv;}

        public int getProjet(){return Projet;}

        public void setProjet(int nv){ this.Projet = nv;}

    }

}
