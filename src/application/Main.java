package application;

import java.util.concurrent.atomic.AtomicInteger;

import application.Board;
import application.GameLogic;
import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	Board board;

	public void startGame(Board board, BorderPane root) {

		GameLogic gl = new GameLogic();
		root.getChildren().add(board);
		
		
		 Timer service = new Timer();
	        AtomicInteger count = new AtomicInteger(0);
	        service.setCount(count.get());
	        service.setPeriod(Duration.millis(500));
	        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

	            @Override
	            public void handle(WorkerStateEvent t) {
	                System.out.println("Called : " + t.getSource().getValue()
	                        + " time(s)");
	                count.set((int) t.getSource().getValue());
	                
	                gl.checkNeighbours(board);
	    			board.updateBoard();
	            }
	        });
	        service.start();
		
		
		
		

		for (int i = 0; i < 20; i++) {
			service.restart();
		}
	}

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		board = new Board(50, 50);

		board.fillBoardWithCells();

		// change a few
		board.getCell(3, 4).changeState();
		board.getCell(3, 5).changeState();
		board.getCell(4, 3).changeState();
		board.getCell(4, 4).changeState();
		board.getCell(5, 4).changeState();

		root.setPrefSize(500, 500);
		Scene scene = new Scene(root);

		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Game of Life");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		startGame(board, root);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
