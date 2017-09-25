/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg360threading;
import java.util.*;
import java.util.concurrent.*;
/**
 *
 * @author natebolton
 */
public class RecurFindPrimes extends RecursiveAction {

    private int threshold = 50000;
    private int s1, s2, i, j;
    public volatile static int numberOfPrimes;
    
    public RecurFindPrimes(int s1, int s2) {
        this.s1 = s1;
        this.s2 = s2;
    }
    
    public void compute()
    {
        long startTime = System.nanoTime(); 
        int flag = 0;

        //System.out.println ("The number of prime numbers between " + s1 + " and " + s2 + " is:");
        if (s2 - s1 <= threshold) {
            //task is small enough, just do it
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
                    RecurFindPrimes.numberOfPrimes++;
                }
            }
        } else {
            int midPoint = ((s2-s1)/2) + s1;
            RecurFindPrimes task1 = new RecurFindPrimes(s1,midPoint);//lower split
            task1.fork();
            RecurFindPrimes task2 = new RecurFindPrimes(midPoint+1,s2);//upper split
            task2.fork();
            task1.join();
            task2.join();
        }
        System.out.println("FORK JOIN POOL:  The number of prime numbers between " + s1 + " and " + s2 + " is:" + numberOfPrimes);
        long totalTime = System.nanoTime() - startTime;
        System.out.println("RecurFindPrimes took about : " + totalTime/1_000_000_000 + " seconds.");
    }
}
