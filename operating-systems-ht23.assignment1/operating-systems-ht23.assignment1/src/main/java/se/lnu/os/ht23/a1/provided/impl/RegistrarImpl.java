package se.lnu.os.ht23.a1.provided.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import se.lnu.os.ht23.a1.provided.Registrar;
import se.lnu.os.ht23.a1.provided.data.VisitEntry;

public abstract class RegistrarImpl implements Registrar {

	List<VisitEntry> recordBook;
	
	protected RegistrarImpl() {
		
		recordBook = new ArrayList<VisitEntry>();
	}
	
	
	public synchronized List<VisitEntry> getVisitRegistry() {
		List<VisitEntry> copy = new ArrayList<VisitEntry>(recordBook);
		Collections.copy(copy, recordBook);
		return copy;
	}


}
