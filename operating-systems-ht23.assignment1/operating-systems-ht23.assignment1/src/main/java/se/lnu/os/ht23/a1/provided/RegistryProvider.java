package se.lnu.os.ht23.a1.provided;

import java.util.List;

import se.lnu.os.ht23.a1.provided.data.VisitEntry;

public interface RegistryProvider {

	List<VisitEntry> getVisitRegistry();
	
}
