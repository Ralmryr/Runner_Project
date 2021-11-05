import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){
        primaryStage.setTitle("Runner");
        Group root = new Group();
        GameScene scene = new GameScene(root, 800, 300);

        root.getChildren().add(scene.getBgLeft());
        root.getChildren().add(scene.getBgRight());
        root.getChildren().add(scene.getHero());
        scene.getListOfFoes().forEach(foe -> root.getChildren().add(foe.getSprite()));

        scene.startTimer();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
        // write your code here
    }
}
