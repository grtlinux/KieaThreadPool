package org.tain.object.stream;

import lombok.Getter;

@Getter
public class StreamObject {

	private String stream;   // length + data
	private int length;
	private String data;
	
	public StreamObject(String stream) {
		this.stream = stream;
		this.length = Integer.parseInt(stream.substring(0, 4));
		this.data = stream.substring(4);
	}
}
