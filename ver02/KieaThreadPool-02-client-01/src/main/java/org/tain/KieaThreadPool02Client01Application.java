package org.tain;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.working.client.ClientWorking;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
//@EnableScheduling
@Slf4j
public class KieaThreadPool02Client01Application implements CommandLineRunner {

	public static void main(String[] args) {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaThreadPool02Client01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) job01();
		if (Flag.flag) job02();
		if (Flag.flag) job03();
		if (Flag.flag) job04();
		if (Flag.flag) job05();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ClientWorking clientWorking;
	
	@SuppressWarnings("deprecation")
	private void job01() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) this.clientWorking.testClientTask();
		if (Flag.flag) this.clientWorking.loadClientTask();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void job02() throws Exception {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void job03() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
	}

	private void job04() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
	}

	private void job05() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
	}
}
