package com.multimodule.api.controller.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

@RestController
public class WebFluxController {
    @GetMapping("/webflux/mono/hello")
    public Mono<String> helloWorld() {
        return Mono.just("Hello, World! Mono");
    }

    @GetMapping("/webflux/flux/hello")
    Flux<String> hello() {
        return Flux.just("Hello", "World Flux");
    }

    @GetMapping("/webflux/flux/stream")
    Flux<Map<String, Integer>> stream() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1); // Java8의 무한Stream
        return Flux.fromStream(stream.limit(10))
                .map(i -> Collections.singletonMap("value", i));
    }

    @PostMapping("/echo")
    Mono<String> echo(@RequestBody Mono<String> body) {
        return body.map(String::toUpperCase);
    }
}
