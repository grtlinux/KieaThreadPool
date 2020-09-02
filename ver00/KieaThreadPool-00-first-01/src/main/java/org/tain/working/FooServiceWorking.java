package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.service.FooServiceImpl;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FooServiceWorking {

	@Autowired
	private FooServiceImpl fooServiceImpl;
	
	public void test01() throws Exception {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
		
		for (int i=0; i < 3; i++) {
			String param = String.format("(PARAM-%d)", i);
			this.fooServiceImpl.fooExecutor(param);
		}
		
		for (int i=0; i < 3; i++) {
			String param = String.format("(PARAM-%d)", i);
			this.fooServiceImpl.fooExecutor2(param);
		}
		
		Sleep.run(10 * 1000);
		
		for (int i=0; i < 5; i++) {
			String param = String.format("(PARAM-%d)", i);
			this.fooServiceImpl.fooExecutor(param);
		}
		
		try {
			for (int i=0; i < 5; i++) {
				String param = String.format("(PARAM-%d)", i);
				this.fooServiceImpl.fooExecutor2(param);
			}
		} catch (Exception e) {
			System.out.println("ERROR >>>>> " + e.getMessage());
		}
	}
}
