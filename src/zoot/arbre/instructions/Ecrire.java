package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    /**
     * expression a écrire
     */
    protected Expression exp ;

    /**
     * constructeur Ecrire
     * @param e
     *      expression à ecrire
     * @param n
     *      numero de la ligne
     */
    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    /**
     * methode verifier
     */
    @Override
    public void verifier() {
        exp.verifier();
    }

    /**
     * methode toMIPS
     * @return code MIPS
     */
    @Override
    public String toMIPS() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.exp.toMIPS());

        String type = this.exp.getType();

        switch (type) {
            case "entier":
                stringBuilder.append(
                        "move $a0 , $v0    # $a0  (valeur à afficher) \n"+
                                "li $v0 , 1 	 # $v0 <- 1 (code du print entier) \n"+
                                "syscall 	# afficher\n"
                );
                break;
            case "booleen":
                stringBuilder.append("jal afficherBooleen\n");
                break;
        }
        
        //retour a la ligne
        stringBuilder.append("la $a0, nl   # pour le retour a la ligne  \n" +
                "li $v0 , 4 	 # $v0 <- 4 (code du print enter) \n"+
                "syscall 	# afficher");
        return stringBuilder.toString();
    }

}
