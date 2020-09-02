package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.service.FooServiceImpl;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

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
	}
}
