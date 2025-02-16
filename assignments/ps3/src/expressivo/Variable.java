package expressivo;

import java.util.Map;

/**
 * 代表expression中的变量, 是expression的最小单元
 */
public class Variable implements Expression {

    private final String symbol;

    // Abstract function:
    //   AF(symbol, value) = a variable of some value(non-negative) in an expression
    // Rep invariant:
    //   true

    // Rep exposure:
    //   1. all fields are private, immutable and unreassignable

    /**
     * @param symbol requires to be the symbol of a variable in the expression
     */
    public Variable(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean isVariable() {
        return true;
    }

    @Override
    public Expression differentiate(String variable) {
        if (this.symbol.equals(variable)) {
            return new Constant(1);
        }
        else {
            return new Constant(0);
        }
    }

    @Override
    public Expression simplify(Map<String, Double> environment) {
        if (environment.containsKey(this.symbol)) {
            return new Constant(environment.get(this.symbol));
        }
        return this;
    }

    @Override
    public String toString() {
        return this.symbol;
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Variable)) {
            return false;
        }
        return this.symbol.equals(((Variable) thatObject).symbol);
    }

    @Override
    public int hashCode() {
        return this.symbol.hashCode();
    }
}
