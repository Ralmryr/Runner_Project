// Clément Dury 2G3TD1TP1
// If the game is laggy, please add the command -Djavafx.animation.fullspeed=true to the VM options.

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){
        primaryStage.setTitle("Runner");
        Group rootGame = new Group();
        Group rootStartMenu = new Group();
        StartScene startScene = new StartScene(rootStartMenu, 800, 300);
        primaryStage.setResizable(false);

        GameScene gameScene = new GameScene(rootGame, 800, 300);

        primaryStage.setScene(startScene);
        startScene.getStartMenu().getStartButton().setOnMouseClicked(mouseEvent -> {
            gameScene.resetGame();
            gameScene.startTimer();
            primaryStage.setScene(gameScene);
        });
        startScene.getStartMenu().getQuitButton().setOnMouseClicked(mouseEvent -> primaryStage.close());
        gameScene.getPauseMenu().getMenuButton().setOnMouseClicked(mouseEvent -> {
            gameScene.toggleMenu(gameScene.getPauseMenu(), false);
            gameScene.resetGame();
            primaryStage.setScene(startScene);
        });
        gameScene.getEndMenu().getMenuButton().setOnMouseClicked(mouseEvent -> {
            gameScene.resetGame();
            primaryStage.setScene(startScene);
        });
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
        // write your code here
    }
}
