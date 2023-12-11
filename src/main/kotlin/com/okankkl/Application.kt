package com.okankkl

import com.okankkl.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.configureModule(){
    configureMonitoring()
    configureRouting()
}


