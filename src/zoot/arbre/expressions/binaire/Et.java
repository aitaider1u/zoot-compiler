package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.outils.Registres;

public class Et extends Binaire{

    public Et(int n, Expression ef, Expression ed) {
        super(n, ef, ed);
    }

    @Override
    public void verifier() {
        this.eg.verifier();
        this.ed.verifier();

        if (!this.eg.getType().equals("") && !this.ed.getType().equals("")){

            if (!this.eg.getType().equals("booleen") || !this.ed.getType().equals("booleen")){
                System.out.println("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : type incompatible pour L'operateur 'Et'   '("+this.eg.getType()+" et "+this.ed.getType()+")'\n");
                AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : type incompatible pour L'operateur 'Et'   '("+this.eg.getType()+" et "+this.ed.getType()+")'\n");
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
        str.append("and $v1, $v1 , $v0\n");
        str.append("move $v0, $v1\n");
        return str.toString();
    }

    /*@Override
    public String toMIPS(Registres registres) {
        StringBuilder str = new StringBuilder();
        str.append(this.eg.toMIPS(registres));
        Registres copyRegs = registres.getCopy();
        String regGauche = copyRegs.extractFirtRegistre();
        String regDroite = copyRegs.getFirtRegistre();
        str.append(this.ed.toMIPS(copyRegs));
        str.append("and "+regGauche+" , "+regGauche+" , "+regDroite+"\n");
        return str.toString();
    }*/

    @Override
    public String getType() {
        return "booleen";
    }
}
