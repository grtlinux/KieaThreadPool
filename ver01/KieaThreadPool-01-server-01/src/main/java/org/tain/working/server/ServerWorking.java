package org.tain.working.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.tasks.server.ServerJobTask;
import org.tain.tasks.server.ServerMainTask;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ServerWorking {

	@Autowired
	private ServerMainTask serverMainTask;
	
	@Autowired
	private ServerJobTask serverJobTask;
	
	@Deprecated
	public void testServerTask() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
		
		/*
		if (Flag.flag) {
			// server main task
			for (int i=0; i < 5; i++) {
				try {
					this.serverMainTask.jobProcess(String.format("%03d", i));
				} catch (Exception e) {
					if (Flag.flag) log.info("KANG-20200902 EXCEPTION >>>>> {} {}", CurrentInfo.get(), e.getMessage());
				}
			}
		}
		
		if (Flag.flag) {
			// server job task
			for (int i=0; i < 5; i++) {
				try {
					this.serverJobTask.jobProcess(String.format("%03d", 100 + i));
				} catch (Exception e) {
					if (Flag.flag) log.info("KANG-20200902 EXCEPTION >>>>> {} {}", CurrentInfo.get(), e.getMessage());
				}
			}
		}
		
		Sleep.run(30 * 1000);
		
		if (Flag.flag) {
			// server main task
			try {
				this.serverMainTask.jobProcess(String.format("%03d", 10));
			} catch (Exception e) {
				if (Flag.flag) log.info("KANG-20200902 EXCEPTION >>>>> {} {}", CurrentInfo.get(), e.getMessage());
			}
		}
		*/
	}
	
	public void loadServerTask() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// server main task
			try {
				this.serverMainTask.jobProcess(String.format("MAIN-000"));
			} catch (Exception e) {
				if (Flag.flag) log.info("KANG-20200902 EXCEPTION >>>>> {} {}", CurrentInfo.get(), e.getMessage());
			}
		}
		
		if (Flag.flag) {
			// server job task
			for (int i=0; i < 5; i++) {
				try {
					this.serverJobTask.jobProcess(String.format("JOB-%03d", 100 + i));
				} catch (Exception e) {
					if (Flag.flag) log.info("KANG-20200902 EXCEPTION >>>>> {} {}", CurrentInfo.get(), e.getMessage());
				}
			}
		}
	}
}
