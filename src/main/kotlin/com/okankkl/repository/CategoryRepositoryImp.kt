package com.okankkl.repository

import com.okankkl.dao.categoryDao
import com.okankkl.dao.productDao
import com.okankkl.model.Category
import com.okankkl.model.CategoryResponse
import com.okankkl.model.ResultResponse

class CategoryRepositoryImp : CategoryRepository {
    override suspend fun getAllCategories(): CategoryResponse {
        return categoryDao.getAllCategories().let {
            CategoryResponse(body = it,count = it.size)
        }
    }

    override suspend fun getCategory(id: Int): Category? {
        return categoryDao.getCategory(id)
    }

    override suspend fun addCategory(category: Category): ResultResponse {
        val isThere = categoryDao.isThereCategoryWithSameName(category.name)
        if(isThere)
            return ResultResponse(true,"There is same category with its name")

        categoryDao.addCategory(category).let {
            if(it != null)
                return ResultResponse(true,null)
            return ResultResponse(false,"Category could not add")
        }
    }

    override suspend fun deleteCategory(id: Int): ResultResponse {
        return when(categoryDao.deleteCategory(id)){
            true -> ResultResponse(true,null)
            false -> ResultResponse(false,"Category could not delete")
        }
    }

    override suspend fun updateCategory(category: Category): ResultResponse {
        val isThere = categoryDao.isThereCategoryWithSameName(category.name)
        if(isThere)
            return ResultResponse(false,"There is same category with its name")

        return when(categoryDao.updateCategory(category)){
            true -> ResultResponse(true,null)
            false -> ResultResponse(false,"Category could not delete")
        }
    }
}

val categoryRepository : CategoryRepository = CategoryRepositoryImp()