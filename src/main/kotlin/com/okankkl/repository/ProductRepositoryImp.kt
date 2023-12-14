package com.okankkl.repository

import com.okankkl.dao.productDao
import com.okankkl.model.Product
import com.okankkl.model.ProductResponse
import com.okankkl.model.ResultResponse

class ProductServiceImp : ProductRepository {
    override suspend fun getAllProducts(): ProductResponse {
        return productDao.getAllProducts().let {
            ProductResponse(body = it, count = it.size)
        }
    }

    override suspend fun getProduct(id: Int): Product? {
        return productDao.getProduct(id)
    }

    override suspend fun addProduct(product: Product): ResultResponse {
        val isThereSameProduct = productDao.isThereSameProductName(product.name)

        if(isThereSameProduct)
            return ResultResponse(false,"there is a same product in database")

        productDao.addProduct(product).let {
            if(it)
                return ResultResponse(true,null)
            return ResultResponse(false,"product could not add")
        }
    }

    override suspend fun updateProduct(product: Product): ResultResponse {
        val isThereSameProduct = productDao.isThereSameProductName(product.name)

        if(isThereSameProduct)
            return ResultResponse(false,"there is a same product in database")

        return when(productDao.updateProduct(product)){
            true -> ResultResponse(true,null)
            false -> ResultResponse(false,"Product cold not update")
        }
    }

    override suspend fun deleteProduct(id: Int): ResultResponse {
        return when(productDao.deleteProduct(id)){
            false -> ResultResponse(false,"Product could not delete")
            true -> ResultResponse(true,null)
        }
    }

    override suspend fun decreaseQuantity(id: Int, number: Int) :ResultResponse {
        val product = productDao.getProduct(id)

        if(product != null){
            var newQuality = product.quantity - number
            if(newQuality >= 0){
                return when(productDao.changeQuantity(id,newQuality)){
                    true -> ResultResponse(true,null)
                    false -> ResultResponse(false,"Product could not decrease")
                }
            }
            return ResultResponse(false,"There is no enough product quantity")
        }
        return  ResultResponse(false,"There is no product")
    }

    override suspend fun increaseQuantity(id: Int, number: Int) : ResultResponse {
        val product = productDao.getProduct(id)
        if(product != null){
            val newQuality = product.quantity + number
            return when(productDao.changeQuantity(id,newQuality)){
                true -> ResultResponse(true,null)
                false -> ResultResponse(false,"Product could not increase")
            }
        }
        return ResultResponse(false,"There is no product")
    }
}

val productRepository : ProductRepository = ProductServiceImp()