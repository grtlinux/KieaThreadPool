package org.tain.tasks.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.object.stream.StreamTaskObject;
import org.tain.queue.stream.StreamTaskQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ServerMainTask {

	@PostConstruct
	public void init() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
	}

	@Autowired
	private StreamTaskQueue streamTaskQueue;
	
	private ServerSocket serverSocket;
	private String listenHost = "localhost";
	private int listenPort = 9999;
	
	@Async("serverMainTaskJob")
	public void jobProcess(String param) throws Exception {
		if (Flag.flag) log.info("KANG-20200902 >>>>> START {} {}", param, CurrentInfo.get());
		
		if (!Flag.flag) Sleep.run(3000);
		
		if (Flag.flag) {
			try {
				this.serverSocket = new ServerSocket();
				this.serverSocket.bind(new InetSocketAddress(this.listenHost, this.listenPort));
				
				while (true) {
					log.info(">>>>> waiting for connection with port={}.....", this.listenPort);
					Socket socket = this.serverSocket.accept();   // block waiting for connect
					
					log.info(">>>>>  Server Connection is OK!!!");
					StreamTaskObject streamTaskObject = new StreamTaskObject(socket);
					
					this.streamTaskQueue.set(streamTaskObject);
					
					Sleep.run(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (this.serverSocket != null)
					try { this.serverSocket.close(); } catch (Exception e) {}
			}
		}
		
		if (Flag.flag) log.info("KANG-20200902 >>>>> END   {} {}", param, CurrentInfo.get());
	}
}
