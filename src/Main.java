import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){
        primaryStage.setTitle("Runner");
        Group rootGame = new Group();
        Group rootStartMenu = new Group();
        StartScene startScene = new StartScene(rootStartMenu, 800, 300);

        GameScene gameScene = new GameScene(rootGame, 800, 300);

        primaryStage.setScene(startScene);
        startScene.getStartMenu().getStartButton().setOnMouseClicked(mouseEvent -> {
            gameScene.startTimer();
            primaryStage.setScene(gameScene);
        });
        startScene.getStartMenu().getQuitButton().setOnMouseClicked(mouseEvent -> primaryStage.close());
        gameScene.getEndMenu().getRestartButton().setOnMouseClicked(mouseEvent -> {
            gameScene.resetGame();
            gameScene.startTimer();
        });
        gameScene.getEndMenu().getMenuButton().setOnMouseClicked(mouseEvent -> {
            gameScene.resetGame();
            primaryStage.setScene(startScene);
        });
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);

        ImageView image = new ImageView("desert.png");
        // write your code here
    }
}
