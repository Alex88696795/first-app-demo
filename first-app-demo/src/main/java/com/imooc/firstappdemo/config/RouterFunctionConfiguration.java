package com.imooc.firstappdemo.config;

import com.imooc.firstappdemo.admain.User;
import com.imooc.firstappdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * @Author: Alex
 * @Date: created in 14:40  2019/8/28
 * @Annotation: 路由器函数 配置
 */
@Configuration
public class RouterFunctionConfiguration {

    /**
     * 在Servlet中
     * 请求接口：ServletRequest 或者 HtpServletRequest
     * 响应接口：ServletResponse 或者 HttpServletResponse
     * Spring 5.0中重新定义了服务请求和相应接口
     * 请求接口：ServerRequest
     * 相应接口：ServerResponse
     * 即可支持servlet规范，也可支持自定义，比如Netty(Web Server)
     * 以本例：
     * 定义GET请求，并返回所有的用户对象URL，/person/find/all
     * Flux是0 - N 个对象的集合
     * Mono 是0 - 1个对象集合
     * Reactive中的Flux或者Mono 是异步处理（非阻塞）
     * 集合对象基本上是同步处理（阻塞）
     */

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository){

        return RouterFunctions.route(RequestPredicates.GET("/person/find/all"),
                request -> {
                    //返回所有用户对象
                    Collection<User> users = userRepository.findAll();
                    Flux<User> userFlux =  Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux,User.class);
                });
    }
}
