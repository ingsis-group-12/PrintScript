package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.StringLiteralNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
import edu.austral.ingsis.gradle.interpreter.util.castToDesiredType

class StringLiteralInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as StringLiteralNode

        return InterpretResult.OperationResult(node.value)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is StringLiteralNode
    }
}

class NumberLiteralInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as NumberLiteralNode

        return InterpretResult.OperationResult(castToDesiredType(node.value))
    }

    override fun canInterpret(node: AST): Boolean {
        return node is NumberLiteralNode
    }
}

class BooleanLiteralInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as BooleanLiteralNode

        return InterpretResult.OperationResult(node.value)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is BooleanLiteralNode
    }
}
