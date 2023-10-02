package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;
import zoot.outils.Registres;

public abstract class Expression extends ArbreAbstrait {

    /**
     * constructeur d'une Expression
     * @param n
     *      numero de ligne
     */

    protected Expression(int n) {
        super(n) ;
    }

    /**
     * getteur type de l'expression
     * @return String chaine vide
     */
    public String getType(){
        return  "";
    }


    public boolean estUneFeuille(){
        return false;
    }



}
