package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.outils.Registres;

public class Mult extends Binaire {

    public Mult(int n, Expression ef, Expression ed) {
        super(n, ef, ed);
    }

    @Override
    public void verifier() {
        this.eg.verifier();
        this.ed.verifier();
        if (!this.eg.getType().equals("")){
            if (!this.eg.getType().equals("entier")){
                System.out.println("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : on ne peut pas multiplier un type '"+this.eg.getType()+"'\n");
                AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : on ne peut pas multiplier un type '"+this.eg.getType()+"'\n");
            }

            if (!this.ed.getType().equals("entier")){
                System.out.println("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : on ne peut pas multiplier un type '"+this.eg.getType()+"'\n");
                AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : on ne peut pas multiplier un type '"+this.eg.getType()+"'\n");
            }
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder str= new StringBuilder();
        str.append(this.eg.toMIPS());
        str.append("jal empilerV0\n");
        str.append(this.ed.toMIPS());
        str.append("jal depilerDansV1\n");
        str.append("mult $v1 , $v0\n");
        str.append("mflo  $v0\n");
        return str.toString();
    }

    /*@Override
    public String toMIPS(Registres registres) {
        StringBuilder str= new StringBuilder();
        str.append(this.eg.toMIPS(registres));
        Registres copyRegs = registres.getCopy();
        String regGauche = copyRegs.extractFirtRegistre();
        String regDroite = copyRegs.getFirtRegistre();
        str.append(this.ed.toMIPS(copyRegs));
        str.append("mult "+ regGauche +", "+  regDroite + "\n");
        str.append("mflo "+ regGauche +"\n");
        System.out.println(registres.toString());
        return str.toString();

    }*/


    @Override
    public String getType() {
        return "entier";
    }


}
