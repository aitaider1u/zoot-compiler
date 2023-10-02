package zoot.arbre.expressions;

import zoot.outils.Registres;

public class ConstanteEntiere extends Constante {

    /**
     * Constructeur ConstanteEntiere
     * @param texte
     *      valeur
     * @param n
     *      numero de ligne
     */
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    /**
     * methode toMIPS
     * @return code MIPS
     */
    @Override
    public String toMIPS() {
        return "li $v0 , "+this.cste+"    #mettre la valeur "+ this.cste+" de le registre $v0\n";
    }

    /**
     * getteur type
     * @return "entier"
     */
    @Override
    public String getType() {
        return "entier";
    }

    /*@Override
    public String toMIPS(Registres registres) {
        String reg = registres.getFirtRegistre();
        return "li "+reg+" , "+this.cste+"    #mettre la valeur "+ this.cste+" de le registre $v0\n";
    }*/
}
