package edu.austral.ingsis.gradle.parser.util

import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Colon
import edu.austral.ingsis.gradle.common.token.Divide
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.LeftParenthesis
import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.Minus
import edu.austral.ingsis.gradle.common.token.Multiply
import edu.austral.ingsis.gradle.common.token.NumberType
import edu.austral.ingsis.gradle.common.token.NumberValue
import edu.austral.ingsis.gradle.common.token.Plus
import edu.austral.ingsis.gradle.common.token.PrintlnKeyword
import edu.austral.ingsis.gradle.common.token.RightParenthesis
import edu.austral.ingsis.gradle.common.token.SemiColon
import edu.austral.ingsis.gradle.common.token.StringType
import edu.austral.ingsis.gradle.common.token.StringValue
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.impl.AssignationParser
import edu.austral.ingsis.gradle.parser.impl.ComposeParser
import edu.austral.ingsis.gradle.parser.impl.DeclarationParser
import edu.austral.ingsis.gradle.parser.impl.ExpressionParser
import edu.austral.ingsis.gradle.parser.impl.IdentifierParser
import edu.austral.ingsis.gradle.parser.impl.LeftParenthesisParser
import edu.austral.ingsis.gradle.parser.impl.PrintlnParser
import edu.austral.ingsis.gradle.parser.impl.ValueNumberParser
import edu.austral.ingsis.gradle.parser.impl.ValueStringParser

fun endOfFile(
    tokens: List<Token>,
    currentIndex: Int,
): Boolean {
    return currentIndex > (tokens.size - 1)
}

fun isColon(token: Token?): Boolean {
    return token != null && token.tokenType == Colon
}

fun isSemiColon(token: Token?): Boolean {
    return token != null && token.tokenType == SemiColon
}

fun isAssignation(token: Token?): Boolean {
    return token != null && token.tokenType == Assignation
}

fun isLetKeyword(token: Token?): Boolean {
    return token != null && token.tokenType == LetKeyword
}

fun isIdentifier(token: Token?): Boolean {
    return token != null && token.tokenType == Identifier
}

fun isType(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(NumberType, StringType))
}

fun isValue(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(NumberValue, StringValue))
}

fun isOperator(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(Plus, Minus, Multiply, Divide))
}

fun isAdditiveOperator(token: Token?): Boolean {
    return token != null && token.tokenType in setOf(Plus, Minus)
}

fun isMultiplicativeOperator(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(Multiply, Divide))
}

fun isLeftParenthesis(token: Token?): Boolean {
    return token != null && token.tokenType == LeftParenthesis
}

fun isRightParenthesis(token: Token?): Boolean {
    return token != null && token.tokenType == RightParenthesis
}

fun isParenthesis(token: Token?): Boolean {
    return token != null && token.tokenType in setOf(RightParenthesis, LeftParenthesis)
}

fun isPrintLn(token: Token?): Boolean {
    return token != null && token.tokenType == PrintlnKeyword
}

fun currentToken(
    list: List<Token>,
    index: Int,
): Token? {
    return if (index < list.size) list[index] else null
}

// Returns new index and the last token consumed.
fun consumeToken(
    list: List<Token>,
    index: Int,
): Pair<Token?, Int> {
    val current = currentToken(list, index)
    val nextIndex = index + 1
    return Pair(current, nextIndex)
}

fun createComposeParser(): ComposeParser {
    return ComposeParser(
        mapOf(
            PrintlnKeyword to PrintlnParser(),
            StringValue to ExpressionParser(EXPRESSION_PARSER_MAP),
            NumberValue to ExpressionParser(EXPRESSION_PARSER_MAP),
            LeftParenthesis to ExpressionParser(EXPRESSION_PARSER_MAP),
            LetKeyword to DeclarationParser(),
            Identifier to AssignationParser(),
        ),
    )
}

val EXPRESSION_PARSER_MAP =
    mapOf(
        NumberValue to ValueNumberParser(),
        StringValue to ValueStringParser(),
        LeftParenthesis to LeftParenthesisParser(),
        Identifier to IdentifierParser(),
    )
