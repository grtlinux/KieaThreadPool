package org.tain.working.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.tasks.client.ClientJobTask;
import org.tain.tasks.client.ClientMainTask;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClientWorking {

	@Autowired
	private ClientMainTask clientMainTask;
	
	@Autowired
	private ClientJobTask clientJobTask;
	
	@Deprecated
	public void testClientTask() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
		
		/*
		if (Flag.flag) {
			// client main task
			for (int i=0; i < 5; i++) {
				try {
					this.clientMainTask.jobProcess(String.format("%03d", i));
				} catch (Exception e) {
					if (Flag.flag) log.info("KANG-20200902 EXCEPTION >>>>> {} {}", CurrentInfo.get(), e.getMessage());
				}
			}
		}
		
		if (Flag.flag) {
			// client job task
			for (int i=0; i < 5; i++) {
				try {
					this.clientJobTask.jobProcess(String.format("%03d", 100 + i));
				} catch (Exception e) {
					if (Flag.flag) log.info("KANG-20200902 EXCEPTION >>>>> {} {}", CurrentInfo.get(), e.getMessage());
				}
			}
		}
		
		Sleep.run(30 * 1000);
		
		if (Flag.flag) {
			// client main task
			try {
				this.clientMainTask.jobProcess(String.format("%03d", 10));
			} catch (Exception e) {
				if (Flag.flag) log.info("KANG-20200902 EXCEPTION >>>>> {} {}", CurrentInfo.get(), e.getMessage());
			}
		}
		*/
	}
	
	public void loadClientTask() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// client main task
			try {
				this.clientMainTask.jobProcess(String.format("MAIN-000"));
			} catch (Exception e) {
				if (Flag.flag) log.info("KANG-20200902 EXCEPTION >>>>> {} {}", CurrentInfo.get(), e.getMessage());
			}
		}
		
		if (Flag.flag) {
			// client job task
			for (int i=0; i < 5; i++) {
				try {
					this.clientJobTask.jobProcess(String.format("JOB-%03d", 100 + i));
				} catch (Exception e) {
					if (Flag.flag) log.info("KANG-20200902 EXCEPTION >>>>> {} {}", CurrentInfo.get(), e.getMessage());
				}
			}
		}
	}
}
