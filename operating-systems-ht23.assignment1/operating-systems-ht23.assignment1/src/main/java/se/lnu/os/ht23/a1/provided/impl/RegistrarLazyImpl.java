package se.lnu.os.ht23.a1.provided.impl;


import se.lnu.os.ht23.a1.provided.Registrar;
import se.lnu.os.ht23.a1.provided.data.VisitEntry;

public class RegistrarLazyImpl extends RegistrarImpl {

	private int sleepTime; 
	private RegistrarLazyImpl() {
		
		super();
	}
	

	public void addVisit(VisitEntry v) {
		int position = recordBook.size();
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recordBook.add(position, v);
	}

	public static Registrar create() {
		return new RegistrarLazyImpl();
	}
	
	@Override
	public void setSleep(int d) {
		sleepTime = d;
	}

}
