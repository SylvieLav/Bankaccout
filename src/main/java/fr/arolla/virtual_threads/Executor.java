package fr.arolla.virtual_threads;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Executor {


    public void runWithVirtualThread() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println("Thread : " + Thread.currentThread());
                    return i;
                });
            });
        }
    }

    public void run() {
        try (var executor = Executors.newFixedThreadPool(1_000)) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println("Thread : " + Thread.currentThread());
                    return i;
                });
            });
        }
    }
}
