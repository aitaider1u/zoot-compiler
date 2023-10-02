package zoot.arbre.expressions;

import zoot.outils.Registres;

/**
 * Constante booleenne
 */
public class ConstanteBooleenne extends  Constante{

    /**
     * constructeur ConstanteBooleenne
     * @param texte
     *      valeur
     * @param n
     *      numero de ligne
     */
    public ConstanteBooleenne(String texte, int n) {
        super(texte, n) ;
    }

    /**
     * methode toMIPS
     * @return code MIPS
     */
    @Override
    public String toMIPS() {
        return "li $v0 , "+ ((this.cste.equals("vrai")) ? 1 : 0) +"    #mettre la valeur "+ this.cste+"de le registre $v0\n";
    }

    /**
     * getteur type
     * @return "booleen"
     */
    @Override
    public String getType() {
        return "booleen";
    }

    /*@Override
    public String toMIPS(Registres registres) {
        String reg = registres.getFirtRegistre();
        return "li "+reg+" , "+ ((this.cste.equals("vrai")) ? 1 : 0) +"    #mettre la valeur "+ this.cste+"de le registre $v0\n";
    }*/
}
