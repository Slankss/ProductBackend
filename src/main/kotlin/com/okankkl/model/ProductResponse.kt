package com.okankkl.model

import com.okankkl.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse (
    val body : List<Product>,
    val count : Int
)