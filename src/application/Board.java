package application;

import application.Cell;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class Board extends Parent {
	
	private Cell[][] cells;
	private Cell[][] cells2;
	private int height;
	private int length;
	private GridPane grid;

	public Board(int height, int length) {
		
		this.cells = new Cell[height][length];
		this.grid = new GridPane();
	}
	
	public void fillBoardWithCells() {
		for (int i = 0; i < cells[0].length; i++){
			for (int j = 0; j < cells.length; j++){
				cells[i][j] = new Cell(false);
				grid.add(cells[i][j], i, j);
			}
		}
		getChildren().add(grid);
	}
	
	public void updateBoard() {
		grid.getChildren().clear();
		for (int i = 0; i < cells[0].length; i++){
			for (int j = 0; j < cells.length; j++){
				grid.add(cells[i][j], i, j);
			}
		}
	}
	
	public void cleanBoard() {
		getChildren().remove(grid);
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
	
	public Cell[][] getCells2() {
		return cells2;
	}

	public void setCells2(Cell[][] cells2) {
		this.cells2 = cells2;
	}
	
	public Cell getCell(int y, int x){
		return cells[y][x];
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	

	
}

