/* This test does not actually test anything. 
 * There is no code to test after all, and the 
 * fourth attempt btw does not respect
 * Starvation Freedom.
 * */

package lezione4;

import static org.junit.Assert.*;

import org.junit.Test;

import lezione04.FourthAttempFromP;
import lezione04.FourthAttempFromQ;

public class TestCodice {

	@Test
	public void test() {
		FourthAttempFromP p = new FourthAttempFromP();
		FourthAttempFromQ q = new FourthAttempFromQ();
		
		p.start();
		q.start();
		
		try {
			p.join();
			q.join();
		}
		catch(InterruptedException e) {}
		
	}

}
