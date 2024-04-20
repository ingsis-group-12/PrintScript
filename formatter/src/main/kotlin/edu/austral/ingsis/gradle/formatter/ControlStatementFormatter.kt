package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.formatter.rule.Rule

class IfElseStatementFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        return when (node) {
            is IfElseStatement -> {
                val composeFormatter = createDefaultFormatter()
                val condition = composeFormatter.format(node.condition, defaultRule, ifBlockRule)
                val ifBlock = formatIfBlock(node.ifBlock.children, defaultRule, ifBlockRule)
                val elseBlock = formatIfBlock(node.elseBlock.children, defaultRule, ifBlockRule)
                val result = "if ($condition) {\n${ifBlock}\n}else {\n${elseBlock}\n}"
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
        return node is IfElseStatement
    }

    private fun formatIfBlock(
        nodes: List<AST>,
        default: Rule,
        ifBlockRules: Rule,
    ): String {
        return IfStatementFormatter().formatIfBlock(nodes, default, ifBlockRules)
    }
}

class IfStatementFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        return when (node) {
            is IfStatement -> {
                val composeFormatter = createDefaultFormatter()
                val condition = composeFormatter.format(node.condition, defaultRule, ifBlockRule)
                val block = formatIfBlock(node.ifBlock.children, defaultRule, ifBlockRule)
                val result = "if ($condition) {\n${block}\n}"
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
        return node is IfStatement
    }

    fun formatIfBlock(
        nodes: List<AST>,
        defaultRule: Rule,
        ifBlockRules: Rule,
    ): String {
        val blockFormatter = createDefaultFormatter()
        val formattedBlock = nodes.joinToString("\n") { blockFormatter.format(it, defaultRule, ifBlockRules) }
        val lines = formattedBlock.split("\n")
        return lines.joinToString("\n") { applyFormat(it, ifBlockRules) }
    }
}
