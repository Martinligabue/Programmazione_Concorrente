package lezione11;

import java.util.concurrent.Semaphore;

/* Esercizio 7: Dining philosophers (first attempt)*/

class Dining  
{
    /* Semaphore for each stick */
    static Semaphore[] stick = new Semaphore[5];

    class Philosopher extends Thread 
    {
        /* Philosopher id */
        int i;
        Philosopher(int i) 
        { 
        	this.i = i; 
        }

        public void run() 
        {
            for (int j = 0; j < 10; j++) 
            {
                /* Think */
                try 
                {
                    stick[i].acquire();
                    System.out.println("Philosopher " + i + " takes stick " + i +".");
                    stick[(i + 1) % 5].acquire();
                    System.out.println("Philosopher " + i + " takes stick " + ((i + 1) % 5) +".");
                } catch (InterruptedException e) { }
                
                /* Eat */
                System.out.println("Philosopher " + i + " is eating.");
                Thread.yield();
                stick[i].release();
                System.out.println("Philosopher " + i + " releases stick " + i +".");
                stick[(i + 1) % 5].release();
                System.out.println("Philosopher " + i + " releases stick " + ((i + 1) % 5) +".");
            }
        }
    }
    
    Dining() 
    {
        for (int i=0; i < 5; i++) 
        {
            stick[i] = new Semaphore(1);
        }
        
        Philosopher P0 = new Philosopher(0);
        Philosopher P1 = new Philosopher(1);
        Philosopher P2 = new Philosopher(2);
        Philosopher P3 = new Philosopher(3);
        Philosopher P4 = new Philosopher(4);
        
        P0.start();
        P1.start();
        P2.start();
        P3.start();
        P4.start();
        
        try
        {
        	P0.join();P1.join();P2.join();P3.join();P4.join();
        }
        catch (InterruptedException e) { };
        
        System.out.println("Program terminated correctly!");
    }
    
    public static void main(String[] args) 
    {
        new Dining();
    }
}

