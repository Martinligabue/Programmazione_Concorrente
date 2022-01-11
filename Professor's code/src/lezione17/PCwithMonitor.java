package lezione17;

import java.util.Random;

class PCwithMonitor
{   

	Random rand = new Random();

	/* Inner class: monitor PCMonitor */
	class PCMonitor 
	{    
	    private final int N = 5;
	    private volatile int Oldest = 0, Newest = 0;
	    private volatile int Count = 0;
	    private volatile int Buffer[] = new int[N];
	
	    synchronized void Append(int V) 
	    {
	    	while (Count == N)
	    	{
	    		try 
	    		{
	    			wait();
	    		} catch (InterruptedException e) {}
	    	}
	    	
	        Buffer[Newest] = V;
	        Newest = (Newest + 1) % N;
	        Count = Count + 1;
	        notifyAll();
	    }
	
	    synchronized int Take() 
	    {
	        int temp;
	        
	        while (Count == 0)
	        {
	        	try 
	        	{
	        		wait();
	        	} catch (InterruptedException e) {}
	        
	        }	
	        
	        temp = Buffer[Oldest];
	        Oldest = (Oldest + 1) % N;
	        Count = Count - 1;
	        notifyAll();
	        return temp;
	    }
	}
	
	
	/* Inner class: thread Consumer */
	class Consumer extends Thread
	{      
	  PCMonitor m;
	  
	  Consumer(PCMonitor m1)
	  { 
		  this.m = m1; 
	  }
	  
	  public void run() 
	  {
	        int d;
	  
	        for (int j = 0; j < 10; j++) 
	        {
	        	d=m.Take();
	        	System.out.println("Consumer consumes " + d +".");
	        }
	  }
	}
	
	/* Inner class: thread Producer */
	class Producer extends Thread
	{   
	  PCMonitor m;
	  
	  Producer(PCMonitor m1)
	  { 
		  this.m = m1; 
	  }
	  
	  public void run() 
	  {
		  for (int j = 0; j < 10; j++) 
		  {
			  m.Append(j);
			  System.out.println("Producer produces " + j +".");
		  }
	  }
	}
	
	/* Costruttore classe principale */
	PCwithMonitor()
	{                
		PCMonitor m = new PCMonitor();  /* Istanzia monitor m */
		Producer P = new Producer(m);   /* Istanzia produttore P */
		Consumer C = new Consumer(m);   /* Istanzia consumatore C */
		
		P.start();                      /* rende eseguibili i thread */
		C.start();
		
		try
		{
			P.join();C.join();     /* attende terminazione di P e C */
		}
		catch (InterruptedException e) {};
		
		System.out.println("Program terminated correctly!");
	}
	
	/* metodo main classe principale, invoca costruttore cl. principale */
	public static void main(String[] args) 
	{    
		new PCwithMonitor();
	}
	
}
