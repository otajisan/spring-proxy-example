package com.example.proxy

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayConfig {

    @Bean
    fun proxyRouteLocator(
        builder: RouteLocatorBuilder,
        proxyAuthorizationFilter: ProxyAuthorizationFilter,
        proxyService: ProxyService
    ): RouteLocator {
        return builder.routes()
            .route("dynamic-proxy-route") { r ->
                r.path("/**")
                    .filters { f ->
                        f.filter(proxyAuthorizationFilter.apply(ProxyAuthorizationFilter.Config()))
                        f.rewritePath("(?<segment>.*)", "/\${segment}")
                    }
                    .uri(proxyService.getActiveProxyUrl())
            }
            .build()
    }
}
