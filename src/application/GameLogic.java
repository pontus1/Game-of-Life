package application;

import application.Board;
import application.Cell;

public class GameLogic {
	
	Cell[][] tempCells1; 
	Cell[][] tempCells2;
	int count;
	
	public GameLogic() {
		tempCells1 = createNewArrayWithDeadCells();
		tempCells2 = createNewArrayWithDeadCells();
		count = 0;
	}

	private Cell[][] createNewArrayWithDeadCells(){
		Cell[][] deadCells = new Cell[50][50];
		for (int i = 0; i < deadCells[0].length; i++){
			for (int j = 0; j < deadCells.length; j++) {
				deadCells[i][j] = new Cell(false);
			}
		}
		return deadCells;
	};
	
	private void killEmAll(Cell[][] cellsToKill){
		for (int i = 0; i < cellsToKill[0].length; i++){
			for (int j = 0; j < cellsToKill.length; j++) {
				cellsToKill[i][j].setAlive(false);
				cellsToKill[i][j].setNeighbours(0);
			}
		}
	}

	public void countNeighbours(Board board) {

		Cell[][] temp;
		count++;
		if (count % 2 == 0){
			temp = tempCells1;
		} else {
			temp = tempCells2;
		}
		
		killEmAll(temp);
		Cell[][] cells = board.getCells();
		
		
		//Cell[][] newCells = new Cell[cells.length][cells[0].length]; //******************************************************

		for (int y = 0; y < cells[0].length; y++) {
			for (int x = 0; x < cells.length; x++) {

				Cell currentCell = cells[y][x];

				countNeighbours(cells, y, x, currentCell);

				// Any live cell with fewer than two live neighbours dies, as if
				// caused by under-population.
				
				// Any live cell with two or three live neighbours lives on to
				// the next generation.
				
				// Any live cell with more than three live neighbours dies, as
				// if by over-population.
				
				// Any dead cell with exactly three live neighbours becomes a
				// live cell, as if by reproduction.

				if (currentCell.isAlive()) {
					if (currentCell.getNeighbours() < 2) {
						//newCells[y][x] = new Cell(false);  // **********************************************************
						temp[y][x].setAlive(false);
					} else if (currentCell.getNeighbours() < 4) {
						//newCells[y][x] = new Cell(true); // *************************************************************
						temp[y][x].setAlive(true);
					} else {
						//newCells[y][x] = new Cell(false);  // ************************************************************
						temp[y][x].setAlive(false);
					}
				} else {
					if (currentCell.getNeighbours() == 3) {
						//newCells[y][x] = new Cell(true);  // ******************************************************
						temp[y][x].setAlive(true);
					} else {
						//newCells[y][x] = new Cell(false);  // ****************************************************
						temp[y][x].setAlive(false);
					}
				}
			}
		}
		board.setCells(temp);
	}

	private void countNeighbours(Cell[][] cells, int y, int x, Cell cell) {

		boolean topLeft = (!isOutOfBounds(y - 1, x - 1, cells)) && cells[y - 1][x - 1].isAlive();
		boolean top = (!isOutOfBounds(y - 1, x, cells)) && cells[y - 1][x].isAlive();
		boolean topRight = (!isOutOfBounds(y - 1, x + 1, cells)) && cells[y - 1][x + 1].isAlive();
		boolean left = (!isOutOfBounds(y, x - 1, cells)) && cells[y][x - 1].isAlive();
		boolean right = (!isOutOfBounds(y, x + 1, cells)) && cells[y][x + 1].isAlive();
		boolean bottomLeft = (!isOutOfBounds(y + 1, x - 1, cells)) && cells[y + 1][x - 1].isAlive();
		boolean bottom = (!isOutOfBounds(y + 1, x, cells)) && cells[y + 1][x].isAlive();
		boolean bottomRight = (!isOutOfBounds(y + 1, x + 1, cells)) && cells[y + 1][x + 1].isAlive();

		if (topLeft) {
			cell.setNeighbours(cell.getNeighbours() + 1);
		}
		if (top) {
			cell.setNeighbours(cell.getNeighbours() + 1);
		}
		if (topRight) {
			cell.setNeighbours(cell.getNeighbours() + 1);
		}
		if (left) {
			cell.setNeighbours(cell.getNeighbours() + 1);
		}
		if (right) {
			cell.setNeighbours(cell.getNeighbours() + 1);
		}
		if (bottomLeft) {
			cell.setNeighbours(cell.getNeighbours() + 1);
		}
		if (bottom) {
			cell.setNeighbours(cell.getNeighbours() + 1);
		}
		if (bottomRight) {
			cell.setNeighbours(cell.getNeighbours() + 1);
		}
	}

	private boolean isOutOfBounds(int y, int x, Cell[][] c) {

		if ((x < 0 || y < 0) || (x > c[0].length - 1 || y > c.length - 1)) {
			return true;
		} else {
			return false;
		}

	}
}
