package org.tain.object.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.tain.utils.Sleep;

public class StreamTaskObject {

	private Socket socket;
	private InetSocketAddress inetSocketAddress = null;
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	
	///////////////////////////////////////////////////////////////////////////
	
	public StreamTaskObject(Socket socket) throws Exception {
		this.socket = socket;
		this.inetSocketAddress = (InetSocketAddress) this.socket.getRemoteSocketAddress();
		this.inputStream = this.socket.getInputStream();
		this.outputStream = this.socket.getOutputStream();
	}
	
	public String getRemoteInfo() {
		return this.inetSocketAddress.toString();
	}
	
	public void close() {
		if (this.outputStream != null) {
			try { this.outputStream.close(); } catch (IOException e) {}
		}
		
		if (this.inputStream != null) {
			try { this.inputStream.close(); } catch (IOException e) {}
		}
		
		if (this.socket != null) {
			try { this.socket.close(); } catch (IOException e) {}
		}
	}
	
	///////////////////////////////////////////////////////////
	// recvPacket
	
	private static int DEFAULT_LENGTH_SIZE = 4;
	
	public StreamObject recvStream() {
		StreamObject streamObject = null;
		try {
			String strLength = this.recvLength(DEFAULT_LENGTH_SIZE);
			int length = Integer.parseInt(strLength);
			String strData = this.recvData(length);
			streamObject = new StreamObject(strLength + strData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return streamObject;
	}
	
	private String recvLength(int length) throws Exception {
		byte[] bytLength = this.recv(length);
		String strLength = new String(bytLength, "UTF-8");
		return strLength;
	}
	
	private String recvData(int length) throws Exception {
		byte[] bytData = this.recv(length);
		String strData = new String(bytData, "UTF-8");
		return strData;
	}
	
	///////////////////////////////////////////////////////////
	// sendPacket
	
	public StreamObject sendStream(StreamObject streamObject) {
		try {
			byte[] bytPacket = streamObject.getData().getBytes();
			int nsend = this.send(bytPacket);
			if (nsend != streamObject.getLength()) {
				throw new IOException("ERROR: wrong send");
			}
		} catch (Exception e) {
			e.printStackTrace();
			streamObject = null;
		}
		
		return streamObject;
	}
	
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	
	// recv and send
	
	public byte[] recv(int length) throws Exception {
		byte[] buffer = new byte[length];
		
		int nread = 0;
		int offset = 0;
		int sleepCount = 20;
		
		while (length > 0) {
			nread = this.inputStream.read(buffer, offset, length);  // read blocking
			if (nread < 0) {
				// no data, no available
				throw new IOException("ERROR: return value of read is negative(-)...");
			} else if (nread == 0) {
				Sleep.run(1000);
				sleepCount --;
				if (sleepCount <= 0) {
					throw new IOException("ERROR: over the limit of looping...");
				}
				continue;
			}
			
			sleepCount = 20;
			
			offset += nread;
			length -= nread;
		}
		
		return buffer;
	}
	
	public int send(byte[] data) throws Exception {
		int length = data.length;
		int offset = 0;
		
		this.outputStream.write(data, offset, length);
		
		return length;
	}
}
