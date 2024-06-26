package edu.austral.ingsis.gradle.sca.adapter.json

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.adapter.ScaAdapter
import edu.austral.ingsis.gradle.sca.rule.ComposeRule
import kotlinx.serialization.json.JsonObject

class JsonComposeAdapter(
    private val adapters: List<ScaAdapter<JsonObject>> =
        listOf(
            JsonIdentifierAdapter(),
            JsonPrintlnAdapter(),
            JsonReadInputAdapter(),
        ),
) :
    ScaAdapter<JsonObject> {
    override fun adapt(input: JsonObject): Rule<AST> {
        return ComposeRule(adapters.map { it.adapt(input) })
    }
}
