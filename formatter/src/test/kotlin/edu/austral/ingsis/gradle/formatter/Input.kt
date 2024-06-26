import edu.austral.ingsis.gradle.common.ast.BlockNode
import edu.austral.ingsis.gradle.common.ast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.DeclarationAssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.IfElseStatementNode
import edu.austral.ingsis.gradle.common.ast.IfStatementNode
import edu.austral.ingsis.gradle.common.ast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.ReAssignationNode
import edu.austral.ingsis.gradle.common.ast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.StringLiteralNode
import edu.austral.ingsis.gradle.common.ast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.SumNode
import edu.austral.ingsis.gradle.common.token.defaultTokenPosition

val input001 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            DeclarationAssignationNode(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("aBlue", defaultTokenPosition()),
                StringLiteralNode("blue", defaultTokenPosition()),
            ),
        ),
    )

val input002 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            IfStatementNode(
                defaultTokenPosition(),
                condition = IdentifierNode("a", defaultTokenPosition()),
                ifBlock =
                    BlockNode(
                        defaultTokenPosition(),
                        listOf(
                            PrintLnNode(
                                defaultTokenPosition(),
                                SumNode(
                                    defaultTokenPosition(),
                                    IdentifierNode(
                                        "a",
                                        defaultTokenPosition(),
                                    ),
                                    IdentifierNode(
                                        "b",
                                        defaultTokenPosition(),
                                    ),
                                ),
                            ),
                            DeclarationAssignationNode(
                                LetKeywordNode(defaultTokenPosition()),
                                defaultTokenPosition(),
                                StringNodeType,
                                IdentifierNode("aBlue", defaultTokenPosition()),
                                StringLiteralNode("blue", defaultTokenPosition()),
                            ),
                        ),
                    ),
            ),
        ),
    )

val input003 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            IfElseStatementNode(
                defaultTokenPosition(),
                condition = IdentifierNode("a", defaultTokenPosition()),
                ifBlock =
                    BlockNode(
                        defaultTokenPosition(),
                        listOf(
                            PrintLnNode(
                                defaultTokenPosition(),
                                SumNode(
                                    defaultTokenPosition(),
                                    IdentifierNode(
                                        "a",
                                        defaultTokenPosition(),
                                    ),
                                    IdentifierNode(
                                        "b",
                                        defaultTokenPosition(),
                                    ),
                                ),
                            ),
                        ),
                    ),
                elseBlock =
                    BlockNode(
                        defaultTokenPosition(),
                        listOf(
                            PrintLnNode(
                                defaultTokenPosition(),
                                SubtractNode(
                                    defaultTokenPosition(),
                                    IdentifierNode(
                                        "a",
                                        defaultTokenPosition(),
                                    ),
                                    IdentifierNode(
                                        "b",
                                        defaultTokenPosition(),
                                    ),
                                ),
                            ),
                        ),
                    ),
            ),
        ),
    )

val input004 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            ReAssignationNode(
                defaultTokenPosition(),
                NumberLiteralNode(
                    1,
                    defaultTokenPosition(),
                ),
                IdentifierNode("a", defaultTokenPosition()),
            ),
        ),
    )

val input005 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            DeclarationAssignationNode(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("aBlue", defaultTokenPosition()),
                StringLiteralNode("blue", defaultTokenPosition()),
            ),
            ReAssignationNode(
                defaultTokenPosition(),
                NumberLiteralNode(
                    1,
                    defaultTokenPosition(),
                ),
                IdentifierNode("a", defaultTokenPosition()),
            ),
            DeclarationAssignationNode(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("aBlue", defaultTokenPosition()),
                StringLiteralNode("blue", defaultTokenPosition()),
            ),
        ),
    )

val input006 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            PrintLnNode(
                defaultTokenPosition(),
                SumNode(
                    defaultTokenPosition(),
                    IdentifierNode(
                        "a",
                        defaultTokenPosition(),
                    ),
                    IdentifierNode(
                        "b",
                        defaultTokenPosition(),
                    ),
                ),
            ),
            DeclarationAssignationNode(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("a", defaultTokenPosition()),
                ReadInputNode(
                    defaultTokenPosition(),
                    StringLiteralNode("Introduzca un valor", defaultTokenPosition()),
                ),
            ),
        ),
    )

val input007 =
    DeclarationAssignationNode(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        StringNodeType,
        IdentifierNode("input", defaultTokenPosition()),
        ReadEnvNode(
            defaultTokenPosition(),
            "PATH",
        ),
    )

val input008 =
    DeclarationAssignationNode(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        BooleanNodeType,
        IdentifierNode("b", defaultTokenPosition()),
        BooleanLiteralNode(true, defaultTokenPosition()),
    )

val input009 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            DeclarationAssignationNode(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("a", defaultTokenPosition()),
                ReadEnvNode(
                    defaultTokenPosition(),
                    "PATH",
                ),
            ),
            DeclarationAssignationNode(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                BooleanNodeType,
                IdentifierNode("b", defaultTokenPosition()),
                BooleanLiteralNode(true, defaultTokenPosition()),
            ),
        ),
    )

val input009_reversed =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            DeclarationAssignationNode(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                BooleanNodeType,
                IdentifierNode("b", defaultTokenPosition()),
                BooleanLiteralNode(true, defaultTokenPosition()),
            ),
            DeclarationAssignationNode(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("a", defaultTokenPosition()),
                ReadEnvNode(
                    defaultTokenPosition(),
                    "PATH",
                ),
            ),
        ),
    )
