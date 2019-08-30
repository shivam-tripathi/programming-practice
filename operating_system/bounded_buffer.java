/**
 * @ Shivam Tripathi
 * Semaphores are not mutex!
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;
import java.util.Random;
import java.lang.Runnable;
import java.lang.Thread;


class BoundedBuffer {
	private int buf[];
	
	int size;
	private final Lock accessLock = new ReentrantLock(true);
	private final Semaphore remaining_space;
	private final Semaphore remaining_reads = new Semaphore(0);
	
	private int readIndex = 0;
	private int writeIndex = 0;

	// Logging
	String magenta = (char)27 + "[45m";
	String blue = (char)27 + "[44m";
	String reset =  (char)27 + "[0m";

	BoundedBuffer(int size) {
		this.size = size;
		this.buf = new int[this.size];
		this.remaining_space = new Semaphore(size);
	}

	public int read() {
		int _read = -1;
		try {
			this.remaining_reads.acquire();
			this.accessLock.lock();
			_read = this.buf[this.readIndex];
			System.out.println(this.blue + " Read  " + Thread.currentThread().getName() + " " + this.reset + " " + _read);
		  	this.readIndex = (this.readIndex + 1) % this.size;
		  	this.remaining_space.release();
		} catch (Exception e) {
			System.out.println("Some error occurred while reading");
		} finally {
		  this.accessLock.unlock(); // Release the lock, crash or not
	  	}
	  	return _read;
	}

	public Boolean write(int val) {
		Boolean status = false;
		try {
			this.remaining_space.acquire();
			this.accessLock.lock();
			this.buf[this.writeIndex] = val;
			System.out.println(this.magenta + " Write " + Thread.currentThread().getName() + " " + this.reset + " "  + val);
			this.writeIndex = (this.writeIndex + 1) % this.size;
			this.remaining_reads.release();
			status = true;
		} catch (Exception e) {
			System.out.println("Some error occurred while writing");
		} finally {
			this.accessLock.unlock(); // Release the lock, crash or not
		}
		return status;
	}
}

class Producer implements Runnable {
	BoundedBuffer buf;
	Random rand = new Random();

	Producer(BoundedBuffer buf) {
		this.buf = buf;
		this.rand = new Random();
	}

	public void write() {
		// System.out.println("Producer: Attempting to write");
		int r = this.rand.nextInt(100);
		buf.write(r);
	}

	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				this.write();
				int sleep = this.rand.nextInt(1000);
				Thread.sleep(sleep);
			}
		} catch (InterruptedException e) {
			System.out.println("Exception InterruptedException occurred while writing");
		}
	}
}

class Consumer implements Runnable {
	BoundedBuffer buf;
	Random rand = new Random();

	Consumer(BoundedBuffer buf) {
		this.buf = buf;
	}

	public int read() {
		// System.out.println("Consumer: Attempting to read");
		int val = this.buf.read();
		return val;				
	}

	public void run() {
		try {
			while(true) {
				int val = this.read();
				int sleep = this.rand.nextInt(1000);
				Thread.sleep(sleep);
			}			
		} catch (InterruptedException e) {
			System.out.println("Exception InterruptedException occurred while reading");
		}
	}

}

class Main {
	public static void main(String[] args) {
		System.out.println("Running main!");	
		BoundedBuffer buffer = new BoundedBuffer(10);
		
		/* Create and start 10 consumer and 10 producer threads */
		Consumer consumers[] = new Consumer[10];
		Thread consumerThreads[] = new Thread[10];

		Producer producers[] = new Producer[10];
		Thread producerThreads[] = new Thread[10];

		for (int i = 0; i < 10; i++) {
			consumers[i] = new Consumer(buffer);
			consumerThreads[i] = new Thread(consumers[i], "Consumer " + i);
			consumerThreads[i].start();

			producers[i] = new Producer(buffer);
			producerThreads[i] = new Thread(producers[i], "Producer " + i);
			producerThreads[i].start();
		}

 		// Consumer consumer = new Consumer(buffer);
		// Thread consumerThread = new Thread(consumer);
		// consumerThread.start();

		// Producer producer = new Producer(buffer);
		// Thread producerThread = new Thread(producer);
		// producerThread.start();
	}
}