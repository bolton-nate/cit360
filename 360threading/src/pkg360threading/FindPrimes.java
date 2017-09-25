/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg360threading;
import java.util.Scanner;
/**
 *
 * @author natebolton
 * modified from: http://www.sanfoundry.com/java-program-find-prime-numbers-within-range-n1-n2/
 */
public class FindPrimes implements Runnable {

    private int s1, s2, i, j;
    int numberOfPrimes = 0;
    
    public FindPrimes(int s1, int s2) {
        this.s1 = s1;
        this.s2 = s2;
    }
    
    public void run()
    {
         long startTime = System.nanoTime();
         int flag = 0;
         //Scanner s = new Scanner(System.in);
         //System.out.println ("Enter the lower limit :"); 
         //s1 = s.nextInt();
         //s1 = 2;
         //System.out.println ("Enter the upper limit :"); 
         //s2 = s.nextInt();
         //s2 = 200000;
         //System.out.println ("The prime numbers in between the entered limits are :");
         for(i = s1; i <= s2; i++)
         {
             for( j = 2; j < i; j++)
             {
                 if(i % j == 0)
                 {
                     flag = 0;
                     break;
                 }
                 else
                 {
                     flag = 1;
                 }
             }
             if(flag == 1)
             {
                 //System.out.println(i);
                 numberOfPrimes++;
             }
         }
         System.out.println("SINGLE THREAD:  The number of prime numbers between " + s1 + " and " + s2 + " is:" + numberOfPrimes);
        long totalTime = System.nanoTime() - startTime;
        System.out.println("FindPrimes took about : " + totalTime/1_000_000_000 + " seconds.");
    }
}
