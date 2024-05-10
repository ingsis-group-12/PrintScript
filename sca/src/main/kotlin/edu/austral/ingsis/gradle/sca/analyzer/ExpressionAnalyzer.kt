package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.Expression
import edu.austral.ingsis.gradle.common.ast.Function
import edu.austral.ingsis.gradle.common.ast.Operand
import edu.austral.ingsis.gradle.common.ast.Operator
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class ExpressionAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is Expression) return ReportSuccess
        return analyzeExpression(ast, rules)
    }

    private fun analyzeExpression(
        expression: Expression,
        rules: List<Rule<AST>>,
    ): ReportResult {
        return when (expression) {
            is Operand -> OperandAnalyzer().analyze(expression, rules)
            is Operator -> OperatorAnalyzer().analyze(expression, rules)
            is Function -> FunctionAnalyzer().analyze(expression, rules)
            else -> {
                val report = rules.map { it.verify(expression) }
                return generateReport(report)
            }
        }
    }
}