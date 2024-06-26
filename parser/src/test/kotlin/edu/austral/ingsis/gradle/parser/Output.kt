package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.ast.BlockNode
import edu.austral.ingsis.gradle.common.ast.ConstKeywordNode
import edu.austral.ingsis.gradle.common.ast.DeclarationAssignationNode
import edu.austral.ingsis.gradle.common.ast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.DivideNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.IfElseStatementNode
import edu.austral.ingsis.gradle.common.ast.IfStatementNode
import edu.austral.ingsis.gradle.common.ast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.StringLiteralNode
import edu.austral.ingsis.gradle.common.ast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition

val output_001 = NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1)))

val output_002 =
    SumNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_003 =
    SumNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        StringLiteralNode("hola", TokenPosition(Position(1, 1), Position(1, 1))),
        StringLiteralNode("loco", TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_004 =
    MultiplyNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_005 =
    DivideNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_006 =
    DeclarationNode(
        LetKeywordNode(TokenPosition(Position(1, 1), Position(1, 4))),
        TokenPosition(Position(1, 1), Position(1, 4)),
        NumberNodeType,
        IdentifierNode("a", TokenPosition(Position(1, 5), Position(1, 6))),
    )

val output_007 =
    DeclarationAssignationNode(
        LetKeywordNode(TokenPosition(Position(1, 1), Position(1, 4))),
        TokenPosition(Position(1, 16), Position(1, 17)),
        NumberNodeType,
        IdentifierNode("a", TokenPosition(Position(1, 5), Position(1, 6))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 18), Position(1, 19))),
    )

val output_008 =
    DeclarationAssignationNode(
        LetKeywordNode(TokenPosition(Position(1, 1), Position(1, 4))),
        TokenPosition(Position(1, 16), Position(1, 17)),
        NumberNodeType,
        IdentifierNode("a", TokenPosition(Position(1, 5), Position(1, 6))),
        NumberLiteralNode(
            5.0,
            TokenPosition(Position(1, 18), Position(1, 19)),
        ),
    )

//
val output_009 =
    PrintLnNode(
        TokenPosition(Position(1, 1), Position(1, 8)),
        SumNode(
            TokenPosition(Position(1, 12), Position(1, 12)),
            NumberLiteralNode(5.0, TokenPosition(Position(1, 10), Position(1, 11))),
            NumberLiteralNode(7.0, TokenPosition(Position(1, 12), Position(1, 13))),
        ),
    )

//
val output_010 =
    MultiplyNode(
        TokenPosition(Position(1, 6), Position(1, 7)),
        SumNode(
            TokenPosition(Position(1, 3), Position(1, 4)),
            NumberLiteralNode(5.0, TokenPosition(Position(1, 2), Position(1, 3))),
            NumberLiteralNode(7.0, TokenPosition(Position(1, 4), Position(1, 5))),
        ),
        NumberLiteralNode(
            4.0,
            TokenPosition(Position(1, 7), Position(1, 8)),
        ),
    )

//
val output_011 =
    ReadInputNode(
        TokenPosition(Position(1, 1), Position(1, 4)),
        StringLiteralNode("hola lucho", TokenPosition(Position(1, 6), Position(1, 7))),
    )

val output_012 =
    ReadInputNode(
        TokenPosition(Position(1, 1), Position(1, 4)),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 6), Position(1, 7))),
    )

val output_013 =
    ReadEnvNode(
        TokenPosition(Position(1, 1), Position(1, 4)),
        "LUCHO_ENV",
    )

val output_014 =
    IfStatementNode(
        TokenPosition(Position(1, 1), Position(1, 4)),
        IdentifierNode("a", TokenPosition(Position(1, 6), Position(1, 7))),
        BlockNode(
            TokenPosition(Position(2, 1), Position(2, 4)),
            listOf(
                PrintLnNode(
                    TokenPosition(Position(2, 1), Position(2, 4)),
                    StringLiteralNode("a is true", TokenPosition(Position(2, 6), Position(2, 7))),
                ),
            ),
        ),
    )

val output_015 =
    IfElseStatementNode(
        TokenPosition(Position(1, 1), Position(1, 4)),
        IdentifierNode("a", TokenPosition(Position(1, 6), Position(1, 7))),
        BlockNode(
            TokenPosition(Position(2, 1), Position(2, 4)),
            listOf(
                PrintLnNode(
                    TokenPosition(Position(2, 1), Position(2, 4)),
                    StringLiteralNode("a is true", TokenPosition(Position(2, 6), Position(2, 7))),
                ),
            ),
        ),
        BlockNode(
            TokenPosition(Position(4, 1), Position(4, 4)),
            listOf(
                PrintLnNode(
                    TokenPosition(Position(4, 1), Position(4, 4)),
                    StringLiteralNode("a is false", TokenPosition(Position(4, 6), Position(4, 7))),
                ),
            ),
        ),
    )

val output_016 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            ReadEnvNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                "LUCHO_ENV",
            ),
            IfElseStatementNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
                BlockNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    listOf(
                        PrintLnNode(
                            TokenPosition(Position(0, 0), Position(0, 0)),
                            StringLiteralNode("a is true", TokenPosition(Position(0, 0), Position(0, 0))),
                        ),
                    ),
                ),
                BlockNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    listOf(
                        PrintLnNode(
                            TokenPosition(Position(0, 0), Position(0, 0)),
                            StringLiteralNode("a is false", TokenPosition(Position(0, 0), Position(0, 0))),
                        ),
                    ),
                ),
            ),
        ),
    )

val output_017 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignationNode(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                TokenPosition(Position(0, 0), Position(0, 0)),
                NumberNodeType,
                IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
                NumberLiteralNode(5.0, TokenPosition(Position(0, 0), Position(0, 0))),
            ),
            DeclarationAssignationNode(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                TokenPosition(Position(0, 0), Position(0, 0)),
                NumberNodeType,
                IdentifierNode("b", TokenPosition(Position(0, 0), Position(0, 0))),
                NumberLiteralNode(6.0, TokenPosition(Position(0, 0), Position(0, 0))),
            ),
            PrintLnNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                SumNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    IdentifierNode(
                        "a",
                        TokenPosition(Position(0, 0), Position(0, 0)),
                    ),
                    IdentifierNode(
                        "b",
                        TokenPosition(Position(0, 0), Position(0, 0)),
                    ),
                ),
            ),
        ),
    )

val output_018 =
    DeclarationAssignationNode(
        ConstKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringNodeType,
        IdentifierNode("b", TokenPosition(Position(0, 0), Position(0, 0))),
        StringLiteralNode("\"this should be valid in 1.1\"", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val output_019 =
    DeclarationAssignationNode(
        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberNodeType,
        IdentifierNode("cuenta", TokenPosition(Position(0, 0), Position(0, 0))),
        SumNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            SubtractNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                MultiplyNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    NumberLiteralNode(
                        5.0,
                        TokenPosition(Position(0, 0), Position(0, 0)),
                    ),
                    NumberLiteralNode(
                        5.0,
                        TokenPosition(Position(0, 0), Position(0, 0)),
                    ),
                ),
                DivideNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    NumberLiteralNode(
                        8.0,
                        TokenPosition(Position(0, 0), Position(0, 0)),
                    ),
                    NumberLiteralNode(
                        4.0,
                        TokenPosition(Position(0, 0), Position(0, 0)),
                    ),
                ),
            ),
            NumberLiteralNode(
                2.0,
                TokenPosition(Position(0, 0), Position(0, 0)),
            ),
        ),
    )
