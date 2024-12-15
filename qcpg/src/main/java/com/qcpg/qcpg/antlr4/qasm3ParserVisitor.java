package com.qcpg.qcpg.antlr4;
// Generated from qasm3Parser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link qasm3Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface qasm3ParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(qasm3Parser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#version}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersion(qasm3Parser.VersionContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(qasm3Parser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(qasm3Parser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScope(qasm3Parser.ScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#pragma}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPragma(qasm3Parser.PragmaContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#statementOrScope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementOrScope(qasm3Parser.StatementOrScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#calibrationGrammarStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalibrationGrammarStatement(qasm3Parser.CalibrationGrammarStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#includeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncludeStatement(qasm3Parser.IncludeStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(qasm3Parser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(qasm3Parser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#endStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndStatement(qasm3Parser.EndStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(qasm3Parser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(qasm3Parser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(qasm3Parser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(qasm3Parser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#switchStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStatement(qasm3Parser.SwitchStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#switchCaseItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCaseItem(qasm3Parser.SwitchCaseItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#barrierStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBarrierStatement(qasm3Parser.BarrierStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#boxStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoxStatement(qasm3Parser.BoxStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#delayStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelayStatement(qasm3Parser.DelayStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#gateCallStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGateCallStatement(qasm3Parser.GateCallStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#measureArrowAssignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeasureArrowAssignmentStatement(qasm3Parser.MeasureArrowAssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#resetStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResetStatement(qasm3Parser.ResetStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#aliasDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasDeclarationStatement(qasm3Parser.AliasDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#classicalDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassicalDeclarationStatement(qasm3Parser.ClassicalDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#constDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDeclarationStatement(qasm3Parser.ConstDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#ioDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIoDeclarationStatement(qasm3Parser.IoDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#oldStyleDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOldStyleDeclarationStatement(qasm3Parser.OldStyleDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#quantumDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantumDeclarationStatement(qasm3Parser.QuantumDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#defStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefStatement(qasm3Parser.DefStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#externStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternStatement(qasm3Parser.ExternStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#gateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGateStatement(qasm3Parser.GateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(qasm3Parser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(qasm3Parser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#calStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalStatement(qasm3Parser.CalStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#defcalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefcalStatement(qasm3Parser.DefcalStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseXorExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseXorExpression(qasm3Parser.BitwiseXorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additiveExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(qasm3Parser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code durationofExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDurationofExpression(qasm3Parser.DurationofExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisExpression(qasm3Parser.ParenthesisExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(qasm3Parser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicativeExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(qasm3Parser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalOrExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(qasm3Parser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code castExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(qasm3Parser.CastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code powerExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowerExpression(qasm3Parser.PowerExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseOrExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseOrExpression(qasm3Parser.BitwiseOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpression(qasm3Parser.CallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitshiftExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitshiftExpression(qasm3Parser.BitshiftExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseAndExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseAndExpression(qasm3Parser.BitwiseAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(qasm3Parser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalAndExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(qasm3Parser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indexExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexExpression(qasm3Parser.IndexExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(qasm3Parser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link qasm3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpression(qasm3Parser.LiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#aliasExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasExpression(qasm3Parser.AliasExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#declarationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationExpression(qasm3Parser.DeclarationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#measureExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeasureExpression(qasm3Parser.MeasureExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#rangeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeExpression(qasm3Parser.RangeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#setExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetExpression(qasm3Parser.SetExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#arrayLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteral(qasm3Parser.ArrayLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#indexOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexOperator(qasm3Parser.IndexOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#indexedIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexedIdentifier(qasm3Parser.IndexedIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#returnSignature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnSignature(qasm3Parser.ReturnSignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#gateModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGateModifier(qasm3Parser.GateModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#scalarType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarType(qasm3Parser.ScalarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#qubitType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQubitType(qasm3Parser.QubitTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(qasm3Parser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#arrayReferenceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayReferenceType(qasm3Parser.ArrayReferenceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#designator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesignator(qasm3Parser.DesignatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#defcalTarget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefcalTarget(qasm3Parser.DefcalTargetContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#defcalArgumentDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefcalArgumentDefinition(qasm3Parser.DefcalArgumentDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#defcalOperand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefcalOperand(qasm3Parser.DefcalOperandContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#gateOperand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGateOperand(qasm3Parser.GateOperandContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#externArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternArgument(qasm3Parser.ExternArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#argumentDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentDefinition(qasm3Parser.ArgumentDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#argumentDefinitionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentDefinitionList(qasm3Parser.ArgumentDefinitionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#defcalArgumentDefinitionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefcalArgumentDefinitionList(qasm3Parser.DefcalArgumentDefinitionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#defcalOperandList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefcalOperandList(qasm3Parser.DefcalOperandListContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(qasm3Parser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(qasm3Parser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#gateOperandList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGateOperandList(qasm3Parser.GateOperandListContext ctx);
	/**
	 * Visit a parse tree produced by {@link qasm3Parser#externArgumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternArgumentList(qasm3Parser.ExternArgumentListContext ctx);
}