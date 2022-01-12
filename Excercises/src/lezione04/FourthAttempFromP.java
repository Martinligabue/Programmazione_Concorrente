package lezione04;

public class FourthAttempFromP extends Thread
{

	private volatile boolean wantp = false;
	private volatile boolean wantq = false;
	
	public void run() 
	{
		while(true) 
		{
			NCS();
			
			wantq = true;
			while(wantp) 
			{
				wantq = false;
				wantq = true;
			}
			
			CS();
			
			wantq = false;
		}
	}
	
	public void NCS() {}
	public void CS() {}	
}
