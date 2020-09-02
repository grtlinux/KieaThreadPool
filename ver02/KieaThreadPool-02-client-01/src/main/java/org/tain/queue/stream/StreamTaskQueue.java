package org.tain.queue.stream;

import java.util.LinkedList;

import org.springframework.stereotype.Component;
import org.tain.object.stream.StreamTaskObject;

@Component
public class StreamTaskQueue {

	private final LinkedList<StreamTaskObject> queue = new LinkedList<>();
	
	public synchronized void set(StreamTaskObject taskObject) {
		this.queue.addLast(taskObject);
		this.notifyAll();
	}
	
	public synchronized StreamTaskObject get() {
		while (this.queue.size() <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		return this.queue.removeFirst();
	}
}
