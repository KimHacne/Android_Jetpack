package com.example.ktor_client_tutorial

import android.util.Log
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

object KtorClient {
    //제이슨 설정
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true //모르는 키가 나타나면 true로
    }

    //http 클라이언트
    val httpClient = HttpClient{

        install(JsonFeature){
            serializer = KotlinxSerializer(json =json)
        }

        install(Logging){
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("TAG","api log: $message")
                }
            }
            level = LogLevel.ALL
        }
        install(HttpTimeout){
            requestTimeoutMillis = 1000
            connectTimeoutMillis = 1000
            socketTimeoutMillis = 1000
        }

        // 기본적이 api 호출 시 넣는 것들 즉 기본 세팅
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}