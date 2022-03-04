package testSudoku;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sudoku.Sudoku;

class TestSudokuSolver {
	Sudoku SudokuSolver; 

	@BeforeEach
	void setUp() throws Exception {
		SudokuSolver = new Sudoku(new int[9][9]);
	}

	@AfterEach
	void tearDown() throws Exception {
		SudokuSolver.clear();
	}
	
	/**
	 * Test if solve
	 */
	@Test
	void testSolve() {
		// test case 1
		assertTrue(SudokuSolver.solve(), "no solution for empty board");
		
		//test case 2
		SudokuSolver.clear();
		SudokuSolver.add(0, 0, 1);
		SudokuSolver.add(0, 1, 1);
		assertTrue(!SudokuSolver.solve(), "solution for board with no solution");
		SudokuSolver.clear();
		SudokuSolver.add(0, 0, 1);
		SudokuSolver.add(1, 0, 1);
		assertTrue(!SudokuSolver.solve(), "solution for board with no solution");
		SudokuSolver.clear();
		SudokuSolver.add(8, 8, 1);
		SudokuSolver.add(7, 7, 1);
		assertTrue(!SudokuSolver.solve(), "solution for board with no solution");

		//test case 3
		SudokuSolver.clear();
		SudokuSolver.add(0, 0, 1);
		SudokuSolver.add(0, 1, 2);
		SudokuSolver.add(0, 2, 3);
		SudokuSolver.add(1, 0, 4);
		SudokuSolver.add(1, 1, 5);
		SudokuSolver.add(1, 2, 6);
		SudokuSolver.add(2, 3, 7);
		assertTrue(!SudokuSolver.solve(), "solution for board with no solution");
		
		//test case 5
		SudokuSolver.clear();
        SudokuSolver.add(0,2,8);
        SudokuSolver.add(0,5,9);
        SudokuSolver.add(0,7,6);
        SudokuSolver.add(0,8,2);
        SudokuSolver.add(1,8,5);
        SudokuSolver.add(2,0,1);
        SudokuSolver.add(2,2,2);
        SudokuSolver.add(2,3,5);
        SudokuSolver.add(3,3,2);
        SudokuSolver.add(3,4,1);
        SudokuSolver.add(3,7,9);
        SudokuSolver.add(4,1,5);
        SudokuSolver.add(4,6,6);
        SudokuSolver.add(5,0,6);
        SudokuSolver.add(5,7,2);
        SudokuSolver.add(5,8,8);
        SudokuSolver.add(6,0,4);
        SudokuSolver.add(6,1,1);
        SudokuSolver.add(6,3,6);
        SudokuSolver.add(6,5,8);
        SudokuSolver.add(7,0,8);
        SudokuSolver.add(7,1,6);
        SudokuSolver.add(7,4,3);
        SudokuSolver.add(7,6,1);
        SudokuSolver.add(8,6,4);
        assertTrue(SudokuSolver.solve(), "no sulution for a board with a solution");
        assertTrue(SudokuSolver.get(0,0) == 5, "wrong solution");
        assertTrue(SudokuSolver.get(0,1) == 4, "wrong solution");
        assertTrue(SudokuSolver.get(8,0) == 9, "wrong solution");
        assertTrue(SudokuSolver.get(4,4) == 8, "wrong solution");
        
	}
	
	
	/**
	 * Test add throws illigalArgumentExeption when on wrong argument 
	 */
	@Test
	void testAdd() {
		assertThrows(IllegalArgumentException.class, () -> SudokuSolver.add(0,0,-1), "doesn´t throw exeption on wrong argument");
		assertThrows(IllegalArgumentException.class, () -> SudokuSolver.add(0,0,10), "doesn´t throw exeption on wrong argument");
		assertThrows(IllegalArgumentException.class, () -> SudokuSolver.add(-1,0,0), "doesn´t throw exeption on wrong argument");
		assertThrows(IllegalArgumentException.class, () -> SudokuSolver.add(9,0,0), "doesn´t throw exeption on wrong argument");
		assertThrows(IllegalArgumentException.class, () -> SudokuSolver.add(0,-1,0), "doesn´t throw exeption on wrong argument");
		assertThrows(IllegalArgumentException.class, () -> SudokuSolver.add(0,9,0), "doesn´t throw exeption on wrong argument");
	}
	
	/**
	 * Test remove
	 */
	@Test
	void testRemove() {
		SudokuSolver.add(0, 0, 7);
		SudokuSolver.remove(0,0);
		assertTrue(SudokuSolver.get(0, 0) == 0, "integer not removed");
		SudokuSolver.add(4, 5, 7);
		SudokuSolver.remove(4,5);
		assertTrue(SudokuSolver.get(4, 5) == 0, "integer not removed");
		SudokuSolver.add(2, 8, 7);
		SudokuSolver.remove(2,8);
		assertTrue(SudokuSolver.get(2, 8) == 0, "integer not removed");
	}
	
	/**
	 * Test get
	 */
	@Test
	void testGet() {
		SudokuSolver.add(0, 0, 7);
		assertTrue(SudokuSolver.get(0, 0) == 7, "not correct digit retreved");
		SudokuSolver.add(4, 5, 7);
		assertTrue(SudokuSolver.get(4, 5) == 7, "not correct digit retreved");
		SudokuSolver.add(2, 8, 7);
		assertTrue(SudokuSolver.get(2, 8) == 7, "not correct digit retreved");
	}
	
