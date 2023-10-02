package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.tds.TDS;

import java.math.BigDecimal;

public class Affectation extends Instruction{

    /**
     * identifiant
     */
    private Idf idf;

    /**
     * expression à affecter à l'identifiant
     */
    private Expression exp;
    /**
     * Constructeur d'Affectation
     * @param i
     *      identifiant
     * @param e
     *      expression
     * @param n
     *      numero de la ligne
     */
    public Affectation (Idf i, Expression e, int n) {
        super(n);
        this.idf = i;
        this.exp = e;
    }

    /**
     * methode verifier
     */
    @Override
    public void verifier() {
        this.idf.verifier();
        this.exp.verifier();
        if(idf.getType() !="" && exp.getType() != "" && idf.getSymbole().estUnSymboleDeVariable()){    // "" signefie que on ne sait pas son type (null)
            if(!idf.getType().equals(exp.getType())){
                //erreur semantique
                String nomFonction = TDS.getInstance().identifierNomFonction(this.numBloc);
                System.out.println("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : "+nomFonction+", Affectation incompatible '"+idf.getType()+" = "+exp.getType()+"'\n");
                AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur  : "+" Affectation incompatible  '"+idf.getType()+" = "+exp.getType()+"'\n");
            }
        }else if (idf.getSymbole().estUnSymboleDeFonction()){
            System.out.println("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur :  Varaible non declaré  \n");
            AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur :  Varaible non declaré  \n");
        }



    }

    /**
     * methode toMIPS
     * @return code MIPS
     */
    @Override
    public String toMIPS() {
        StringBuilder stringBuilder = new StringBuilder();
        //sauvegarder la valeur de l'exp dans $v0
        stringBuilder.append(this.exp.toMIPS());
        // ecrire la valeur de exp dans la zone memoire de la variable
        if (this.idf.getSymbole().getNumeroBloc() == 1 )
            stringBuilder.append("sw $v0, " + this.idf.getDeplacement() + " ($s6) \n");
        else
            stringBuilder.append("sw $v0, " + this.idf.getDeplacement() + " ($s7) \n");
        return stringBuilder.toString();
    }
}
