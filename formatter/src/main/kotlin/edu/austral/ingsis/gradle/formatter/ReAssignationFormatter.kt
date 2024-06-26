package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.ReAssignationNode
import edu.austral.ingsis.gradle.formatter.rule.Rules

class ReAssignationFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is ReAssignationNode) throw IllegalArgumentException("$node is not a ReAssignationNode")

        val identifier = node.identifierNode.name
        val composeFormatter = createDefaultFormatter()
        val expression = composeFormatter.format(node.expression, rules)
        val result = "$identifier = $expression;"
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReAssignationNode
    }
}
