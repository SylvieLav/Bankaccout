package fr.arolla.virtual_threads;

import org.junit.jupiter.api.Test;

class ExecutorTest {
    @Test
    void should_test_without_virtual_threads() {
        Executor executor = new Executor();
        executor.run();
    }

    @Test
    void should_test_virtual_threads() {
        Executor executor = new Executor();
        executor.runWithVirtualThread();
    }
}
