package com.okankkl.dao

import com.okankkl.model.Category

interface CategoryDao {
    suspend fun getAllCategories() : List<Category>
    suspend fun getCategory(id : Int) : Category?
    suspend fun isThereCategoryWithSameName(name : String) : Boolean
    suspend fun addCategory(category: Category) : Category?
    suspend fun deleteCategory(id : Int) : Boolean
    suspend fun updateCategory(category : Category) : Boolean
}