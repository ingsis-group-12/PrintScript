package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.sca.util.CamelCaseRule
import edu.austral.ingsis.gradle.sca.util.SnakeCaseRule
import edu.austral.ingsis.gradle.sca.util.identifierRuleWithCustomErrorMap
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScaTest {
    @Test // let aBlue : String = "blue";
    fun test001_camelCaseRuleWithValidInputShouldReturnSuccess() {
        val rule = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val node = ASTNodeImpl("Program", null, ProgramNode, input_001)
        val result = rule.verify(node)
        assertEquals(ReportSuccess, result)
    }

    @Test // let a_Blue : String = "blue";
    fun test002_camelCaseRuleWithNoValidInputShouldReturnFailure() {
        val rule = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val node = ASTNodeImpl("Program", null, ProgramNode, input_002)
        val result = rule.verify(node)
        assert(result is ReportFailure)
    }

    @Test // let a_blue : String = "blue";
    fun test003_snakeCaseRuleWithValidInputShouldReturnSuccess() {
        val rule = IdentifierRule(SnakeCaseRule, identifierRuleWithCustomErrorMap)
        val node = ASTNodeImpl("Program", null, ProgramNode, input_002)
        val result = rule.verify(node)
        assert(result is ReportSuccess)
    }

    @Test // let a_Blue : String = "blue";
    fun test004_snakeCaseRuleWithNoValidInputShouldReturnFailure() {
        val rule = IdentifierRule(SnakeCaseRule, identifierRuleWithCustomErrorMap)
        val node = ASTNodeImpl("Program", null, ProgramNode, input_001)
        val result = rule.verify(node)
        assert(result is ReportFailure)
    }

    @Test // println(b);
    fun test005_printlnRuleWithValidInputShouldReturnSuccess() {
        val rule = PrintlnRule()
        val node = ASTNodeImpl("Program", null, ProgramNode, input_003)
        val result = rule.verify(node)
        assert(result is ReportSuccess)
    }

    @Test // println(a+b);
    fun test006_printlnRuleWithValidInputShouldReturnSuccess() {
        val rule = PrintlnRule()
        val node = ASTNodeImpl("Program", null, ProgramNode, input_004)
        val result = rule.verify(node)
        assert(result is ReportFailure)
    }

    @Test // let a_snake : String ="snake"; println(a_snake);
    fun test007_composeRuleWithValidInputShouldReturnSuccess() {
        val rule1 = IdentifierRule(SnakeCaseRule, identifierRuleWithCustomErrorMap)
        val rule2 = PrintlnRule()
        val composeRule = ComposeRule(listOf(rule1, rule2))
        val node = ASTNodeImpl("Program", null, ProgramNode, input_005)
        val result = composeRule.verify(node)
        assert(result is ReportSuccess)
    }

    @Test // let aCamel : string;
    fun test008_camelCaseRuleWithNoAssignationWithValidInputShouldReturnSuccess() {
        val rule = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val node = ASTNodeImpl("Program", null, ProgramNode, input_006)
        val result = rule.verify(node)
        assert(result is ReportSuccess)
    }

    @Test // let a_snake: string;
    fun test009_snakeCaseRuleWithNoAssignationWithValidInputShouldReturnSuccess() {
        val rule = IdentifierRule(SnakeCaseRule, identifierRuleWithCustomErrorMap)
        val node = ASTNodeImpl("Program", null, ProgramNode, input_007)
        val result = rule.verify(node)
        assert(result is ReportSuccess)
    }

    @Test // let a_snake : string;
    fun test010_camelCaseRuleWithNoAssignationWithInvalidInputShouldReturnFailure() {
        val rule = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val node = ASTNodeImpl("Program", null, ProgramNode, input_007)
        val result = rule.verify(node)
        assert(result is ReportFailure)
    }
}
