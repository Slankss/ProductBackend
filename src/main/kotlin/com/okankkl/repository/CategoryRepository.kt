package com.okankkl.repository

import com.okankkl.model.Category
import com.okankkl.model.CategoryResponse
import com.okankkl.model.ResultResponse

interface CategoryRepository {

    suspend fun getAllCategories(): CategoryResponse
    suspend fun getCategory(id : Int) : Category?
    suspend fun addCategory(category: Category) : ResultResponse
    suspend fun deleteCategory(id : Int) : ResultResponse
    suspend fun updateCategory(category: Category) : ResultResponse
}