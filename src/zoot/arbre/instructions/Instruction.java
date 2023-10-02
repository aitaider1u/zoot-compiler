package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;

public abstract class Instruction extends ArbreAbstrait {

    /**
     * constructeur d'une instruction
     * @param n
     *      numero de ligne de l'instruction
     */
    protected Instruction(int n) {
        super(n);
    }

}
