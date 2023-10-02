package zoot.arbre;

import zoot.outils.Registres;
import zoot.tds.TDS;

public abstract class ArbreAbstrait {

    /**
     * numero de la ligne du debut de l'instruction
     */
    protected int noLigne ;
    protected int numBloc ;
    /**
     * constructeur d'un ArbreAbstrait
     * @param n
     *      numero de la ligne
     */
    protected ArbreAbstrait(int n) {
        noLigne = n ;
        this.numBloc = TDS.getInstance().getBlocActuel();
    }

    /**
     * getteur numero de la ligne
     * @return noLigne
     */
    public int getNoLigne() {
            return noLigne ;
    }

    /**
     * methode verifier
     */
    public abstract void verifier() ;

    /**
     * methode toMIPS
     * @return code MIPS
     */
    public abstract String toMIPS();



    /**
     * getteur numero de la numero du bloc ou il se situe
     * @return noLigne
     */
    public int getNumBloc() {
        return numBloc;
    }

    public boolean hasRetourne(){
        return false;
    }
}
