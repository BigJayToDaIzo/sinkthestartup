package com.hfj.sinkthestartup.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomizerUtilityTest {
	RandomizerUtility r;
	@BeforeEach
	void setup(){
		r = new RandomizerUtility();
	}

	@Test
	void testGetCoinFlip() {
		int result = r.getCoinFlip();
		assertTrue(result >= 0 && result < 2);
		assertFalse(result < 0 && result >= 2);

	}

	@Test
	void testGetRandInt() {
		int result = r.getRandInt(33);
		assertTrue(result >= 0 && result < 33);
		assertFalse(result < 0 && result >= 32);
	}
}
