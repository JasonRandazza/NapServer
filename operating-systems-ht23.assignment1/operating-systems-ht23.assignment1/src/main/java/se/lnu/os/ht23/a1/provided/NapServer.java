package se.lnu.os.ht23.a1.provided;

public interface NapServer extends RegistryProvider{

	void newNapRequest(String clientName, double napDuration);
	
	void stop();	
}
