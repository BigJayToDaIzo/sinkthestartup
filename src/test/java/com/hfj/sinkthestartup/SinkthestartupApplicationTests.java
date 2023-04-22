package com.hfj.sinkthestartup;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SinkthestartupApplicationTests {

	@Autowired
	private SinkthestartupApplication app = new SinkthestartupApplication();

	@Test
	void contextLoads(){
		assertNotNull(app);
	}

}
