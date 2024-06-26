package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.Declaration
import edu.austral.ingsis.gradle.common.ast.DeclarationNode
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class DeclarationNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is DeclarationNode) return ReportSuccess
        return analyzeDeclaration(ast, rules)
    }

    private fun analyzeDeclaration(
        declaration: Declaration,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val report = rules.map { it.verify(declaration) }
        return generateReport(report)
    }
}
