package lezione05;

public class CountCS extends Thread 
{
	static volatile int n = 0;
	static volatile Object lock = new Object();
	
	int temp;
	int id;	//campo identità del thread
	
	CountCS(int id) //costruttore, inizializza il campo id
	{
		this.id = id;
	}
	
	public void run() 
	{
		for(int i = 0; i < 10; i++) 
		{
			System.out.println("Process " + id + " in NCS"); //NCS
			synchronized(lock) //CS = blocco synchronized 
			{
				temp = n;
				n = temp + 1;
			}
		}
	}
	
	public static void main(String[] args) 
	{
		CountCS p = new CountCS(1);
		CountCS q = new CountCS(2);
		p.start();
		q.start();
		
		try {
			p.join();
			q.join();
		}
		catch(InterruptedException e) { }
		
		System.out.println("The value of n is " + n);
	}
}
