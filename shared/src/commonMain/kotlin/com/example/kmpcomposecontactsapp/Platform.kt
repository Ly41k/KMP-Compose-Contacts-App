package com.example.kmpcomposecontactsapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform