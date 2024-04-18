package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class ReadInputFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        return when (node) {
            is ReadInputNode -> {
                val value = ExpressionFormatter().format(node.expression, defaultRule, ifBlockRule)
                val result = "readInput($value);"
                return applyFormat(result, defaultRule)
            }

            else -> ""
        }
    }

    override fun applyFormat(
        result: String,
        rule: Rule,
    ): String {
        return rule.applyRule(result)
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReadInputNode
    }
}