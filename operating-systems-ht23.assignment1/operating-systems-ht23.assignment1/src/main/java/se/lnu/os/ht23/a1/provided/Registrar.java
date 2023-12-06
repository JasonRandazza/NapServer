package se.lnu.os.ht23.a1.provided;

import se.lnu.os.ht23.a1.provided.data.VisitEntry;

public interface Registrar extends RegistryProvider {

	
	void addVisit(VisitEntry v);

	default void setSleep(int d) {
		throw new UnsupportedOperationException();
	};
	
	
}
