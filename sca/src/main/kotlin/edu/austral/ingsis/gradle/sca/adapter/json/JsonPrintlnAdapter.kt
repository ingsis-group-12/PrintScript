package edu.austral.ingsis.gradle.sca.adapter.json

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.adapter.ScaAdapter
import edu.austral.ingsis.gradle.sca.rule.DisabledRule
import edu.austral.ingsis.gradle.sca.rule.PrintlnRule
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonPrimitive

class JsonPrintlnAdapter(private val expectedInput: String = "enforceLiteralOrIdentifierInPrintlnRule") :
    ScaAdapter<JsonObject> {
    /**
     * Println rule is optional, so if its disabled returns a disable rule that contains a ReportSuccess as default
     **/

    override fun adapt(input: JsonObject): Rule<AST> {
        if (input.containsKey(expectedInput)) {
            val isEnable = input.getValue(expectedInput).jsonPrimitive.boolean // If it's not boolean fails.
            if (isEnable) {
                return PrintlnRule()
            }
        }
        return DisabledRule()
    }
}
