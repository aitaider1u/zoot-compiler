package zoot.tds;

public class SymboleVaraible extends Symbole{

    /**
     * deplacement variable
     */
    private int deplacement;

    /**
     * constructeur d'un symbole de variable
     * @param type
     *      type de la variable
     */
    public SymboleVaraible(String type) {
        super(type);
        this.deplacement = TDS.getInstance().incrementerCpt();
    }

    /**
     * méthode indiquant si c'est un symbole de variable
     * @return true
     */
    @Override
    public boolean estUnSymboleDeVariable() {
        return true;
    }

    /**
     * getteur de déplacement
     * @return deplacement
     */
    @Override
    public int getDeplacement() {
        return this.deplacement;
    }

    @Override
    public String toString() {
        return ", type = " + type  +" num = "+this.getNumeroBloc() + " --> deplacement : " + this.deplacement + "  \n";
    }

}
