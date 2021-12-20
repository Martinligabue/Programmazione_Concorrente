package lezione4;

public class FourthAttempFromQ extends Thread
{

	private volatile boolean wantp = false;
	private volatile boolean wantq = false;
	
	public void run() 
	{
		while(true) 
		{
			NCS();
			
			wantp = true;
			while(wantq) 
			{
				wantp = false;
				wantp = true;
			}
			
			CS();
			
			wantp = false;
		}
	}
	
	public void NCS() {}
	public void CS() {}
	
}
