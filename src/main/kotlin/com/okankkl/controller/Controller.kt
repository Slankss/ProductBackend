package com.okankkl.controller

import com.okankkl.dao.categoryDao
import com.okankkl.model.Product
import com.okankkl.dao.productDao
import com.okankkl.model.Category
import com.okankkl.repository.categoryRepository
import com.okankkl.repository.productRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
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

            post("{}"){
                try {

                    var product = call.receive<Product>()
                    val resultResponse = productRepository.addProduct(product)
                    call.respond(message = resultResponse)

                }catch ( e : Exception){
                    call.respond(message = e.localizedMessage, status = HttpStatusCode.BadRequest)
                }
            }

            post("/update"){
                try {
                    var product = call.receive<Product>()
                    val resultResponse = productRepository.updateProduct(product)
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
                    var category = call.receive<Category>()
                    val resultResponse = categoryRepository.addCategory(category)
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
                    var category = call.receive<Category>()
                    val resultResponse = categoryRepository.updateCategory(category)
                    call.respond(message = resultResponse)

                } catch ( e : Exception){
                    call.respond(message = e.localizedMessage, status = HttpStatusCode.BadRequest)
                }
            }


        }
    }

}
