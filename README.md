# java-completablefuture-demo
This project shows the various async options available such as RunAsync, SupplyAsync and Future.


## RunAsyncDemo
If you want to run some background task asynchronously and don’t want to return anything from the task, then you can use CompletableFuture.runAsync() method.

## SupplyAsyncDemo
CompletableFuture.runAsync() is useful for tasks that don’t return anything. But what if you want to return some result from your background task?

Well, CompletableFuture.supplyAsync() is your companion. It takes a Supplier<T> and returns CompletableFuture<T> where T is the type of the value obtained by calling the given supplier.

## FutureDemo
Java Callable tasks return java.util.concurrent.Future object. Using Java Future object, we can find out the status of the Callable task and get the returned Object. It provides get() method that can wait for the Callable to finish and then return the result.

 ## EmployeeNotificationService
Does the following options

 * Prepare Employee Object.
 * Filter for new joiner.
 * Filter for pending learning .
 * Collect email ids.
 * Send email.