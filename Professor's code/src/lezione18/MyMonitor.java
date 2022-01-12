package lezione18;
import java.util.Random;


/*esercizio 4*/
class MyMonitor
{   
	
	 private Random rand = new Random();
	 private int x = 0;   /* variabili incapsulate: x,y */
	 private int y = 0;
	 private boolean done = false;  /* vale true x oppure y sono state decrementate */
	 
	 synchronized  void inc() 
	 {
		 if (rand.nextInt(10) > 5)
		 {
			 x++;
	         System.out.println("x incremented.");
		 }
	     else 
	     {
	    	 y++;
	         System.out.println("y incremented.");
	     }
	     
		 notifyAll();
	}
		
	synchronized  void decx() 
	{
		while ((x<=0) && !done) 
		{
			try 
			{ 
				System.out.println("Waiting to decrement x...");
				wait();
			} catch (InterruptedException e) {}
	        
			if (!done)
			{
				x--;
	            done=true;
	            System.out.println("x decremented.");
	            notify();
	        }
	    }
	}
	
	synchronized  void decy() 
	{
		while ((y<=0) && !done) 
		{
			try 
			{ 
				System.out.println("Waiting to decrement y...");
				wait();
			} catch (InterruptedException e) {}
	        
			if (!done)
			{
				y--;
	            done=true;
	            System.out.println("y decremented.");
	            notify();
			}
	    }
	}
	
	synchronized  int getx() 
	{
		return x;
	}
	
	synchronized  int gety() 
	{
		return y;
	}

}
