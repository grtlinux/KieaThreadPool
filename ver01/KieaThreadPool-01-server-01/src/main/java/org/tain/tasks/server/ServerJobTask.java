package org.tain.tasks.server;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.object.stream.StreamObject;
import org.tain.object.stream.StreamTaskObject;
import org.tain.queue.stream.StreamTaskQueue;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ServerJobTask {

	@PostConstruct
	public void init() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
	}

	@Autowired
	private StreamTaskQueue streamTaskQueue;
	
	private StreamTaskObject streamTaskObject = null;
	
	@Async("serverJobTaskJob")
	public void jobProcess(String param) throws Exception {
		if (Flag.flag) log.info("KANG-20200902 >>>>> START {} {}", param, CurrentInfo.get());
		
		if (!Flag.flag) Sleep.run(3000);
		
		if (Flag.flag) {
			this.streamTaskObject = this.streamTaskQueue.get();
			if (Flag.flag) log.info("KANG-20200902 >>>>> 1. GET_SOCKET {} {}", param, CurrentInfo.get());
			
			while (true) {
				StreamObject req = this.streamTaskObject.recvStream();
				if (Flag.flag) log.info("KANG-20200902 >>>>> 2. RECV_REQ_STREAM {} {}", param, CurrentInfo.get());
				if (Flag.flag) JsonPrint.getInstance().printPrettyJson(req);
				
				if (Flag.flag) Sleep.run(1000);
				
				StreamObject res = null;
				if (Flag.flag) {
					// make res
					StringBuffer sb = new StringBuffer();
					sb.append("RES");
					sb.append("1234567890");
					sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
					sb.insert(0, String.format("%04d", sb.length()));
					
					res = new StreamObject(sb.toString());
				}
				if (Flag.flag) log.info("KANG-20200902 >>>>> 3. MADE_RES_STREAM {} {}", param, CurrentInfo.get());
				
				this.streamTaskObject.sendStream(res);
				if (Flag.flag) log.info("KANG-20200902 >>>>> 4. SEND_RES_STREAM {} {}", param, CurrentInfo.get());
			}
		}
		
		if (Flag.flag) log.info("KANG-20200902 >>>>> END   {} {}", param, CurrentInfo.get());
	}
}
