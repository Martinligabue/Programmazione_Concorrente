package lezione11;

import java.util.concurrent.Semaphore;

/* Esercizio 9: Travel Agency  */


class Travel  
{
    /* Semaphores ensure correct order of execution */
    static Semaphore[] s = new Semaphore[4];

    class Client extends Thread 
    {
         public void run() {
            for (int j = 0; j < 4; j++) 
            {
                try 
                {
                    s[0].acquire();
                    System.out.println("Client: provideData.");
                    s[1].release();
                    s[2].release();
                    s[0].acquire();
                    s[0].acquire();
                    System.out.println("Client: pay.");
                    s[3].release();
                } 
                    catch (InterruptedException e) {}               
            }
        }
    }


    class Flight extends Thread 
    {
         public void run() 
         {
            for (int j = 0; j < 4; j++) 
            {
                try 
                {
                    s[1].acquire();
                    System.out.println("Flight: bookF.");
                    s[0].release();
                } 
                    catch (InterruptedException e) {}               
            }
        }
    }
    
    
    class Hotel extends Thread 
    {
         public void run() 
         {                
            for (int j = 0; j < 4; j++) 
            {
                try 
                {
                    s[2].acquire();
                    System.out.println("Hotel: bookH.");
                    s[0].release();
                } 
                    catch (InterruptedException e) {}               
            }
        }
    }
    
    class Bank extends Thread 
    {
         public void run() 
         {
            for (int j = 0; j < 4; j++) 
            {
                try 
                {
                    s[3].acquire();
                    System.out.println("Bank: paymentOk.");
                    s[0].release();
                } 
                    catch (InterruptedException e) {}               
            }
        }
    }
    
    
    Travel() 
    {
        s[0] = new Semaphore(1);      /* Initialize semaphores  */
        
        for (int i=1; i < 4; i++) 
        {
            s[i] = new Semaphore(0);
        }
        
        Client C = new Client();     /* Instantiate processes */
        Flight F = new Flight();
        Hotel H  = new Hotel();
        Bank  B  = new Bank();
        
        C.start();                 /* Start processes (=make them 'ready') */
        F.start();
        H.start();
        B.start();
        
        try
        {
        	C.join();F.join();H.join();B.join();    /* Wait for terminaton of all proceses */
        }
        
        catch (InterruptedException e) {};
        
        System.out.println("Program terminated correctly!");
    }

    public static void main(String[] args) 
    {
        new Travel();
    }
}
