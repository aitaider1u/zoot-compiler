package zoot.tds;

public class EntreeVariable extends  Entree{

    /**
     * constructeur d'Entree
     *
     * @param idf identifiant
     */
    public EntreeVariable(String idf) {
        super(idf);
    }

    /**
     * méthode indiquant si c'est une entree de variable
     * @return true
     */
    @Override
    public boolean estEntreeVariable() {
        return true;
    }

    @Override
    public String toString() {
        return "idf = " + idf + "est une Variable ";
    }
}
