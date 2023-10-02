package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Expression;

public abstract  class Unaire extends Expression{

    protected Expression exp;

    public Unaire(int n, Expression exp) {
        super(n);
        this.exp = exp;
    }

    @Override
    public String getType() {
        return "";
    }

}
