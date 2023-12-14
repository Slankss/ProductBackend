package com.okankkl.repository

import com.okankkl.model.Product
import com.okankkl.model.ProductResponse
import com.okankkl.model.ResultResponse

interface ProductRepository {

    suspend fun getAllProducts() : ProductResponse
    suspend fun getProduct(id : Int) : Product?
    suspend fun addProduct(product: Product) : ResultResponse
    suspend fun updateProduct(product: Product) : ResultResponse
    suspend fun deleteProduct(id : Int) : ResultResponse
    suspend fun decreaseQuantity(id: Int, number: Int) : ResultResponse
    suspend fun increaseQuantity(id: Int, number: Int) : ResultResponse
}