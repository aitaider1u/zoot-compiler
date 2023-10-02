package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.outils.CompteurTagMips;
import zoot.outils.Registres;

public class Inf extends Binaire{

    public Inf(int n, Expression ef, Expression ed) {
        super(n, ef, ed);
    }

    @Override
    public String getType() {
        return "booleen";
    }

    @Override
    public void verifier() {
        this.eg.verifier();
        this.ed.verifier();

        if (!this.eg.getType().equals("") && !this.ed.getType().equals("")){

            if (!this.eg.getType().equals("entier") || !this.ed.getType().equals("entier")){
                System.out.println("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : type incompatible '("+this.eg.getType()+" < "+this.ed.getType()+")'\n");
                AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + this.noLigne + " ligne d'erreur : type incompatible '("+this.eg.getType()+" < "+this.ed.getType()+")'\n");
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
        str.append("blt $v1, $v0, tagSup"+ CompteurTagMips.getInstance().incrementerNbrSup()+"\n");
        str.append("li  $v0, 0\n");
        str.append("j finTagSup"+CompteurTagMips.getInstance().getNbrSup()+"\n");
        str.append("tagSup"+CompteurTagMips.getInstance().getNbrSup()+":\n");
        str.append("li $v0, 1\n");
        str.append("finTagSup"+CompteurTagMips.getInstance().getNbrSup()+":\n");
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

        str.append("blt "+ regGauche +", "+  regDroite + ", tagSup"+ CompteurTagMips.getInstance().incrementerNbrSup()+"\n");
        str.append("li "+regGauche+" , 0\n");
        str.append("j finTagSup"+CompteurTagMips.getInstance().getNbrSup()+"\n");
        str.append("tagSup"+CompteurTagMips.getInstance().getNbrSup()+":\n");
        str.append("li "+regGauche+" , 1\n");
        str.append("finTagSup"+CompteurTagMips.getInstance().getNbrSup()+":\n");

        System.out.println(registres.toString());
        return str.toString();
    }*/
}
