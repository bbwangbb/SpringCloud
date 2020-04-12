package cn.mb.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRouteConfig {
    @Bean
    public RouteLocator myRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        //  路由ID
        routes.route("gateway_route_a",
                //  路由规则：当访问localhost:9527/guonei时会转发到下面哪个
                r -> r.path("/guonei")
                        //  转发地址
                        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
