package zoot.tds;

/**
 * classe Symbole représentant le type de la variable
 */
public class Symbole {

    /**
     * type de la variable
     */
    protected String type;

    /**
     * numero du bloc
     */
    protected int numeroBloc;

    /**
     * constructeur de Symbole
     * @param type
     *      type du symbole
     * @param deplacement
     *      taille de la variable
    */
    public Symbole(String type, int deplacement) {
        this.type = type;
        //this.deplacement = deplacement;
    }

    /**
     * constructeur de Symbole
     * @param type
     *      type du symbole
     */
    public Symbole(String type) {
        this.type = type;
        //System.out.println(".................... "+TDS.getInstance().getBlocActuel());
        this.numeroBloc = TDS.getInstance().getBlocActuel();
    }

    /**
     * getteur type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * getteur deplacement
     * @return deplacement
     */
    public int getDeplacement() {
        return 1;
    }

    /**
     * method toString
     * @return string
     */
    @Override
    public String toString() {
        return ", type = " + type  +" num = "+this.getNumeroBloc()+"\n";
    }

    /**
     * méthode indiquant si c'est un symbole de variable
     * @return false
     */
    public boolean estUnSymboleDeVariable(){
        return false;
    }

    /**
     * méthode indiquant si c'est un symbole de fonction
     * @return false
     */
    public boolean estUnSymboleDeFonction(){
        return false;
    }

    /**
     * getteur etiquette MIPS
     * @return null
     */
    public String getEtiqueMips() {
        return null;
    }

    /**
     * getteur numero de bloc
     * @return 1
     */
    public int getNumeroBloc() {
        return this.numeroBloc;
    }

    //utile pour SymboleFonction
    //elle disigne c'est quoi la region que repesente ce symbole de fonction
    public int getBlocRepresenter() {
        return 1;
    }



}
