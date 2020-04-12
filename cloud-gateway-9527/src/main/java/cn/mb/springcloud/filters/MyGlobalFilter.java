package cn.mb.springcloud.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class MyGlobalFilter implements Ordered, GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**
         * 感觉就是servlet里的过滤器
         * ServerWebExchange包含请求和响应的信息
         * GatewayFilterChain可以用于放行等
         */
        String user = exchange.getRequest().getQueryParams().getFirst("user");
        if (user == null) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            //  错误直接返回
            return exchange.getResponse().setComplete();
        }
        //  放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //  表示优先级，越小越先执行
        return 0;
    }
}
