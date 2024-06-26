package edu.austral.ingsis.gradle.formatter.rule.adapter

import kotlinx.serialization.Serializable

@Serializable
data class RuleJson(
    val type: String,
    val allowed: Boolean,
    val maxInt: Int?,
)
