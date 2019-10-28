import java.io.*;
import java.util.*;

class DemandPaging {
	private int maxFrames = 0;
	private int pageFaults = 0;
	private String method;

	private Set<Integer> frames;
	private Queue<Integer> fifo;
	private HashMap<Integer, Integer> lru;
	private int lruCounter;

	private String logStringVictim = String.format("%c[45m VICTIM %c[0m", 27, 27);
	private String logStringInsert = String.format("%c[44m INSERT %c[0m", 27, 27);
	private String logStringFrames = String.format("%c[44m FRAMES %c[0m ", 27, 27);
	private String logStringDemand = String.format("%c[46m DEMAND %c[0m", 27, 27);
	private String logStringUpdate;

	DemandPaging(int maxFrames, String method) {
		this.frames = new HashSet<>();
		this.maxFrames = maxFrames;
		this.method = method;

		if (this.method == "fifo") {
			this.fifo = new LinkedList<>();
		}

		if (this.method == "lru") {
			this.lru = new HashMap<>();
			this.lruCounter = 0;
		}

		logStringUpdate = String.format("%c[48m %6s %c[0m ", 27, this.method, 27);
	}

	private Integer update(int pageId) throws Exception {
		System.out.println(this.logStringUpdate + this.lru + " " + this.pageFaults);
		if (this.method == "fifo") {
			if (this.frames.contains(pageId)) {
				return null;
			}

			if (this.fifo.size() < this.maxFrames) {
				System.out.println(String.format("%s Inserting %d (%d/%d)", logStringInsert, pageId, this.fifo.size(), this.maxFrames));
				this.fifo.add(pageId);
				return null;
			}

			Integer victim = this.fifo.peek();
			System.out.println(String.format("%s Victim id %d (%d/%d)", logStringVictim, victim, this.fifo.size(), this.maxFrames));
			this.fifo.remove();
			this.fifo.add(pageId);
			return victim;
		}

		if (this.method == "lru") {
			if (this.lru.containsKey(pageId)) {
				this.lru.put(pageId, this.lruCounter++);
				return null;
			}

			if (this.lru.size() < this.maxFrames) {
				System.out.println(String.format("%s Inserting %d (%d/%d)", logStringInsert, pageId, this.lru.size(), this.maxFrames));
				this.lru.put(pageId, this.lruCounter++);
				return null;
			}

			Integer victim = this.lru.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue)).get().getKey();
			System.out.println(String.format("%s Victim id %d (%d/%d)", logStringVictim, victim, this.lru.size(), this.maxFrames));
			this.lru.remove(victim);
			this.lru.put(pageId, this.lruCounter++);
			return victim;
		}

		return null;
	}

	public Boolean demand(int pageId) throws Exception {
		Integer victim = this.update(pageId);
		if (frames.contains(pageId)) {
			System.out.println(String.format("%s Page hit happened. Returning %d. (%d/%d)", logStringDemand, pageId, this.frames.size(), this.maxFrames));
			System.out.println(logStringFrames + this.frames);
			return true;
		}

		System.out.println(String.format("%s Page fault happended. Inserted %d and removing %d. (%d/%d)", logStringDemand, pageId, victim, this.frames.size(), this.maxFrames));
		this.pageFaults++;

		if (victim == null && this.frames.size() >= this.maxFrames) {
			throw new Exception("Cannot insert without a victim as frames are more than max allowed");
		}

		this.frames.add(pageId);
		if (victim != null) {
			this.frames.remove(victim);
		}


		System.out.println(logStringFrames + this.frames);
		return false;
	}

	public int getPageFaults() {
		return this.pageFaults;
	}
}

class Main {
	public static void main(String[] args) throws Exception {
		DemandPaging demandPaging = new DemandPaging(3, "lru");
		String referenceString = "70120304230321201701";
		for(int i = 0; i < referenceString.length(); i++) {
			int pageId = referenceString.charAt(i) - '0';
			demandPaging.demand(pageId);
		}

		System.out.println("Page faults => " +  demandPaging.getPageFaults());
		// 1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5 } Belady's Anomaly
	}
}

