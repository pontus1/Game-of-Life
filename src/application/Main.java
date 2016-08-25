package application;

import java.util.concurrent.atomic.AtomicInteger;

import application.Board;
import application.GameLogic;
import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	Board board;

	private void initializeGame(Board board, BorderPane root) {

		Button btnStart = new Button("START");

		btnStart.getStyleClass().add("btn-start");
		root.setAlignment(btnStart, Pos.BOTTOM_CENTER);

		root.getChildren().add(board);
		root.setBottom(btnStart);

		btnStart.setOnAction(event -> {
			startGame(board, root);
			btnStart.setVisible(false);
		});

	}

	private void startGame(Board board, BorderPane root) {

		GameLogic gl = new GameLogic();

		Timer service = new Timer();
		AtomicInteger count = new AtomicInteger(0);
		service.setCount(count.get());
		service.setPeriod(Duration.millis(10));
		service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent t) {
				System.out.println("Called : " + t.getSource().getValue() + " time(s)");
				count.set((int) t.getSource().getValue());

				gl.countNeighbours(board);
				board.updateBoard();
			}
		});
		service.start();
		//service.restart();
	}

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		board = new Board(50, 50);

		board.fillBoardWithCells();

		root.setPrefSize(600, 500);
		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Game of Life");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		initializeGame(board, root);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
