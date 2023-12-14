package com.okankkl.dao

import com.okankkl.model.Product

interface ProductDao {
    suspend fun getAllProducts() : List<Product>
    suspend fun getProduct(id : Int) : Product?
    suspend fun addProduct(product: Product) : Boolean
    suspend fun isThereSameProductName(name : String) : Boolean
    suspend fun changeQuantity(id : Int, number : Int) : Boolean
    suspend fun updateProduct(product: Product) : Boolean
    suspend fun deleteProduct(id : Int) : Boolean
}