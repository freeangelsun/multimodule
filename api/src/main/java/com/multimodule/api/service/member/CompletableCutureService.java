package com.multimodule.api.service.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class CompletableCutureService {

    /**
     * 리턴값이 없는경우
     */
    public String completablefuture1() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> log.debug("completablefuture No Return"));
        return "hello completablefuture";
    }

    /**
     * 리턴값이 있는 경우에는 supplyAsync()를 사용한다.
     */
    public String completablefuture2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.debug("Return completablefuture2");
            return "ParamReturn completablefuture2";
        });
        log.debug(future.get()); // 결과 가져오기

        return "hello completablefuture2";
    }

    /**
     *  CompletableFuture를 사용하여 Asynchronous하게 콜백을 실행시킬수도 있다.
     *  리턴값을 받아서 다른 값으로 바꾸는 콜백을 구현하기 위해 thenApply()를 사용할 수 있다.
     */
    public String completablefuture3() throws ExecutionException, InterruptedException{
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.debug("Return: " + Thread.currentThread().getName());
            return "ParamReturn completablefuture3";
        }).thenApply((s) -> {
            log.debug("CallBack: " + Thread.currentThread().getName());
            return s.toUpperCase();
        });
        log.debug(future.get()); // 결과 가져오기

        return "hello completablefutur3";
    }


    /**
     * thenApply() 말고도 콜백을 구현할 수 있는 함수들이 있다.
     * thenAccept()을 사용하면 리턴값을 받아 리턴 없이 또 다른 작업을 처리하는 콜백을 구현할 수 있고,
     * thenRun()을 사용하면 리턴값을 받지 않고 또 다른 작업을 처리하는 콜백을 구현할 수 있다.
     * Executor를 해당 스레드에서 사용하게 할 수도 있다.
     * runAsync() OR supplyAsync()의 두 번째 인자로 ExecutorService를 주면 해당 스레드 풀 내에서 작업을 할당해서 처리한다.
     */
    public String completablefuture4() throws ExecutionException, InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            log.debug("Hello: " + Thread.currentThread().getName());
            return "HelloReturn";
        }, executorService).thenAccept((s) -> {
            log.debug("HelloCallBack: " + Thread.currentThread().getName());
            log.debug(s.toUpperCase());
        });

        future.get();
        executorService.shutdown();

        return "hello completablefuture4";
    }

    /**
     * 스레드의 콜백작업을 또 다른 스레드에서 처리할 수 있는데!
     * then~() 에 Async를 붙인 then~Async()함수를 사용하고 두 번째 인자로 ExecutorService를 주면 된다.
     */
    public String completablefuture5() throws ExecutionException, InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            log.debug("Bye: " + Thread.currentThread().getName());
            return "ByeReturn";
        }, executorService).thenAcceptAsync((s) -> {
            log.debug("ByeCallBack: " + Thread.currentThread().getName());
            log.debug(s.toUpperCase());
        }, executorService);

        future.get();
        executorService.shutdown();

        return "hello completablefuture5";
    }
}
