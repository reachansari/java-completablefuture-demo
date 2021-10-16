package com.example.demo.async;

import com.example.demo.async.dto.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RunAsyncDemo {



    public void saveEmployeesWithCustomExecutor(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        Executor executor = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(
                () -> {
                    try {
                        List<Employee> employees = mapper
                                .readValue(jsonFile, new TypeReference<List<Employee>>() {
                                });
                        //write logic t save list of employee to database say: repository.saveAll(employees);
                        System.out.println("Thread : " + Thread.currentThread().getName());
                        System.out.println(employees.size());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },executor);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunAsyncDemo runAsyncDemo = new RunAsyncDemo();
        runAsyncDemo.saveEmployeesWithCustomExecutor(new File("employees.json"));

    }
}
