package se.lnu.os.ht23.a1.required.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.lnu.os.ht23.a1.TestUtils;
import se.lnu.os.ht23.a1.provided.NapServer;
import se.lnu.os.ht23.a1.provided.data.RegistrarType;
import se.lnu.os.ht23.a1.provided.data.VisitEntry;
import se.lnu.os.ht23.a1.provided.impl.RegistrarFactoryImpl;
import se.lnu.os.ht23.a1.required.NapServerImpl;

public class LazyServerTest {
    
    NapServer server;
	ArrayList<VisitEntry> toCheck;

	@BeforeEach
	public void createNewServer() {
		server = NapServerImpl.createInstance(RegistrarFactoryImpl.createRegistrar(RegistrarType.LAZY));
		toCheck = new ArrayList<VisitEntry>();
	}

    @Test
	void checkOneElement() {
		
		System.out.println("======Starting checkOneElement=====");
		double napTime = 1.0;

		createRequestAndEntry("Cli 1", napTime);

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, server.getVisitRegistry().size(), "server registry did not contain the expected number of elements.");
		assertEquals(1, toCheck.size(), "list of elements to check did not contain the expected number of elements.");
		assertTrue(TestUtils.checkEqual(server.getVisitRegistry(), toCheck));

	}
	
	
	@Test
	void checkFiveElements() {
		
		System.out.println("======Starting checkFiveElements=====");
		
		long startT = System.currentTimeMillis();
		double napTime = 1.0;

		createRequestAndEntry("Cli 1", napTime);
		createRequestAndEntry("Cli 2", napTime+0.05);
		createRequestAndEntry("Cli 3", napTime+0.1);
		createRequestAndEntry("Cli 4", napTime+0.15);
		createRequestAndEntry("Cli 5", napTime+0.2);
		
		
		try {
			Thread.sleep(16500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertTrue(System.currentTimeMillis()-startT<16500, "Creating the five elements and sleep 1 second each needed more than 2 seconds. It should be around the 1.5 seconds of sleep");
		Assertions.assertTrue(System.currentTimeMillis()-startT>900, "Creating the five elements and sleep 1 second each needed less than 900 milliseconds");
		//assertEquals(5, server.getVisitRegistry().size(), "server registry did not contain the expected number of elements.");
		//assertEquals(5, toCheck.size(), "list of elements to check did not contain the expected number of elements.");
		assertTrue(TestUtils.checkEqual(server.getVisitRegistry(), toCheck));
		//assertEquals(server.getVisitRegistry().size(), toCheck.size());

		for (int i = 0; i < server.getVisitRegistry().size(); i++) {
            System.out.println("Real" + server.getVisitRegistry().get(i).getClientName());
            System.out.println("Control" + toCheck.get(i).getClientName());
        }

	}
	
	
	@Test
	void checkSixElements() {
		
		System.out.println("======Starting checkSixElements=====");
		
		long startT = System.currentTimeMillis();
		double napTime = 1.0;

		createRequestAndEntry("Cli 1", napTime);
		createRequestAndEntry("Cli 2", napTime+0.05);
		createRequestAndEntry("Cli 3", napTime+0.1);
		createRequestAndEntry("Cli 4", napTime+0.15);
		createRequestAndEntry("Cli 5", napTime+0.2);
		
		//Now we need to fix the values in v because the is expected some waiting
		VisitEntry v = createRequestAndEntry("Cli 6", napTime);
		int waitTime = 1000; //It has to wait for cli 1 to finish, since the beginning. 
		v.setWaitEndTime(v.getWaitEndTime()+waitTime);
		v.setNapEndTime(v.getNapEndTime()+waitTime);
		
		
		
		Assertions.assertTrue(System.currentTimeMillis()-startT<1000, "Creating the six elements and sleep 1 second each needed more than 1 second. It should be almost immediate");
		
		//Give some time to finish the first round
		try {
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(server.getVisitRegistry().size(), 5, "At this point the five first clients should have finished, but not the sixth"); 
		
		// give some time to finish the last client
		try {
			Thread.sleep(1600);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(server.getVisitRegistry().size(), 6, "At this point all the six clients should have finished");
		
		assertTrue(TestUtils.checkEqual(server.getVisitRegistry(), toCheck));

	}
	

	private VisitEntry createRequestAndEntry(String cliName, double napTime) {
		VisitEntry v = VisitEntry.createVisitEntry().setArrivalTime(System.currentTimeMillis()).setClientName(cliName)
				.setNapEndTime(System.currentTimeMillis() + (int) (napTime * 1000.0)).setNapTimeWanted(napTime)
				.setWaitEndTime(System.currentTimeMillis());
		
		server.newNapRequest(cliName, napTime);

		toCheck.add(v);
		return v;
	}

}
