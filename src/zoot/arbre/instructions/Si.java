package zoot.arbre.instructions;

import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.outils.CompteurTagMips;
import zoot.tds.TDS;

public class Si extends Instruction{
    private Expression exp;
    private BlocDInstructions blocInstructions;

    public Si(int n, Expression exp, BlocDInstructions blocInstructions) {
        super(n);
        this.exp = exp;
        this.blocInstructions = blocInstructions;
    }


    public Si(int n, Expression exp) {
        super(n);
        this.exp = exp;
        this.blocInstructions = null;
    }

    @Override
    public void verifier() {

        exp.verifier();
        if(!exp.getType().equals("")){
            if(!exp.getType().equals("booleen")){
                String nomFonction = TDS.getInstance().identifierNomFonction(this.numBloc);
                System.out.println("ERREUR SEMANTIQUE : " + exp.getNoLigne() + " ligne d'erreur : "+nomFonction+", Condition a verifier dans 'Si' n'est pas de type Booleen\n");
                AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + exp.getNoLigne() + " ligne d'erreur : "+nomFonction+", Condition a verifier dans 'Si' n'est pas de type Booleen\n");
            }
        }

        if(blocInstructions != null){
            blocInstructions.verifier();
        }





    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        str.append(exp.toMIPS());

        int i = CompteurTagMips.getInstance().incrementerNbrSi();
        str.append("beq $v0 , $zero, finSi"+ i+"\n");
        if(blocInstructions != null){
            str.append(blocInstructions.toMIPS());
        }
        str.append("finSi"+ i +":\n");
        return str.toString();
    }

    @Override
    public boolean hasRetourne() {
      return  false;
    }
}
