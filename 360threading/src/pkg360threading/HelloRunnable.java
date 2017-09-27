/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg360threading;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 *
 * @author natebolton
 */
public class HelloRunnable implements Runnable {

    public void run() {
        System.out.println(Thread.currentThread().getName() + ":  Hello from a thread!");
        
        //from https://docs.oracle.com/javase/tutorial/essential/concurrency/simple.html
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };

        //implement a try to check for interrupt exception
        for (int i = 0; i < importantInfo.length; i++) {
            //Pause for 1 seconds
            try {
                Thread.sleep(1200);
                System.out.println(Thread.currentThread().getName() + ":  isInterrupted:  " + Thread.currentThread().isInterrupted());
             } catch (InterruptedException e) {
                //apparently isInterrupted is cleared at this point
                System.out.println(Thread.currentThread().getName() + ":  isInterrupted:  " + Thread.currentThread().isInterrupted());
                //intterupt, quit
                System.out.println(Thread.currentThread().getName() + ":  Oh no I Die (1)");
                return;
            }
            //Print a message
            System.out.println(Thread.currentThread().getName() + ":  " + importantInfo[i]);
        }
        
        System.out.println(Thread.currentThread().getName() + ": Try 2");
        //same thing, but also check for Thread.interrupted()
        for (int i = 0; i < importantInfo.length; i++) {
            System.out.println(Thread.currentThread().getName() + ":  isInterrupted:  " + Thread.currentThread().isInterrupted());
            if (Thread.currentThread().isInterrupted()) {
                // We've been interrupted: no more crunching.
                System.out.println(Thread.currentThread().getName() + ":  Oh no I die! (2)");
                return;
            }
            try {
                Thread.sleep(1200);
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName() + ":  Oh no I\"ve received interrupt, but I'm going to ignore and re-issue it to see what that does (3)");
                Thread.currentThread().interrupt();
            }
            //Print a message
            System.out.println(Thread.currentThread().getName() + ":  " + importantInfo[i]);
        }
    }

    public static void main(String args[]) throws InterruptedException {
        
        HelloRunnable hr = new HelloRunnable();
        Map<String, Thread> myMap = new HashMap<String, Thread>();
        int numThreads = 100;  //change to increase or decrease simultaneous threads.
        
        //First we make em'
        System.out.println(Thread.currentThread().getName() + ":  Creating " + numThreads + " new threads...");
        for (int i = 0; i < numThreads; i++) {
            myMap.put("thread" + i, new Thread(hr));
            //(new Thread(new HelloRunnable())).start();
            //t.join();
        }
        //then we start em'
        System.out.println(Thread.currentThread().getName() + ":  Starting " + numThreads + " new threads...");
        for (int i = 0; i < numThreads; i++) {
            myMap.get("thread" + i).start();
        }
//        Thread t = new Thread(hr);
//        Thread t2 = new Thread(hr);
//        //(new Thread(new HelloRunnable())).start();
//        t.start();
//        t2.start();
        System.out.println(Thread.currentThread().getName() + ":  sleeping for x seconds...");
        Thread.sleep(1000);
        
        //then we interrupt em'
        System.out.println(Thread.currentThread().getName() + ":  I'm awake, time to interrupt all " + numThreads + " threads!");
        for (int i = 0; i < numThreads; i++) {
            myMap.get("thread" + i).interrupt();
        }
        //t.interrupt();
        System.out.println(Thread.currentThread().getName() + ":  I'm Done.");
        
        boolean imStillAlive = true;
        while(imStillAlive) {
            imStillAlive = false;
            for (int i = 0; i < numThreads; i++) {
                if (myMap.get("thread" + i).isAlive()) {
                    imStillAlive = true;
                    break;
                }
            }
            if (imStillAlive) {
                Thread.sleep(2000);
            }
        }
        System.out.println("All threads have completed operations, hit enter to begin executor code...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //BEGIN EXECUTOR CODE HERE (uncomment one at a time)
        //CHANGE THE NUMBERS BELOW TO INCREASE OR DECREASE PROCESSING TIME
        int primeStart = 2;
        int primeEnd = 1000000;
        int threshold = 10000; //threshold for fork
        //SINGLE THREAD EXECUTOR
//        System.out.println("Starting again with Executors");
//        FindPrimes fp = new FindPrimes(primeStart, primeEnd);
//        ExecutorService exec = Executors.newSingleThreadExecutor();
//        exec.submit(fp);
//        exec.shutdown();

        //RECURSIVE MULTITHREADED/FORKED EXECUTOR
        System.out.println("Starting again with ForkJoinPool");
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        RecurFindPrimes recurFindPrimes = new RecurFindPrimes(primeStart,primeEnd,threshold);
        forkJoinPool.invoke(recurFindPrimes);
    }

}
