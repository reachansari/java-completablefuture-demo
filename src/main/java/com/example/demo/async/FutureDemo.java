package com.example.demo.async;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ExecutorService service = Executors.newFixedThreadPool(10);

		Future<List<Integer>> future = service.submit(() -> {
			// You can call API here
			System.out.println("Thread : " + Thread.currentThread().getName());
			return Arrays.asList(1, 2, 3, 4);
		});

		if (!future.isDone()) {
			List<Integer> integers = future.get();
			System.out.println("--->" + integers);
		}

	}
}
