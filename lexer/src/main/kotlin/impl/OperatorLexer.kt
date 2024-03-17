package lexer.impl

import common.token.*
import lexer.Lexer
import lexer.util.RegexPatterns
import lexer.util.createToken
import lexer.util.isInQuotes

class OperatorLexer : Lexer {
    private val regex = RegexPatterns.OPERATOR_REGEX

    private val tokens: Map<String, TokenType> = mapOf(
        "+" to Plus,
        "-" to Minus,
        "*" to Multiply,
        "/" to Divide,
        "=" to Assignation,
        "(" to LeftParenthesis,
        ")" to RightParenthesis,
        ":" to Colon,
        ";" to SemiColon
    )

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { matchResult ->
            if (tokens.containsKey(matchResult.value) && !isInQuotes(matchResult, code)) {
                createToken(matchResult, code, tokens.getValue(matchResult.value))
            } else null
        }.toList()
    }
}