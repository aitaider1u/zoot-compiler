package zoot.tds;

/**
 * Identificateur permettant de désigner plusieurs choses
 */
public class Entree {

    /**
     * identifiant de l'entrée
     */
    protected String idf;

    /**
     * espace des noms
     * vue dans le td pour differencier pas la suite les idf qui veux desinier plusieure chose avec le meme idf
     */
    private String espaceDesNom;

    //pour le moment on se contante de idf.

    /**
     * constructeur d'Entree
     * @param idf
     *      identifiant
     */
    public Entree(String idf) {
        this.idf = idf;
    }

    /**
     * getteur idf
     * @return idf
     */
    public String getIdf() {
        return idf;
    }

    /**
     * method toString
     * @return String
     */
    @Override
    public String toString() {
        return "idf = " + idf ;
    }

    /**
     * méthode indiquant si c'est une entree de fonction
     * @return false
     */
    public boolean estEntreeFonction(){
        return false;
    }

    /**
     * méthode indiquant si c'est une entrée de variable
     * @return false
     */
    public boolean estEntreeVariable(){
        return false;
    }

    public int getNbrParam() {
        return  0;
    }

}
