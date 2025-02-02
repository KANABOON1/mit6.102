package expressivo;


/**
 * 表示expression中的加法,
 * 一个expression可以被递归表示为: Plus(leftExpr, rightExpr)
 */
public class Plus implements Expression {

    private final Expression left;
    private final Expression right;

    // Abstract function:
    //   AF(leftExpr, rightExpr): add the left part and the right part of the expression
    //                            to recursively represent the whole expression
    // Rep invariant:
    //   true

    // Rep exposure:
    //   all fields are immutable and unreassignable

    public Plus(Expression leftExpr, Expression rightExpr) {
        this.left = leftExpr;
        this.right = rightExpr;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        // 字符化左侧: 如果是unit, 则不加括号; 如果是复合表达式, 则需要加括号
        if (left instanceof Constant || left instanceof Variable) {
            builder.append(left);
        } else {
            builder.append("(").append(left.toString()).append(")");
        }

        // 字符化operator
        builder.append(" + ");

        // 字符化右侧
        if (right instanceof Constant || right instanceof Variable) {
            builder.append(right);
        } else {
            builder.append("(").append(right.toString()).append(")");
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Plus)) {
            return false;
        }

        return this.left.equals(((Plus) thatObject).left) &&
                this.right.equals(((Plus) thatObject).right);
    }

    @Override
    public int hashCode() {

        // 使用质数, 减少冲突
        // 由于hashcode的不可变性, 可以采用计算一次后缓存的方式
        return 31 * left.hashCode() + 17 * right.hashCode();
    }
}
