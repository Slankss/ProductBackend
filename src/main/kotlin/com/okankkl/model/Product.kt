package com.okankkl.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*


@Serializable
data class Product(
    val id : Int = 0,
    val name : String,
    val description : String,
    val categoryId : Int,
    val quantity : Int,
    val price : Double,
    val categoryName : String? = null
)

object Products : Table(){
    val id = integer("id").autoIncrement()
    val name = varchar("name",50)
    val description = varchar("description",200)
    val categoryId = integer("category_id") references Categories.id
    val quantity = integer("quality")
    val price = double("price")
    override val primaryKey = PrimaryKey(id)
}