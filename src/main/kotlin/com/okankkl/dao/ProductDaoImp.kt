package com.okankkl.dao

import com.okankkl.model.Categories
import com.okankkl.model.Product
import com.okankkl.model.Products
import com.okankkl.plugins.DatabaseSingleton.dbQuery
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ProductDaoFacadeImp : ProductDao {

    private fun resultRowToProduct(row : ResultRow) = Product(
        id = row[Products.id],
        name = row[Products.name],
        description = row[Products.description],
        categoryId = row[Products.categoryId],
        price = row[Products.price],
        quantity = row[Products.quantity],
        categoryName = row[Categories.name]
    )

    override suspend fun getAllProducts(): List<Product> = dbQuery{
        (Products innerJoin Categories)
            .slice(Products.id,Products.name,Products.description,Products.categoryId,Products.price,Products.quantity,Categories.name)
            .selectAll()
            .map(::resultRowToProduct).toList()
    }

    override suspend fun getProduct(id: Int): Product? = dbQuery{
        (Products innerJoin Categories)
            .slice(Products.id,Products.name,Products.description,Products.categoryId,Products.price,Products.quantity,Categories.name)
            .select { Products.id eq id }
            .map(::resultRowToProduct)
            .singleOrNull()
    }

    override suspend fun addProduct(product: Product): Boolean = dbQuery{

        val insertStatement = Products.insert {
            it[name] = product.name
            it[description] = product.description
            it[price] = product.price
            it[quantity] = product.quantity
            it[categoryId] = product.categoryId
        }
        insertStatement.insertedCount > 0
    }

    override suspend fun isThereSameProductName(name: String): Boolean = dbQuery {
        (Products innerJoin Categories)
            .slice(Products.id,Products.name,Products.description,Products.categoryId,Products.price,Products.quantity,Categories.name)
            .select { Products.name eq name }
            .map(::resultRowToProduct)
            .isNotEmpty()
    }

    override suspend fun changeQuantity(id : Int, number: Int): Boolean = dbQuery {
        Products.update({ Products.id eq id}) {
            it[quantity] = number
        } > 0
    }
    override suspend fun updateProduct(product: Product): Boolean = dbQuery {
        Products.update({ Products.id eq product.id}) {
            it[name] = product.name
            it[description] = product.description
            it[price] = product.price
            it[quantity] = product.quantity
            it[categoryId] = product.categoryId
        } > 0
    }

    override suspend fun deleteProduct(id: Int): Boolean = dbQuery {
        Products.deleteWhere{ Products.id eq id} > 0
    }
}

val productDao : ProductDao = ProductDaoFacadeImp().apply {
    runBlocking {
        if(getAllProducts().isEmpty()){
            var testProduct = Product(0,"name,","description",10,
                15,100.0)
            addProduct(testProduct)
        }
    }
}