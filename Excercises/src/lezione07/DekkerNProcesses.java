package lezione07;

public class DekkerNProcesses extends Thread
{
	private static volatile boolean[] want = {false, false, false};
	private static volatile boolean[] passed = {false, false, false};
	
	int turn = 1;
	
	private String name;
	private int id;
	private boolean done = false;
	public DekkerNProcesses(String name, int id) 
	{
		this.name = name;
		this.id = id;
	}
	
	public void run() 
	{
		for(int i = 0; i < 10; i++) //"loop forever"
		{
			System.out.println("NCS " + name); //NCS
			want[id] = true;
			while(!done) 
			{
				while(turn != id) 
				{
					if(!want[turn]) 
					{
						turn = id;
					}
				}
				
				passed[id] = true;
				done = true;
				for(int j = 1; j < 3; j++) 
				{
					if(passed[j]) 
					{
						passed[id] = false;
						done = false;
					} 
				}
			}
			
			System.out.println("Begin CS: " +  name); /* CS */
	        System.out.println("End CS:   " +  name); /* CS */
	        
	        passed[id] = false;
	        want[id] = false;
		}
	}
	
}
