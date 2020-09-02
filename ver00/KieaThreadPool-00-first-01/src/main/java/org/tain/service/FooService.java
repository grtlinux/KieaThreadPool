package org.tain.service;

import org.springframework.scheduling.annotation.Async;

public interface FooService {

	@Async("fooExecutor")
	void fooExecutor(String param) throws Exception;
	
	@Async("fooExecutor2")
	void fooExecutor2(String param) throws Exception;
}
