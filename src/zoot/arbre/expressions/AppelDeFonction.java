package zoot.arbre.expressions;

import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.outils.Registres;
import zoot.tds.Entree;
import zoot.tds.EntreeFonction;
import zoot.tds.Symbole;
import zoot.tds.TDS;

import java.util.ArrayList;

public class AppelDeFonction extends Expression{


    /**
     * nom de l'identifiant
     */
    private String nom;

    /**
     * symbole de l'identifiant
     */
    private Symbole symbole = null;
    private ArrayList<Expression> parEff = new ArrayList<>();

    /**
     * constructeur d'AppelDeFonction
     * @param nom
     *      identifiant de la fonction
     * @param n
     *      numero de la ligne
     */
    public AppelDeFonction(String nom, int n) {
        super(n);
        this.nom = nom;
    }
    public AppelDeFonction(String nom, ArrayList<Expression> parEff,int n) {
        super(n);
        this.nom = nom;
        this.parEff = parEff;
    }
    /**
     * méthode vérifier
     */
    @Override
    public void verifier() {


        this.symbole = TDS.getInstance().getBlocIndex(1).identifier(new EntreeFonction(this.nom,this.parEff.size()),this.noLigne);
        //verifier les expression
        for(Expression e : this.parEff){
            e.verifier();
        }
        if ( symbole != null ) {  // si le symbole n'est pas null  (la fonction elle existe)
        String[] typesPrams =  TDS.getInstance().getParams( this.symbole.getBlocRepresenter());

                int n = this.parEff.size();
                for(int i = 0 ; i < n; i++ ){
                     if(!this.parEff.get(n-1-i).getType().equals(typesPrams[i])){
                         String nomFonction = TDS.getInstance().identifierNomFonction(this.symbole.getBlocRepresenter());
                         System.out.println("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : "+nomFonction+", Type de Parametre N°"+(i+1)+ " incompatible avec la fonction '"+this.parEff.get(n-1-i).getType()+" = "+typesPrams[i]+"'\n");
                         AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur  : "+"Type de Parametre N°"+(i+1)+ " incompatible avec la fonction '"+this.parEff.get(n-1-i).getType()+" = "+typesPrams[i]+"'\n");
                    }
                }
        }
    }

    /**
     * méthode toMIPS
     * @return code MIPS en String
     */
    @Override
    public String toMIPS() {
        //System.out.println("nbr par_effect -------->  :  "+ this.parEff.size());
        StringBuilder stringBuilder = new StringBuilder();
        int n = this.parEff.size();
        //stringBuilder.append("addi $sp, $sp, "+(-4*n)+"\n");
        for (int i = 0 ; i< n ; i++){
            stringBuilder.append("addi $sp, $sp,"+(-12-4*(i))+" \n");
            stringBuilder.append( this.parEff.get(n-i-1).toMIPS());
            //stringBuilder.append("sw $v0,"+(-12-(i*4))+"($sp)\n");    //le -12 pour la region/@return
            stringBuilder.append("addi $sp, $sp,"+(12+4*(i))+" \n");
            stringBuilder.append("sw $v0,"+(-12-4*(i))+"($sp)\n");    //le -12 pour la region/@return/
            //System.out.println( this.parEff.get(n-i-1).toMIPS());
        }
        stringBuilder.append("jal "+this.symbole.getEtiqueMips()+"\n");
        return stringBuilder.toString();
    }

    /**
     * getteur tu type du symbole
     * @return symbole.type
     */
    @Override
    public String getType() {
        if (this.symbole  == null)
            return ""; // "" veut dire que on sait pas le type car ca la fonction est pas declarer
        return this.symbole.getType();
    }


    /*@Override
    public String toMIPS(Registres registres) {
        return null;
    }*/

    /*
    @Override
    public boolean estUneFeuille() {
        return true;
    }*/
}
