package com.okankkl.dao

import com.okankkl.model.Categories
import com.okankkl.model.Category
import com.okankkl.model.Product
import com.okankkl.model.Products
import com.okankkl.plugins.DatabaseSingleton.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class CategoryDaoImp : CategoryDao {
    private fun resultRowToCategory(row : ResultRow) = Category(
        id = row[Categories.id],
        name = row[Categories.name],
        product_count = row[Products.id.count()]
    )
    override suspend fun getAllCategories(): List<Category>  = dbQuery{
        (Categories innerJoin Products)
            .slice(Categories.id,Categories.name,Products.id.count())
            .selectAll()
            .groupBy(Categories.name)
            .map (::resultRowToCategory)
            .toList()
    }
    override suspend fun getCategory(id: Int): Category? = dbQuery{
        (Categories innerJoin Products)
            .slice(Categories.id,Categories.name,Products.id.count())
            .select { Categories.id eq id }
            .groupBy(Categories.id)
            .map(::resultRowToCategory)
            .singleOrNull()
    }

    override suspend fun isThereCategoryWithSameName(name: String): Boolean = dbQuery{
        (Categories innerJoin Products)
            .slice(Categories.id,Categories.name,Products.id.count())
            .select { Categories.name eq name }
            .groupBy(Categories.id)
            .map(::resultRowToCategory)
            .isNotEmpty()
    }

    override suspend fun addCategory(category: Category): Category? = dbQuery {
        val insertStatement = Categories.insert {
            it[name] = category.name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCategory)
    }

    override suspend fun deleteCategory(id: Int): Boolean = dbQuery{
        Categories.deleteWhere { Categories.id eq id } > 0
    }

    override suspend fun updateCategory(category: Category): Boolean = dbQuery{
        Categories.update({ Categories.id eq category.id }) {
            it[name] = category.name
        } > 0
    }

}

val categoryDao : CategoryDao = CategoryDaoImp()