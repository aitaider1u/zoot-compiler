package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;

public abstract class Binaire extends Expression {
    protected Expression eg;
    protected Expression ed;

    /**
     * constructeur d'une Expression
     *
     * @param n numero de ligne
     */
    public Binaire(int n, Expression ef, Expression ed) {
        super(n);
        this.eg = ef;
        this.ed = ed;
    }

    @Override
    public String getType() {
        return "";
    }


    public int NumberEchove(Expression expression){



        return 10;
    }





}
