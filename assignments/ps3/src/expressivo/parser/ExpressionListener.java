// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link ExpressionParser#root}.
   * @param ctx the parse tree
   */
  void enterRoot(ExpressionParser.RootContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#root}.
   * @param ctx the parse tree
   */
  void exitRoot(ExpressionParser.RootContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void enterExpr(ExpressionParser.ExprContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#expr}.
   * @param ctx the parse tree
   */
  void exitExpr(ExpressionParser.ExprContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#plusExpr}.
   * @param ctx the parse tree
   */
  void enterPlusExpr(ExpressionParser.PlusExprContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#plusExpr}.
   * @param ctx the parse tree
   */
  void exitPlusExpr(ExpressionParser.PlusExprContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#timesExpr}.
   * @param ctx the parse tree
   */
  void enterTimesExpr(ExpressionParser.TimesExprContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#timesExpr}.
   * @param ctx the parse tree
   */
  void exitTimesExpr(ExpressionParser.TimesExprContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#primitive}.
   * @param ctx the parse tree
   */
  void enterPrimitive(ExpressionParser.PrimitiveContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#primitive}.
   * @param ctx the parse tree
   */
  void exitPrimitive(ExpressionParser.PrimitiveContext ctx);
}