package zoot.arbre.instructions;

import com.sun.nio.file.ExtendedOpenOption;
import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.outils.CompteurTagMips;
import zoot.tds.TDS;

public class Repeter extends Instruction{
    private Expression exp ;
    private BlocDInstructions blocInstuctions;


    public Repeter(int n, Expression exp, BlocDInstructions blocInstuctions) {
        super(n);
        this.exp = exp;
        this.blocInstuctions = blocInstuctions;
    }

    @Override
    public void verifier() {
        blocInstuctions.verifier();
        exp.verifier();

        if(!exp.getType().equals("")){
            if(!exp.getType().equals("booleen")){
                String nomFonction = TDS.getInstance().identifierNomFonction(this.numBloc);
                System.out.println("ERREUR SEMANTIQUE : " + exp.getNoLigne() + " ligne d'erreur : "+nomFonction+", Type incompatible (n'est pas de type booleen) dans la condition a verifier dans repeter\n");
                AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + exp.getNoLigne() + " ligne d'erreur : "+nomFonction+", Type incompatible (n'est pas de type booleen) dans la condition a verifier dans repeter\n");
            }
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();

        int i = CompteurTagMips.getInstance().incrementerNbrRepeter();
        str.append("reperter"+ i+": \n");
        str.append(blocInstuctions.toMIPS());
        str.append(exp.toMIPS());
        str.append("beq $v0 , $zero, finReperter"+i+"\n");
        str.append("j reperter"+i+"\n");
        str.append("finReperter"+i+":\n");
        return str.toString();

    }


    @Override
    public boolean hasRetourne() {
        return blocInstuctions.hasRetourne();
    }
}
