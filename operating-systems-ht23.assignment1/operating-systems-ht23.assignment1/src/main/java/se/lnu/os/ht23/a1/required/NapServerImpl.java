package se.lnu.os.ht23.a1.required;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.lang.Thread;
import se.lnu.os.ht23.a1.provided.NapServer;
import se.lnu.os.ht23.a1.provided.Registrar;
import se.lnu.os.ht23.a1.provided.data.VisitEntry;

public class NapServerImpl implements NapServer {
    private NapServerImpl(Registrar r) {
        registrar = r;
    }
    Registrar registrar;

    private ExecutorService napService = Executors.newFixedThreadPool(5); // Thread pool with 5 threads
    private Deque<VisitEntry>waitingHall;
    public static NapServer createInstance(Registrar r) {
        NapServer n = (new NapServerImpl(r)).initialize();
        return n;
    }
    private NapServer initialize() {
        //TODO You have to write this method to initialize your server:
        //For instance, create the Hammocks, the waiting hall, etc.

        waitingHall = new ArrayDeque<>();
        return this;
    }
    @Override
    public List<VisitEntry> getVisitRegistry() {
        return registrar.getVisitRegistry();
    }
    private VisitEntry createEntry(String clientName, double napDuration) {
        VisitEntry v = VisitEntry.createVisitEntry();
        v.setClientName(clientName);
        v.setNapTimeWanted(napDuration);
        return v;
    }
    
    @Override
	public void newNapRequest(String clientName, double napDuration) {
        VisitEntry v = createEntry(clientName, napDuration);
        synchronized (this) {
            waitingHall.add(v);
			v.setArrivalTime(System.currentTimeMillis());
        }

        napService.submit(() -> {
			synchronized (this) {
				waitingHall.remove(v);
				v.setWaitEndTime(System.currentTimeMillis());
			}
			try {
				// Simulating nap
				Thread.sleep((long) (napDuration * 1000));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				newNapRequest(clientName, napDuration);
				return;
			}
			synchronized (this) {
            
				v.setNapEndTime(System.currentTimeMillis());
				registrar.addVisit(v);
                
			}
        });
    }
    @Override
    public void stop() {
        // TODO You have to write this method for a clean stop of your server
        napService.shutdown();
        try {
        if (!napService.awaitTermination(60, TimeUnit.SECONDS)) {
            napService.shutdownNow();
            if (!napService.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("Thread pool did not terminate");
            }
        }
    } catch (InterruptedException ie) {
        napService.shutdownNow();
        Thread.currentThread().interrupt();
     }
}
}
