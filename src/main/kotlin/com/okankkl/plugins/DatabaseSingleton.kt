package com.okankkl.plugins

import com.okankkl.model.Categories
import com.okankkl.model.Products
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseSingleton {

    fun init() {
        var driverName = "org.h2.Driver"
        var jdbcUrl = "jdbc:h2:file:./build/db"
        val database = Database.connect(jdbcUrl,driverName)
        transaction {
            SchemaUtils.create(Products)
            SchemaUtils.create(Categories)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

}