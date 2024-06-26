package edu.austral.ingsis.gradle.formatter.rule.adapter

import edu.austral.ingsis.gradle.formatter.rule.adapter.context.RuleContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File

class RuleParser {
    fun parseRulesFromFile(
        filePath: String,
        ruleContext: RuleContext,
    ): List<RuleJson> {
        val json = File(filePath).readText()
        return parseRules(json, ruleContext)
    }

    private fun parseRules(
        json: String,
        ruleContext: RuleContext,
    ): List<RuleJson> {
        val jsonObject = Json.parseToJsonElement(json).jsonObject
        val contextRulesJsonArray = jsonObject[ruleContext.toString()]!!.jsonArray

        val contextRules = parseRuleDataArray(contextRulesJsonArray)

        return contextRules
    }

    private fun parseRuleDataArray(jsonArray: JsonArray): List<RuleJson> {
        return jsonArray.map { element ->
            val ruleJson = element.jsonObject
            RuleJson(
                type = ruleJson["type"]!!.jsonPrimitive.content,
                allowed = ruleJson["allowed"]!!.jsonPrimitive.boolean,
                maxInt = ruleJson["maxInt"]?.jsonPrimitive?.int,
            )
        }
    }
}
