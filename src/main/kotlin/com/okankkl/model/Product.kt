package com.okankkl.model

import org.jetbrains.exposed.sql.*

data class Product(
    val id : Int,
    val name : String,
    val description : String,
    val categoryId : Int,
    val quality : Int,
    val price : Double
)

object Products : Table(){
    val id = integer("id").autoIncrement()
    val name = varchar("name",50)
    val description = varchar("description",200)
    val categoryId = integer("category_id")
    val quality = integer("quality")
    val price = double("price")
    override val primaryKey = PrimaryKey(id)
}