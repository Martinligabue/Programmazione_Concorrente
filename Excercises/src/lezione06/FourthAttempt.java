package lezione06;

class FourthAttempt extends Thread 
{
	private static volatile boolean[] want = {false, false};
    private String  myname;
    private int  id;
    
    public FourthAttempt(String myname, int id)
    {
        this.myname = myname;
        this.id = id;
    }
        
    public void run() 
    {
      for (int i = 0; i < 10; i++) 
      {
        System.out.println("NCS: " +  myname); /* NCS */
        want[id] = true;
        while (want[1-id])
        {
            want[id]=false;
            want[id]=true;
        } 
        System.out.println("Begin CS: " +  myname); /* CS */
        System.out.println("End CS:   " +  myname); /* CS */
        want[id]=false;
      }
    }

    public static void main(String[] args) {
      FourthAttempt p = new FourthAttempt("p",0);
      FourthAttempt q = new FourthAttempt("q",1);
      p.start();
      q.start();
      try { p.join(); q.join(); }
      catch (InterruptedException e) { }
      System.out.println("End of program");
    }
}