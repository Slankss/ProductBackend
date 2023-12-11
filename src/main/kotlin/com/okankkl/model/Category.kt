package com.okankkl.model

import org.jetbrains.exposed.sql.*

data class Category(
    val id : Int,
    val name : String
)

object Categories : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name",50)
    override val primaryKey = PrimaryKey(id)
}