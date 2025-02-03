package expressivo;

import java.util.Map;

/**
 * 表示expression中的乘法,
 * 一个expression可以被递归表示为: Times(leftExpr, rightExpr)
 */
public class Times implements Expression {

    private final Expression left;
    private final Expression right;

    // Abstract function:
    //   AF(leftExpr, rightExpr): multiply the left part and the right part of the expression
    //                            to recursively represent the whole expression
    // Rep invariant:
    //   true

    // Rep exposure:
    //   all fields are immutable and unreassignable

    public Times(Expression leftExpr, Expression rightExpr) {
        this.left = leftExpr;
        this.right = rightExpr;
    }

    @Override
    public Expression differentiate(String variable) {
        Expression dLeft = this.left.differentiate(variable);
        Expression dRight = this.right.differentiate(variable);
        Expression leftPart = new Times(dLeft, this.right);
        Expression rightPart = new Times(this.left, dRight);

        return new Plus(leftPart, rightPart);
    }

    @Override
    public Expression simplify(Map<String, Double> environment) {
        // 左右两侧分别简化
        Expression simplifiedLeft = this.left.simplify(environment);
        Expression simplifiedRight = this.right.simplify(environment);

        // 如果左右两侧都是constant, 则必须化至最简
        if (simplifiedLeft.isConstant() && simplifiedRight.isConstant()) {
            return new Constant(((Constant) simplifiedLeft).getValue() *
                    ((Constant) simplifiedRight).getValue());
        }
        return new Times(simplifiedLeft, simplifiedRight);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        // 字符化左侧: 如果是unit, 则不加括号; 如果是复合表达式, 则需要加括号
        if (this.left.isConstant() || this.left.isVariable()) {
            builder.append(left);
        } else {
            builder.append("(").append(left).append(")");
        }

        // 字符化operator
        builder.append(" * ");

        // 字符化右侧
        if (this.right.isConstant() || this.right.isVariable()) {
            builder.append(right);
        } else {
            builder.append("(").append(right.toString()).append(")");
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Times)) {
            return false;
        }

        return this.left.equals(((Times) thatObject).left) &&
                this.right.equals(((Times) thatObject).right);
    }

    @Override
    public int hashCode() {

        // 使用质数, 减少冲突
        // 由于hashcode的不可变性, 可以采用计算一次后缓存的方式
        return 37 * left.hashCode() + 19 * right.hashCode();
    }
}
