package lezione18;

/* Esercizio 1 */
class RWMonitor 
{
    private volatile int readers = 0; 
    private volatile boolean writing = false;
    
    synchronized void StartRead() 
    {
	    while (writing) 
	    {
	    	try 
	    	{
	    		wait();
		    } catch (InterruptedException e) {}
	    }
		    
	    readers = readers + 1;
		notifyAll();
    }

    synchronized void EndRead() 
    {
        readers = readers - 1;
        
        if (readers == 0) 
        { 
        	notifyAll();
        }
    }

    synchronized void StartWrite() 
    {
	    while (writing || (readers != 0)) 
	    {
	    	try 
	    	{
	    		wait();
	    	} catch (InterruptedException e) {}
	    }
	    
	    writing = true;
    }

    synchronized void EndWrite() 
    {
        writing = false;
        notifyAll();
    }
}
