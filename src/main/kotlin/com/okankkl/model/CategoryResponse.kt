package com.okankkl.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val body : List<Category>,
    val count : Int
)