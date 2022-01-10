package lezione11;

import java.util.concurrent.Semaphore;

class CountSem extends Thread 
{
  static volatile int n = 0;
  static Semaphore s = new Semaphore(1); //Corrisponde a S <-- (k,0)

  public void run() 
  {
    int temp;
    for (int i = 0; i < 10; i++) 
    {
      try 
      {
        s.acquire(); //Corrisponde a wait(S)
      }
      catch (InterruptedException e) {}
      
      temp = n;
      n = temp + 1;
      s.release(); //Corrisponde a signal(S)
    }
  }

  public static void main(String[] args) 
  {
      CountSem p = new CountSem();
      CountSem q = new CountSem();
      
      p.start();
      q.start();
      
      try 
      {
    	  p.join();
    	  q.join();
      }
      catch(InterruptedException e) {}
      
      System.out.println("The value of n is " + n);
  
  }
}