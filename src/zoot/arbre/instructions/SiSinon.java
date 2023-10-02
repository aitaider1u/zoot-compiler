package zoot.arbre.instructions;

import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.outils.CompteurTagMips;
import zoot.tds.TDS;

public class SiSinon extends Instruction{
    private Expression exp;
    private BlocDInstructions blocSi;
    private BlocDInstructions blocSinon;

    public SiSinon(int n, Expression exp, BlocDInstructions blocSi, BlocDInstructions blocSiSinon) {
        super(n);
        this.exp = exp;
        this.blocSi = blocSi;
        this.blocSinon = blocSiSinon;
    }

    public SiSinon(int n, Expression exp, BlocDInstructions blocSiSinon) {
        super(n);
        this.exp = exp;
        this.blocSi = null;
        this.blocSinon = blocSiSinon;
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
        if(blocSi != null){
            blocSi.verifier();
        }
        blocSinon.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        str.append(exp.toMIPS());
        int i = CompteurTagMips.getInstance().incrementerNbrSiSinon();
        str.append("beq $v0 , $zero, siSinon"+i +"\n");
        if(blocSi != null){
            str.append(blocSi.toMIPS());
        }
        str.append("j finSiSinon"+i+"\n");
        str.append("siSinon"+i+":\n");
        str.append(blocSinon.toMIPS());
        str.append("finSiSinon"+i+":\n");

        return str.toString();
    }

    @Override
    public boolean hasRetourne() {
        boolean a = false;
        if(blocSi != null){
            a = this.blocSi.hasRetourne();
        }
        boolean b = this.blocSinon.hasRetourne();
        return a && b ;
    }
}
