package lezione18;
import java.util.Random;

/* esercizio 2 */
class TestRWMonitor 
{
    Random rand = new Random();
    final int Iter = 3;
    RWMonitor monitor = new RWMonitor();

    class Reader extends Thread 
    {
        int Name;
        Reader(int ID) 
        { 
        	Name = ID; 
        }

        public void run() 
        {
            for (int I = 1; I < Iter; I++) 
            {
                monitor.StartRead();
                System.out.println("Reader " + Name + " starts reading ");
                System.out.println("Reader " + Name + " ends reading ");
                monitor.EndRead();
            }
        }
    }

    class Writer extends Thread 
    {
        int Name;
        Writer(int ID) 
        { 
        	Name = ID; 
        }

        public void run() 
        {
            for (int I = 1; I < Iter; I++) 
            {
                try
                {
                	Thread.sleep(rand.nextInt(2));
                } catch (InterruptedException e) {};
                
                monitor.StartWrite();
                System.out.println("Writer " + Name + " starts writing ");
                System.out.println("Writer " + Name + " ends writing ");
                monitor.EndWrite();
            }
        }
    }

    TestRWMonitor() 
    {
        Reader R1 = new Reader(1);
        Reader R2 = new Reader(2);
        Reader R3 = new Reader(3);
        
        Writer W1 = new Writer(1);
        Writer W2 = new Writer(2);
        Writer W3 = new Writer(3);
        
        R1.start(); 
        W1.start();
        R2.start(); 
        W2.start();
        R3.start(); 
        W3.start();  
        
        try 
        { 
            R1.join(); 
            W1.join();
            R2.join(); 
            W2.join();
            R3.join(); 
            W3.join();
        } catch (InterruptedException e) {}
                
        System.out.println("Program terminated correctly!");     
    }

    public static void main(String[] args) 
    {
        TestRWMonitor tm = new TestRWMonitor();
    }
}
