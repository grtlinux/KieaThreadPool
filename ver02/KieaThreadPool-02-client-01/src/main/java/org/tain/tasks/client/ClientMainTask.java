package org.tain.tasks.client;

import java.net.InetSocketAddress;
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
public class ClientMainTask {

	@PostConstruct
	public void init() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
	}

	@Autowired
	private StreamTaskQueue streamTaskQueue;
	
	private Socket connectSocket;
	private String connectHost = "localhost";
	private int connectPort = 9999;
	
	@Async("clientMainTaskJob")
	public void jobProcess(String param) throws Exception {
		if (Flag.flag) log.info("KANG-20200902 >>>>> START {} {}", param, CurrentInfo.get());
		
		if (!Flag.flag) Sleep.run(3000);
		
		if (Flag.flag) {
			try {
				this.connectSocket = new Socket();
				this.connectSocket.connect(new InetSocketAddress(this.connectHost, this.connectPort));
				if (Flag.flag) log.info(">>>>>  Client Connection is OK!!!");
				
				StreamTaskObject streamTaskObject = new StreamTaskObject(this.connectSocket);
				this.streamTaskQueue.set(streamTaskObject);
				
				if (Flag.flag) Sleep.run(1000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
		
		if (Flag.flag) log.info("KANG-20200902 >>>>> END   {} {}", param, CurrentInfo.get());
	}
}
