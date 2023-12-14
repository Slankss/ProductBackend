package com.okankkl.model

import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    var state : Boolean,
    var errorMsg : String?
)