	/**
	 * Test isValid
	 */
	@Test
	void testIsValid() {
		// test case 1
		assertTrue(SudokuSolver.isValid(), "no solution for empty board");
		
		//test case 2
		SudokuSolver.clear();
		SudokuSolver.add(0, 0, 1);
		SudokuSolver.add(0, 1, 1);
		assertTrue(!SudokuSolver.isValid(), "valid when two of same digit in same row");
		SudokuSolver.clear();
		SudokuSolver.add(0, 0, 1);
		SudokuSolver.add(1, 0, 1);
		assertTrue(!SudokuSolver.isValid(), "valid when two of same digit in same col");
		SudokuSolver.clear();
		SudokuSolver.add(8, 8, 1);
		SudokuSolver.add(7, 7, 1);
		assertTrue(!SudokuSolver.isValid(), "valid when two of same digit in same square");

		//test case 3
		SudokuSolver.clear();
        SudokuSolver.add(0,2,8);
        SudokuSolver.add(0,5,9);
        SudokuSolver.add(0,7,6);
        SudokuSolver.add(0,8,2);
        SudokuSolver.add(1,8,5);
        SudokuSolver.add(2,0,1);
        SudokuSolver.add(2,2,2);
        SudokuSolver.add(2,3,5);
        SudokuSolver.add(3,3,2);
        SudokuSolver.add(3,4,1);
        SudokuSolver.add(3,7,9);
        SudokuSolver.add(4,1,5);
        SudokuSolver.add(4,6,6);
        SudokuSolver.add(5,0,6);
        SudokuSolver.add(5,7,2);
        SudokuSolver.add(5,8,8);
        SudokuSolver.add(6,0,4);
        SudokuSolver.add(6,1,1);
        SudokuSolver.add(6,3,6);
        SudokuSolver.add(6,5,8);
        SudokuSolver.add(7,0,8);
        SudokuSolver.add(7,1,6);
        SudokuSolver.add(7,4,3);
        SudokuSolver.add(7,6,1);
        SudokuSolver.add(8,6,4);
        assertTrue(SudokuSolver.isValid(), "not valid for a board with a solution");

	}
	
	/**
	 * Test clear
	 */
	@Test
	void testClear() {
		// test case 1
		SudokuSolver.clear();
		for (int[] i : SudokuSolver.getMatrix()) {
			for (int j : i) {
				assertTrue(j == 0, "didn´t clear all squares");
			}
		}
		//test case 2
		SudokuSolver.clear();
        SudokuSolver.add(0,2,8);
        SudokuSolver.add(0,5,9);
        SudokuSolver.add(0,7,6);
        SudokuSolver.add(0,8,2);
        SudokuSolver.add(1,8,5);
        SudokuSolver.add(2,0,1);
        SudokuSolver.add(2,2,2);
        SudokuSolver.add(2,3,5);
        SudokuSolver.add(3,3,2);
        SudokuSolver.add(3,4,1);
        SudokuSolver.add(3,7,9);
        SudokuSolver.add(4,1,5);
        SudokuSolver.add(4,6,6);
        SudokuSolver.add(5,0,6);
        SudokuSolver.add(5,7,2);
        SudokuSolver.add(5,8,8);
        SudokuSolver.add(6,0,4);
        SudokuSolver.add(6,1,1);
        SudokuSolver.add(6,3,6);
        SudokuSolver.add(6,5,8);
        SudokuSolver.add(7,0,8);
        SudokuSolver.add(7,1,6);
        SudokuSolver.add(7,4,3);
        SudokuSolver.add(7,6,1);
        SudokuSolver.add(8,6,4);
        SudokuSolver.clear();
		for (int[] i : SudokuSolver.getMatrix()) {
			for (int j : i) {
				assertTrue(j == 0, "didn´t clear all squares");
			}
		}

	}
	
	/**
	 * Test add throws illigalArgumentExeption when on wrong argument 
	 */
	@Test
	void testSetMatrix() {
		assertThrows(IllegalArgumentException.class, () -> SudokuSolver.setMatrix(new int[10][9]), "accepts wrong matrix size");
		assertThrows(IllegalArgumentException.class, () -> SudokuSolver.setMatrix(new int[9][10]), "accepts wrong matrix size");
		int[][] matrix1 = new int[9][9];
		matrix1[0][0] = -1;
		assertThrows(IllegalArgumentException.class, () -> SudokuSolver.setMatrix(matrix1), "accepts wrong digit in matrix");
		int[][] matrix2 = new int[9][9];
		matrix2[0][0] = 10;
		assertThrows(IllegalArgumentException.class, () -> SudokuSolver.setMatrix(matrix2), "accepts wrong digit in matrix");
	}
	
	@Test
	void testGetMatrix() {
		for (int[] i : SudokuSolver.getMatrix()) {
			for (int j : i) {
				assertEquals(j,0,"de två siffrorna är inte samma");
			}
		}
		int[][] matrix1 = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				matrix1[i][j] = 5;
			}
		}
		SudokuSolver.setMatrix(matrix1);
		for (int[] i : SudokuSolver.getMatrix()) {
			for (int j : i) {
				assertEquals(j,5,"de två siffrorna är inte samma");
			}
		}
		
		
//		int[][] j = new int[9][9];
//		assertEquals(SudokuSolver.getMatrix(),j , "returned diffrent matrix1");
//		int[][] matrix1 = new int[9][9];
//		matrix1[3][4] = 5;
//		SudokuSolver.setMatrix(matrix1);
//		assertTrue(SudokuSolver.getMatrix().equals(matrix1) , "returned diffrent matrix2");
//		int[][] matrix2 = new int[9][9];
//		matrix2[3][4] = 5;
//		matrix2[2][8] = 2;
//		matrix2[4][6] = 3;
//		matrix2[7][1] = 8;
//		SudokuSolver.setMatrix(matrix2);
//		assertTrue(SudokuSolver.getMatrix().equals(matrix2) , "returned diffrent matrix3");
	}

}
