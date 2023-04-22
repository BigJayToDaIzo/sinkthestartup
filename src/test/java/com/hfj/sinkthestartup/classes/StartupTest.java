package com.hfj.sinkthestartup.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StartupTest {
	Startup s1;

	@BeforeEach
	void setup(){
		s1 = new Startup("A");
	}

	@Test
	void testCellsToString() {
		s1.setCell("A0");
		s1.setCell("A1");
		s1.setCell("A2");
		String expected = "A0 + A1 + A2";
		String received = s1.cellsToString().toString();
		assertEquals(expected, received);

	}

	@Test
	void testClearCells() {
		s1.setCell("A1");
		s1.setCell("A2");
		s1.setCell("A3");
		assertEquals(3, s1.getCells().size());
		s1.clearCells();
		assertEquals(0, s1.getCells().size());

	}

	@Test
	void testContainsCell() {
		s1.setCell("A1");
		assertTrue(s1.containsCell("A1"));
		assertFalse(s1.containsCell("A0"));

	}

	@Test
	void testConvertIntToRowName() {
		assertEquals('A', s1.convertIntToRowName(0));
		assertEquals('B', s1.convertIntToRowName(1));
		assertEquals('C', s1.convertIntToRowName(2));
		assertEquals('D', s1.convertIntToRowName(3));
		assertEquals('E', s1.convertIntToRowName(4));
		assertEquals('F', s1.convertIntToRowName(5));
		assertEquals('G', s1.convertIntToRowName(6));
		assertEquals('H', s1.convertIntToRowName(7));
		// any other int returns the char 0
		assertEquals('0', s1.convertIntToRowName(99));
		assertEquals('0', s1.convertIntToRowName(-1));
	}

	@Test
	void testRandomizeCells() {
		// DI randomizers into class to unit test

	}

	@Test
	void testRemoveCell() {
		s1.setCell("A1");
		assertTrue(s1.containsCell("A1"));
		s1.removeCell("A1");
		assertFalse(s1.containsCell("A1"));
	}

	@Test
	void testToString() {
		s1.setCell("A0");
		s1.setCell("A1");
		s1.setCell("A2");
		String received = s1.toString();
		assertEquals("name: A | cells: [A0, A1, A2]", received);
		s1.clearCells();
		received = s1.toString();
		assertEquals("name: A | cells: []", received);
	}
}
