package zoot.arbre;

import zoot.arbre.instructions.Instruction;
import zoot.exceptions.AccumulateurErreurSemantiques;
import zoot.tds.TDS;

import java.util.ArrayList;

public class Programme extends ArbreAbstrait{

    /**
     * liste des instructions formant le programme
     */
    protected ArrayList<BlocDInstructions> programme ;
    protected ArrayList<BlocDInstructions> blocsDeFonctions ;


    /**
     * taille zoneMemoireVariable
     */
    //protected int zoneMemoireVariable;

    /**
     * constructeur d'un bloc d'instruction
     * @param n
     *      numero de la ligne
     */
    public Programme(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }

    /**
     * methode ajoutant une instruction au programme
     * @param i
     *      instruction à ajouter
     */
    public void ajouter(BlocDInstructions i) {
        programme.add(i) ;
    }
    public void ajouter(ArrayList<BlocDInstructions> blocsFonct) {
       this.blocsDeFonctions = blocsFonct ;
    }


    /**
     * methode verifier
     */
    @Override
    public void verifier()
    {

        for (BlocDInstructions bloc : this.blocsDeFonctions) {
            AccumulateurErreurSemantiques.getInstance().incrementerLeNumDeBloc();
            bloc.verifier();
            //if(AccumulateurErreurSemantiques.getInstance().nbrDeRetourneDuBlocIndex()==0){
            //    System.out.println("ERREUR SEMANTIQUE : " + bloc.getNoLigne()  + " L'instruction 'retourne' est obligatoire dans une fonction\n");
            //    AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + bloc.getNoLigne()  + " L'instruction 'retourne' est obligatoire dans une fonction\n");
            //}
            if(!bloc.hasRetourne()){
                System.out.println("ERREUR SEMANTIQUE : " + bloc.getNoLigne()  + " L'instruction 'retourne' est obligatoire dans une fonction\n");
                AccumulateurErreurSemantiques.getInstance().ajouter("ERREUR SEMANTIQUE : " + bloc.getNoLigne()  + " L'instruction 'retourne' est obligatoire dans une fonction\n");
            }
        }

        for (ArbreAbstrait bloc : programme) {
            AccumulateurErreurSemantiques.getInstance().setOnEstLeProgrammePrincipal(true);
            bloc.verifier();
            AccumulateurErreurSemantiques.getInstance().setOnEstLeProgrammePrincipal(false);
        }


    }

    /**
     * methode toMIPS
     * @return code MIPS
     */
    @Override
    public String toMIPS() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(".data \n");
        strBuilder.append("faux : .asciiz \"faux\" \n");
        strBuilder.append("vrai : .asciiz \"vrai\" \n");
        strBuilder.append("lus : .space 256 \n");
        strBuilder.append("nl : .asciiz \"\\n\" \n");
        strBuilder.append(".text \n");
        strBuilder.append("main: \n");

        int i = 0 ;
        for (ArbreAbstrait bloc : programme) {

            if (i == 0) {  // si c'est le premier main
                BlocDInstructions b = (BlocDInstructions)  bloc;
                //System.out.println("PP___________------> "+ b.getNumBloc());
                strBuilder.append("# initialiser s7 avec sp (initialisation de la base des variables) \n");
                strBuilder.append("move $s7,$sp \n\n");
                strBuilder.append("move $s6,$sp \n");  // ref ver le bloc 0 pour les variables globale
                strBuilder.append("# reservation de l'espace pour " + b.getZoneMemoireVariable() + " variables \n");
                strBuilder.append("addi $sp, $sp, "+(-1)*b.getZoneMemoireVariable()+" \n\n");

                strBuilder.append(bloc.toMIPS());
                strBuilder.append("\n\n");
                strBuilder.append("# fin du programme\n");
                strBuilder.append("li $v0, 10      # retour au systeme\n");
                strBuilder.append("syscall\n");
            }else{
                strBuilder.append(bloc.toMIPS());
            }
        }

        // la fonction qui affiche les booleens pour eviter la duplication de code

        int ii = 1;
        for (BlocDInstructions b: this.blocsDeFonctions) {
            ii ++;;
            //System.out.println("fonction  ___________------> "+ b.getNumBloc());
            strBuilder.append("# reservation de l'espace pour " + b.getZoneMemoireVariable() + " variables \n");
            strBuilder.append("\n");
            strBuilder.append("\n");
            //System.out.println("------> fonc "+ ii);
            strBuilder.append("#decalaration de fonction\n");
            strBuilder.append("fonction"+ii+":\n");   //ajouter l'etique mips
            //placer l'environnement.
            //reserver un mot pour le resultat.

            //strBuilder.append("addi $sp, $sp,-4\n");
            //reserver un mot pour l'adresse retour.
            strBuilder.append("sw $ra, 0($sp)    \n");
            strBuilder.append("addi $sp, $sp,-4\n");

            //reverver un mot pour le chainage dynamyme (pour la sauvagarde de l'ancienne base )
            //strBuilder.append("addi $sp, $sp,-4\n");

            strBuilder.append("sw $s7, 0($sp)    \n");
            strBuilder.append("addi $sp, $sp,-4\n");

            //reserver un mot pour l'addresse le numero de region
            //strBuilder.append("addi $sp, $sp,-4\n");
            strBuilder.append("li $v0,"+ii+"\n");
            strBuilder.append("sw $v0, 0($sp)\n");
            strBuilder.append("addi $sp, $sp,-4\n");

            //mise a jour de la base sur $s7 pour la nouvelle base
            //strBuilder.append("move $t1, $s7\n");  //d'abord il faut sauvegarder dans un registre tempo $t1 le s7 utile pour l'initialisation les varaibles parametres de la fonction
            strBuilder.append("move $s7, $sp\n");
            strBuilder.append("addi $sp, $sp, "+(-1)*b.getZoneMemoireVariable()+" \n\n");

            //ecrire les instructions du de la fonction on mips.
            strBuilder.append(b.toMIPS());  // les fonction
            strBuilder.append("\n");
            strBuilder.append("\n");
        }
        strBuilder.append("\n");
        strBuilder.append("\n");
        strBuilder.append("\n");
        strBuilder.append("#fonction pour afficher les booleens vrai | faux \n");
        strBuilder.append("afficherBooleen :\n"
                +"		beq $v0 , $zero,Sinon    #  si c'est $v0 est different de zero donc c'est vrai\n"
                +"			la $a0, vrai     # $a0 <- adresse de la chaîne à écrire \n"
                +"			li $v0, 4     # $v0 <- code du print \n"
                +"			syscall     # afficher \n"
                +"			j FinSi \n"
                +"		Sinon :      # afficher faux  \n"
                +"			la $a0, faux     # $a0 <- adresse de la chaîne à écrire  \n"
                +"			li $v0, 4     # $v0 <- code du print \n"
                +"			syscall     # afficher \n"
                +"		FinSi: \n"
                +"			jr $ra    # retour a la suite juste apres l'appele de fonction  afficherBooleen  \n");
        strBuilder.append("empilerV0 :\n"
                +"		sw $v0 ,0($sp)\n"
                +"		addi $sp, $sp,-4\n"
                +"	     jr $ra \n" );
        strBuilder.append("depilerDansV1 :\n"
                +"		addi $sp, $sp,4\n"
                +"		lw $v1 ,0($sp)\n"
                +"	    jr $ra \n" );
        return strBuilder.toString();


    }

    /**
     * method toString
     * @return String
     */
    @Override
    public String toString() {
        return programme.toString() ;
    }

}
