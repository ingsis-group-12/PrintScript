import common.ast.ASTNode
import common.token.Token

interface Parser {
    fun parse(tokens: List<Token>): Pair<ASTNode,Int>
}