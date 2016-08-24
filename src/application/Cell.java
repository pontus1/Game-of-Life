package application;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableObjectValue;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell<square> extends Parent {

	private boolean isAlive;
	private Rectangle square;
	private int neighbours;

	public Cell(boolean isAlive) {
		super();
		this.isAlive = isAlive;
		square = new Rectangle(50, 50);
		square.setStroke(Color.BLACK);
		square.setFill(isAlive ? Color.GREEN : Color.BLACK);
		getChildren().add(square);
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Rectangle getSquare() {
		return square;
	}

	public void setSquare(Rectangle square) {
		this.square = square;
	}

	public int getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(int neighbours) {
		this.neighbours = neighbours;
	}

	protected void changeState() {
		if (isAlive) {
			isAlive = false;
			square.setFill(Color.BLACK);
		} else {
			isAlive = true;
			square.setFill(Color.GREEN);
		}
	}


}
