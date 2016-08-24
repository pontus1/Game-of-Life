package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class Timer extends ScheduledService<Integer> {

	private IntegerProperty count = new SimpleIntegerProperty();

	public final void setCount(Integer value) {
		count.set(value);
	}

	public final Integer getCount() {
		return count.get();
	}

	public final IntegerProperty countProperty() {
		return count;
	}

	protected Task<Integer> createTask() {
		return new Task<Integer>() {
			protected Integer call() {
				// Adds 1 to the count
				count.set(getCount() + 1);
				return getCount();
			}
		};
	}

}
