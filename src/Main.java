import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{

    private int gameState = 0;

    public void start(Stage primaryStage){
        primaryStage.setTitle("Runner");
        Group rootGame = new Group();
        Group rootStartMenu = new Group();
        Scene scene2 = new Scene(rootStartMenu, 800, 300);

        GameScene scene = new GameScene(rootGame, 800, 300);

        scene.startTimer();

        Circle circle = new Circle();
        circle.setCenterX(100);
        circle.setCenterY(200);
        circle.setRadius(40);
        circle.setFill(Color.BLUE);

        rootStartMenu.getChildren().add(circle);
        primaryStage.setScene(scene);
        rootStartMenu.setOnMouseClicked(mouseEvent -> {
            scene.startTimer();
            primaryStage.setScene(scene);
        });
//        rootGame.setOnMouseClicked(mouseEvent -> {
//            scene.stopTimer();
//            primaryStage.setScene(scene2);
//        });
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
        // write your code here
    }
}
