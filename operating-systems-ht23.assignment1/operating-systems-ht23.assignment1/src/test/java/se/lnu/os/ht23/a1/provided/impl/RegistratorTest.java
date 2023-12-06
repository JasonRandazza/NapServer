package se.lnu.os.ht23.a1.provided.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.lnu.os.ht23.a1.TestUtils;
import se.lnu.os.ht23.a1.provided.Registrar;
import se.lnu.os.ht23.a1.provided.data.VisitEntry;

public abstract class RegistratorTest {


	
	@BeforeEach
	public abstract void defineRegistrar();
	
	Registrar r;
	
	@Test
	void checkOneElement() {
		double napTime = 2.5;
		VisitEntry v = VisitEntry.createVisitEntry()
				.setArrivalTime(System.currentTimeMillis())
				.setClientName("Cli 1")
				.setNapEndTime(System.currentTimeMillis()+ (int)(napTime*1000.0) )
				.setNapTimeWanted(napTime)
				.setWaitEndTime(System.currentTimeMillis()+1000);
		
				
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		VisitEntry v2 = VisitEntry.createVisitEntry()
				.setArrivalTime(System.currentTimeMillis())
				.setClientName("Cli 1")
				.setNapEndTime(System.currentTimeMillis()+ (int)(napTime*1000.0) )
				.setNapTimeWanted(napTime)
				.setWaitEndTime(System.currentTimeMillis()+1000);
		
		r.addVisit(v);
		
		ArrayList<VisitEntry> toCheck = new ArrayList<VisitEntry>();
		toCheck.add(v2);
		assertTrue(TestUtils.checkEqual(r.getVisitRegistry(), toCheck));
	}


}
