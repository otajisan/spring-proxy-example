package com.example.proxy

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProxyAuthorizationFilter(private val proxyAuthConfig: ProxyAuthConfig) :
    AbstractGatewayFilterFactory<ProxyAuthorizationFilter.Config>(Config::class.java) {

    override fun apply(config: Config): GatewayFilter {
        val filter =  GatewayFilter { exchange, chain ->
            // 環境変数から取得した認証情報をBase64でエンコード
            val encodedCredentials = Base64.getEncoder()
                .encodeToString("${proxyAuthConfig.username}:${proxyAuthConfig.password}".toByteArray())

            println("Adding Proxy-Authorization header: Basic $encodedCredentials")

            // Proxy-Authorization ヘッダーを追加
            exchange.mutate()
                .request { it.header("Proxy-Authorization", "Basic $encodedCredentials") }
                .build()

            exchange.request.headers.forEach { (key, value) ->
                println("Header: $key -> $value")
            }

            // 次のフィルター処理に進む
            chain.filter(exchange)
        }

        return filter
    }

    // 設定用のクラス
    data class Config(val name: String = "ProxyAuthorizationFilter")
}
