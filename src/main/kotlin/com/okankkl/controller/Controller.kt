package com.okankkl.controller

import com.okankkl.dao.categoryDao
import com.okankkl.model.Product
import com.okankkl.dao.productDao
import com.okankkl.model.Category
import com.okankkl.repository.categoryRepository
import com.okankkl.repository.productRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing() {

        route("/product"){

            get {
                try {
                    val productResponse = productRepository.getAllProducts()
                    call.respond(message = productResponse,status = HttpStatusCode.OK)
                } catch (e : Exception){
                    call.respond(message = e.localizedMessage,status = HttpStatusCode.BadRequest)
                }
            }

            get("/get{id}") {
                try {
                    val id = call.parameters["id"]?.toInt()
                    val product = productRepository.getProduct(id!!)
                    if(product != null)
                        call.respond(message = product)
                } catch ( e : Exception){
                    call.respond(message = e.localizedMessage, status = HttpStatusCode.BadRequest)
                }
            }

            post {
                try {
                    val testProduct = Product(1,"name 24","description 2",2,18,150.0)
                    val resultResponse = productRepository.addProduct(testProduct)
                    call.respond(message = resultResponse)

                }catch ( e : Exception){
                    call.respond(message = e.localizedMessage, status = HttpStatusCode.BadRequest)
                }
            }

            post("/update"){
                try {
                    val testProduct = Product(1,"name 2","description 2",1,18,150.0)
                    val resultResponse = productRepository.updateProduct(testProduct)
                    call.respond(message = resultResponse)

                }catch ( e : Exception){
                    call.respond(message = e.localizedMessage, status = HttpStatusCode.BadRequest)
                }
            }

            get("/delete"){
                try {
                    val id = call.parameters["id"]?.toInt()
                    val resultResponse = productRepository.deleteProduct(id!!)
                    call.respond(message = resultResponse)
                } catch (e : Exception){
                    call.respond(message = e.localizedMessage, status = HttpStatusCode.BadRequest)

                }
            }

        }

        route("/category"){

            get{
                try {
                    val categoryResponse = categoryRepository.getAllCategories()
                    call.respond(message = categoryResponse)

                }catch ( e : Exception){
                    call.respond(message = e.localizedMessage, status = HttpStatusCode.BadRequest)
                }
            }
            get("/get{id}") {
                try {
                    var id = call.parameters["id"]?.toInt()
                    var category = categoryRepository.getCategory(id!!)

                    if(category != null)
                        call.respond(message = category)
                }catch ( e : Exception){
                    call.respond(message = e.localizedMessage, status = HttpStatusCode.BadRequest)
                }
            }

            post {
                try {
                    val test = Category(10,"category B")
                    val resultResponse = categoryRepository.addCategory(test)
                    call.respond(message = resultResponse)

                } catch ( e : Exception){
                    call.respond(message = e.localizedMessage, status = HttpStatusCode.BadRequest)
                }
            }

            get("/delete") {
                try {
                    val id = call.parameters["id"]?.toInt()
                    val resultResponse = categoryRepository.deleteCategory(id!!)
                    call.respond(message = resultResponse)
                } catch (e : Exception){
                    call.respond(message = e.localizedMessage, status = HttpStatusCode.BadRequest)
                }
            }

            post("/update") {
                try {
                    val test = Category(10,"category B")
                    val resultResponse = categoryRepository.updateCategory(test)
                    call.respond(message = resultResponse)

                } catch ( e : Exception){
                    call.respond(message = e.localizedMessage, status = HttpStatusCode.BadRequest)
                }
            }


        }
    }

}
