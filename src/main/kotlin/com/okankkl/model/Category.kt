package com.okankkl.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class Category(
    var id : Int,
    var name : String,
    var product_count : Long? = null
)
object Categories : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name",50)
    override val primaryKey = PrimaryKey(id)
}