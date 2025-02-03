/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    //    Expression = Constant(value: double)
    //                 + Variable(symbol: String)
    //                 + Plus(leftExpr: Expression, rightExpr: Expression)
    //                 + Times(leftExpr: Expression, rightExpr: Expression)
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {   // 通过静态方法的形式隐藏掉内部实现

        // 将string, reader, inputStream 转化为字符流, 为接下来输入至lexer进行准备
        ParseTree tree = getParseTree(input);

//        // 遍历解析树
//        ParseTreeWalker walker = new ParseTreeWalker();      // 创建解析树遍历器
//        ExpressionListener listener = new PrintEverything(); // 自定义监听器
//        walker.walk(listener, tree);

        // 遍历解析树, 得到表达式
        MakeExpression exprMaker = new MakeExpression();
        new ParseTreeWalker().walk(exprMaker, tree);
        return exprMaker.getExpression();
    }

    private static ParseTree getParseTree(String input) {
        CharStream stream = new ANTLRInputStream(input);

        // 得到根据自己编写的grammar file生成的lexer, 并将字符流传入lexer
        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();  // 若有错误, 转为异常抛出
        TokenStream tokens = new CommonTokenStream(lexer);

        // 将终结符流传递给语法分析器, parser将为每一个非终结符生成一个方法
        ExpressionParser parser = new ExpressionParser(tokens);
        parser.reportErrorsAsExceptions();

        return parser.root();    // 生成root的解析树
    }

    /**
     * Differentiate the expression with respect to the variable, not in the simplest form.
     * @param variable the variable to differentiate by, a case-sensitive nonempty string of letters.
     * @return the differentiated expression but not in the simplest form.
     */
    public Expression differentiate(String variable);

    /**
     * Substitute the values for those variables into the expression, and simplify the substituted polynomial.
     * @param environment a map between the variables in the environment and their values.
     *                    The set of variables in the environment is allowed to be different
     *                    than the set of variables actually found in the expression.
     * @return a simplified expression. If the substituted polynomial is a constant expression, with no variables remaining,
     *         then simplification must reduce it to a single number.
     */
    public Expression simplify(Map<String, Double> environment);

    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     * And the representation must follow these rules:
     * There should be no spaces between parentheses and variables,
     * and there should be spaces between variables and operators.
     * For example, the format should be like a + (b * c).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     * Note: this function distinguish different groupings, so it is strict equal
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();

    /**
     * @return whether the expression is a constant
     */
    default boolean isConstant() {  // 使用helper function 避免使用instanceof
        return false;
    }

    /**
     * @return whether the expression is a variable
     */
    default boolean isVariable() {  // 使用helper function 避免使用instanceof
        return false;
    }
}

// Make an Expression value from the parse tree
class MakeExpression implements ExpressionListener {
    private final Stack<Expression> stack = new Stack<>();
    // Invariant:
    // stack中存储了那些已经被遍历完毕的但是其父节点尚未被退出的子树的Expression value,
    // 由于栈的LIFO性质, 因此栈顶元素是刚刚遍历完的子树形成的Expression value.
    //
    // 在walk的开始, 栈为空;
    //
    // 当从一个节点退出时, 其子节点对应的Expression value必然是stack的最上方的c个,
    // 因此需要将这些子节点弹出, 计算出一个新值再存入栈中;
    //
    // 当walk结束时, 只有root节点满足: "fully walked but parent not yet exited"性质,
    // 因此只有root元素的Expression value会在栈顶, 这就是 expression of the entire parse tree.

    public Expression getExpression() {
        return stack.getFirst();
    }

    @Override
    public void exitExpr(ExpressionParser.ExprContext ctx) {
        // do nothing
    }

