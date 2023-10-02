package zoot.tds;

public class SymboleFonction extends Symbole{

    /**
     * etiquette MIPS de la fonction
     */
    private String etiqueMips ;

    /**
     * numéro de bloc de la fonction
     */
    //private int numBloc;=

    private int blocRepresenter;    // elle reprensete que bloc de fonction le num de bloc ou de region.
    /**
     * constructeur d'un symbole de fonction
     * @param type
     *      type de retour de la fonction
     */
    public SymboleFonction(String type) {
        super(type);
        this.etiqueMips = "fonction"+(TDS.getInstance().getCptBloc());
        //this.numBloc = TDS.getInstance().getCptBloc();
        this.blocRepresenter = TDS.getInstance().getCptBloc();
    }

    /**
     * getteur etiquette MIPS
     * @return etiqueMips
     */
    @Override
    public String getEtiqueMips() {
        return this.etiqueMips;
    }

    /**
     * méthode indiquant si c'est un symbole de fonction
     * @return true
     */
    @Override
    public boolean estUnSymboleDeFonction() {
        return true;
    }

    @Override
    public int getBlocRepresenter() {
        return blocRepresenter;
    }
}
