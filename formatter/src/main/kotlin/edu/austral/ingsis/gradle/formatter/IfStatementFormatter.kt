package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode

class IfStatementFormatter : Formatter<AST> {
    override fun format(node: AST): String {
        return when (node) {
            is ProgramNode -> formatChildren(node.children)
            is IfStatement -> formatIfStatement(node)
            else -> ""
        }
    }

    private fun formatChildren(nodes: List<AST>): String {
        val formattedDeclarations =
            nodes.map { child ->
                when (child) {
                    is IfStatement -> formatIfStatement(child)
                    else -> ""
                }
            }
        return formattedDeclarations.joinToString("")
    }

    private fun formatIfStatement(node: IfStatement): String {
        val condition = ExpressionFormatter().format(node.condition)
        val block = formatIfBlock(node.ifBlock.children)
        return "if ($condition) {\n${block}\n}"
    }

    // este vendria a ser el caso inicial donde tengo una lista de nodos - visitor?
    // format()? crearComposeFormatter aca adentro?
    private fun formatIfBlock(nodes: List<AST>): String {
        val formattedDeclarations =
            nodes.map { child ->
                when (child) {
                    // Todas las posibilidades??
                    is DeclarationAssignation -> DeclarationAssignationFormatter().format(child)
                    is PrintLnNode -> PrintLnFormatter().format(child)
                    is ReadInputNode -> ReadInputFormatter().format(child)
                    is IdentifierNode -> IdentifierFormatter().format(child)

                    is SumNode -> "${ExpressionFormatter().format(child.left)} + ${ExpressionFormatter().format(child.right)}"
                    is SubtractNode -> "${ExpressionFormatter().format(child.left)} - ${
                        ExpressionFormatter().format(
                            child.right,
                        )
                    }"

                    is MultiplyNode -> "${ExpressionFormatter().format(child.left)} * ${
                        ExpressionFormatter().format(
                            child.right,
                        )
                    }"

                    is DivideNode -> "${ExpressionFormatter().format(child.left)} / ${ExpressionFormatter().format(child.right)}"

                    is StringLiteral -> child.value
                    is NumberLiteralNode -> child.value.toString()
                    is BooleanLiteralNode -> child.value.toString()

                    else -> ""
                }
            }
        return formattedDeclarations.joinToString("")
    }
}
