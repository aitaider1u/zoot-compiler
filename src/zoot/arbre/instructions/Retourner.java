package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.tds.Symbole;
import zoot.tds.TDS;

public class Retourner extends Instruction {


    /**
     * identifiant
     */
    private Idf idf;

    /**
     * expression à affecter à l'identifiant
     */
    private Expression exp;


    private  int zoneMemoireAdetruire;  // utile pour savoir la zone memoire des varaible a detruire a la fin de la appel

    /**
     * Constructeur d'Affectation
     * @param e
     *      expression
     * @param n
     *      numero de la ligne
     */
    public Retourner (Expression e, int n) {
        super(n);
        this.exp = e;
    }

    /**
     * méthode vérifier
     */
    @Override
    public void verifier() {
        //utile pour voir si le retourne existe dans un bloc
        this.exp.verifier();
        this.zoneMemoireAdetruire = TDS.getInstance().getZoneMemoireVariable(this.getNumBloc());
        if(AccumulateurErreurSemantiques.getInstance().onEstLeProgrammePrincipal()) {
            System.out.println("ERREUR SEMANTIQUE : " + noLigne + " ligne d'erreur : Instruction 'retourne' ne peut pas se trouver dans le Programme Principal\n");
            AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + noLigne + " L'instruction 'retourne' ne peut pas se trouver dans le Programme Principal\n");
        }
        else {
            AccumulateurErreurSemantiques.getInstance().incrementerNbrDeRetourneDuBlocindex();
            Symbole s = TDS.getInstance().identifierFonction(this.numBloc);
            String nomFonction = TDS.getInstance().identifierNomFonction(this.numBloc);


            if (s != null){

                if(!s.getType().equals(this.exp.getType()) && !this.exp.getType().equals("")){
                    System.out.println("ERREUR SEMANTIQUE : " + noLigne + " ligne d'erreur : "+nomFonction+", le Type retourné imcompatible avec la fonction '"+s.getType()+"->"+this.exp.getType()+"'\n");
                    AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + noLigne + " type retourné imcompatible avec la fonction '"+s.getType()+"->"+this.exp.getType()+"'\n");
                }
            }
        }
    }

    /**
     * méthode toMIPS
     * @return code MIPS en String
     */
    @Override
    public String toMIPS() {
        StringBuilder str = new StringBuilder();
        str.append(this.exp.toMIPS());


        str.append("addi $sp,$sp,"+(4*this.zoneMemoireAdetruire) +"\n");     //liberer l'espace memoire reserver au varaible (profil et local )

        str.append("addi $sp,$sp,4\n");     //liberer le mot reserver pour le numero de region

        str.append("addi $sp,$sp,4\n");
        str.append("lw $s7,0($sp)\n");         // reccuper l'ancienne base de bloc appellant
        //str.append("addi $sp,$sp,4\n");     //recupere le chainage dynamique de l'encienne base


        str.append("addi $sp,$sp,4\n");
        str.append("lw $ra,0($sp)\n");         // ecrire la valeur de @return dans le registre
        //str.append("addi $sp,$sp,4\n");     // detruire la mot reserver pour @return dans la pile


        //str.append("sw $v0,0 ($sp)\n");         //  ecrire le resultat dans la zone reserver dans la pile
        str.append("jr $ra\n");             // retourner vers le bloc appeler
        return str.toString();
    }

    @Override
    public boolean hasRetourne() {
        return true;
    }
}
