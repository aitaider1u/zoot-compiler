package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.outils.CompteurTagMips;
import zoot.outils.Registres;

public class Non extends Unaire{

    public Non(int n, Expression exp) {
        super(n, exp);
    }



    @Override
    public void verifier() {
        this.exp.verifier();
        if (!this.exp.getType().equals("")){
            if (!this.exp.getType().equals("booleen")){
                System.out.println("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : type incompatible  non ('"+this.exp.getType()+"') \n");
                AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : on ne peut pas multiplier un type '"+this.exp.getType()+"'\n");
            }
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder str= new StringBuilder();
        str.append(this.exp.toMIPS());
        str.append("beq $v0, $zero , tagNon"+ CompteurTagMips.getInstance().incrementerNbrNon()+"\n");
        str.append("li $v0, 0\n");
        str.append("j finTagNon"+CompteurTagMips.getInstance().getNbrNon()+"\n");
        str.append("tagNon"+CompteurTagMips.getInstance().getNbrNon()+":\n");
        str.append("li $v0, 1\n");
        str.append("finTagNon"+CompteurTagMips.getInstance().getNbrNon()+":\n");
        return str.toString();
    }


    /*@Override
    public String toMIPS(Registres registres) {
        StringBuilder str= new StringBuilder();
        Registres copyRegs = registres.getCopy();
        String reg = copyRegs.extractFirtRegistre();
        str.append(this.exp.toMIPS(registres));
        str.append("beq "+ reg +", $zero , tagNon"+ CompteurTagMips.getInstance().incrementerNbrNon()+"\n");
        str.append("li "+reg+" , 0\n");
        str.append("j finTagNon"+CompteurTagMips.getInstance().getNbrNon()+"\n");
        str.append("tagNon"+CompteurTagMips.getInstance().getNbrNon()+":\n");
        str.append("li "+reg+" , 1\n");
        str.append("finTagNon"+CompteurTagMips.getInstance().getNbrNon()+":\n");
        //str.append("nor "+ reg +", "+reg +", "+reg +"  \n");
        return str.toString();
    }*/

    @Override
    public String getType() {
        return "booleen";
    }
}
