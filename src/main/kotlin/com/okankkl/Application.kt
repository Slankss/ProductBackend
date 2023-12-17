package com.okankkl

import com.okankkl.controller.configureRouting
import com.okankkl.plugins.DatabaseSingleton
import com.okankkl.plugins.configureMonitoring
import com.okankkl.plugins.*
import freemarker.cache.ClassTemplateLoader
import freemarker.core.HTMLOutputFormat
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.configureModule(){
    DatabaseSingleton.init()
    install(ContentNegotiation){
        json(Json{
            prettyPrint = true
            isLenient = true
        })
    }
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        outputFormat = HTMLOutputFormat.INSTANCE
    }
    configureMonitoring()
    configureRouting()
}


