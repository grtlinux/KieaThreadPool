package org.tain.working;

import org.springframework.stereotype.Component;
import org.tain.service.VersionServiceImpl;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataSourceWorking {

	public void version() {
		if (Flag.flag) log.info("KANG-20200902 >>>>> {} {}", CurrentInfo.get());
		
		System.out.println(">>>>> H2 version: " + VersionServiceImpl.getVersion());
	}
}
