package se.lnu.os.ht23.a1.provided.data;

public class VisitEntry implements Cloneable{

	private String clientName;
	private double napTimeWanted;
	private long arrivalTime;
	private long waitEndTime;


	private long napEndTime;
	private long entryCreationTime;
	
	private VisitEntry() {}
	
	public static VisitEntry createVisitEntry() { // maybe needed the implements cloneable
		
		VisitEntry theVisit = new VisitEntry();
		theVisit.entryCreationTime = System.currentTimeMillis();
		return theVisit;
		
	}

	public String getClientName() {
		return clientName;
	}

	public VisitEntry setClientName(String clientName) {
		this.clientName = clientName;
		return this;
	}

	public double getNapTimeWanted() {
		return napTimeWanted;
	}

	public VisitEntry setNapTimeWanted(double napTimeWanted) {
		this.napTimeWanted = napTimeWanted;
		return this;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public VisitEntry setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
		return this;
	}

	public long getNapEndTime() {
		return napEndTime;
	}

	public VisitEntry setNapEndTime(long napEndTime) {
		this.napEndTime = napEndTime;
		return this;
	}

	public long getWaitEndTime() {
		return waitEndTime;
	}

	public VisitEntry setWaitEndTime(long waitEndTime) {
		this.waitEndTime = waitEndTime;
		return this;
	}

	
	@Override
	public String toString() {
		String result="Client name= "+ getClientName()+ "; ";
				result+="It arrived at Napserver at "+ getArrivalTime();
				result+=" asking for a nap of "+ getNapTimeWanted() + " seconds;";
				result+="It had to wait until "+ getWaitEndTime() + " for the hammock; ";
				result+="It took the nap and left satisfied our napServer at "+ getNapEndTime() + "." + System.getProperty("line.separator");
				result+="[And this record entry was created at " + entryCreationTime +"]";
				
		return result;
	}
	
	 @Override
	 public Object clone() throws CloneNotSupportedException {
	 return super.clone();
	 }
}
