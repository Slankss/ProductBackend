package com.okankkl.model

import com.okankkl.plugins.DatabaseSingleton.dbQuery
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ProductDaoFacadeImp : ProductDaoFacade {

    private fun resultRowToProduct(row : ResultRow) = Product(
        id = row[Products.id],
        name = row[Products.name],
        description = row[Products.description],
        categoryId = row[Products.categoryId],
        price = row[Products.price],
        quality = row[Products.quality]
    )

    override suspend fun allProducts(): List<Product> = dbQuery{
        Products.selectAll().map(::resultRowToProduct)
    }

    override suspend fun product(id: Int): Product? = dbQuery{
        Products
            .select { Products.id eq id }
            .map(::resultRowToProduct)
            .singleOrNull()
    }

    override suspend fun addProduct(product: Product): Product? = dbQuery{
        val insertStatement = Products.insert {
            it[id] = product.id
            it[name] = product.name
            it[description] = product.description
            it[price] = product.price
            it[quality] = product.quality
            it[categoryId] = product.categoryId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToProduct)
    }

    override suspend fun updateProduct(product: Product): Boolean = dbQuery {
        Products.update({ Products.id eq product.id}) {
            it[name] = product.name
            it[description] = product.description
            it[price] = product.price
            it[quality] = product.quality
            it[categoryId] = product.categoryId
        } > 0
    }

    override suspend fun deleteProduct(id: Int): Boolean = dbQuery {
        Products.deleteWhere{ Products.id eq id} > 0
    }
}

val dao : ProductDaoFacade = ProductDaoFacadeImp().apply {
    runBlocking {
        if(allProducts().isEmpty()){
            var testProduct = Product(0,"name,","description",10,
                15,100.0)
            addProduct(testProduct)
        }
    }
}