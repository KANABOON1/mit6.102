// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, LETTER=5, NUMBER=6, SPACES=7;
  public static final int
    RULE_root = 0, RULE_expr = 1, RULE_plusExpr = 2, RULE_timesExpr = 3, 
    RULE_primitive = 4;
  public static final String[] ruleNames = {
    "root", "expr", "plusExpr", "timesExpr", "primitive"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'+'", "'*'", "'('", "')'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, "LETTER", "NUMBER", "SPACES"
  };
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  static {
    tokenNames = new String[_SYMBOLIC_NAMES.length];
    for (int i = 0; i < tokenNames.length; i++) {
      tokenNames[i] = VOCABULARY.getLiteralName(i);
      if (tokenNames[i] == null) {
        tokenNames[i] = VOCABULARY.getSymbolicName(i);
      }

      if (tokenNames[i] == null) {
        tokenNames[i] = "<INVALID>";
      }
    }
  }

  @Override
  @Deprecated
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override

  public Vocabulary getVocabulary() {
    return VOCABULARY;
  }

  @Override
  public String getGrammarFileName() { return "Expression.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public ATN getATN() { return _ATN; }


      // This method makes the lexer or parser stop running if it encounters
      // invalid input and throw a ParseCancellationException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol,
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }

  public ExpressionParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class RootContext extends ParserRuleContext {
    public ExprContext expr() {
      return getRuleContext(ExprContext.class,0);
    }
    public TerminalNode EOF() { return getToken(ExpressionParser.EOF, 0); }
    public RootContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_root; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterRoot(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitRoot(this);
    }
  }

  public final RootContext root() throws RecognitionException {
    RootContext _localctx = new RootContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_root);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(10);
      expr();
      setState(11);
      match(EOF);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ExprContext extends ParserRuleContext {
    public PlusExprContext plusExpr() {
      return getRuleContext(PlusExprContext.class,0);
    }
    public ExprContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_expr; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExpr(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExpr(this);
    }
  }

  public final ExprContext expr() throws RecognitionException {
    ExprContext _localctx = new ExprContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_expr);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(13);
      plusExpr();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class PlusExprContext extends ParserRuleContext {
    public List<TimesExprContext> timesExpr() {
      return getRuleContexts(TimesExprContext.class);
    }
    public TimesExprContext timesExpr(int i) {
      return getRuleContext(TimesExprContext.class,i);
    }
    public PlusExprContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_plusExpr; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPlusExpr(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPlusExpr(this);
    }
  }

  public final PlusExprContext plusExpr() throws RecognitionException {
    PlusExprContext _localctx = new PlusExprContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_plusExpr);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(15);
      timesExpr();
      setState(20);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__0) {
        {
        {
        setState(16);
        match(T__0);
        setState(17);
        timesExpr();
        }
        }
        setState(22);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class TimesExprContext extends ParserRuleContext {
    public List<PrimitiveContext> primitive() {
      return getRuleContexts(PrimitiveContext.class);
    }
    public PrimitiveContext primitive(int i) {
      return getRuleContext(PrimitiveContext.class,i);
    }
    public TimesExprContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_timesExpr; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterTimesExpr(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitTimesExpr(this);
    }
  }

  public final TimesExprContext timesExpr() throws RecognitionException {
    TimesExprContext _localctx = new TimesExprContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_timesExpr);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(23);
      primitive();
      setState(28);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__1) {
        {
        {
        setState(24);
        match(T__1);
        setState(25);
        primitive();
        }
        }
        setState(30);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class PrimitiveContext extends ParserRuleContext {
    public TerminalNode LETTER() { return getToken(ExpressionParser.LETTER, 0); }
    public TerminalNode NUMBER() { return getToken(ExpressionParser.NUMBER, 0); }
    public ExprContext expr() {
      return getRuleContext(ExprContext.class,0);
    }
    public PrimitiveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_primitive; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPrimitive(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPrimitive(this);
    }
  }

  public final PrimitiveContext primitive() throws RecognitionException {
    PrimitiveContext _localctx = new PrimitiveContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_primitive);
    try {
      setState(37);
      switch (_input.LA(1)) {
      case LETTER:
        enterOuterAlt(_localctx, 1);
        {
        setState(31);
        match(LETTER);
        }
        break;
      case NUMBER:
        enterOuterAlt(_localctx, 2);
        {
        setState(32);
        match(NUMBER);
        }
        break;
      case T__2:
        enterOuterAlt(_localctx, 3);
        {
        setState(33);
        match(T__2);
        setState(34);
        expr();
        setState(35);
        match(T__3);
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t*\4\2\t\2\4\3"+
      "\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\7\4\25"+
      "\n\4\f\4\16\4\30\13\4\3\5\3\5\3\5\7\5\35\n\5\f\5\16\5 \13\5\3\6\3"+
      "\6\3\6\3\6\3\6\3\6\5\6(\n\6\3\6\2\2\7\2\4\6\b\n\2\2(\2\f\3\2\2\2\4"+
      "\17\3\2\2\2\6\21\3\2\2\2\b\31\3\2\2\2\n\'\3\2\2\2\f\r\5\4\3\2\r\16"+
      "\7\2\2\3\16\3\3\2\2\2\17\20\5\6\4\2\20\5\3\2\2\2\21\26\5\b\5\2\22"+
      "\23\7\3\2\2\23\25\5\b\5\2\24\22\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2"+
      "\2\26\27\3\2\2\2\27\7\3\2\2\2\30\26\3\2\2\2\31\36\5\n\6\2\32\33\7"+
      "\4\2\2\33\35\5\n\6\2\34\32\3\2\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37"+
      "\3\2\2\2\37\t\3\2\2\2 \36\3\2\2\2!(\7\7\2\2\"(\7\b\2\2#$\7\5\2\2$"+
      "%\5\4\3\2%&\7\6\2\2&(\3\2\2\2\'!\3\2\2\2\'\"\3\2\2\2\'#\3\2\2\2(\13"+
      "\3\2\2\2\5\26\36\'";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}