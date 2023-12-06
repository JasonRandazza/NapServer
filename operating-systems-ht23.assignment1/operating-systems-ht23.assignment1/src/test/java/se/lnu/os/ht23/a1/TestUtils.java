package se.lnu.os.ht23.a1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Objects;

import se.lnu.os.ht23.a1.provided.data.VisitEntry;

public class TestUtils {

	private static long toleranceMillis=250;
	
	
	public static boolean checkEqual(List<VisitEntry> a, List<VisitEntry> b) {
		assertEquals(a.size(),b.size());
		
		for(int i=0; i<a.size(); i++) {
			if (!checkEquals(a.get(i),b.get(i))) {return false;}
		}
		
		return true;
		
	}
	
	
	

	private static boolean checkEquals(VisitEntry a, VisitEntry b) {

		assertTrue(similar(a.getArrivalTime(),b.getArrivalTime()), "expected similar: " + a.getArrivalTime()+ ","+b.getArrivalTime());
		assertEquals(a.getClientName(), b.getClientName());
		assertTrue(similar(a.getNapEndTime(),b.getNapEndTime()), "expected similar: " + a.getNapEndTime() + "," + b.getNapEndTime());
		assertTrue(similar(a.getWaitEndTime(),b.getWaitEndTime()));
		assertTrue(same(a.getNapTimeWanted(),b.getNapTimeWanted()), "expected same: " + a.getNapTimeWanted()+","+b.getNapTimeWanted());
		return true;
	}


	
	
	private static boolean similar(long a, long b) {
		return Math.abs(a-b)<toleranceMillis;
	}

	private static boolean same(double a, double b) {
		// Some small tolerance for doubles in case of format conversions during the student's code
		return Math.abs(a-b)<0.01;
	}
	
	
}
