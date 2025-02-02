package expressivo;

/**
 * 代表expression中的变量, 是expression的最小单元
 */
public class Variable implements Expression {

    private final String symbol;

    // Abstract function:
    //   AF(symbol) = a variable in an expression
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
