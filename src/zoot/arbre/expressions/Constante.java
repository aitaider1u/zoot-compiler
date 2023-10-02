package zoot.arbre.expressions;

public abstract class Constante extends Expression {

    /**
     * valeur de la constante
     */
    protected String cste ;

    /**
     * Constructeur d'une constante
     * @param texte
     *      valeur de la constante
     * @param n
     *      numero de ligne
     */
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }

    /**
     * methode verifier
     */
    @Override
    public void verifier() {
        //throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

    /**
     * method toString
     * @return cste
     */
    @Override
    public String toString() {
        return cste ;
    }


    @Override
    public boolean estUneFeuille() {
        return true;
    }



}
