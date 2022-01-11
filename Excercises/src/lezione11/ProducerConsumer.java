package lezione11;

import java.util.concurrent.Semaphore;
import java.util.Queue;
import java.util.LinkedList;

/* Esercizio 6: Produttore consumatore con semafori in Java (versione buffer infinito)*/
class ProducerConsumer 
{
    /* Size of the finite queue */
    static final int N = 10;
    
    /* Semaphores: at the start N empty slots, 0 used slots. */
    static Semaphore empty = new Semaphore(N);
    static Semaphore full = new Semaphore(0);
    
    /* Predefined type: finite queue of produced items */
    static Queue<Integer> queue = new LinkedList<Integer>();
    
    class Producer extends Thread 
    {
        public void run() 
        {
            int c = 1;
            for (int i = 0; i < 10; i++) /* "for(...)" replaces "while(true)" */
            {          
                try 
                {
                    empty.acquire();
                } catch (InterruptedException e) {}
                
                System.out.println("Producing item " + c);
                queue.add(c++);
                full.release();
            }
        }
    }

    class Consumer extends Thread 
    {
        public void run() 
        {
             for (int i = 0; i < 10; i++)
             {
                try 
                {
                    full.acquire();
                } catch (InterruptedException e) { }
                
                System.out.println("Consuming item " + queue.remove());
                empty.release();
            }
        }
    }

  ProducerConsumer() 
  {     
      Producer p =  new Producer();
      Consumer c =  new Consumer();
      p.start();
      c.start();
   } 

    public static void main(String[] args) 
    {
       new ProducerConsumer();  
    }
}

