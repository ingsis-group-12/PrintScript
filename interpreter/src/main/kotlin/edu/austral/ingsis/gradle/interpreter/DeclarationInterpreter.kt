package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.DeclarationNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

/**
 * Interpreter for only declaring variables
 */

class DeclarationInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as DeclarationNode

        if (isAlreadyDeclared(context, node)) {
            throw RuntimeException("Variable ${node.identifierNode.name} already declared")
        }

        return when (node.keyword.value) {
            "let" -> {
                val newContext =
                    Context(declaredVariablesAndConstants = mapOf(node.identifierNode.name to node.nodeType))
                InterpretResult.ContextResult(newContext)
            }
            "const" -> {
                throw RuntimeException("Cannot declare a constant without initialization")
            }
            else -> throw RuntimeException("Invalid keyword: ${node.keyword.value}")
        }
    }

    private fun isAlreadyDeclared(
        context: Context,
        node: DeclarationNode,
    ): Boolean {
        return context.isInContext(node.identifierNode.name)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is DeclarationNode
    }
}
