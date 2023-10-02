package zoot.tds;

import java.util.ArrayList;

public class EntreeFonction extends Entree{


    /**
     * constructeur d'Entree
     *
     * @param idf identifiant
     */
    private int nbrParam;


    public EntreeFonction(String idf) {
        super(idf);
    }



    public EntreeFonction(String idf,int nbrParam) {
        super(idf);
        this.nbrParam = nbrParam;
    }

    /**
     * méthode indiquant si c'est une entree de fonction
     * @return true
     */
    @Override
    public boolean estEntreeFonction() {
        return true;
    }

    @Override
    public String toString() {
        return "idf = " + idf +  "est une Fonction  avec "+this.nbrParam + " de varProfil";
    }

    @Override
    public int getNbrParam() {
        return nbrParam;
    }
}
