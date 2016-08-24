package application;

import application.Board;
import application.Cell;

public class GameLogic {

	public void checkNeighbours(Board board) {

		Cell[][] cells = board.getCells();

		Cell[][] newCells = new Cell[cells.length][cells[0].length];

		for (int y = 0; y < cells[0].length; y++) {
			for (int x = 0; x < cells.length; x++) {

				Cell currentCell = cells[y][x];

				checkNeighbours(cells, y, x, currentCell);

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
						newCells[y][x] = new Cell(false);
					} else if (currentCell.getNeighbours() < 4) {
						newCells[y][x] = new Cell(true);
					} else {
						newCells[y][x] = new Cell(false);
					}
				} else {
					if (currentCell.getNeighbours() == 3) {
						newCells[y][x] = new Cell(true);
					} else {
						newCells[y][x] = new Cell(false);
					}
				}
			}
		}
		board.setCells(newCells);
	}

	private void checkNeighbours(Cell[][] cells, int y, int x, Cell cell) {

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
