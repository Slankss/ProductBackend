package com.okankkl.model

interface ProductDaoFacade {
    suspend fun allProducts() : List<Product>
    suspend fun product(id : Int) : Product?
    suspend fun addProduct(product: Product) : Product?
    suspend fun updateProduct(product: Product) : Boolean
    suspend fun deleteProduct(id : Int) : Boolean
}