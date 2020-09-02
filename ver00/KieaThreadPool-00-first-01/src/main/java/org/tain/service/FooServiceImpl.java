package org.tain.service;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tain.utils.Flag;
import org.tain.utils.Sleep;

@Component
//public class FooServiceImpl implements FooService {
public class FooServiceImpl {
	
	@PostConstruct
	public void init() {
		if (Flag.flag) System.out.println(">>>>> PostConstruct .... of ForServiceImpl..");
		if (Flag.flag) System.out.println(">>>>> PostConstruct .... of ForServiceImpl..");
		if (Flag.flag) System.out.println(">>>>> PostConstruct .... of ForServiceImpl..");
	}

	/*
	@Override
	public void fooExecutor(String param) throws Exception {
		//if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
		if (Flag.flag) System.out.println(">>>>> fooExecutor " + param + " START.....");
		Sleep.run(3000);
		if (Flag.flag) System.out.println(">>>>> fooExecutor " + param + " END.....");
	}

	@Override
	public void fooExecutor2(String param) throws Exception {
		//if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
		if (Flag.flag) System.out.println(">>>>> fooExecutor2 " + param + " START.....");
		Sleep.run(5000);
		if (Flag.flag) System.out.println(">>>>> fooExecutor2 " + param + " END.....");
	}
	*/
	
	@Async("fooExecutor")
	public void fooExecutor(String param) throws Exception {
		if (Flag.flag) System.out.println(">>>>> fooExecutor " + param + " START.....");
		Sleep.run(3000);
		if (Flag.flag) System.out.println(">>>>> fooExecutor " + param + " END.....");
	}
	
	@Async("fooExecutor2")
	public void fooExecutor2(String param) throws Exception {
		if (Flag.flag) System.out.println(">>>>> fooExecutor2 " + param + " START.....");
		Sleep.run(5000);
		if (Flag.flag) System.out.println(">>>>> fooExecutor2 " + param + " END.....");
	}
	
}
