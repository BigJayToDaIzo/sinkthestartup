package com.hfj.sinkthestartup.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StartupTest {
	Startup s;

	@BeforeEach
	void setup(){
		s = new Startup("A");
	}

	@Test
	void createsStartupObject(){
		assertNotNull(s);
	}

	@Test
	void testCellsToString() {

	}

	@Test
	void testClearCells() {

	}

	@Test
	void testContainsCell() {

	}

	@Test
	void testConvertIntToRowName() {

	}

	@Test
	void testDisplayCells() {

	}

	@Test
	void testRandomizeCells() {
		assertTrue(s.getCells().isEmpty());
		assertNotNull(s.getCells());
		s.randomizeCells();
		assertEquals(3, s.getCells().size());
	}

	@Test
	void testRemoveCell() {

	}

	@Test
	void testToString() {

	}
}
