package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.outils.CompteurTagMips;
import zoot.outils.Registres;

public class Egale extends  Binaire{

    public Egale(int n, Expression ef, Expression ed) {
        super(n, ef, ed);
    }

    @Override
    public void verifier() {
        this.eg.verifier();
        this.ed.verifier();
        String typeExpGauche = this.eg.getType();
        String typeExpDroite = this.ed.getType();
        if (!typeExpGauche.equals("") && !typeExpDroite.equals("")){
            if(!typeExpGauche.equals(typeExpDroite)){
                System.out.println("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : Incompatible types pour Egale    ('"+typeExpGauche+"' == '"+typeExpDroite+"')\n");
                AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : Incompatible types pour Egale    ('"+typeExpGauche+"' == '"+typeExpDroite+"')\n");
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
        str.append("beq $v1, $v0, tagEgale"+ CompteurTagMips.getInstance().incrementerNbrEgale()+"\n");
        str.append("li $v0 , 0\n");
        str.append("j finTagEgale"+CompteurTagMips.getInstance().getNbrEgale()+"\n");
        str.append("tagEgale"+CompteurTagMips.getInstance().getNbrEgale()+":\n");
        str.append("li $v0 , 1\n");
        str.append("finTagEgale"+CompteurTagMips.getInstance().getNbrEgale()+":\n");
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

        str.append("beq "+ regGauche +", "+ regDroite+", tagEgale"+ CompteurTagMips.getInstance().incrementerNbrEgale()+"\n");
        str.append("li "+regGauche+" , 0\n");
        str.append("j finTagEgale"+CompteurTagMips.getInstance().getNbrEgale()+"\n");
        str.append("tagEgale"+CompteurTagMips.getInstance().getNbrEgale()+":\n");
        str.append("li "+regGauche+" , 1\n");
        str.append("finTagEgale"+CompteurTagMips.getInstance().getNbrEgale()+":\n");

        System.out.println(registres.toString());
        return str.toString();
    }*/

    @Override
    public String getType() {
        return "booleen";
    }
}
