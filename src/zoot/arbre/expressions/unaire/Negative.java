package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.outils.Registres;

public class Negative extends Unaire{


    public Negative(int n, Expression exp) {
        super(n, exp);
    }

    @Override
    public void verifier() {
        this.exp.verifier();
        if (!this.exp.getType().equals("")){
            if (!this.exp.getType().equals("entier")){
                System.out.println("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : on ne peut pas avoir la negation  d'un type '"+this.exp.getType()+"  '\n");
                AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : on ne peut pas multiplier un type '"+this.exp.getType()+"'\n");
            }
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder str= new StringBuilder();
        str.append(this.exp.toMIPS());
        str.append("sub $v0, $zero , $v0\n");
        return str.toString();
    }

  /*@Override
    public String toMIPS(Registres registres) {
        StringBuilder str= new StringBuilder();
        Registres copyRegs = registres.getCopy();
        String reg = copyRegs.extractFirtRegistre();
        str.append(this.exp.toMIPS(registres));
        str.append("sub "+ reg +", $zero , " + reg +"\n");
        return str.toString();
    }*/

    @Override
    public String getType() {
        return "entier";
    }
}
