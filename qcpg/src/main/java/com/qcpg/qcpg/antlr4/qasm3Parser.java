package com.qcpg.qcpg.antlr4;
// Generated from qasm3Parser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class qasm3Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OPENQASM=1, INCLUDE=2, DEFCALGRAMMAR=3, DEF=4, CAL=5, DEFCAL=6, GATE=7, 
		EXTERN=8, BOX=9, LET=10, BREAK=11, CONTINUE=12, IF=13, ELSE=14, END=15, 
		RETURN=16, FOR=17, WHILE=18, IN=19, SWITCH=20, CASE=21, DEFAULT=22, PRAGMA=23, 
		AnnotationKeyword=24, INPUT=25, OUTPUT=26, CONST=27, READONLY=28, MUTABLE=29, 
		QREG=30, QUBIT=31, CREG=32, BOOL=33, BIT=34, INT=35, UINT=36, FLOAT=37, 
		ANGLE=38, COMPLEX=39, ARRAY=40, VOID=41, DURATION=42, STRETCH=43, GPHASE=44, 
		INV=45, POW=46, CTRL=47, NEGCTRL=48, DIM=49, DURATIONOF=50, DELAY=51, 
		RESET=52, MEASURE=53, BARRIER=54, BooleanLiteral=55, LBRACKET=56, RBRACKET=57, 
		LBRACE=58, RBRACE=59, LPAREN=60, RPAREN=61, COLON=62, SEMICOLON=63, DOT=64, 
		COMMA=65, EQUALS=66, ARROW=67, PLUS=68, DOUBLE_PLUS=69, MINUS=70, ASTERISK=71, 
		DOUBLE_ASTERISK=72, SLASH=73, PERCENT=74, PIPE=75, DOUBLE_PIPE=76, AMPERSAND=77, 
		DOUBLE_AMPERSAND=78, CARET=79, AT=80, TILDE=81, EXCLAMATION_POINT=82, 
		EqualityOperator=83, CompoundAssignmentOperator=84, ComparisonOperator=85, 
		BitshiftOperator=86, IMAG=87, ImaginaryLiteral=88, BinaryIntegerLiteral=89, 
		OctalIntegerLiteral=90, DecimalIntegerLiteral=91, HexIntegerLiteral=92, 
		Identifier=93, HardwareQubit=94, FloatLiteral=95, TimingLiteral=96, BitstringLiteral=97, 
		Whitespace=98, Newline=99, LineComment=100, BlockComment=101, VERSION_IDENTIFER_WHITESPACE=102, 
		VersionSpecifier=103, ARBITRARY_STRING_WHITESPACE=104, StringLiteral=105, 
		EAT_INITIAL_SPACE=106, EAT_LINE_END=107, RemainingLineContent=108, CAL_PRELUDE_WHITESPACE=109, 
		CAL_PRELUDE_COMMENT=110, DEFCAL_PRELUDE_WHITESPACE=111, DEFCAL_PRELUDE_COMMENT=112, 
		CalibrationBlock=113;
	public static final int
		RULE_program = 0, RULE_version = 1, RULE_statement = 2, RULE_annotation = 3, 
		RULE_scope = 4, RULE_pragma = 5, RULE_statementOrScope = 6, RULE_calibrationGrammarStatement = 7, 
		RULE_includeStatement = 8, RULE_breakStatement = 9, RULE_continueStatement = 10, 
		RULE_endStatement = 11, RULE_forStatement = 12, RULE_ifStatement = 13, 
		RULE_returnStatement = 14, RULE_whileStatement = 15, RULE_switchStatement = 16, 
		RULE_switchCaseItem = 17, RULE_barrierStatement = 18, RULE_boxStatement = 19, 
		RULE_delayStatement = 20, RULE_gateCallStatement = 21, RULE_measureArrowAssignmentStatement = 22, 
		RULE_resetStatement = 23, RULE_aliasDeclarationStatement = 24, RULE_classicalDeclarationStatement = 25, 
		RULE_constDeclarationStatement = 26, RULE_ioDeclarationStatement = 27, 
		RULE_oldStyleDeclarationStatement = 28, RULE_quantumDeclarationStatement = 29, 
		RULE_defStatement = 30, RULE_externStatement = 31, RULE_gateStatement = 32, 
		RULE_assignmentStatement = 33, RULE_expressionStatement = 34, RULE_calStatement = 35, 
		RULE_defcalStatement = 36, RULE_expression = 37, RULE_aliasExpression = 38, 
		RULE_declarationExpression = 39, RULE_measureExpression = 40, RULE_rangeExpression = 41, 
		RULE_setExpression = 42, RULE_arrayLiteral = 43, RULE_indexOperator = 44, 
		RULE_indexedIdentifier = 45, RULE_returnSignature = 46, RULE_gateModifier = 47, 
		RULE_scalarType = 48, RULE_qubitType = 49, RULE_arrayType = 50, RULE_arrayReferenceType = 51, 
		RULE_designator = 52, RULE_defcalTarget = 53, RULE_defcalArgumentDefinition = 54, 
		RULE_defcalOperand = 55, RULE_gateOperand = 56, RULE_externArgument = 57, 
		RULE_argumentDefinition = 58, RULE_argumentDefinitionList = 59, RULE_defcalArgumentDefinitionList = 60, 
		RULE_defcalOperandList = 61, RULE_expressionList = 62, RULE_identifierList = 63, 
		RULE_gateOperandList = 64, RULE_externArgumentList = 65;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "version", "statement", "annotation", "scope", "pragma", "statementOrScope", 
			"calibrationGrammarStatement", "includeStatement", "breakStatement", 
			"continueStatement", "endStatement", "forStatement", "ifStatement", "returnStatement", 
			"whileStatement", "switchStatement", "switchCaseItem", "barrierStatement", 
			"boxStatement", "delayStatement", "gateCallStatement", "measureArrowAssignmentStatement", 
			"resetStatement", "aliasDeclarationStatement", "classicalDeclarationStatement", 
			"constDeclarationStatement", "ioDeclarationStatement", "oldStyleDeclarationStatement", 
			"quantumDeclarationStatement", "defStatement", "externStatement", "gateStatement", 
			"assignmentStatement", "expressionStatement", "calStatement", "defcalStatement", 
			"expression", "aliasExpression", "declarationExpression", "measureExpression", 
			"rangeExpression", "setExpression", "arrayLiteral", "indexOperator", 
			"indexedIdentifier", "returnSignature", "gateModifier", "scalarType", 
			"qubitType", "arrayType", "arrayReferenceType", "designator", "defcalTarget", 
			"defcalArgumentDefinition", "defcalOperand", "gateOperand", "externArgument", 
			"argumentDefinition", "argumentDefinitionList", "defcalArgumentDefinitionList", 
			"defcalOperandList", "expressionList", "identifierList", "gateOperandList", 
			"externArgumentList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'OPENQASM'", "'include'", "'defcalgrammar'", "'def'", "'cal'", 
			"'defcal'", "'gate'", "'extern'", "'box'", "'let'", "'break'", "'continue'", 
			"'if'", "'else'", "'end'", "'return'", "'for'", "'while'", "'in'", "'switch'", 
			"'case'", "'default'", null, null, "'input'", "'output'", "'const'", 
			"'readonly'", "'mutable'", "'qreg'", "'qubit'", "'creg'", "'bool'", "'bit'", 
			"'int'", "'uint'", "'float'", "'angle'", "'complex'", "'array'", "'void'", 
			"'duration'", "'stretch'", "'gphase'", "'inv'", "'pow'", "'ctrl'", "'negctrl'", 
			"'#dim'", "'durationof'", "'delay'", "'reset'", "'measure'", "'barrier'", 
			null, "'['", "']'", "'{'", "'}'", "'('", "')'", "':'", "';'", "'.'", 
			"','", "'='", "'->'", "'+'", "'++'", "'-'", "'*'", "'**'", "'/'", "'%'", 
			"'|'", "'||'", "'&'", "'&&'", "'^'", "'@'", "'~'", "'!'", null, null, 
			null, null, "'im'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "OPENQASM", "INCLUDE", "DEFCALGRAMMAR", "DEF", "CAL", "DEFCAL", 
			"GATE", "EXTERN", "BOX", "LET", "BREAK", "CONTINUE", "IF", "ELSE", "END", 
			"RETURN", "FOR", "WHILE", "IN", "SWITCH", "CASE", "DEFAULT", "PRAGMA", 
			"AnnotationKeyword", "INPUT", "OUTPUT", "CONST", "READONLY", "MUTABLE", 
			"QREG", "QUBIT", "CREG", "BOOL", "BIT", "INT", "UINT", "FLOAT", "ANGLE", 
			"COMPLEX", "ARRAY", "VOID", "DURATION", "STRETCH", "GPHASE", "INV", "POW", 
			"CTRL", "NEGCTRL", "DIM", "DURATIONOF", "DELAY", "RESET", "MEASURE", 
			"BARRIER", "BooleanLiteral", "LBRACKET", "RBRACKET", "LBRACE", "RBRACE", 
			"LPAREN", "RPAREN", "COLON", "SEMICOLON", "DOT", "COMMA", "EQUALS", "ARROW", 
			"PLUS", "DOUBLE_PLUS", "MINUS", "ASTERISK", "DOUBLE_ASTERISK", "SLASH", 
			"PERCENT", "PIPE", "DOUBLE_PIPE", "AMPERSAND", "DOUBLE_AMPERSAND", "CARET", 
			"AT", "TILDE", "EXCLAMATION_POINT", "EqualityOperator", "CompoundAssignmentOperator", 
			"ComparisonOperator", "BitshiftOperator", "IMAG", "ImaginaryLiteral", 
			"BinaryIntegerLiteral", "OctalIntegerLiteral", "DecimalIntegerLiteral", 
			"HexIntegerLiteral", "Identifier", "HardwareQubit", "FloatLiteral", "TimingLiteral", 
			"BitstringLiteral", "Whitespace", "Newline", "LineComment", "BlockComment", 
			"VERSION_IDENTIFER_WHITESPACE", "VersionSpecifier", "ARBITRARY_STRING_WHITESPACE", 
			"StringLiteral", "EAT_INITIAL_SPACE", "EAT_LINE_END", "RemainingLineContent", 
			"CAL_PRELUDE_WHITESPACE", "CAL_PRELUDE_COMMENT", "DEFCAL_PRELUDE_WHITESPACE", 
			"DEFCAL_PRELUDE_COMMENT", "CalibrationBlock"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "qasm3Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public qasm3Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(qasm3Parser.EOF, 0); }
		public VersionContext version() {
			return getRuleContext(VersionContext.class,0);
		}
		public List<StatementOrScopeContext> statementOrScope() {
			return getRuleContexts(StatementOrScopeContext.class);
		}
		public StatementOrScopeContext statementOrScope(int i) {
			return getRuleContext(StatementOrScopeContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPENQASM) {
				{
				setState(132);
				version();
				}
			}

			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1512644325007671292L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 268179457L) != 0)) {
				{
				{
				setState(135);
				statementOrScope();
				}
				}
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(141);
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

	@SuppressWarnings("CheckReturnValue")
	public static class VersionContext extends ParserRuleContext {
		public TerminalNode OPENQASM() { return getToken(qasm3Parser.OPENQASM, 0); }
		public TerminalNode VersionSpecifier() { return getToken(qasm3Parser.VersionSpecifier, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public VersionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_version; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitVersion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionContext version() throws RecognitionException {
		VersionContext _localctx = new VersionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_version);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(OPENQASM);
			setState(144);
			match(VersionSpecifier);
			setState(145);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public PragmaContext pragma() {
			return getRuleContext(PragmaContext.class,0);
		}
		public AliasDeclarationStatementContext aliasDeclarationStatement() {
			return getRuleContext(AliasDeclarationStatementContext.class,0);
		}
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public BarrierStatementContext barrierStatement() {
			return getRuleContext(BarrierStatementContext.class,0);
		}
		public BoxStatementContext boxStatement() {
			return getRuleContext(BoxStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public CalStatementContext calStatement() {
			return getRuleContext(CalStatementContext.class,0);
		}
		public CalibrationGrammarStatementContext calibrationGrammarStatement() {
			return getRuleContext(CalibrationGrammarStatementContext.class,0);
		}
		public ClassicalDeclarationStatementContext classicalDeclarationStatement() {
			return getRuleContext(ClassicalDeclarationStatementContext.class,0);
		}
		public ConstDeclarationStatementContext constDeclarationStatement() {
			return getRuleContext(ConstDeclarationStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public DefStatementContext defStatement() {
			return getRuleContext(DefStatementContext.class,0);
		}
		public DefcalStatementContext defcalStatement() {
			return getRuleContext(DefcalStatementContext.class,0);
		}
		public DelayStatementContext delayStatement() {
			return getRuleContext(DelayStatementContext.class,0);
		}
		public EndStatementContext endStatement() {
			return getRuleContext(EndStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public ExternStatementContext externStatement() {
			return getRuleContext(ExternStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public GateCallStatementContext gateCallStatement() {
			return getRuleContext(GateCallStatementContext.class,0);
		}
		public GateStatementContext gateStatement() {
			return getRuleContext(GateStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public IncludeStatementContext includeStatement() {
			return getRuleContext(IncludeStatementContext.class,0);
		}
		public IoDeclarationStatementContext ioDeclarationStatement() {
			return getRuleContext(IoDeclarationStatementContext.class,0);
		}
		public MeasureArrowAssignmentStatementContext measureArrowAssignmentStatement() {
			return getRuleContext(MeasureArrowAssignmentStatementContext.class,0);
		}
		public OldStyleDeclarationStatementContext oldStyleDeclarationStatement() {
			return getRuleContext(OldStyleDeclarationStatementContext.class,0);
		}
		public QuantumDeclarationStatementContext quantumDeclarationStatement() {
			return getRuleContext(QuantumDeclarationStatementContext.class,0);
		}
		public ResetStatementContext resetStatement() {
			return getRuleContext(ResetStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		int _la;
		try {
			setState(185);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRAGMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				pragma();
				}
				break;
			case INCLUDE:
			case DEFCALGRAMMAR:
			case DEF:
			case CAL:
			case DEFCAL:
			case GATE:
			case EXTERN:
			case BOX:
			case LET:
			case BREAK:
			case CONTINUE:
			case IF:
			case END:
			case RETURN:
			case FOR:
			case WHILE:
			case SWITCH:
			case AnnotationKeyword:
			case INPUT:
			case OUTPUT:
			case CONST:
			case QREG:
			case QUBIT:
			case CREG:
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case ARRAY:
			case DURATION:
			case STRETCH:
			case GPHASE:
			case INV:
			case POW:
			case CTRL:
			case NEGCTRL:
			case DURATIONOF:
			case DELAY:
			case RESET:
			case MEASURE:
			case BARRIER:
			case BooleanLiteral:
			case LPAREN:
			case MINUS:
			case TILDE:
			case EXCLAMATION_POINT:
			case ImaginaryLiteral:
			case BinaryIntegerLiteral:
			case OctalIntegerLiteral:
			case DecimalIntegerLiteral:
			case HexIntegerLiteral:
			case Identifier:
			case HardwareQubit:
			case FloatLiteral:
			case TimingLiteral:
			case BitstringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AnnotationKeyword) {
					{
					{
					setState(148);
					annotation();
					}
					}
					setState(153);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(183);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(154);
					aliasDeclarationStatement();
					}
					break;
				case 2:
					{
					setState(155);
					assignmentStatement();
					}
					break;
				case 3:
					{
					setState(156);
					barrierStatement();
					}
					break;
				case 4:
					{
					setState(157);
					boxStatement();
					}
					break;
				case 5:
					{
					setState(158);
					breakStatement();
					}
					break;
				case 6:
					{
					setState(159);
					calStatement();
					}
					break;
				case 7:
					{
					setState(160);
					calibrationGrammarStatement();
					}
					break;
				case 8:
					{
					setState(161);
					classicalDeclarationStatement();
					}
					break;
				case 9:
					{
					setState(162);
					constDeclarationStatement();
					}
					break;
				case 10:
					{
					setState(163);
					continueStatement();
					}
					break;
				case 11:
					{
					setState(164);
					defStatement();
					}
					break;
				case 12:
					{
					setState(165);
					defcalStatement();
					}
					break;
				case 13:
					{
					setState(166);
					delayStatement();
					}
					break;
				case 14:
					{
					setState(167);
					endStatement();
					}
					break;
				case 15:
					{
					setState(168);
					expressionStatement();
					}
					break;
				case 16:
					{
					setState(169);
					externStatement();
					}
					break;
				case 17:
					{
					setState(170);
					forStatement();
					}
					break;
				case 18:
					{
					setState(171);
					gateCallStatement();
					}
					break;
				case 19:
					{
					setState(172);
					gateStatement();
					}
					break;
				case 20:
					{
					setState(173);
					ifStatement();
					}
					break;
				case 21:
					{
					setState(174);
					includeStatement();
					}
					break;
				case 22:
					{
					setState(175);
					ioDeclarationStatement();
					}
					break;
				case 23:
					{
					setState(176);
					measureArrowAssignmentStatement();
					}
					break;
				case 24:
					{
					setState(177);
					oldStyleDeclarationStatement();
					}
					break;
				case 25:
					{
					setState(178);
					quantumDeclarationStatement();
					}
					break;
				case 26:
					{
					setState(179);
					resetStatement();
					}
					break;
				case 27:
					{
					setState(180);
					returnStatement();
					}
					break;
				case 28:
					{
					setState(181);
					switchStatement();
					}
					break;
				case 29:
					{
					setState(182);
					whileStatement();
					}
					break;
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode AnnotationKeyword() { return getToken(qasm3Parser.AnnotationKeyword, 0); }
		public TerminalNode RemainingLineContent() { return getToken(qasm3Parser.RemainingLineContent, 0); }
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(AnnotationKeyword);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RemainingLineContent) {
				{
				setState(188);
				match(RemainingLineContent);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class ScopeContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(qasm3Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(qasm3Parser.RBRACE, 0); }
		public List<StatementOrScopeContext> statementOrScope() {
			return getRuleContexts(StatementOrScopeContext.class);
		}
		public StatementOrScopeContext statementOrScope(int i) {
			return getRuleContext(StatementOrScopeContext.class,i);
		}
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(LBRACE);
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1512644325007671292L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 268179457L) != 0)) {
				{
				{
				setState(192);
				statementOrScope();
				}
				}
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(198);
			match(RBRACE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PragmaContext extends ParserRuleContext {
		public TerminalNode PRAGMA() { return getToken(qasm3Parser.PRAGMA, 0); }
		public TerminalNode RemainingLineContent() { return getToken(qasm3Parser.RemainingLineContent, 0); }
		public PragmaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pragma; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitPragma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PragmaContext pragma() throws RecognitionException {
		PragmaContext _localctx = new PragmaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_pragma);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(PRAGMA);
			setState(201);
			match(RemainingLineContent);
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementOrScopeContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public StatementOrScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementOrScope; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitStatementOrScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementOrScopeContext statementOrScope() throws RecognitionException {
		StatementOrScopeContext _localctx = new StatementOrScopeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statementOrScope);
		try {
			setState(205);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INCLUDE:
			case DEFCALGRAMMAR:
			case DEF:
			case CAL:
			case DEFCAL:
			case GATE:
			case EXTERN:
			case BOX:
			case LET:
			case BREAK:
			case CONTINUE:
			case IF:
			case END:
			case RETURN:
			case FOR:
			case WHILE:
			case SWITCH:
			case PRAGMA:
			case AnnotationKeyword:
			case INPUT:
			case OUTPUT:
			case CONST:
			case QREG:
			case QUBIT:
			case CREG:
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case ARRAY:
			case DURATION:
			case STRETCH:
			case GPHASE:
			case INV:
			case POW:
			case CTRL:
			case NEGCTRL:
			case DURATIONOF:
			case DELAY:
			case RESET:
			case MEASURE:
			case BARRIER:
			case BooleanLiteral:
			case LPAREN:
			case MINUS:
			case TILDE:
			case EXCLAMATION_POINT:
			case ImaginaryLiteral:
			case BinaryIntegerLiteral:
			case OctalIntegerLiteral:
			case DecimalIntegerLiteral:
			case HexIntegerLiteral:
			case Identifier:
			case HardwareQubit:
			case FloatLiteral:
			case TimingLiteral:
			case BitstringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(203);
				statement();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(204);
				scope();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CalibrationGrammarStatementContext extends ParserRuleContext {
		public TerminalNode DEFCALGRAMMAR() { return getToken(qasm3Parser.DEFCALGRAMMAR, 0); }
		public TerminalNode StringLiteral() { return getToken(qasm3Parser.StringLiteral, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public CalibrationGrammarStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calibrationGrammarStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitCalibrationGrammarStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalibrationGrammarStatementContext calibrationGrammarStatement() throws RecognitionException {
		CalibrationGrammarStatementContext _localctx = new CalibrationGrammarStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_calibrationGrammarStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(DEFCALGRAMMAR);
			setState(208);
			match(StringLiteral);
			setState(209);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IncludeStatementContext extends ParserRuleContext {
		public TerminalNode INCLUDE() { return getToken(qasm3Parser.INCLUDE, 0); }
		public TerminalNode StringLiteral() { return getToken(qasm3Parser.StringLiteral, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public IncludeStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_includeStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitIncludeStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IncludeStatementContext includeStatement() throws RecognitionException {
		IncludeStatementContext _localctx = new IncludeStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_includeStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(INCLUDE);
			setState(212);
			match(StringLiteral);
			setState(213);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(qasm3Parser.BREAK, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(BREAK);
			setState(216);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(qasm3Parser.CONTINUE, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(CONTINUE);
			setState(219);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class EndStatementContext extends ParserRuleContext {
		public TerminalNode END() { return getToken(qasm3Parser.END, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public EndStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitEndStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndStatementContext endStatement() throws RecognitionException {
		EndStatementContext _localctx = new EndStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_endStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(END);
			setState(222);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ForStatementContext extends ParserRuleContext {
		public StatementOrScopeContext body;
		public TerminalNode FOR() { return getToken(qasm3Parser.FOR, 0); }
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public TerminalNode IN() { return getToken(qasm3Parser.IN, 0); }
		public StatementOrScopeContext statementOrScope() {
			return getRuleContext(StatementOrScopeContext.class,0);
		}
		public SetExpressionContext setExpression() {
			return getRuleContext(SetExpressionContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(qasm3Parser.LBRACKET, 0); }
		public RangeExpressionContext rangeExpression() {
			return getRuleContext(RangeExpressionContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(qasm3Parser.RBRACKET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(FOR);
			setState(225);
			scalarType();
			setState(226);
			match(Identifier);
			setState(227);
			match(IN);
			setState(234);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(228);
				setExpression();
				}
				break;
			case LBRACKET:
				{
				setState(229);
				match(LBRACKET);
				setState(230);
				rangeExpression();
				setState(231);
				match(RBRACKET);
				}
				break;
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case ARRAY:
			case DURATION:
			case STRETCH:
			case DURATIONOF:
			case BooleanLiteral:
			case LPAREN:
			case MINUS:
			case TILDE:
			case EXCLAMATION_POINT:
			case ImaginaryLiteral:
			case BinaryIntegerLiteral:
			case OctalIntegerLiteral:
			case DecimalIntegerLiteral:
			case HexIntegerLiteral:
			case Identifier:
			case HardwareQubit:
			case FloatLiteral:
			case TimingLiteral:
			case BitstringLiteral:
				{
				setState(233);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(236);
			((ForStatementContext)_localctx).body = statementOrScope();
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

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public StatementOrScopeContext if_body;
		public StatementOrScopeContext else_body;
		public TerminalNode IF() { return getToken(qasm3Parser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public List<StatementOrScopeContext> statementOrScope() {
			return getRuleContexts(StatementOrScopeContext.class);
		}
		public StatementOrScopeContext statementOrScope(int i) {
			return getRuleContext(StatementOrScopeContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(qasm3Parser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(IF);
			setState(239);
			match(LPAREN);
			setState(240);
			expression(0);
			setState(241);
			match(RPAREN);
			setState(242);
			((IfStatementContext)_localctx).if_body = statementOrScope();
			setState(245);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(243);
				match(ELSE);
				setState(244);
				((IfStatementContext)_localctx).else_body = statementOrScope();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(qasm3Parser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MeasureExpressionContext measureExpression() {
			return getRuleContext(MeasureExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(RETURN);
			setState(250);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case ARRAY:
			case DURATION:
			case STRETCH:
			case DURATIONOF:
			case BooleanLiteral:
			case LPAREN:
			case MINUS:
			case TILDE:
			case EXCLAMATION_POINT:
			case ImaginaryLiteral:
			case BinaryIntegerLiteral:
			case OctalIntegerLiteral:
			case DecimalIntegerLiteral:
			case HexIntegerLiteral:
			case Identifier:
			case HardwareQubit:
			case FloatLiteral:
			case TimingLiteral:
			case BitstringLiteral:
				{
				setState(248);
				expression(0);
				}
				break;
			case MEASURE:
				{
				setState(249);
				measureExpression();
				}
				break;
			case SEMICOLON:
				break;
			default:
				break;
			}
			setState(252);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends ParserRuleContext {
		public StatementOrScopeContext body;
		public TerminalNode WHILE() { return getToken(qasm3Parser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public StatementOrScopeContext statementOrScope() {
			return getRuleContext(StatementOrScopeContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(WHILE);
			setState(255);
			match(LPAREN);
			setState(256);
			expression(0);
			setState(257);
			match(RPAREN);
			setState(258);
			((WhileStatementContext)_localctx).body = statementOrScope();
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

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchStatementContext extends ParserRuleContext {
		public TerminalNode SWITCH() { return getToken(qasm3Parser.SWITCH, 0); }
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(qasm3Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(qasm3Parser.RBRACE, 0); }
		public List<SwitchCaseItemContext> switchCaseItem() {
			return getRuleContexts(SwitchCaseItemContext.class);
		}
		public SwitchCaseItemContext switchCaseItem(int i) {
			return getRuleContext(SwitchCaseItemContext.class,i);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitSwitchStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_switchStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(SWITCH);
			setState(261);
			match(LPAREN);
			setState(262);
			expression(0);
			setState(263);
			match(RPAREN);
			setState(264);
			match(LBRACE);
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE || _la==DEFAULT) {
				{
				{
				setState(265);
				switchCaseItem();
				}
				}
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(271);
			match(RBRACE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchCaseItemContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(qasm3Parser.CASE, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(qasm3Parser.DEFAULT, 0); }
		public SwitchCaseItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchCaseItem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitSwitchCaseItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchCaseItemContext switchCaseItem() throws RecognitionException {
		SwitchCaseItemContext _localctx = new SwitchCaseItemContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_switchCaseItem);
		try {
			setState(279);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(273);
				match(CASE);
				setState(274);
				expressionList();
				setState(275);
				scope();
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(277);
				match(DEFAULT);
				setState(278);
				scope();
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

	@SuppressWarnings("CheckReturnValue")
	public static class BarrierStatementContext extends ParserRuleContext {
		public TerminalNode BARRIER() { return getToken(qasm3Parser.BARRIER, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public GateOperandListContext gateOperandList() {
			return getRuleContext(GateOperandListContext.class,0);
		}
		public BarrierStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_barrierStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitBarrierStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BarrierStatementContext barrierStatement() throws RecognitionException {
		BarrierStatementContext _localctx = new BarrierStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_barrierStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			match(BARRIER);
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier || _la==HardwareQubit) {
				{
				setState(282);
				gateOperandList();
				}
			}

			setState(285);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BoxStatementContext extends ParserRuleContext {
		public TerminalNode BOX() { return getToken(qasm3Parser.BOX, 0); }
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public BoxStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boxStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitBoxStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoxStatementContext boxStatement() throws RecognitionException {
		BoxStatementContext _localctx = new BoxStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_boxStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			match(BOX);
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(288);
				designator();
				}
			}

			setState(291);
			scope();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DelayStatementContext extends ParserRuleContext {
		public TerminalNode DELAY() { return getToken(qasm3Parser.DELAY, 0); }
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public GateOperandListContext gateOperandList() {
			return getRuleContext(GateOperandListContext.class,0);
		}
		public DelayStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delayStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitDelayStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DelayStatementContext delayStatement() throws RecognitionException {
		DelayStatementContext _localctx = new DelayStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_delayStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(DELAY);
			setState(294);
			designator();
			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier || _la==HardwareQubit) {
				{
				setState(295);
				gateOperandList();
				}
			}

			setState(298);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class GateCallStatementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public GateOperandListContext gateOperandList() {
			return getRuleContext(GateOperandListContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public List<GateModifierContext> gateModifier() {
			return getRuleContexts(GateModifierContext.class);
		}
		public GateModifierContext gateModifier(int i) {
			return getRuleContext(GateModifierContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode GPHASE() { return getToken(qasm3Parser.GPHASE, 0); }
		public GateCallStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gateCallStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitGateCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GateCallStatementContext gateCallStatement() throws RecognitionException {
		GateCallStatementContext _localctx = new GateCallStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_gateCallStatement);
		int _la;
		try {
			setState(341);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 527765581332480L) != 0)) {
					{
					{
					setState(300);
					gateModifier();
					}
					}
					setState(305);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(306);
				match(Identifier);
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(307);
					match(LPAREN);
					setState(309);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1190091586105507840L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 268179457L) != 0)) {
						{
						setState(308);
						expressionList();
						}
					}

					setState(311);
					match(RPAREN);
					}
				}

				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(314);
					designator();
					}
				}

				setState(317);
				gateOperandList();
				setState(318);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 527765581332480L) != 0)) {
					{
					{
					setState(320);
					gateModifier();
					}
					}
					setState(325);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(326);
				match(GPHASE);
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(327);
					match(LPAREN);
					setState(329);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1190091586105507840L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 268179457L) != 0)) {
						{
						setState(328);
						expressionList();
						}
					}

					setState(331);
					match(RPAREN);
					}
				}

				setState(335);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(334);
					designator();
					}
				}

				setState(338);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier || _la==HardwareQubit) {
					{
					setState(337);
					gateOperandList();
					}
				}

				setState(340);
				match(SEMICOLON);
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class MeasureArrowAssignmentStatementContext extends ParserRuleContext {
		public MeasureExpressionContext measureExpression() {
			return getRuleContext(MeasureExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public TerminalNode ARROW() { return getToken(qasm3Parser.ARROW, 0); }
		public IndexedIdentifierContext indexedIdentifier() {
			return getRuleContext(IndexedIdentifierContext.class,0);
		}
		public MeasureArrowAssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_measureArrowAssignmentStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitMeasureArrowAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MeasureArrowAssignmentStatementContext measureArrowAssignmentStatement() throws RecognitionException {
		MeasureArrowAssignmentStatementContext _localctx = new MeasureArrowAssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_measureArrowAssignmentStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			measureExpression();
			setState(346);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(344);
				match(ARROW);
				setState(345);
				indexedIdentifier();
				}
			}

			setState(348);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ResetStatementContext extends ParserRuleContext {
		public TerminalNode RESET() { return getToken(qasm3Parser.RESET, 0); }
		public GateOperandContext gateOperand() {
			return getRuleContext(GateOperandContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public ResetStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resetStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitResetStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResetStatementContext resetStatement() throws RecognitionException {
		ResetStatementContext _localctx = new ResetStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_resetStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(RESET);
			setState(351);
			gateOperand();
			setState(352);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AliasDeclarationStatementContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(qasm3Parser.LET, 0); }
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public TerminalNode EQUALS() { return getToken(qasm3Parser.EQUALS, 0); }
		public AliasExpressionContext aliasExpression() {
			return getRuleContext(AliasExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public AliasDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasDeclarationStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitAliasDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasDeclarationStatementContext aliasDeclarationStatement() throws RecognitionException {
		AliasDeclarationStatementContext _localctx = new AliasDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_aliasDeclarationStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			match(LET);
			setState(355);
			match(Identifier);
			setState(356);
			match(EQUALS);
			setState(357);
			aliasExpression();
			setState(358);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ClassicalDeclarationStatementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(qasm3Parser.EQUALS, 0); }
		public DeclarationExpressionContext declarationExpression() {
			return getRuleContext(DeclarationExpressionContext.class,0);
		}
		public ClassicalDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classicalDeclarationStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitClassicalDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassicalDeclarationStatementContext classicalDeclarationStatement() throws RecognitionException {
		ClassicalDeclarationStatementContext _localctx = new ClassicalDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_classicalDeclarationStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case DURATION:
			case STRETCH:
				{
				setState(360);
				scalarType();
				}
				break;
			case ARRAY:
				{
				setState(361);
				arrayType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(364);
			match(Identifier);
			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(365);
				match(EQUALS);
				setState(366);
				declarationExpression();
				}
			}

			setState(369);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConstDeclarationStatementContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(qasm3Parser.CONST, 0); }
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public TerminalNode EQUALS() { return getToken(qasm3Parser.EQUALS, 0); }
		public DeclarationExpressionContext declarationExpression() {
			return getRuleContext(DeclarationExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public ConstDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDeclarationStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitConstDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstDeclarationStatementContext constDeclarationStatement() throws RecognitionException {
		ConstDeclarationStatementContext _localctx = new ConstDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constDeclarationStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			match(CONST);
			setState(372);
			scalarType();
			setState(373);
			match(Identifier);
			setState(374);
			match(EQUALS);
			setState(375);
			declarationExpression();
			setState(376);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IoDeclarationStatementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public TerminalNode INPUT() { return getToken(qasm3Parser.INPUT, 0); }
		public TerminalNode OUTPUT() { return getToken(qasm3Parser.OUTPUT, 0); }
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public IoDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ioDeclarationStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitIoDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IoDeclarationStatementContext ioDeclarationStatement() throws RecognitionException {
		IoDeclarationStatementContext _localctx = new IoDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_ioDeclarationStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			_la = _input.LA(1);
			if ( !(_la==INPUT || _la==OUTPUT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(381);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case DURATION:
			case STRETCH:
				{
				setState(379);
				scalarType();
				}
				break;
			case ARRAY:
				{
				setState(380);
				arrayType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(383);
			match(Identifier);
			setState(384);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class OldStyleDeclarationStatementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public TerminalNode CREG() { return getToken(qasm3Parser.CREG, 0); }
		public TerminalNode QREG() { return getToken(qasm3Parser.QREG, 0); }
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public OldStyleDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oldStyleDeclarationStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitOldStyleDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OldStyleDeclarationStatementContext oldStyleDeclarationStatement() throws RecognitionException {
		OldStyleDeclarationStatementContext _localctx = new OldStyleDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_oldStyleDeclarationStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			_la = _input.LA(1);
			if ( !(_la==QREG || _la==CREG) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(387);
			match(Identifier);
			setState(389);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(388);
				designator();
				}
			}

			setState(391);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class QuantumDeclarationStatementContext extends ParserRuleContext {
		public QubitTypeContext qubitType() {
			return getRuleContext(QubitTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public QuantumDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantumDeclarationStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitQuantumDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuantumDeclarationStatementContext quantumDeclarationStatement() throws RecognitionException {
		QuantumDeclarationStatementContext _localctx = new QuantumDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_quantumDeclarationStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			qubitType();
			setState(394);
			match(Identifier);
			setState(395);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefStatementContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(qasm3Parser.DEF, 0); }
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public ArgumentDefinitionListContext argumentDefinitionList() {
			return getRuleContext(ArgumentDefinitionListContext.class,0);
		}
		public ReturnSignatureContext returnSignature() {
			return getRuleContext(ReturnSignatureContext.class,0);
		}
		public DefStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitDefStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefStatementContext defStatement() throws RecognitionException {
		DefStatementContext _localctx = new DefStatementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_defStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			match(DEF);
			setState(398);
			match(Identifier);
			setState(399);
			match(LPAREN);
			setState(401);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 14293382725632L) != 0)) {
				{
				setState(400);
				argumentDefinitionList();
				}
			}

			setState(403);
			match(RPAREN);
			setState(405);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(404);
				returnSignature();
				}
			}

			setState(407);
			scope();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExternStatementContext extends ParserRuleContext {
		public TerminalNode EXTERN() { return getToken(qasm3Parser.EXTERN, 0); }
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public ExternArgumentListContext externArgumentList() {
			return getRuleContext(ExternArgumentListContext.class,0);
		}
		public ReturnSignatureContext returnSignature() {
			return getRuleContext(ReturnSignatureContext.class,0);
		}
		public ExternStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_externStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitExternStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExternStatementContext externStatement() throws RecognitionException {
		ExternStatementContext _localctx = new ExternStatementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_externStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409);
			match(EXTERN);
			setState(410);
			match(Identifier);
			setState(411);
			match(LPAREN);
			setState(413);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 14290161500160L) != 0)) {
				{
				setState(412);
				externArgumentList();
				}
			}

			setState(415);
			match(RPAREN);
			setState(417);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(416);
				returnSignature();
				}
			}

			setState(419);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class GateStatementContext extends ParserRuleContext {
		public IdentifierListContext params;
		public IdentifierListContext qubits;
		public TerminalNode GATE() { return getToken(qasm3Parser.GATE, 0); }
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public List<IdentifierListContext> identifierList() {
			return getRuleContexts(IdentifierListContext.class);
		}
		public IdentifierListContext identifierList(int i) {
			return getRuleContext(IdentifierListContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public GateStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gateStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitGateStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GateStatementContext gateStatement() throws RecognitionException {
		GateStatementContext _localctx = new GateStatementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_gateStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			match(GATE);
			setState(422);
			match(Identifier);
			setState(428);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(423);
				match(LPAREN);
				setState(425);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(424);
					((GateStatementContext)_localctx).params = identifierList();
					}
				}

				setState(427);
				match(RPAREN);
				}
			}

			setState(430);
			((GateStatementContext)_localctx).qubits = identifierList();
			setState(431);
			scope();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentStatementContext extends ParserRuleContext {
		public Token op;
		public IndexedIdentifierContext indexedIdentifier() {
			return getRuleContext(IndexedIdentifierContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public TerminalNode EQUALS() { return getToken(qasm3Parser.EQUALS, 0); }
		public TerminalNode CompoundAssignmentOperator() { return getToken(qasm3Parser.CompoundAssignmentOperator, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MeasureExpressionContext measureExpression() {
			return getRuleContext(MeasureExpressionContext.class,0);
		}
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_assignmentStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			indexedIdentifier();
			setState(434);
			((AssignmentStatementContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==EQUALS || _la==CompoundAssignmentOperator) ) {
				((AssignmentStatementContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(437);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case ARRAY:
			case DURATION:
			case STRETCH:
			case DURATIONOF:
			case BooleanLiteral:
			case LPAREN:
			case MINUS:
			case TILDE:
			case EXCLAMATION_POINT:
			case ImaginaryLiteral:
			case BinaryIntegerLiteral:
			case OctalIntegerLiteral:
			case DecimalIntegerLiteral:
			case HexIntegerLiteral:
			case Identifier:
			case HardwareQubit:
			case FloatLiteral:
			case TimingLiteral:
			case BitstringLiteral:
				{
				setState(435);
				expression(0);
				}
				break;
			case MEASURE:
				{
				setState(436);
				measureExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(439);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(qasm3Parser.SEMICOLON, 0); }
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			expression(0);
			setState(442);
			match(SEMICOLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CalStatementContext extends ParserRuleContext {
		public TerminalNode CAL() { return getToken(qasm3Parser.CAL, 0); }
		public TerminalNode LBRACE() { return getToken(qasm3Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(qasm3Parser.RBRACE, 0); }
		public TerminalNode CalibrationBlock() { return getToken(qasm3Parser.CalibrationBlock, 0); }
		public CalStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitCalStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalStatementContext calStatement() throws RecognitionException {
		CalStatementContext _localctx = new CalStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_calStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			match(CAL);
			setState(445);
			match(LBRACE);
			setState(447);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CalibrationBlock) {
				{
				setState(446);
				match(CalibrationBlock);
				}
			}

			setState(449);
			match(RBRACE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefcalStatementContext extends ParserRuleContext {
		public TerminalNode DEFCAL() { return getToken(qasm3Parser.DEFCAL, 0); }
		public DefcalTargetContext defcalTarget() {
			return getRuleContext(DefcalTargetContext.class,0);
		}
		public DefcalOperandListContext defcalOperandList() {
			return getRuleContext(DefcalOperandListContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(qasm3Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(qasm3Parser.RBRACE, 0); }
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public ReturnSignatureContext returnSignature() {
			return getRuleContext(ReturnSignatureContext.class,0);
		}
		public TerminalNode CalibrationBlock() { return getToken(qasm3Parser.CalibrationBlock, 0); }
		public DefcalArgumentDefinitionListContext defcalArgumentDefinitionList() {
			return getRuleContext(DefcalArgumentDefinitionListContext.class,0);
		}
		public DefcalStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defcalStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitDefcalStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefcalStatementContext defcalStatement() throws RecognitionException {
		DefcalStatementContext _localctx = new DefcalStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_defcalStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			match(DEFCAL);
			setState(452);
			defcalTarget();
			setState(458);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(453);
				match(LPAREN);
				setState(455);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1190091594427006976L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 268179457L) != 0)) {
					{
					setState(454);
					defcalArgumentDefinitionList();
					}
				}

				setState(457);
				match(RPAREN);
				}
			}

			setState(460);
			defcalOperandList();
			setState(462);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(461);
				returnSignature();
				}
			}

			setState(464);
			match(LBRACE);
			setState(466);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CalibrationBlock) {
				{
				setState(465);
				match(CalibrationBlock);
				}
			}

			setState(468);
			match(RBRACE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BitwiseXorExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode CARET() { return getToken(qasm3Parser.CARET, 0); }
		public BitwiseXorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitBitwiseXorExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(qasm3Parser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(qasm3Parser.MINUS, 0); }
		public AdditiveExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DurationofExpressionContext extends ExpressionContext {
		public TerminalNode DURATIONOF() { return getToken(qasm3Parser.DURATIONOF, 0); }
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public DurationofExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitDurationofExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesisExpressionContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public ParenthesisExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitParenthesisExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ComparisonOperator() { return getToken(qasm3Parser.ComparisonOperator, 0); }
		public ComparisonExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitComparisonExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ASTERISK() { return getToken(qasm3Parser.ASTERISK, 0); }
		public TerminalNode SLASH() { return getToken(qasm3Parser.SLASH, 0); }
		public TerminalNode PERCENT() { return getToken(qasm3Parser.PERCENT, 0); }
		public MultiplicativeExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOrExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode DOUBLE_PIPE() { return getToken(qasm3Parser.DOUBLE_PIPE, 0); }
		public LogicalOrExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitLogicalOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CastExpressionContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public CastExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitCastExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PowerExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode DOUBLE_ASTERISK() { return getToken(qasm3Parser.DOUBLE_ASTERISK, 0); }
		public PowerExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitPowerExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BitwiseOrExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PIPE() { return getToken(qasm3Parser.PIPE, 0); }
		public BitwiseOrExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitBitwiseOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallExpressionContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public CallExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BitshiftExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BitshiftOperator() { return getToken(qasm3Parser.BitshiftOperator, 0); }
		public BitshiftExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitBitshiftExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BitwiseAndExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AMPERSAND() { return getToken(qasm3Parser.AMPERSAND, 0); }
		public BitwiseAndExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitBitwiseAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EqualityOperator() { return getToken(qasm3Parser.EqualityOperator, 0); }
		public EqualityExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitEqualityExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalAndExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode DOUBLE_AMPERSAND() { return getToken(qasm3Parser.DOUBLE_AMPERSAND, 0); }
		public LogicalAndExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitLogicalAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IndexExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IndexOperatorContext indexOperator() {
			return getRuleContext(IndexOperatorContext.class,0);
		}
		public IndexExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitIndexExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExpressionContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode TILDE() { return getToken(qasm3Parser.TILDE, 0); }
		public TerminalNode EXCLAMATION_POINT() { return getToken(qasm3Parser.EXCLAMATION_POINT, 0); }
		public TerminalNode MINUS() { return getToken(qasm3Parser.MINUS, 0); }
		public UnaryExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralExpressionContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public TerminalNode BinaryIntegerLiteral() { return getToken(qasm3Parser.BinaryIntegerLiteral, 0); }
		public TerminalNode OctalIntegerLiteral() { return getToken(qasm3Parser.OctalIntegerLiteral, 0); }
		public TerminalNode DecimalIntegerLiteral() { return getToken(qasm3Parser.DecimalIntegerLiteral, 0); }
		public TerminalNode HexIntegerLiteral() { return getToken(qasm3Parser.HexIntegerLiteral, 0); }
		public TerminalNode FloatLiteral() { return getToken(qasm3Parser.FloatLiteral, 0); }
		public TerminalNode ImaginaryLiteral() { return getToken(qasm3Parser.ImaginaryLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(qasm3Parser.BooleanLiteral, 0); }
		public TerminalNode BitstringLiteral() { return getToken(qasm3Parser.BitstringLiteral, 0); }
		public TerminalNode TimingLiteral() { return getToken(qasm3Parser.TimingLiteral, 0); }
		public TerminalNode HardwareQubit() { return getToken(qasm3Parser.HardwareQubit, 0); }
		public LiteralExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 74;
		enterRecursionRule(_localctx, 74, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(497);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				_localctx = new ParenthesisExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(471);
				match(LPAREN);
				setState(472);
				expression(0);
				setState(473);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new UnaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(475);
				((UnaryExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 6145L) != 0)) ) {
					((UnaryExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(476);
				expression(15);
				}
				break;
			case 3:
				{
				_localctx = new CastExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(479);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BOOL:
				case BIT:
				case INT:
				case UINT:
				case FLOAT:
				case ANGLE:
				case COMPLEX:
				case DURATION:
				case STRETCH:
					{
					setState(477);
					scalarType();
					}
					break;
				case ARRAY:
					{
					setState(478);
					arrayType();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(481);
				match(LPAREN);
				setState(482);
				expression(0);
				setState(483);
				match(RPAREN);
				}
				break;
			case 4:
				{
				_localctx = new DurationofExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(485);
				match(DURATIONOF);
				setState(486);
				match(LPAREN);
				setState(487);
				scope();
				setState(488);
				match(RPAREN);
				}
				break;
			case 5:
				{
				_localctx = new CallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(490);
				match(Identifier);
				setState(491);
				match(LPAREN);
				setState(493);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1190091586105507840L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 268179457L) != 0)) {
					{
					setState(492);
					expressionList();
					}
				}

				setState(495);
				match(RPAREN);
				}
				break;
			case 6:
				{
				_localctx = new LiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(496);
				_la = _input.LA(1);
				if ( !(((((_la - 55)) & ~0x3f) == 0 && ((1L << (_la - 55)) & 8787503087617L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(536);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(534);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
					case 1:
						{
						_localctx = new PowerExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(499);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(500);
						((PowerExpressionContext)_localctx).op = match(DOUBLE_ASTERISK);
						setState(501);
						expression(16);
						}
						break;
					case 2:
						{
						_localctx = new MultiplicativeExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(502);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(503);
						((MultiplicativeExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & 13L) != 0)) ) {
							((MultiplicativeExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(504);
						expression(15);
						}
						break;
					case 3:
						{
						_localctx = new AdditiveExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(505);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(506);
						((AdditiveExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((AdditiveExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(507);
						expression(14);
						}
						break;
					case 4:
						{
						_localctx = new BitshiftExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(508);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(509);
						((BitshiftExpressionContext)_localctx).op = match(BitshiftOperator);
						setState(510);
						expression(13);
						}
						break;
					case 5:
						{
						_localctx = new ComparisonExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(511);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(512);
						((ComparisonExpressionContext)_localctx).op = match(ComparisonOperator);
						setState(513);
						expression(12);
						}
						break;
					case 6:
						{
						_localctx = new EqualityExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(514);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(515);
						((EqualityExpressionContext)_localctx).op = match(EqualityOperator);
						setState(516);
						expression(11);
						}
						break;
					case 7:
						{
						_localctx = new BitwiseAndExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(517);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(518);
						((BitwiseAndExpressionContext)_localctx).op = match(AMPERSAND);
						setState(519);
						expression(10);
						}
						break;
					case 8:
						{
						_localctx = new BitwiseXorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(520);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(521);
						((BitwiseXorExpressionContext)_localctx).op = match(CARET);
						setState(522);
						expression(9);
						}
						break;
					case 9:
						{
						_localctx = new BitwiseOrExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(523);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(524);
						((BitwiseOrExpressionContext)_localctx).op = match(PIPE);
						setState(525);
						expression(8);
						}
						break;
					case 10:
						{
						_localctx = new LogicalAndExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(526);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(527);
						((LogicalAndExpressionContext)_localctx).op = match(DOUBLE_AMPERSAND);
						setState(528);
						expression(7);
						}
						break;
					case 11:
						{
						_localctx = new LogicalOrExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(529);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(530);
						((LogicalOrExpressionContext)_localctx).op = match(DOUBLE_PIPE);
						setState(531);
						expression(6);
						}
						break;
					case 12:
						{
						_localctx = new IndexExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(532);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(533);
						indexOperator();
						}
						break;
					}
					} 
				}
				setState(538);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AliasExpressionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> DOUBLE_PLUS() { return getTokens(qasm3Parser.DOUBLE_PLUS); }
		public TerminalNode DOUBLE_PLUS(int i) {
			return getToken(qasm3Parser.DOUBLE_PLUS, i);
		}
		public AliasExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitAliasExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasExpressionContext aliasExpression() throws RecognitionException {
		AliasExpressionContext _localctx = new AliasExpressionContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_aliasExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(539);
			expression(0);
			setState(544);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOUBLE_PLUS) {
				{
				{
				setState(540);
				match(DOUBLE_PLUS);
				setState(541);
				expression(0);
				}
				}
				setState(546);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationExpressionContext extends ParserRuleContext {
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MeasureExpressionContext measureExpression() {
			return getRuleContext(MeasureExpressionContext.class,0);
		}
		public DeclarationExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitDeclarationExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationExpressionContext declarationExpression() throws RecognitionException {
		DeclarationExpressionContext _localctx = new DeclarationExpressionContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_declarationExpression);
		try {
			setState(550);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(547);
				arrayLiteral();
				}
				break;
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case ARRAY:
			case DURATION:
			case STRETCH:
			case DURATIONOF:
			case BooleanLiteral:
			case LPAREN:
			case MINUS:
			case TILDE:
			case EXCLAMATION_POINT:
			case ImaginaryLiteral:
			case BinaryIntegerLiteral:
			case OctalIntegerLiteral:
			case DecimalIntegerLiteral:
			case HexIntegerLiteral:
			case Identifier:
			case HardwareQubit:
			case FloatLiteral:
			case TimingLiteral:
			case BitstringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(548);
				expression(0);
				}
				break;
			case MEASURE:
				enterOuterAlt(_localctx, 3);
				{
				setState(549);
				measureExpression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class MeasureExpressionContext extends ParserRuleContext {
		public TerminalNode MEASURE() { return getToken(qasm3Parser.MEASURE, 0); }
		public GateOperandContext gateOperand() {
			return getRuleContext(GateOperandContext.class,0);
		}
		public MeasureExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_measureExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitMeasureExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MeasureExpressionContext measureExpression() throws RecognitionException {
		MeasureExpressionContext _localctx = new MeasureExpressionContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_measureExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(552);
			match(MEASURE);
			setState(553);
			gateOperand();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RangeExpressionContext extends ParserRuleContext {
		public List<TerminalNode> COLON() { return getTokens(qasm3Parser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(qasm3Parser.COLON, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RangeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitRangeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeExpressionContext rangeExpression() throws RecognitionException {
		RangeExpressionContext _localctx = new RangeExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_rangeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(556);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1190091586105507840L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 268179457L) != 0)) {
				{
				setState(555);
				expression(0);
				}
			}

			setState(558);
			match(COLON);
			setState(560);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1190091586105507840L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 268179457L) != 0)) {
				{
				setState(559);
				expression(0);
				}
			}

			setState(564);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(562);
				match(COLON);
				setState(563);
				expression(0);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class SetExpressionContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(qasm3Parser.LBRACE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RBRACE() { return getToken(qasm3Parser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(qasm3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(qasm3Parser.COMMA, i);
		}
		public SetExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitSetExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetExpressionContext setExpression() throws RecognitionException {
		SetExpressionContext _localctx = new SetExpressionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_setExpression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(566);
			match(LBRACE);
			setState(567);
			expression(0);
			setState(572);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(568);
					match(COMMA);
					setState(569);
					expression(0);
					}
					} 
				}
				setState(574);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
			}
			setState(576);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(575);
				match(COMMA);
				}
			}

			setState(578);
			match(RBRACE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayLiteralContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(qasm3Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(qasm3Parser.RBRACE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ArrayLiteralContext> arrayLiteral() {
			return getRuleContexts(ArrayLiteralContext.class);
		}
		public ArrayLiteralContext arrayLiteral(int i) {
			return getRuleContext(ArrayLiteralContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(qasm3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(qasm3Parser.COMMA, i);
		}
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_arrayLiteral);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(580);
			match(LBRACE);
			setState(583);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case ARRAY:
			case DURATION:
			case STRETCH:
			case DURATIONOF:
			case BooleanLiteral:
			case LPAREN:
			case MINUS:
			case TILDE:
			case EXCLAMATION_POINT:
			case ImaginaryLiteral:
			case BinaryIntegerLiteral:
			case OctalIntegerLiteral:
			case DecimalIntegerLiteral:
			case HexIntegerLiteral:
			case Identifier:
			case HardwareQubit:
			case FloatLiteral:
			case TimingLiteral:
			case BitstringLiteral:
				{
				setState(581);
				expression(0);
				}
				break;
			case LBRACE:
				{
				setState(582);
				arrayLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(592);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(585);
					match(COMMA);
					setState(588);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case BOOL:
					case BIT:
					case INT:
					case UINT:
					case FLOAT:
					case ANGLE:
					case COMPLEX:
					case ARRAY:
					case DURATION:
					case STRETCH:
					case DURATIONOF:
					case BooleanLiteral:
					case LPAREN:
					case MINUS:
					case TILDE:
					case EXCLAMATION_POINT:
					case ImaginaryLiteral:
					case BinaryIntegerLiteral:
					case OctalIntegerLiteral:
					case DecimalIntegerLiteral:
					case HexIntegerLiteral:
					case Identifier:
					case HardwareQubit:
					case FloatLiteral:
					case TimingLiteral:
					case BitstringLiteral:
						{
						setState(586);
						expression(0);
						}
						break;
					case LBRACE:
						{
						setState(587);
						arrayLiteral();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(594);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			}
			setState(596);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(595);
				match(COMMA);
				}
			}

			setState(598);
			match(RBRACE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IndexOperatorContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(qasm3Parser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(qasm3Parser.RBRACKET, 0); }
		public SetExpressionContext setExpression() {
			return getRuleContext(SetExpressionContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<RangeExpressionContext> rangeExpression() {
			return getRuleContexts(RangeExpressionContext.class);
		}
		public RangeExpressionContext rangeExpression(int i) {
			return getRuleContext(RangeExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(qasm3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(qasm3Parser.COMMA, i);
		}
		public IndexOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexOperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitIndexOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexOperatorContext indexOperator() throws RecognitionException {
		IndexOperatorContext _localctx = new IndexOperatorContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_indexOperator);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(600);
			match(LBRACKET);
			setState(619);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(601);
				setExpression();
				}
				break;
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case ARRAY:
			case DURATION:
			case STRETCH:
			case DURATIONOF:
			case BooleanLiteral:
			case LPAREN:
			case COLON:
			case MINUS:
			case TILDE:
			case EXCLAMATION_POINT:
			case ImaginaryLiteral:
			case BinaryIntegerLiteral:
			case OctalIntegerLiteral:
			case DecimalIntegerLiteral:
			case HexIntegerLiteral:
			case Identifier:
			case HardwareQubit:
			case FloatLiteral:
			case TimingLiteral:
			case BitstringLiteral:
				{
				setState(604);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
				case 1:
					{
					setState(602);
					expression(0);
					}
					break;
				case 2:
					{
					setState(603);
					rangeExpression();
					}
					break;
				}
				setState(613);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(606);
						match(COMMA);
						setState(609);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
						case 1:
							{
							setState(607);
							expression(0);
							}
							break;
						case 2:
							{
							setState(608);
							rangeExpression();
							}
							break;
						}
						}
						} 
					}
					setState(615);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
				}
				setState(617);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(616);
					match(COMMA);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(621);
			match(RBRACKET);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IndexedIdentifierContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public List<IndexOperatorContext> indexOperator() {
			return getRuleContexts(IndexOperatorContext.class);
		}
		public IndexOperatorContext indexOperator(int i) {
			return getRuleContext(IndexOperatorContext.class,i);
		}
		public IndexedIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedIdentifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitIndexedIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexedIdentifierContext indexedIdentifier() throws RecognitionException {
		IndexedIdentifierContext _localctx = new IndexedIdentifierContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_indexedIdentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(623);
			match(Identifier);
			setState(627);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACKET) {
				{
				{
				setState(624);
				indexOperator();
				}
				}
				setState(629);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnSignatureContext extends ParserRuleContext {
		public TerminalNode ARROW() { return getToken(qasm3Parser.ARROW, 0); }
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public ReturnSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnSignature; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitReturnSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnSignatureContext returnSignature() throws RecognitionException {
		ReturnSignatureContext _localctx = new ReturnSignatureContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_returnSignature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(630);
			match(ARROW);
			setState(631);
			scalarType();
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

	@SuppressWarnings("CheckReturnValue")
	public static class GateModifierContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(qasm3Parser.AT, 0); }
		public TerminalNode INV() { return getToken(qasm3Parser.INV, 0); }
		public TerminalNode POW() { return getToken(qasm3Parser.POW, 0); }
		public TerminalNode LPAREN() { return getToken(qasm3Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(qasm3Parser.RPAREN, 0); }
		public TerminalNode CTRL() { return getToken(qasm3Parser.CTRL, 0); }
		public TerminalNode NEGCTRL() { return getToken(qasm3Parser.NEGCTRL, 0); }
		public GateModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gateModifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitGateModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GateModifierContext gateModifier() throws RecognitionException {
		GateModifierContext _localctx = new GateModifierContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_gateModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(646);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INV:
				{
				setState(633);
				match(INV);
				}
				break;
			case POW:
				{
				setState(634);
				match(POW);
				setState(635);
				match(LPAREN);
				setState(636);
				expression(0);
				setState(637);
				match(RPAREN);
				}
				break;
			case CTRL:
			case NEGCTRL:
				{
				setState(639);
				_la = _input.LA(1);
				if ( !(_la==CTRL || _la==NEGCTRL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(644);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(640);
					match(LPAREN);
					setState(641);
					expression(0);
					setState(642);
					match(RPAREN);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(648);
			match(AT);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ScalarTypeContext extends ParserRuleContext {
		public TerminalNode BIT() { return getToken(qasm3Parser.BIT, 0); }
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public TerminalNode INT() { return getToken(qasm3Parser.INT, 0); }
		public TerminalNode UINT() { return getToken(qasm3Parser.UINT, 0); }
		public TerminalNode FLOAT() { return getToken(qasm3Parser.FLOAT, 0); }
		public TerminalNode ANGLE() { return getToken(qasm3Parser.ANGLE, 0); }
		public TerminalNode BOOL() { return getToken(qasm3Parser.BOOL, 0); }
		public TerminalNode DURATION() { return getToken(qasm3Parser.DURATION, 0); }
		public TerminalNode STRETCH() { return getToken(qasm3Parser.STRETCH, 0); }
		public TerminalNode COMPLEX() { return getToken(qasm3Parser.COMPLEX, 0); }
		public TerminalNode LBRACKET() { return getToken(qasm3Parser.LBRACKET, 0); }
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(qasm3Parser.RBRACKET, 0); }
		public ScalarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitScalarType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarTypeContext scalarType() throws RecognitionException {
		ScalarTypeContext _localctx = new ScalarTypeContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_scalarType);
		int _la;
		try {
			setState(680);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(650);
				match(BIT);
				setState(652);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(651);
					designator();
					}
				}

				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(654);
				match(INT);
				setState(656);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(655);
					designator();
					}
				}

				}
				break;
			case UINT:
				enterOuterAlt(_localctx, 3);
				{
				setState(658);
				match(UINT);
				setState(660);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(659);
					designator();
					}
				}

				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 4);
				{
				setState(662);
				match(FLOAT);
				setState(664);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(663);
					designator();
					}
				}

				}
				break;
			case ANGLE:
				enterOuterAlt(_localctx, 5);
				{
				setState(666);
				match(ANGLE);
				setState(668);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(667);
					designator();
					}
				}

				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 6);
				{
				setState(670);
				match(BOOL);
				}
				break;
			case DURATION:
				enterOuterAlt(_localctx, 7);
				{
				setState(671);
				match(DURATION);
				}
				break;
			case STRETCH:
				enterOuterAlt(_localctx, 8);
				{
				setState(672);
				match(STRETCH);
				}
				break;
			case COMPLEX:
				enterOuterAlt(_localctx, 9);
				{
				setState(673);
				match(COMPLEX);
				setState(678);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(674);
					match(LBRACKET);
					setState(675);
					scalarType();
					setState(676);
					match(RBRACKET);
					}
				}

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

	@SuppressWarnings("CheckReturnValue")
	public static class QubitTypeContext extends ParserRuleContext {
		public TerminalNode QUBIT() { return getToken(qasm3Parser.QUBIT, 0); }
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public QubitTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qubitType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitQubitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QubitTypeContext qubitType() throws RecognitionException {
		QubitTypeContext _localctx = new QubitTypeContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_qubitType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(682);
			match(QUBIT);
			setState(684);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(683);
				designator();
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayTypeContext extends ParserRuleContext {
		public TerminalNode ARRAY() { return getToken(qasm3Parser.ARRAY, 0); }
		public TerminalNode LBRACKET() { return getToken(qasm3Parser.LBRACKET, 0); }
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(qasm3Parser.COMMA, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(qasm3Parser.RBRACKET, 0); }
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(686);
			match(ARRAY);
			setState(687);
			match(LBRACKET);
			setState(688);
			scalarType();
			setState(689);
			match(COMMA);
			setState(690);
			expressionList();
			setState(691);
			match(RBRACKET);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayReferenceTypeContext extends ParserRuleContext {
		public TerminalNode ARRAY() { return getToken(qasm3Parser.ARRAY, 0); }
		public TerminalNode LBRACKET() { return getToken(qasm3Parser.LBRACKET, 0); }
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(qasm3Parser.COMMA, 0); }
		public TerminalNode RBRACKET() { return getToken(qasm3Parser.RBRACKET, 0); }
		public TerminalNode READONLY() { return getToken(qasm3Parser.READONLY, 0); }
		public TerminalNode MUTABLE() { return getToken(qasm3Parser.MUTABLE, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode DIM() { return getToken(qasm3Parser.DIM, 0); }
		public TerminalNode EQUALS() { return getToken(qasm3Parser.EQUALS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayReferenceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayReferenceType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitArrayReferenceType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayReferenceTypeContext arrayReferenceType() throws RecognitionException {
		ArrayReferenceTypeContext _localctx = new ArrayReferenceTypeContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_arrayReferenceType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(693);
			_la = _input.LA(1);
			if ( !(_la==READONLY || _la==MUTABLE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(694);
			match(ARRAY);
			setState(695);
			match(LBRACKET);
			setState(696);
			scalarType();
			setState(697);
			match(COMMA);
			setState(702);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case ARRAY:
			case DURATION:
			case STRETCH:
			case DURATIONOF:
			case BooleanLiteral:
			case LPAREN:
			case MINUS:
			case TILDE:
			case EXCLAMATION_POINT:
			case ImaginaryLiteral:
			case BinaryIntegerLiteral:
			case OctalIntegerLiteral:
			case DecimalIntegerLiteral:
			case HexIntegerLiteral:
			case Identifier:
			case HardwareQubit:
			case FloatLiteral:
			case TimingLiteral:
			case BitstringLiteral:
				{
				setState(698);
				expressionList();
				}
				break;
			case DIM:
				{
				setState(699);
				match(DIM);
				setState(700);
				match(EQUALS);
				setState(701);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(704);
			match(RBRACKET);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DesignatorContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(qasm3Parser.LBRACKET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(qasm3Parser.RBRACKET, 0); }
		public DesignatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_designator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitDesignator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DesignatorContext designator() throws RecognitionException {
		DesignatorContext _localctx = new DesignatorContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_designator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(706);
			match(LBRACKET);
			setState(707);
			expression(0);
			setState(708);
			match(RBRACKET);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefcalTargetContext extends ParserRuleContext {
		public TerminalNode MEASURE() { return getToken(qasm3Parser.MEASURE, 0); }
		public TerminalNode RESET() { return getToken(qasm3Parser.RESET, 0); }
		public TerminalNode DELAY() { return getToken(qasm3Parser.DELAY, 0); }
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public DefcalTargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defcalTarget; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitDefcalTarget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefcalTargetContext defcalTarget() throws RecognitionException {
		DefcalTargetContext _localctx = new DefcalTargetContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_defcalTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(710);
			_la = _input.LA(1);
			if ( !(((((_la - 51)) & ~0x3f) == 0 && ((1L << (_la - 51)) & 4398046511111L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefcalArgumentDefinitionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgumentDefinitionContext argumentDefinition() {
			return getRuleContext(ArgumentDefinitionContext.class,0);
		}
		public DefcalArgumentDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defcalArgumentDefinition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitDefcalArgumentDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefcalArgumentDefinitionContext defcalArgumentDefinition() throws RecognitionException {
		DefcalArgumentDefinitionContext _localctx = new DefcalArgumentDefinitionContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_defcalArgumentDefinition);
		try {
			setState(714);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(712);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(713);
				argumentDefinition();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefcalOperandContext extends ParserRuleContext {
		public TerminalNode HardwareQubit() { return getToken(qasm3Parser.HardwareQubit, 0); }
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public DefcalOperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defcalOperand; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitDefcalOperand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefcalOperandContext defcalOperand() throws RecognitionException {
		DefcalOperandContext _localctx = new DefcalOperandContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_defcalOperand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(716);
			_la = _input.LA(1);
			if ( !(_la==Identifier || _la==HardwareQubit) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	@SuppressWarnings("CheckReturnValue")
	public static class GateOperandContext extends ParserRuleContext {
		public IndexedIdentifierContext indexedIdentifier() {
			return getRuleContext(IndexedIdentifierContext.class,0);
		}
		public TerminalNode HardwareQubit() { return getToken(qasm3Parser.HardwareQubit, 0); }
		public GateOperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gateOperand; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitGateOperand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GateOperandContext gateOperand() throws RecognitionException {
		GateOperandContext _localctx = new GateOperandContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_gateOperand);
		try {
			setState(720);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(718);
				indexedIdentifier();
				}
				break;
			case HardwareQubit:
				enterOuterAlt(_localctx, 2);
				{
				setState(719);
				match(HardwareQubit);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExternArgumentContext extends ParserRuleContext {
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public ArrayReferenceTypeContext arrayReferenceType() {
			return getRuleContext(ArrayReferenceTypeContext.class,0);
		}
		public TerminalNode CREG() { return getToken(qasm3Parser.CREG, 0); }
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public ExternArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_externArgument; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitExternArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExternArgumentContext externArgument() throws RecognitionException {
		ExternArgumentContext _localctx = new ExternArgumentContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_externArgument);
		int _la;
		try {
			setState(728);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case DURATION:
			case STRETCH:
				enterOuterAlt(_localctx, 1);
				{
				setState(722);
				scalarType();
				}
				break;
			case READONLY:
			case MUTABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(723);
				arrayReferenceType();
				}
				break;
			case CREG:
				enterOuterAlt(_localctx, 3);
				{
				setState(724);
				match(CREG);
				setState(726);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(725);
					designator();
					}
				}

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

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentDefinitionContext extends ParserRuleContext {
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(qasm3Parser.Identifier, 0); }
		public QubitTypeContext qubitType() {
			return getRuleContext(QubitTypeContext.class,0);
		}
		public TerminalNode CREG() { return getToken(qasm3Parser.CREG, 0); }
		public TerminalNode QREG() { return getToken(qasm3Parser.QREG, 0); }
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public ArrayReferenceTypeContext arrayReferenceType() {
			return getRuleContext(ArrayReferenceTypeContext.class,0);
		}
		public ArgumentDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentDefinition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitArgumentDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentDefinitionContext argumentDefinition() throws RecognitionException {
		ArgumentDefinitionContext _localctx = new ArgumentDefinitionContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_argumentDefinition);
		int _la;
		try {
			setState(744);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case BIT:
			case INT:
			case UINT:
			case FLOAT:
			case ANGLE:
			case COMPLEX:
			case DURATION:
			case STRETCH:
				enterOuterAlt(_localctx, 1);
				{
				setState(730);
				scalarType();
				setState(731);
				match(Identifier);
				}
				break;
			case QUBIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(733);
				qubitType();
				setState(734);
				match(Identifier);
				}
				break;
			case QREG:
			case CREG:
				enterOuterAlt(_localctx, 3);
				{
				setState(736);
				_la = _input.LA(1);
				if ( !(_la==QREG || _la==CREG) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(737);
				match(Identifier);
				setState(739);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(738);
					designator();
					}
				}

				}
				break;
			case READONLY:
			case MUTABLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(741);
				arrayReferenceType();
				setState(742);
				match(Identifier);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentDefinitionListContext extends ParserRuleContext {
		public List<ArgumentDefinitionContext> argumentDefinition() {
			return getRuleContexts(ArgumentDefinitionContext.class);
		}
		public ArgumentDefinitionContext argumentDefinition(int i) {
			return getRuleContext(ArgumentDefinitionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(qasm3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(qasm3Parser.COMMA, i);
		}
		public ArgumentDefinitionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentDefinitionList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitArgumentDefinitionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentDefinitionListContext argumentDefinitionList() throws RecognitionException {
		ArgumentDefinitionListContext _localctx = new ArgumentDefinitionListContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_argumentDefinitionList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(746);
			argumentDefinition();
			setState(751);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(747);
					match(COMMA);
					setState(748);
					argumentDefinition();
					}
					} 
				}
				setState(753);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			setState(755);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(754);
				match(COMMA);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefcalArgumentDefinitionListContext extends ParserRuleContext {
		public List<DefcalArgumentDefinitionContext> defcalArgumentDefinition() {
			return getRuleContexts(DefcalArgumentDefinitionContext.class);
		}
		public DefcalArgumentDefinitionContext defcalArgumentDefinition(int i) {
			return getRuleContext(DefcalArgumentDefinitionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(qasm3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(qasm3Parser.COMMA, i);
		}
		public DefcalArgumentDefinitionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defcalArgumentDefinitionList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitDefcalArgumentDefinitionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefcalArgumentDefinitionListContext defcalArgumentDefinitionList() throws RecognitionException {
		DefcalArgumentDefinitionListContext _localctx = new DefcalArgumentDefinitionListContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_defcalArgumentDefinitionList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(757);
			defcalArgumentDefinition();
			setState(762);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(758);
					match(COMMA);
					setState(759);
					defcalArgumentDefinition();
					}
					} 
				}
				setState(764);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			}
			setState(766);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(765);
				match(COMMA);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefcalOperandListContext extends ParserRuleContext {
		public List<DefcalOperandContext> defcalOperand() {
			return getRuleContexts(DefcalOperandContext.class);
		}
		public DefcalOperandContext defcalOperand(int i) {
			return getRuleContext(DefcalOperandContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(qasm3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(qasm3Parser.COMMA, i);
		}
		public DefcalOperandListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defcalOperandList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitDefcalOperandList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefcalOperandListContext defcalOperandList() throws RecognitionException {
		DefcalOperandListContext _localctx = new DefcalOperandListContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_defcalOperandList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(768);
			defcalOperand();
			setState(773);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(769);
					match(COMMA);
					setState(770);
					defcalOperand();
					}
					} 
				}
				setState(775);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			}
			setState(777);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(776);
				match(COMMA);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(qasm3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(qasm3Parser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_expressionList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(779);
			expression(0);
			setState(784);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(780);
					match(COMMA);
					setState(781);
					expression(0);
					}
					} 
				}
				setState(786);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			setState(788);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(787);
				match(COMMA);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierListContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(qasm3Parser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(qasm3Parser.Identifier, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(qasm3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(qasm3Parser.COMMA, i);
		}
		public IdentifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitIdentifierList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierListContext identifierList() throws RecognitionException {
		IdentifierListContext _localctx = new IdentifierListContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_identifierList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(790);
			match(Identifier);
			setState(795);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(791);
					match(COMMA);
					setState(792);
					match(Identifier);
					}
					} 
				}
				setState(797);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
			}
			setState(799);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(798);
				match(COMMA);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class GateOperandListContext extends ParserRuleContext {
		public List<GateOperandContext> gateOperand() {
			return getRuleContexts(GateOperandContext.class);
		}
		public GateOperandContext gateOperand(int i) {
			return getRuleContext(GateOperandContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(qasm3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(qasm3Parser.COMMA, i);
		}
		public GateOperandListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gateOperandList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitGateOperandList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GateOperandListContext gateOperandList() throws RecognitionException {
		GateOperandListContext _localctx = new GateOperandListContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_gateOperandList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(801);
			gateOperand();
			setState(806);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(802);
					match(COMMA);
					setState(803);
					gateOperand();
					}
					} 
				}
				setState(808);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
			}
			setState(810);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(809);
				match(COMMA);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExternArgumentListContext extends ParserRuleContext {
		public List<ExternArgumentContext> externArgument() {
			return getRuleContexts(ExternArgumentContext.class);
		}
		public ExternArgumentContext externArgument(int i) {
			return getRuleContext(ExternArgumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(qasm3Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(qasm3Parser.COMMA, i);
		}
		public ExternArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_externArgumentList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof qasm3ParserVisitor ) return ((qasm3ParserVisitor<? extends T>)visitor).visitExternArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExternArgumentListContext externArgumentList() throws RecognitionException {
		ExternArgumentListContext _localctx = new ExternArgumentListContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_externArgumentList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(812);
			externArgument();
			setState(817);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(813);
					match(COMMA);
					setState(814);
					externArgument();
					}
					} 
				}
				setState(819);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			}
			setState(821);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(820);
				match(COMMA);
				}
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 37:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 16);
		case 1:
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 11);
		case 5:
			return precpred(_ctx, 10);
		case 6:
			return precpred(_ctx, 9);
		case 7:
			return precpred(_ctx, 8);
		case 8:
			return precpred(_ctx, 7);
		case 9:
			return precpred(_ctx, 6);
		case 10:
			return precpred(_ctx, 5);
		case 11:
			return precpred(_ctx, 17);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001q\u0338\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0001\u0000\u0003\u0000\u0086\b\u0000\u0001\u0000\u0005\u0000"+
		"\u0089\b\u0000\n\u0000\f\u0000\u008c\t\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0005"+
		"\u0002\u0096\b\u0002\n\u0002\f\u0002\u0099\t\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u00b8\b\u0002\u0003\u0002"+
		"\u00ba\b\u0002\u0001\u0003\u0001\u0003\u0003\u0003\u00be\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0005\u0004\u00c2\b\u0004\n\u0004\f\u0004\u00c5\t\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0003\u0006\u00ce\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u00eb\b\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u00f6\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0003"+
		"\u000e\u00fb\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u010b\b\u0010\n"+
		"\u0010\f\u0010\u010e\t\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0118"+
		"\b\u0011\u0001\u0012\u0001\u0012\u0003\u0012\u011c\b\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0003\u0013\u0122\b\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0129\b\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0015\u0005\u0015\u012e\b\u0015\n\u0015"+
		"\f\u0015\u0131\t\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u0136\b\u0015\u0001\u0015\u0003\u0015\u0139\b\u0015\u0001\u0015\u0003"+
		"\u0015\u013c\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005"+
		"\u0015\u0142\b\u0015\n\u0015\f\u0015\u0145\t\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u014a\b\u0015\u0001\u0015\u0003\u0015\u014d\b"+
		"\u0015\u0001\u0015\u0003\u0015\u0150\b\u0015\u0001\u0015\u0003\u0015\u0153"+
		"\b\u0015\u0001\u0015\u0003\u0015\u0156\b\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0003\u0016\u015b\b\u0016\u0001\u0016\u0001\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u016b\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0170\b"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0003\u001b\u017e\b\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u0186\b\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0192\b\u001e\u0001\u001e\u0001"+
		"\u001e\u0003\u001e\u0196\b\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u019e\b\u001f\u0001\u001f\u0001"+
		"\u001f\u0003\u001f\u01a2\b\u001f\u0001\u001f\u0001\u001f\u0001 \u0001"+
		" \u0001 \u0001 \u0003 \u01aa\b \u0001 \u0003 \u01ad\b \u0001 \u0001 \u0001"+
		" \u0001!\u0001!\u0001!\u0001!\u0003!\u01b6\b!\u0001!\u0001!\u0001\"\u0001"+
		"\"\u0001\"\u0001#\u0001#\u0001#\u0003#\u01c0\b#\u0001#\u0001#\u0001$\u0001"+
		"$\u0001$\u0001$\u0003$\u01c8\b$\u0001$\u0003$\u01cb\b$\u0001$\u0001$\u0003"+
		"$\u01cf\b$\u0001$\u0001$\u0003$\u01d3\b$\u0001$\u0001$\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0003%\u01e0\b%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0003%\u01ee\b%\u0001%\u0001%\u0003%\u01f2\b%\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0005%\u0217\b%\n%\f%\u021a\t%\u0001&\u0001&\u0001&\u0005&\u021f"+
		"\b&\n&\f&\u0222\t&\u0001\'\u0001\'\u0001\'\u0003\'\u0227\b\'\u0001(\u0001"+
		"(\u0001(\u0001)\u0003)\u022d\b)\u0001)\u0001)\u0003)\u0231\b)\u0001)\u0001"+
		")\u0003)\u0235\b)\u0001*\u0001*\u0001*\u0001*\u0005*\u023b\b*\n*\f*\u023e"+
		"\t*\u0001*\u0003*\u0241\b*\u0001*\u0001*\u0001+\u0001+\u0001+\u0003+\u0248"+
		"\b+\u0001+\u0001+\u0001+\u0003+\u024d\b+\u0005+\u024f\b+\n+\f+\u0252\t"+
		"+\u0001+\u0003+\u0255\b+\u0001+\u0001+\u0001,\u0001,\u0001,\u0001,\u0003"+
		",\u025d\b,\u0001,\u0001,\u0001,\u0003,\u0262\b,\u0005,\u0264\b,\n,\f,"+
		"\u0267\t,\u0001,\u0003,\u026a\b,\u0003,\u026c\b,\u0001,\u0001,\u0001-"+
		"\u0001-\u0005-\u0272\b-\n-\f-\u0275\t-\u0001.\u0001.\u0001.\u0001/\u0001"+
		"/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0003"+
		"/\u0285\b/\u0003/\u0287\b/\u0001/\u0001/\u00010\u00010\u00030\u028d\b"+
		"0\u00010\u00010\u00030\u0291\b0\u00010\u00010\u00030\u0295\b0\u00010\u0001"+
		"0\u00030\u0299\b0\u00010\u00010\u00030\u029d\b0\u00010\u00010\u00010\u0001"+
		"0\u00010\u00010\u00010\u00010\u00030\u02a7\b0\u00030\u02a9\b0\u00011\u0001"+
		"1\u00031\u02ad\b1\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"3\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00033\u02bf"+
		"\b3\u00013\u00013\u00014\u00014\u00014\u00014\u00015\u00015\u00016\u0001"+
		"6\u00036\u02cb\b6\u00017\u00017\u00018\u00018\u00038\u02d1\b8\u00019\u0001"+
		"9\u00019\u00019\u00039\u02d7\b9\u00039\u02d9\b9\u0001:\u0001:\u0001:\u0001"+
		":\u0001:\u0001:\u0001:\u0001:\u0001:\u0003:\u02e4\b:\u0001:\u0001:\u0001"+
		":\u0003:\u02e9\b:\u0001;\u0001;\u0001;\u0005;\u02ee\b;\n;\f;\u02f1\t;"+
		"\u0001;\u0003;\u02f4\b;\u0001<\u0001<\u0001<\u0005<\u02f9\b<\n<\f<\u02fc"+
		"\t<\u0001<\u0003<\u02ff\b<\u0001=\u0001=\u0001=\u0005=\u0304\b=\n=\f="+
		"\u0307\t=\u0001=\u0003=\u030a\b=\u0001>\u0001>\u0001>\u0005>\u030f\b>"+
		"\n>\f>\u0312\t>\u0001>\u0003>\u0315\b>\u0001?\u0001?\u0001?\u0005?\u031a"+
		"\b?\n?\f?\u031d\t?\u0001?\u0003?\u0320\b?\u0001@\u0001@\u0001@\u0005@"+
		"\u0325\b@\n@\f@\u0328\t@\u0001@\u0003@\u032b\b@\u0001A\u0001A\u0001A\u0005"+
		"A\u0330\bA\nA\fA\u0333\tA\u0001A\u0003A\u0336\bA\u0001A\u0000\u0001JB"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082"+
		"\u0000\u000b\u0001\u0000\u0019\u001a\u0002\u0000\u001e\u001e  \u0002\u0000"+
		"BBTT\u0002\u0000FFQR\u0002\u000077Xa\u0002\u0000GGIJ\u0002\u0000DDFF\u0001"+
		"\u0000/0\u0001\u0000\u001c\u001d\u0002\u000035]]\u0001\u0000]^\u038c\u0000"+
		"\u0085\u0001\u0000\u0000\u0000\u0002\u008f\u0001\u0000\u0000\u0000\u0004"+
		"\u00b9\u0001\u0000\u0000\u0000\u0006\u00bb\u0001\u0000\u0000\u0000\b\u00bf"+
		"\u0001\u0000\u0000\u0000\n\u00c8\u0001\u0000\u0000\u0000\f\u00cd\u0001"+
		"\u0000\u0000\u0000\u000e\u00cf\u0001\u0000\u0000\u0000\u0010\u00d3\u0001"+
		"\u0000\u0000\u0000\u0012\u00d7\u0001\u0000\u0000\u0000\u0014\u00da\u0001"+
		"\u0000\u0000\u0000\u0016\u00dd\u0001\u0000\u0000\u0000\u0018\u00e0\u0001"+
		"\u0000\u0000\u0000\u001a\u00ee\u0001\u0000\u0000\u0000\u001c\u00f7\u0001"+
		"\u0000\u0000\u0000\u001e\u00fe\u0001\u0000\u0000\u0000 \u0104\u0001\u0000"+
		"\u0000\u0000\"\u0117\u0001\u0000\u0000\u0000$\u0119\u0001\u0000\u0000"+
		"\u0000&\u011f\u0001\u0000\u0000\u0000(\u0125\u0001\u0000\u0000\u0000*"+
		"\u0155\u0001\u0000\u0000\u0000,\u0157\u0001\u0000\u0000\u0000.\u015e\u0001"+
		"\u0000\u0000\u00000\u0162\u0001\u0000\u0000\u00002\u016a\u0001\u0000\u0000"+
		"\u00004\u0173\u0001\u0000\u0000\u00006\u017a\u0001\u0000\u0000\u00008"+
		"\u0182\u0001\u0000\u0000\u0000:\u0189\u0001\u0000\u0000\u0000<\u018d\u0001"+
		"\u0000\u0000\u0000>\u0199\u0001\u0000\u0000\u0000@\u01a5\u0001\u0000\u0000"+
		"\u0000B\u01b1\u0001\u0000\u0000\u0000D\u01b9\u0001\u0000\u0000\u0000F"+
		"\u01bc\u0001\u0000\u0000\u0000H\u01c3\u0001\u0000\u0000\u0000J\u01f1\u0001"+
		"\u0000\u0000\u0000L\u021b\u0001\u0000\u0000\u0000N\u0226\u0001\u0000\u0000"+
		"\u0000P\u0228\u0001\u0000\u0000\u0000R\u022c\u0001\u0000\u0000\u0000T"+
		"\u0236\u0001\u0000\u0000\u0000V\u0244\u0001\u0000\u0000\u0000X\u0258\u0001"+
		"\u0000\u0000\u0000Z\u026f\u0001\u0000\u0000\u0000\\\u0276\u0001\u0000"+
		"\u0000\u0000^\u0286\u0001\u0000\u0000\u0000`\u02a8\u0001\u0000\u0000\u0000"+
		"b\u02aa\u0001\u0000\u0000\u0000d\u02ae\u0001\u0000\u0000\u0000f\u02b5"+
		"\u0001\u0000\u0000\u0000h\u02c2\u0001\u0000\u0000\u0000j\u02c6\u0001\u0000"+
		"\u0000\u0000l\u02ca\u0001\u0000\u0000\u0000n\u02cc\u0001\u0000\u0000\u0000"+
		"p\u02d0\u0001\u0000\u0000\u0000r\u02d8\u0001\u0000\u0000\u0000t\u02e8"+
		"\u0001\u0000\u0000\u0000v\u02ea\u0001\u0000\u0000\u0000x\u02f5\u0001\u0000"+
		"\u0000\u0000z\u0300\u0001\u0000\u0000\u0000|\u030b\u0001\u0000\u0000\u0000"+
		"~\u0316\u0001\u0000\u0000\u0000\u0080\u0321\u0001\u0000\u0000\u0000\u0082"+
		"\u032c\u0001\u0000\u0000\u0000\u0084\u0086\u0003\u0002\u0001\u0000\u0085"+
		"\u0084\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086"+
		"\u008a\u0001\u0000\u0000\u0000\u0087\u0089\u0003\f\u0006\u0000\u0088\u0087"+
		"\u0001\u0000\u0000\u0000\u0089\u008c\u0001\u0000\u0000\u0000\u008a\u0088"+
		"\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u008d"+
		"\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008d\u008e"+
		"\u0005\u0000\u0000\u0001\u008e\u0001\u0001\u0000\u0000\u0000\u008f\u0090"+
		"\u0005\u0001\u0000\u0000\u0090\u0091\u0005g\u0000\u0000\u0091\u0092\u0005"+
		"?\u0000\u0000\u0092\u0003\u0001\u0000\u0000\u0000\u0093\u00ba\u0003\n"+
		"\u0005\u0000\u0094\u0096\u0003\u0006\u0003\u0000\u0095\u0094\u0001\u0000"+
		"\u0000\u0000\u0096\u0099\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000"+
		"\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u00b7\u0001\u0000"+
		"\u0000\u0000\u0099\u0097\u0001\u0000\u0000\u0000\u009a\u00b8\u00030\u0018"+
		"\u0000\u009b\u00b8\u0003B!\u0000\u009c\u00b8\u0003$\u0012\u0000\u009d"+
		"\u00b8\u0003&\u0013\u0000\u009e\u00b8\u0003\u0012\t\u0000\u009f\u00b8"+
		"\u0003F#\u0000\u00a0\u00b8\u0003\u000e\u0007\u0000\u00a1\u00b8\u00032"+
		"\u0019\u0000\u00a2\u00b8\u00034\u001a\u0000\u00a3\u00b8\u0003\u0014\n"+
		"\u0000\u00a4\u00b8\u0003<\u001e\u0000\u00a5\u00b8\u0003H$\u0000\u00a6"+
		"\u00b8\u0003(\u0014\u0000\u00a7\u00b8\u0003\u0016\u000b\u0000\u00a8\u00b8"+
		"\u0003D\"\u0000\u00a9\u00b8\u0003>\u001f\u0000\u00aa\u00b8\u0003\u0018"+
		"\f\u0000\u00ab\u00b8\u0003*\u0015\u0000\u00ac\u00b8\u0003@ \u0000\u00ad"+
		"\u00b8\u0003\u001a\r\u0000\u00ae\u00b8\u0003\u0010\b\u0000\u00af\u00b8"+
		"\u00036\u001b\u0000\u00b0\u00b8\u0003,\u0016\u0000\u00b1\u00b8\u00038"+
		"\u001c\u0000\u00b2\u00b8\u0003:\u001d\u0000\u00b3\u00b8\u0003.\u0017\u0000"+
		"\u00b4\u00b8\u0003\u001c\u000e\u0000\u00b5\u00b8\u0003 \u0010\u0000\u00b6"+
		"\u00b8\u0003\u001e\u000f\u0000\u00b7\u009a\u0001\u0000\u0000\u0000\u00b7"+
		"\u009b\u0001\u0000\u0000\u0000\u00b7\u009c\u0001\u0000\u0000\u0000\u00b7"+
		"\u009d\u0001\u0000\u0000\u0000\u00b7\u009e\u0001\u0000\u0000\u0000\u00b7"+
		"\u009f\u0001\u0000\u0000\u0000\u00b7\u00a0\u0001\u0000\u0000\u0000\u00b7"+
		"\u00a1\u0001\u0000\u0000\u0000\u00b7\u00a2\u0001\u0000\u0000\u0000\u00b7"+
		"\u00a3\u0001\u0000\u0000\u0000\u00b7\u00a4\u0001\u0000\u0000\u0000\u00b7"+
		"\u00a5\u0001\u0000\u0000\u0000\u00b7\u00a6\u0001\u0000\u0000\u0000\u00b7"+
		"\u00a7\u0001\u0000\u0000\u0000\u00b7\u00a8\u0001\u0000\u0000\u0000\u00b7"+
		"\u00a9\u0001\u0000\u0000\u0000\u00b7\u00aa\u0001\u0000\u0000\u0000\u00b7"+
		"\u00ab\u0001\u0000\u0000\u0000\u00b7\u00ac\u0001\u0000\u0000\u0000\u00b7"+
		"\u00ad\u0001\u0000\u0000\u0000\u00b7\u00ae\u0001\u0000\u0000\u0000\u00b7"+
		"\u00af\u0001\u0000\u0000\u0000\u00b7\u00b0\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b1\u0001\u0000\u0000\u0000\u00b7\u00b2\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b3\u0001\u0000\u0000\u0000\u00b7\u00b4\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b6\u0001\u0000\u0000\u0000\u00b8"+
		"\u00ba\u0001\u0000\u0000\u0000\u00b9\u0093\u0001\u0000\u0000\u0000\u00b9"+
		"\u0097\u0001\u0000\u0000\u0000\u00ba\u0005\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bd\u0005\u0018\u0000\u0000\u00bc\u00be\u0005l\u0000\u0000\u00bd\u00bc"+
		"\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u0007"+
		"\u0001\u0000\u0000\u0000\u00bf\u00c3\u0005:\u0000\u0000\u00c0\u00c2\u0003"+
		"\f\u0006\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c5\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000"+
		"\u0000\u0000\u00c4\u00c6\u0001\u0000\u0000\u0000\u00c5\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c7\u0005;\u0000\u0000\u00c7\t\u0001\u0000\u0000"+
		"\u0000\u00c8\u00c9\u0005\u0017\u0000\u0000\u00c9\u00ca\u0005l\u0000\u0000"+
		"\u00ca\u000b\u0001\u0000\u0000\u0000\u00cb\u00ce\u0003\u0004\u0002\u0000"+
		"\u00cc\u00ce\u0003\b\u0004\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000\u00cd"+
		"\u00cc\u0001\u0000\u0000\u0000\u00ce\r\u0001\u0000\u0000\u0000\u00cf\u00d0"+
		"\u0005\u0003\u0000\u0000\u00d0\u00d1\u0005i\u0000\u0000\u00d1\u00d2\u0005"+
		"?\u0000\u0000\u00d2\u000f\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005\u0002"+
		"\u0000\u0000\u00d4\u00d5\u0005i\u0000\u0000\u00d5\u00d6\u0005?\u0000\u0000"+
		"\u00d6\u0011\u0001\u0000\u0000\u0000\u00d7\u00d8\u0005\u000b\u0000\u0000"+
		"\u00d8\u00d9\u0005?\u0000\u0000\u00d9\u0013\u0001\u0000\u0000\u0000\u00da"+
		"\u00db\u0005\f\u0000\u0000\u00db\u00dc\u0005?\u0000\u0000\u00dc\u0015"+
		"\u0001\u0000\u0000\u0000\u00dd\u00de\u0005\u000f\u0000\u0000\u00de\u00df"+
		"\u0005?\u0000\u0000\u00df\u0017\u0001\u0000\u0000\u0000\u00e0\u00e1\u0005"+
		"\u0011\u0000\u0000\u00e1\u00e2\u0003`0\u0000\u00e2\u00e3\u0005]\u0000"+
		"\u0000\u00e3\u00ea\u0005\u0013\u0000\u0000\u00e4\u00eb\u0003T*\u0000\u00e5"+
		"\u00e6\u00058\u0000\u0000\u00e6\u00e7\u0003R)\u0000\u00e7\u00e8\u0005"+
		"9\u0000\u0000\u00e8\u00eb\u0001\u0000\u0000\u0000\u00e9\u00eb\u0003J%"+
		"\u0000\u00ea\u00e4\u0001\u0000\u0000\u0000\u00ea\u00e5\u0001\u0000\u0000"+
		"\u0000\u00ea\u00e9\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000"+
		"\u0000\u00ec\u00ed\u0003\f\u0006\u0000\u00ed\u0019\u0001\u0000\u0000\u0000"+
		"\u00ee\u00ef\u0005\r\u0000\u0000\u00ef\u00f0\u0005<\u0000\u0000\u00f0"+
		"\u00f1\u0003J%\u0000\u00f1\u00f2\u0005=\u0000\u0000\u00f2\u00f5\u0003"+
		"\f\u0006\u0000\u00f3\u00f4\u0005\u000e\u0000\u0000\u00f4\u00f6\u0003\f"+
		"\u0006\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000"+
		"\u0000\u0000\u00f6\u001b\u0001\u0000\u0000\u0000\u00f7\u00fa\u0005\u0010"+
		"\u0000\u0000\u00f8\u00fb\u0003J%\u0000\u00f9\u00fb\u0003P(\u0000\u00fa"+
		"\u00f8\u0001\u0000\u0000\u0000\u00fa\u00f9\u0001\u0000\u0000\u0000\u00fa"+
		"\u00fb\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc"+
		"\u00fd\u0005?\u0000\u0000\u00fd\u001d\u0001\u0000\u0000\u0000\u00fe\u00ff"+
		"\u0005\u0012\u0000\u0000\u00ff\u0100\u0005<\u0000\u0000\u0100\u0101\u0003"+
		"J%\u0000\u0101\u0102\u0005=\u0000\u0000\u0102\u0103\u0003\f\u0006\u0000"+
		"\u0103\u001f\u0001\u0000\u0000\u0000\u0104\u0105\u0005\u0014\u0000\u0000"+
		"\u0105\u0106\u0005<\u0000\u0000\u0106\u0107\u0003J%\u0000\u0107\u0108"+
		"\u0005=\u0000\u0000\u0108\u010c\u0005:\u0000\u0000\u0109\u010b\u0003\""+
		"\u0011\u0000\u010a\u0109\u0001\u0000\u0000\u0000\u010b\u010e\u0001\u0000"+
		"\u0000\u0000\u010c\u010a\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000"+
		"\u0000\u0000\u010d\u010f\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000"+
		"\u0000\u0000\u010f\u0110\u0005;\u0000\u0000\u0110!\u0001\u0000\u0000\u0000"+
		"\u0111\u0112\u0005\u0015\u0000\u0000\u0112\u0113\u0003|>\u0000\u0113\u0114"+
		"\u0003\b\u0004\u0000\u0114\u0118\u0001\u0000\u0000\u0000\u0115\u0116\u0005"+
		"\u0016\u0000\u0000\u0116\u0118\u0003\b\u0004\u0000\u0117\u0111\u0001\u0000"+
		"\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0118#\u0001\u0000\u0000"+
		"\u0000\u0119\u011b\u00056\u0000\u0000\u011a\u011c\u0003\u0080@\u0000\u011b"+
		"\u011a\u0001\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c"+
		"\u011d\u0001\u0000\u0000\u0000\u011d\u011e\u0005?\u0000\u0000\u011e%\u0001"+
		"\u0000\u0000\u0000\u011f\u0121\u0005\t\u0000\u0000\u0120\u0122\u0003h"+
		"4\u0000\u0121\u0120\u0001\u0000\u0000\u0000\u0121\u0122\u0001\u0000\u0000"+
		"\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0124\u0003\b\u0004\u0000"+
		"\u0124\'\u0001\u0000\u0000\u0000\u0125\u0126\u00053\u0000\u0000\u0126"+
		"\u0128\u0003h4\u0000\u0127\u0129\u0003\u0080@\u0000\u0128\u0127\u0001"+
		"\u0000\u0000\u0000\u0128\u0129\u0001\u0000\u0000\u0000\u0129\u012a\u0001"+
		"\u0000\u0000\u0000\u012a\u012b\u0005?\u0000\u0000\u012b)\u0001\u0000\u0000"+
		"\u0000\u012c\u012e\u0003^/\u0000\u012d\u012c\u0001\u0000\u0000\u0000\u012e"+
		"\u0131\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u012f"+
		"\u0130\u0001\u0000\u0000\u0000\u0130\u0132\u0001\u0000\u0000\u0000\u0131"+
		"\u012f\u0001\u0000\u0000\u0000\u0132\u0138\u0005]\u0000\u0000\u0133\u0135"+
		"\u0005<\u0000\u0000\u0134\u0136\u0003|>\u0000\u0135\u0134\u0001\u0000"+
		"\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136\u0137\u0001\u0000"+
		"\u0000\u0000\u0137\u0139\u0005=\u0000\u0000\u0138\u0133\u0001\u0000\u0000"+
		"\u0000\u0138\u0139\u0001\u0000\u0000\u0000\u0139\u013b\u0001\u0000\u0000"+
		"\u0000\u013a\u013c\u0003h4\u0000\u013b\u013a\u0001\u0000\u0000\u0000\u013b"+
		"\u013c\u0001\u0000\u0000\u0000\u013c\u013d\u0001\u0000\u0000\u0000\u013d"+
		"\u013e\u0003\u0080@\u0000\u013e\u013f\u0005?\u0000\u0000\u013f\u0156\u0001"+
		"\u0000\u0000\u0000\u0140\u0142\u0003^/\u0000\u0141\u0140\u0001\u0000\u0000"+
		"\u0000\u0142\u0145\u0001\u0000\u0000\u0000\u0143\u0141\u0001\u0000\u0000"+
		"\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144\u0146\u0001\u0000\u0000"+
		"\u0000\u0145\u0143\u0001\u0000\u0000\u0000\u0146\u014c\u0005,\u0000\u0000"+
		"\u0147\u0149\u0005<\u0000\u0000\u0148\u014a\u0003|>\u0000\u0149\u0148"+
		"\u0001\u0000\u0000\u0000\u0149\u014a\u0001\u0000\u0000\u0000\u014a\u014b"+
		"\u0001\u0000\u0000\u0000\u014b\u014d\u0005=\u0000\u0000\u014c\u0147\u0001"+
		"\u0000\u0000\u0000\u014c\u014d\u0001\u0000\u0000\u0000\u014d\u014f\u0001"+
		"\u0000\u0000\u0000\u014e\u0150\u0003h4\u0000\u014f\u014e\u0001\u0000\u0000"+
		"\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0152\u0001\u0000\u0000"+
		"\u0000\u0151\u0153\u0003\u0080@\u0000\u0152\u0151\u0001\u0000\u0000\u0000"+
		"\u0152\u0153\u0001\u0000\u0000\u0000\u0153\u0154\u0001\u0000\u0000\u0000"+
		"\u0154\u0156\u0005?\u0000\u0000\u0155\u012f\u0001\u0000\u0000\u0000\u0155"+
		"\u0143\u0001\u0000\u0000\u0000\u0156+\u0001\u0000\u0000\u0000\u0157\u015a"+
		"\u0003P(\u0000\u0158\u0159\u0005C\u0000\u0000\u0159\u015b\u0003Z-\u0000"+
		"\u015a\u0158\u0001\u0000\u0000\u0000\u015a\u015b\u0001\u0000\u0000\u0000"+
		"\u015b\u015c\u0001\u0000\u0000\u0000\u015c\u015d\u0005?\u0000\u0000\u015d"+
		"-\u0001\u0000\u0000\u0000\u015e\u015f\u00054\u0000\u0000\u015f\u0160\u0003"+
		"p8\u0000\u0160\u0161\u0005?\u0000\u0000\u0161/\u0001\u0000\u0000\u0000"+
		"\u0162\u0163\u0005\n\u0000\u0000\u0163\u0164\u0005]\u0000\u0000\u0164"+
		"\u0165\u0005B\u0000\u0000\u0165\u0166\u0003L&\u0000\u0166\u0167\u0005"+
		"?\u0000\u0000\u01671\u0001\u0000\u0000\u0000\u0168\u016b\u0003`0\u0000"+
		"\u0169\u016b\u0003d2\u0000\u016a\u0168\u0001\u0000\u0000\u0000\u016a\u0169"+
		"\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000\u0000\u0000\u016c\u016f"+
		"\u0005]\u0000\u0000\u016d\u016e\u0005B\u0000\u0000\u016e\u0170\u0003N"+
		"\'\u0000\u016f\u016d\u0001\u0000\u0000\u0000\u016f\u0170\u0001\u0000\u0000"+
		"\u0000\u0170\u0171\u0001\u0000\u0000\u0000\u0171\u0172\u0005?\u0000\u0000"+
		"\u01723\u0001\u0000\u0000\u0000\u0173\u0174\u0005\u001b\u0000\u0000\u0174"+
		"\u0175\u0003`0\u0000\u0175\u0176\u0005]\u0000\u0000\u0176\u0177\u0005"+
		"B\u0000\u0000\u0177\u0178\u0003N\'\u0000\u0178\u0179\u0005?\u0000\u0000"+
		"\u01795\u0001\u0000\u0000\u0000\u017a\u017d\u0007\u0000\u0000\u0000\u017b"+
		"\u017e\u0003`0\u0000\u017c\u017e\u0003d2\u0000\u017d\u017b\u0001\u0000"+
		"\u0000\u0000\u017d\u017c\u0001\u0000\u0000\u0000\u017e\u017f\u0001\u0000"+
		"\u0000\u0000\u017f\u0180\u0005]\u0000\u0000\u0180\u0181\u0005?\u0000\u0000"+
		"\u01817\u0001\u0000\u0000\u0000\u0182\u0183\u0007\u0001\u0000\u0000\u0183"+
		"\u0185\u0005]\u0000\u0000\u0184\u0186\u0003h4\u0000\u0185\u0184\u0001"+
		"\u0000\u0000\u0000\u0185\u0186\u0001\u0000\u0000\u0000\u0186\u0187\u0001"+
		"\u0000\u0000\u0000\u0187\u0188\u0005?\u0000\u0000\u01889\u0001\u0000\u0000"+
		"\u0000\u0189\u018a\u0003b1\u0000\u018a\u018b\u0005]\u0000\u0000\u018b"+
		"\u018c\u0005?\u0000\u0000\u018c;\u0001\u0000\u0000\u0000\u018d\u018e\u0005"+
		"\u0004\u0000\u0000\u018e\u018f\u0005]\u0000\u0000\u018f\u0191\u0005<\u0000"+
		"\u0000\u0190\u0192\u0003v;\u0000\u0191\u0190\u0001\u0000\u0000\u0000\u0191"+
		"\u0192\u0001\u0000\u0000\u0000\u0192\u0193\u0001\u0000\u0000\u0000\u0193"+
		"\u0195\u0005=\u0000\u0000\u0194\u0196\u0003\\.\u0000\u0195\u0194\u0001"+
		"\u0000\u0000\u0000\u0195\u0196\u0001\u0000\u0000\u0000\u0196\u0197\u0001"+
		"\u0000\u0000\u0000\u0197\u0198\u0003\b\u0004\u0000\u0198=\u0001\u0000"+
		"\u0000\u0000\u0199\u019a\u0005\b\u0000\u0000\u019a\u019b\u0005]\u0000"+
		"\u0000\u019b\u019d\u0005<\u0000\u0000\u019c\u019e\u0003\u0082A\u0000\u019d"+
		"\u019c\u0001\u0000\u0000\u0000\u019d\u019e\u0001\u0000\u0000\u0000\u019e"+
		"\u019f\u0001\u0000\u0000\u0000\u019f\u01a1\u0005=\u0000\u0000\u01a0\u01a2"+
		"\u0003\\.\u0000\u01a1\u01a0\u0001\u0000\u0000\u0000\u01a1\u01a2\u0001"+
		"\u0000\u0000\u0000\u01a2\u01a3\u0001\u0000\u0000\u0000\u01a3\u01a4\u0005"+
		"?\u0000\u0000\u01a4?\u0001\u0000\u0000\u0000\u01a5\u01a6\u0005\u0007\u0000"+
		"\u0000\u01a6\u01ac\u0005]\u0000\u0000\u01a7\u01a9\u0005<\u0000\u0000\u01a8"+
		"\u01aa\u0003~?\u0000\u01a9\u01a8\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001"+
		"\u0000\u0000\u0000\u01aa\u01ab\u0001\u0000\u0000\u0000\u01ab\u01ad\u0005"+
		"=\u0000\u0000\u01ac\u01a7\u0001\u0000\u0000\u0000\u01ac\u01ad\u0001\u0000"+
		"\u0000\u0000\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae\u01af\u0003~?\u0000"+
		"\u01af\u01b0\u0003\b\u0004\u0000\u01b0A\u0001\u0000\u0000\u0000\u01b1"+
		"\u01b2\u0003Z-\u0000\u01b2\u01b5\u0007\u0002\u0000\u0000\u01b3\u01b6\u0003"+
		"J%\u0000\u01b4\u01b6\u0003P(\u0000\u01b5\u01b3\u0001\u0000\u0000\u0000"+
		"\u01b5\u01b4\u0001\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000"+
		"\u01b7\u01b8\u0005?\u0000\u0000\u01b8C\u0001\u0000\u0000\u0000\u01b9\u01ba"+
		"\u0003J%\u0000\u01ba\u01bb\u0005?\u0000\u0000\u01bbE\u0001\u0000\u0000"+
		"\u0000\u01bc\u01bd\u0005\u0005\u0000\u0000\u01bd\u01bf\u0005:\u0000\u0000"+
		"\u01be\u01c0\u0005q\u0000\u0000\u01bf\u01be\u0001\u0000\u0000\u0000\u01bf"+
		"\u01c0\u0001\u0000\u0000\u0000\u01c0\u01c1\u0001\u0000\u0000\u0000\u01c1"+
		"\u01c2\u0005;\u0000\u0000\u01c2G\u0001\u0000\u0000\u0000\u01c3\u01c4\u0005"+
		"\u0006\u0000\u0000\u01c4\u01ca\u0003j5\u0000\u01c5\u01c7\u0005<\u0000"+
		"\u0000\u01c6\u01c8\u0003x<\u0000\u01c7\u01c6\u0001\u0000\u0000\u0000\u01c7"+
		"\u01c8\u0001\u0000\u0000\u0000\u01c8\u01c9\u0001\u0000\u0000\u0000\u01c9"+
		"\u01cb\u0005=\u0000\u0000\u01ca\u01c5\u0001\u0000\u0000\u0000\u01ca\u01cb"+
		"\u0001\u0000\u0000\u0000\u01cb\u01cc\u0001\u0000\u0000\u0000\u01cc\u01ce"+
		"\u0003z=\u0000\u01cd\u01cf\u0003\\.\u0000\u01ce\u01cd\u0001\u0000\u0000"+
		"\u0000\u01ce\u01cf\u0001\u0000\u0000\u0000\u01cf\u01d0\u0001\u0000\u0000"+
		"\u0000\u01d0\u01d2\u0005:\u0000\u0000\u01d1\u01d3\u0005q\u0000\u0000\u01d2"+
		"\u01d1\u0001\u0000\u0000\u0000\u01d2\u01d3\u0001\u0000\u0000\u0000\u01d3"+
		"\u01d4\u0001\u0000\u0000\u0000\u01d4\u01d5\u0005;\u0000\u0000\u01d5I\u0001"+
		"\u0000\u0000\u0000\u01d6\u01d7\u0006%\uffff\uffff\u0000\u01d7\u01d8\u0005"+
		"<\u0000\u0000\u01d8\u01d9\u0003J%\u0000\u01d9\u01da\u0005=\u0000\u0000"+
		"\u01da\u01f2\u0001\u0000\u0000\u0000\u01db\u01dc\u0007\u0003\u0000\u0000"+
		"\u01dc\u01f2\u0003J%\u000f\u01dd\u01e0\u0003`0\u0000\u01de\u01e0\u0003"+
		"d2\u0000\u01df\u01dd\u0001\u0000\u0000\u0000\u01df\u01de\u0001\u0000\u0000"+
		"\u0000\u01e0\u01e1\u0001\u0000\u0000\u0000\u01e1\u01e2\u0005<\u0000\u0000"+
		"\u01e2\u01e3\u0003J%\u0000\u01e3\u01e4\u0005=\u0000\u0000\u01e4\u01f2"+
		"\u0001\u0000\u0000\u0000\u01e5\u01e6\u00052\u0000\u0000\u01e6\u01e7\u0005"+
		"<\u0000\u0000\u01e7\u01e8\u0003\b\u0004\u0000\u01e8\u01e9\u0005=\u0000"+
		"\u0000\u01e9\u01f2\u0001\u0000\u0000\u0000\u01ea\u01eb\u0005]\u0000\u0000"+
		"\u01eb\u01ed\u0005<\u0000\u0000\u01ec\u01ee\u0003|>\u0000\u01ed\u01ec"+
		"\u0001\u0000\u0000\u0000\u01ed\u01ee\u0001\u0000\u0000\u0000\u01ee\u01ef"+
		"\u0001\u0000\u0000\u0000\u01ef\u01f2\u0005=\u0000\u0000\u01f0\u01f2\u0007"+
		"\u0004\u0000\u0000\u01f1\u01d6\u0001\u0000\u0000\u0000\u01f1\u01db\u0001"+
		"\u0000\u0000\u0000\u01f1\u01df\u0001\u0000\u0000\u0000\u01f1\u01e5\u0001"+
		"\u0000\u0000\u0000\u01f1\u01ea\u0001\u0000\u0000\u0000\u01f1\u01f0\u0001"+
		"\u0000\u0000\u0000\u01f2\u0218\u0001\u0000\u0000\u0000\u01f3\u01f4\n\u0010"+
		"\u0000\u0000\u01f4\u01f5\u0005H\u0000\u0000\u01f5\u0217\u0003J%\u0010"+
		"\u01f6\u01f7\n\u000e\u0000\u0000\u01f7\u01f8\u0007\u0005\u0000\u0000\u01f8"+
		"\u0217\u0003J%\u000f\u01f9\u01fa\n\r\u0000\u0000\u01fa\u01fb\u0007\u0006"+
		"\u0000\u0000\u01fb\u0217\u0003J%\u000e\u01fc\u01fd\n\f\u0000\u0000\u01fd"+
		"\u01fe\u0005V\u0000\u0000\u01fe\u0217\u0003J%\r\u01ff\u0200\n\u000b\u0000"+
		"\u0000\u0200\u0201\u0005U\u0000\u0000\u0201\u0217\u0003J%\f\u0202\u0203"+
		"\n\n\u0000\u0000\u0203\u0204\u0005S\u0000\u0000\u0204\u0217\u0003J%\u000b"+
		"\u0205\u0206\n\t\u0000\u0000\u0206\u0207\u0005M\u0000\u0000\u0207\u0217"+
		"\u0003J%\n\u0208\u0209\n\b\u0000\u0000\u0209\u020a\u0005O\u0000\u0000"+
		"\u020a\u0217\u0003J%\t\u020b\u020c\n\u0007\u0000\u0000\u020c\u020d\u0005"+
		"K\u0000\u0000\u020d\u0217\u0003J%\b\u020e\u020f\n\u0006\u0000\u0000\u020f"+
		"\u0210\u0005N\u0000\u0000\u0210\u0217\u0003J%\u0007\u0211\u0212\n\u0005"+
		"\u0000\u0000\u0212\u0213\u0005L\u0000\u0000\u0213\u0217\u0003J%\u0006"+
		"\u0214\u0215\n\u0011\u0000\u0000\u0215\u0217\u0003X,\u0000\u0216\u01f3"+
		"\u0001\u0000\u0000\u0000\u0216\u01f6\u0001\u0000\u0000\u0000\u0216\u01f9"+
		"\u0001\u0000\u0000\u0000\u0216\u01fc\u0001\u0000\u0000\u0000\u0216\u01ff"+
		"\u0001\u0000\u0000\u0000\u0216\u0202\u0001\u0000\u0000\u0000\u0216\u0205"+
		"\u0001\u0000\u0000\u0000\u0216\u0208\u0001\u0000\u0000\u0000\u0216\u020b"+
		"\u0001\u0000\u0000\u0000\u0216\u020e\u0001\u0000\u0000\u0000\u0216\u0211"+
		"\u0001\u0000\u0000\u0000\u0216\u0214\u0001\u0000\u0000\u0000\u0217\u021a"+
		"\u0001\u0000\u0000\u0000\u0218\u0216\u0001\u0000\u0000\u0000\u0218\u0219"+
		"\u0001\u0000\u0000\u0000\u0219K\u0001\u0000\u0000\u0000\u021a\u0218\u0001"+
		"\u0000\u0000\u0000\u021b\u0220\u0003J%\u0000\u021c\u021d\u0005E\u0000"+
		"\u0000\u021d\u021f\u0003J%\u0000\u021e\u021c\u0001\u0000\u0000\u0000\u021f"+
		"\u0222\u0001\u0000\u0000\u0000\u0220\u021e\u0001\u0000\u0000\u0000\u0220"+
		"\u0221\u0001\u0000\u0000\u0000\u0221M\u0001\u0000\u0000\u0000\u0222\u0220"+
		"\u0001\u0000\u0000\u0000\u0223\u0227\u0003V+\u0000\u0224\u0227\u0003J"+
		"%\u0000\u0225\u0227\u0003P(\u0000\u0226\u0223\u0001\u0000\u0000\u0000"+
		"\u0226\u0224\u0001\u0000\u0000\u0000\u0226\u0225\u0001\u0000\u0000\u0000"+
		"\u0227O\u0001\u0000\u0000\u0000\u0228\u0229\u00055\u0000\u0000\u0229\u022a"+
		"\u0003p8\u0000\u022aQ\u0001\u0000\u0000\u0000\u022b\u022d\u0003J%\u0000"+
		"\u022c\u022b\u0001\u0000\u0000\u0000\u022c\u022d\u0001\u0000\u0000\u0000"+
		"\u022d\u022e\u0001\u0000\u0000\u0000\u022e\u0230\u0005>\u0000\u0000\u022f"+
		"\u0231\u0003J%\u0000\u0230\u022f\u0001\u0000\u0000\u0000\u0230\u0231\u0001"+
		"\u0000\u0000\u0000\u0231\u0234\u0001\u0000\u0000\u0000\u0232\u0233\u0005"+
		">\u0000\u0000\u0233\u0235\u0003J%\u0000\u0234\u0232\u0001\u0000\u0000"+
		"\u0000\u0234\u0235\u0001\u0000\u0000\u0000\u0235S\u0001\u0000\u0000\u0000"+
		"\u0236\u0237\u0005:\u0000\u0000\u0237\u023c\u0003J%\u0000\u0238\u0239"+
		"\u0005A\u0000\u0000\u0239\u023b\u0003J%\u0000\u023a\u0238\u0001\u0000"+
		"\u0000\u0000\u023b\u023e\u0001\u0000\u0000\u0000\u023c\u023a\u0001\u0000"+
		"\u0000\u0000\u023c\u023d\u0001\u0000\u0000\u0000\u023d\u0240\u0001\u0000"+
		"\u0000\u0000\u023e\u023c\u0001\u0000\u0000\u0000\u023f\u0241\u0005A\u0000"+
		"\u0000\u0240\u023f\u0001\u0000\u0000\u0000\u0240\u0241\u0001\u0000\u0000"+
		"\u0000\u0241\u0242\u0001\u0000\u0000\u0000\u0242\u0243\u0005;\u0000\u0000"+
		"\u0243U\u0001\u0000\u0000\u0000\u0244\u0247\u0005:\u0000\u0000\u0245\u0248"+
		"\u0003J%\u0000\u0246\u0248\u0003V+\u0000\u0247\u0245\u0001\u0000\u0000"+
		"\u0000\u0247\u0246\u0001\u0000\u0000\u0000\u0248\u0250\u0001\u0000\u0000"+
		"\u0000\u0249\u024c\u0005A\u0000\u0000\u024a\u024d\u0003J%\u0000\u024b"+
		"\u024d\u0003V+\u0000\u024c\u024a\u0001\u0000\u0000\u0000\u024c\u024b\u0001"+
		"\u0000\u0000\u0000\u024d\u024f\u0001\u0000\u0000\u0000\u024e\u0249\u0001"+
		"\u0000\u0000\u0000\u024f\u0252\u0001\u0000\u0000\u0000\u0250\u024e\u0001"+
		"\u0000\u0000\u0000\u0250\u0251\u0001\u0000\u0000\u0000\u0251\u0254\u0001"+
		"\u0000\u0000\u0000\u0252\u0250\u0001\u0000\u0000\u0000\u0253\u0255\u0005"+
		"A\u0000\u0000\u0254\u0253\u0001\u0000\u0000\u0000\u0254\u0255\u0001\u0000"+
		"\u0000\u0000\u0255\u0256\u0001\u0000\u0000\u0000\u0256\u0257\u0005;\u0000"+
		"\u0000\u0257W\u0001\u0000\u0000\u0000\u0258\u026b\u00058\u0000\u0000\u0259"+
		"\u026c\u0003T*\u0000\u025a\u025d\u0003J%\u0000\u025b\u025d\u0003R)\u0000"+
		"\u025c\u025a\u0001\u0000\u0000\u0000\u025c\u025b\u0001\u0000\u0000\u0000"+
		"\u025d\u0265\u0001\u0000\u0000\u0000\u025e\u0261\u0005A\u0000\u0000\u025f"+
		"\u0262\u0003J%\u0000\u0260\u0262\u0003R)\u0000\u0261\u025f\u0001\u0000"+
		"\u0000\u0000\u0261\u0260\u0001\u0000\u0000\u0000\u0262\u0264\u0001\u0000"+
		"\u0000\u0000\u0263\u025e\u0001\u0000\u0000\u0000\u0264\u0267\u0001\u0000"+
		"\u0000\u0000\u0265\u0263\u0001\u0000\u0000\u0000\u0265\u0266\u0001\u0000"+
		"\u0000\u0000\u0266\u0269\u0001\u0000\u0000\u0000\u0267\u0265\u0001\u0000"+
		"\u0000\u0000\u0268\u026a\u0005A\u0000\u0000\u0269\u0268\u0001\u0000\u0000"+
		"\u0000\u0269\u026a\u0001\u0000\u0000\u0000\u026a\u026c\u0001\u0000\u0000"+
		"\u0000\u026b\u0259\u0001\u0000\u0000\u0000\u026b\u025c\u0001\u0000\u0000"+
		"\u0000\u026c\u026d\u0001\u0000\u0000\u0000\u026d\u026e\u00059\u0000\u0000"+
		"\u026eY\u0001\u0000\u0000\u0000\u026f\u0273\u0005]\u0000\u0000\u0270\u0272"+
		"\u0003X,\u0000\u0271\u0270\u0001\u0000\u0000\u0000\u0272\u0275\u0001\u0000"+
		"\u0000\u0000\u0273\u0271\u0001\u0000\u0000\u0000\u0273\u0274\u0001\u0000"+
		"\u0000\u0000\u0274[\u0001\u0000\u0000\u0000\u0275\u0273\u0001\u0000\u0000"+
		"\u0000\u0276\u0277\u0005C\u0000\u0000\u0277\u0278\u0003`0\u0000\u0278"+
		"]\u0001\u0000\u0000\u0000\u0279\u0287\u0005-\u0000\u0000\u027a\u027b\u0005"+
		".\u0000\u0000\u027b\u027c\u0005<\u0000\u0000\u027c\u027d\u0003J%\u0000"+
		"\u027d\u027e\u0005=\u0000\u0000\u027e\u0287\u0001\u0000\u0000\u0000\u027f"+
		"\u0284\u0007\u0007\u0000\u0000\u0280\u0281\u0005<\u0000\u0000\u0281\u0282"+
		"\u0003J%\u0000\u0282\u0283\u0005=\u0000\u0000\u0283\u0285\u0001\u0000"+
		"\u0000\u0000\u0284\u0280\u0001\u0000\u0000\u0000\u0284\u0285\u0001\u0000"+
		"\u0000\u0000\u0285\u0287\u0001\u0000\u0000\u0000\u0286\u0279\u0001\u0000"+
		"\u0000\u0000\u0286\u027a\u0001\u0000\u0000\u0000\u0286\u027f\u0001\u0000"+
		"\u0000\u0000\u0287\u0288\u0001\u0000\u0000\u0000\u0288\u0289\u0005P\u0000"+
		"\u0000\u0289_\u0001\u0000\u0000\u0000\u028a\u028c\u0005\"\u0000\u0000"+
		"\u028b\u028d\u0003h4\u0000\u028c\u028b\u0001\u0000\u0000\u0000\u028c\u028d"+
		"\u0001\u0000\u0000\u0000\u028d\u02a9\u0001\u0000\u0000\u0000\u028e\u0290"+
		"\u0005#\u0000\u0000\u028f\u0291\u0003h4\u0000\u0290\u028f\u0001\u0000"+
		"\u0000\u0000\u0290\u0291\u0001\u0000\u0000\u0000\u0291\u02a9\u0001\u0000"+
		"\u0000\u0000\u0292\u0294\u0005$\u0000\u0000\u0293\u0295\u0003h4\u0000"+
		"\u0294\u0293\u0001\u0000\u0000\u0000\u0294\u0295\u0001\u0000\u0000\u0000"+
		"\u0295\u02a9\u0001\u0000\u0000\u0000\u0296\u0298\u0005%\u0000\u0000\u0297"+
		"\u0299\u0003h4\u0000\u0298\u0297\u0001\u0000\u0000\u0000\u0298\u0299\u0001"+
		"\u0000\u0000\u0000\u0299\u02a9\u0001\u0000\u0000\u0000\u029a\u029c\u0005"+
		"&\u0000\u0000\u029b\u029d\u0003h4\u0000\u029c\u029b\u0001\u0000\u0000"+
		"\u0000\u029c\u029d\u0001\u0000\u0000\u0000\u029d\u02a9\u0001\u0000\u0000"+
		"\u0000\u029e\u02a9\u0005!\u0000\u0000\u029f\u02a9\u0005*\u0000\u0000\u02a0"+
		"\u02a9\u0005+\u0000\u0000\u02a1\u02a6\u0005\'\u0000\u0000\u02a2\u02a3"+
		"\u00058\u0000\u0000\u02a3\u02a4\u0003`0\u0000\u02a4\u02a5\u00059\u0000"+
		"\u0000\u02a5\u02a7\u0001\u0000\u0000\u0000\u02a6\u02a2\u0001\u0000\u0000"+
		"\u0000\u02a6\u02a7\u0001\u0000\u0000\u0000\u02a7\u02a9\u0001\u0000\u0000"+
		"\u0000\u02a8\u028a\u0001\u0000\u0000\u0000\u02a8\u028e\u0001\u0000\u0000"+
		"\u0000\u02a8\u0292\u0001\u0000\u0000\u0000\u02a8\u0296\u0001\u0000\u0000"+
		"\u0000\u02a8\u029a\u0001\u0000\u0000\u0000\u02a8\u029e\u0001\u0000\u0000"+
		"\u0000\u02a8\u029f\u0001\u0000\u0000\u0000\u02a8\u02a0\u0001\u0000\u0000"+
		"\u0000\u02a8\u02a1\u0001\u0000\u0000\u0000\u02a9a\u0001\u0000\u0000\u0000"+
		"\u02aa\u02ac\u0005\u001f\u0000\u0000\u02ab\u02ad\u0003h4\u0000\u02ac\u02ab"+
		"\u0001\u0000\u0000\u0000\u02ac\u02ad\u0001\u0000\u0000\u0000\u02adc\u0001"+
		"\u0000\u0000\u0000\u02ae\u02af\u0005(\u0000\u0000\u02af\u02b0\u00058\u0000"+
		"\u0000\u02b0\u02b1\u0003`0\u0000\u02b1\u02b2\u0005A\u0000\u0000\u02b2"+
		"\u02b3\u0003|>\u0000\u02b3\u02b4\u00059\u0000\u0000\u02b4e\u0001\u0000"+
		"\u0000\u0000\u02b5\u02b6\u0007\b\u0000\u0000\u02b6\u02b7\u0005(\u0000"+
		"\u0000\u02b7\u02b8\u00058\u0000\u0000\u02b8\u02b9\u0003`0\u0000\u02b9"+
		"\u02be\u0005A\u0000\u0000\u02ba\u02bf\u0003|>\u0000\u02bb\u02bc\u0005"+
		"1\u0000\u0000\u02bc\u02bd\u0005B\u0000\u0000\u02bd\u02bf\u0003J%\u0000"+
		"\u02be\u02ba\u0001\u0000\u0000\u0000\u02be\u02bb\u0001\u0000\u0000\u0000"+
		"\u02bf\u02c0\u0001\u0000\u0000\u0000\u02c0\u02c1\u00059\u0000\u0000\u02c1"+
		"g\u0001\u0000\u0000\u0000\u02c2\u02c3\u00058\u0000\u0000\u02c3\u02c4\u0003"+
		"J%\u0000\u02c4\u02c5\u00059\u0000\u0000\u02c5i\u0001\u0000\u0000\u0000"+
		"\u02c6\u02c7\u0007\t\u0000\u0000\u02c7k\u0001\u0000\u0000\u0000\u02c8"+
		"\u02cb\u0003J%\u0000\u02c9\u02cb\u0003t:\u0000\u02ca\u02c8\u0001\u0000"+
		"\u0000\u0000\u02ca\u02c9\u0001\u0000\u0000\u0000\u02cbm\u0001\u0000\u0000"+
		"\u0000\u02cc\u02cd\u0007\n\u0000\u0000\u02cdo\u0001\u0000\u0000\u0000"+
		"\u02ce\u02d1\u0003Z-\u0000\u02cf\u02d1\u0005^\u0000\u0000\u02d0\u02ce"+
		"\u0001\u0000\u0000\u0000\u02d0\u02cf\u0001\u0000\u0000\u0000\u02d1q\u0001"+
		"\u0000\u0000\u0000\u02d2\u02d9\u0003`0\u0000\u02d3\u02d9\u0003f3\u0000"+
		"\u02d4\u02d6\u0005 \u0000\u0000\u02d5\u02d7\u0003h4\u0000\u02d6\u02d5"+
		"\u0001\u0000\u0000\u0000\u02d6\u02d7\u0001\u0000\u0000\u0000\u02d7\u02d9"+
		"\u0001\u0000\u0000\u0000\u02d8\u02d2\u0001\u0000\u0000\u0000\u02d8\u02d3"+
		"\u0001\u0000\u0000\u0000\u02d8\u02d4\u0001\u0000\u0000\u0000\u02d9s\u0001"+
		"\u0000\u0000\u0000\u02da\u02db\u0003`0\u0000\u02db\u02dc\u0005]\u0000"+
		"\u0000\u02dc\u02e9\u0001\u0000\u0000\u0000\u02dd\u02de\u0003b1\u0000\u02de"+
		"\u02df\u0005]\u0000\u0000\u02df\u02e9\u0001\u0000\u0000\u0000\u02e0\u02e1"+
		"\u0007\u0001\u0000\u0000\u02e1\u02e3\u0005]\u0000\u0000\u02e2\u02e4\u0003"+
		"h4\u0000\u02e3\u02e2\u0001\u0000\u0000\u0000\u02e3\u02e4\u0001\u0000\u0000"+
		"\u0000\u02e4\u02e9\u0001\u0000\u0000\u0000\u02e5\u02e6\u0003f3\u0000\u02e6"+
		"\u02e7\u0005]\u0000\u0000\u02e7\u02e9\u0001\u0000\u0000\u0000\u02e8\u02da"+
		"\u0001\u0000\u0000\u0000\u02e8\u02dd\u0001\u0000\u0000\u0000\u02e8\u02e0"+
		"\u0001\u0000\u0000\u0000\u02e8\u02e5\u0001\u0000\u0000\u0000\u02e9u\u0001"+
		"\u0000\u0000\u0000\u02ea\u02ef\u0003t:\u0000\u02eb\u02ec\u0005A\u0000"+
		"\u0000\u02ec\u02ee\u0003t:\u0000\u02ed\u02eb\u0001\u0000\u0000\u0000\u02ee"+
		"\u02f1\u0001\u0000\u0000\u0000\u02ef\u02ed\u0001\u0000\u0000\u0000\u02ef"+
		"\u02f0\u0001\u0000\u0000\u0000\u02f0\u02f3\u0001\u0000\u0000\u0000\u02f1"+
		"\u02ef\u0001\u0000\u0000\u0000\u02f2\u02f4\u0005A\u0000\u0000\u02f3\u02f2"+
		"\u0001\u0000\u0000\u0000\u02f3\u02f4\u0001\u0000\u0000\u0000\u02f4w\u0001"+
		"\u0000\u0000\u0000\u02f5\u02fa\u0003l6\u0000\u02f6\u02f7\u0005A\u0000"+
		"\u0000\u02f7\u02f9\u0003l6\u0000\u02f8\u02f6\u0001\u0000\u0000\u0000\u02f9"+
		"\u02fc\u0001\u0000\u0000\u0000\u02fa\u02f8\u0001\u0000\u0000\u0000\u02fa"+
		"\u02fb\u0001\u0000\u0000\u0000\u02fb\u02fe\u0001\u0000\u0000\u0000\u02fc"+
		"\u02fa\u0001\u0000\u0000\u0000\u02fd\u02ff\u0005A\u0000\u0000\u02fe\u02fd"+
		"\u0001\u0000\u0000\u0000\u02fe\u02ff\u0001\u0000\u0000\u0000\u02ffy\u0001"+
		"\u0000\u0000\u0000\u0300\u0305\u0003n7\u0000\u0301\u0302\u0005A\u0000"+
		"\u0000\u0302\u0304\u0003n7\u0000\u0303\u0301\u0001\u0000\u0000\u0000\u0304"+
		"\u0307\u0001\u0000\u0000\u0000\u0305\u0303\u0001\u0000\u0000\u0000\u0305"+
		"\u0306\u0001\u0000\u0000\u0000\u0306\u0309\u0001\u0000\u0000\u0000\u0307"+
		"\u0305\u0001\u0000\u0000\u0000\u0308\u030a\u0005A\u0000\u0000\u0309\u0308"+
		"\u0001\u0000\u0000\u0000\u0309\u030a\u0001\u0000\u0000\u0000\u030a{\u0001"+
		"\u0000\u0000\u0000\u030b\u0310\u0003J%\u0000\u030c\u030d\u0005A\u0000"+
		"\u0000\u030d\u030f\u0003J%\u0000\u030e\u030c\u0001\u0000\u0000\u0000\u030f"+
		"\u0312\u0001\u0000\u0000\u0000\u0310\u030e\u0001\u0000\u0000\u0000\u0310"+
		"\u0311\u0001\u0000\u0000\u0000\u0311\u0314\u0001\u0000\u0000\u0000\u0312"+
		"\u0310\u0001\u0000\u0000\u0000\u0313\u0315\u0005A\u0000\u0000\u0314\u0313"+
		"\u0001\u0000\u0000\u0000\u0314\u0315\u0001\u0000\u0000\u0000\u0315}\u0001"+
		"\u0000\u0000\u0000\u0316\u031b\u0005]\u0000\u0000\u0317\u0318\u0005A\u0000"+
		"\u0000\u0318\u031a\u0005]\u0000\u0000\u0319\u0317\u0001\u0000\u0000\u0000"+
		"\u031a\u031d\u0001\u0000\u0000\u0000\u031b\u0319\u0001\u0000\u0000\u0000"+
		"\u031b\u031c\u0001\u0000\u0000\u0000\u031c\u031f\u0001\u0000\u0000\u0000"+
		"\u031d\u031b\u0001\u0000\u0000\u0000\u031e\u0320\u0005A\u0000\u0000\u031f"+
		"\u031e\u0001\u0000\u0000\u0000\u031f\u0320\u0001\u0000\u0000\u0000\u0320"+
		"\u007f\u0001\u0000\u0000\u0000\u0321\u0326\u0003p8\u0000\u0322\u0323\u0005"+
		"A\u0000\u0000\u0323\u0325\u0003p8\u0000\u0324\u0322\u0001\u0000\u0000"+
		"\u0000\u0325\u0328\u0001\u0000\u0000\u0000\u0326\u0324\u0001\u0000\u0000"+
		"\u0000\u0326\u0327\u0001\u0000\u0000\u0000\u0327\u032a\u0001\u0000\u0000"+
		"\u0000\u0328\u0326\u0001\u0000\u0000\u0000\u0329\u032b\u0005A\u0000\u0000"+
		"\u032a\u0329\u0001\u0000\u0000\u0000\u032a\u032b\u0001\u0000\u0000\u0000"+
		"\u032b\u0081\u0001\u0000\u0000\u0000\u032c\u0331\u0003r9\u0000\u032d\u032e"+
		"\u0005A\u0000\u0000\u032e\u0330\u0003r9\u0000\u032f\u032d\u0001\u0000"+
		"\u0000\u0000\u0330\u0333\u0001\u0000\u0000\u0000\u0331\u032f\u0001\u0000"+
		"\u0000\u0000\u0331\u0332\u0001\u0000\u0000\u0000\u0332\u0335\u0001\u0000"+
		"\u0000\u0000\u0333\u0331\u0001\u0000\u0000\u0000\u0334\u0336\u0005A\u0000"+
		"\u0000\u0335\u0334\u0001\u0000\u0000\u0000\u0335\u0336\u0001\u0000\u0000"+
		"\u0000\u0336\u0083\u0001\u0000\u0000\u0000`\u0085\u008a\u0097\u00b7\u00b9"+
		"\u00bd\u00c3\u00cd\u00ea\u00f5\u00fa\u010c\u0117\u011b\u0121\u0128\u012f"+
		"\u0135\u0138\u013b\u0143\u0149\u014c\u014f\u0152\u0155\u015a\u016a\u016f"+
		"\u017d\u0185\u0191\u0195\u019d\u01a1\u01a9\u01ac\u01b5\u01bf\u01c7\u01ca"+
		"\u01ce\u01d2\u01df\u01ed\u01f1\u0216\u0218\u0220\u0226\u022c\u0230\u0234"+
		"\u023c\u0240\u0247\u024c\u0250\u0254\u025c\u0261\u0265\u0269\u026b\u0273"+
		"\u0284\u0286\u028c\u0290\u0294\u0298\u029c\u02a6\u02a8\u02ac\u02be\u02ca"+
		"\u02d0\u02d6\u02d8\u02e3\u02e8\u02ef\u02f3\u02fa\u02fe\u0305\u0309\u0310"+
		"\u0314\u031b\u031f\u0326\u032a\u0331\u0335";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}