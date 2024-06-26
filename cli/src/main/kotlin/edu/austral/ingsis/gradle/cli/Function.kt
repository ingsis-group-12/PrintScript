package edu.austral.ingsis.gradle.cli

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.formatter.createBlockRules
import edu.austral.ingsis.gradle.formatter.createDefaultFormatter
import edu.austral.ingsis.gradle.formatter.createDefaultRules
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.formatter.rule.Rules
import edu.austral.ingsis.gradle.interpreter.ComposeInterpreter
import edu.austral.ingsis.gradle.interpreter.util.KotlinEnvReader
import edu.austral.ingsis.gradle.interpreter.util.KotlinInputReader
import edu.austral.ingsis.gradle.interpreter.util.KotlinPrinter
import edu.austral.ingsis.gradle.iterator.LexerIterator
import edu.austral.ingsis.gradle.iterator.ParserIterator
import edu.austral.ingsis.gradle.lexer.director.LexerDirector
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.factory.createComposeParser
import edu.austral.ingsis.gradle.parser.impl.ProgramNodeParser
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.adapter.FileToJsonAdapter
import edu.austral.ingsis.gradle.sca.analyzer.DefaultAnalyzer
import java.io.File

interface Function<in T, out O> {
    fun evaluate(
        input: T,
        version: String,
    ): O
}

class ExecuteFunction : Function<String, List<Any>> {
    override fun evaluate(
        input: String,
        version: String,
    ): List<Any> {
        val versionAdapted = version.take(3)
        val lexer = LexerDirector().createComposeLexer(versionAdapted)
        val lexerIterator = LexerIterator(lexer, input.byteInputStream().bufferedReader())
        val composeParser = createComposeParser()
        val parserIterator = ParserIterator(lexerIterator, composeParser)
        var interpreter =
            ComposeInterpreter(
                emitter = KotlinPrinter(),
                envReader = KotlinEnvReader(),
                inputReader = KotlinInputReader(),
            )
        while (parserIterator.hasNext()) {
            val ast = parserIterator.next()
            if (ast != null) {
                interpreter = interpreter.interpretAndUpdateContext(ast)
            }
        }
        return listOf(interpreter.getContext())
    }
}

class AnalyzeFunction : Function<Pair<String, File>, ReportResult> {
    override fun evaluate(
        input: Pair<String, File>,
        version: String,
    ): ReportResult {
        val ast = createAstNode(input, version).first
        val sca = FileToJsonAdapter().adapt(input.second)
        val composeRule = sca as edu.austral.ingsis.gradle.sca.rule.ComposeRule
        println("Analyzing...\n")
        return DefaultAnalyzer().analyze(ast, composeRule.getRules())
    }
}

class FormatFunction : Function<Pair<String, File>, String> {
    override fun evaluate(
        input: Pair<String, File>,
        version: String,
    ): String {
        val ast = createAstNode(input, version)
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules(input.second.absolutePath))
        val blockRules = ComposeRule(createBlockRules(input.second.absolutePath))
        val rules = Rules(defaultRules, blockRules)
        println("Formatting...")
        return formatter.format(ast.first, rules)
    }
}

private fun createAstNode(
    input: Pair<String, File>,
    version: String,
): Pair<AST, Int> {
    val adaptedVersion = version.take(3)
    val lexer = LexerDirector().createComposeLexer(adaptedVersion)
    println("Lexing...")
    val tokenList = lexer.splitIntoTokens(input.first)
    val parser = ProgramNodeParser()
    println("Parsing...")
    return parser.parse(InputContext(tokenList))
}