    @Override
    public void exitPlusExpr(ExpressionParser.PlusExprContext ctx) {
        // match the timesExpr ('+' timesExpr)* rule
        final List<ExpressionParser.TimesExprContext> addends = ctx.timesExpr();
        assert stack.size() >= addends.size();   // fail fast

        // the pattern above always has at least 1 child
        // pop the last child
        assert !addends.isEmpty();
        Expression sum = stack.pop();

        // pop the older children, one by one, and add them on
        for (int i = 1; i < addends.size(); i++) {
            sum = new Plus(stack.pop(), sum);
        }

        // the result is this subtree's Expression
        stack.push(sum);
    }

    @Override
    public void exitTimesExpr(ExpressionParser.TimesExprContext ctx) {
        // match the primitive ('*' primitive)* rule
        final List<ExpressionParser.PrimitiveContext> factors = ctx.primitive();
        assert stack.size() >= factors.size();

        // the pattern above always has at least 1 child
        // pop the last child
        assert !factors.isEmpty();
        Expression product = stack.pop();

        // pop the older children, one by one, and add them on
        for (int i = 1; i < factors.size(); i++) {
            product = new Times(stack.pop(), product);
        }

        // the result is this subtree's Expression
        stack.push(product);
    }

    @Override
    public void exitPrimitive(ExpressionParser.PrimitiveContext ctx) {

        if (ctx.LETTER() != null) {           // 上下文匹配LETTER终结符
            String symbol = ctx.LETTER().getText();
            stack.push(new Variable(symbol));

        } else if (ctx.NUMBER() != null) {    // 上下文匹配NUMBER终结符
            double d = Double.parseDouble(ctx.NUMBER().getText());
            stack.push(new Constant(d));

        } else {                              // 上下文匹配'(' expr ')'终结符
            // match the case: '(' expr ')'
            // do nothing, because the Expression value has already in the stack
        }
    }

    @Override
    public void enterPlusExpr(ExpressionParser.PlusExprContext ctx) { }

    @Override
    public void enterTimesExpr(ExpressionParser.TimesExprContext ctx) { }

    @Override
    public void enterPrimitive(ExpressionParser.PrimitiveContext ctx) { }

    @Override
    public void enterExpr(ExpressionParser.ExprContext ctx) { }

    @Override
    public void enterRoot(ExpressionParser.RootContext ctx) { }

    @Override
    public void exitRoot(ExpressionParser.RootContext ctx) { }

    @Override
    public void visitTerminal(TerminalNode terminalNode) { }

    @Override
    public void visitErrorNode(ErrorNode errorNode) { }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) { }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) { }
}

// 自定义ParseTreeWalker的监听器
class PrintEverything implements ExpressionListener {

    @Override public void enterRoot(ExpressionParser.RootContext context) {
        System.err.println("entering root");
    }

    @Override public void exitRoot(ExpressionParser.RootContext context) {
        System.err.println("exiting root");
    }

    @Override public void enterExpr(ExpressionParser.ExprContext context) {
        System.err.println("entering sum");
    }

    @Override public void exitExpr(ExpressionParser.ExprContext context) {
        System.err.println("exiting sum");
    }

    @Override
    public void enterPlusExpr(ExpressionParser.PlusExprContext ctx) {
        System.err.println("entering plus");
    }

    @Override
    public void exitPlusExpr(ExpressionParser.PlusExprContext ctx) {
        System.err.println("entering plus");
    }

    @Override
    public void enterTimesExpr(ExpressionParser.TimesExprContext ctx) {
        System.err.println("entering times");
    }

    @Override
    public void exitTimesExpr(ExpressionParser.TimesExprContext ctx) {
        System.err.println("entering times");
    }

    @Override public void enterPrimitive(ExpressionParser.PrimitiveContext context) {
        System.err.println("entering primitive");
    }
    @Override public void exitPrimitive(ExpressionParser.PrimitiveContext context) {
        System.err.println("exiting primitive");
    }

    @Override public void visitTerminal(TerminalNode terminal) {
        System.err.println("terminal " + terminal.getText());
    }

    // don't need these here, so just make them empty implementations
    @Override public void enterEveryRule(ParserRuleContext context) { }
    @Override public void exitEveryRule(ParserRuleContext context) { }
    @Override public void visitErrorNode(ErrorNode node) { }
}