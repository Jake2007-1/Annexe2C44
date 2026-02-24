package com.example.annexe2;

public class Compte {
    private String nomCompte;
    private double solde;

    public Compte(String nomCompte, double solde) {
        this.nomCompte = nomCompte;
        this.solde = solde;
    }

    public String getNomCompte() {
        return nomCompte;
    }

    public void setNomCompte(String nomCompte) {
        this.nomCompte = nomCompte;
    }

    public double getSolde() {
        return solde;
    }

    public boolean transfert(double montant){
        if (solde >= montant){
            solde -= montant;
            return true;
        }
        return false;
    }
}
