package zoot.arbre.expressions;

import zoot.outils.Registres;
import zoot.tds.Entree;
import zoot.tds.EntreeVariable;
import zoot.tds.Symbole;
import zoot.tds.TDS;

public class Idf extends Expression{

    /**
     * nom de l'identifiant
     */
    private String nom;

    /**
     * symbole de l'identifiant
     */
    private Symbole symbole = null;

    /**
     * constructeur d'Idf
     * @param nom
     *      identifiant
     * @param n
     *      numero de la ligne
     */
    public Idf(String nom,int n) {
        super(n);
        this.nom = nom;
    }

    /**
     * methode verifier
     */
    @Override
    public void verifier() {
        this.symbole = TDS.getInstance().identifier(new EntreeVariable(this.nom),this.getNumBloc(),this.noLigne);
    }


    /**
     * getteur type
     * @return type du Symbole
     */
    @Override
    public String getType() {
        if (this.symbole  == null)
            return ""; // "" veut dire que on sait pas le type car c'est une variable non decalarer puisque symbole = null
        return this.symbole.getType();

    }

    /**
     * getteur deplacement
     * @return symbole.deplacement
     */
    public int getDeplacement() {
        return this.symbole.getDeplacement();
    }

    public Symbole getSymbole(){
        return this.symbole;
    }

    @Override
    public boolean estUneFeuille() {
        return true;
    }



    /*@Override
    public String toMIPS(Registres registres) {
        String reg = registres.getFirtRegistre();
        StringBuilder stringBuilder = new StringBuilder();
        // lire la valeur de la memoire est la mettre dans le registre $v0
        if (this.symbole.getNumeroBloc() == 1){ // si elle est globale
            stringBuilder.append("lw "+reg+", " + this.symbole.getDeplacement() + " ($s6) \n");
        }else {
            stringBuilder.append("lw "+reg+ ", " + this.symbole.getDeplacement() + " ($s7) \n");
        }
        return stringBuilder.toString();
    }*/


    /**
     * methode toMIPS
     * @return code MIPS
     */
    @Override
    public String toMIPS() {
        StringBuilder stringBuilder = new StringBuilder();
        // lire la valeur de la memoire est la mettre dans le registre $v0
        if (this.symbole.getNumeroBloc() == 1){ // si elle est globale
            stringBuilder.append("lw $v0, " + this.symbole.getDeplacement() + " ($s6) \n");
        }else {
            stringBuilder.append("lw $v0, " + this.symbole.getDeplacement() + " ($s7) \n");
        }
        return stringBuilder.toString();
    }





}
