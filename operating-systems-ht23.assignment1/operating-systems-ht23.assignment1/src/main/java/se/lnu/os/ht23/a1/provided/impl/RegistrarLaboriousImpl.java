package se.lnu.os.ht23.a1.provided.impl;


import se.lnu.os.ht23.a1.provided.Registrar;
import se.lnu.os.ht23.a1.provided.data.VisitEntry;

public class RegistrarLaboriousImpl extends RegistrarImpl {

	
	private RegistrarLaboriousImpl() {
		
		super();
	}
	

	public synchronized void addVisit(VisitEntry v) {
		recordBook.add(v);

	}

	public static Registrar create() {
		return new RegistrarLaboriousImpl();
	}

}
