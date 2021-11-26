import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StartScene extends Scene {

    private final StartMenu startMenu;
    private final long timeBetweenRenderNs = (long) 1e9/60;
    private final StaticThing bgLeft, bgRight;
    private final Camera camera;
    private final ImageView mainTitle;
    private final ImageView heroUp;
    private final ImageView pauseButton;
    private final Text jumpText;
    private final Text pauseText;

    public StartScene(Group root, double width, double height) {
        super(root, width, height);
        bgLeft = new StaticThing("desert.png", 0, 0);
        bgRight = new StaticThing("desert.png", 800, 0);
        camera = new Camera(0, 50);
        startMenu = new StartMenu();

        mainTitle = new ImageView("mainTitle.png");
        mainTitle.setPreserveRatio(true);
        mainTitle.setFitWidth(400);
        mainTitle.setX((width - mainTitle.getFitWidth())/2);
        mainTitle.setY(10);

        heroUp = new ImageView("heros.png");
        heroUp.setViewport(new Rectangle2D(0, 160, 80, 100));
        heroUp.setX(50);
        heroUp.setY(50);
        jumpText = new Text("Press space bar in order to jump !");
        jumpText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        jumpText.setX(150);
        jumpText.setY(100);

        pauseButton = new ImageView("pauseButton.png");
        pauseButton.setX(700);
        pauseButton.setY(200);
        pauseText = new Text("To pause the game, click the pause button or hit ESC !");
        pauseText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        pauseText.setX(140);
        pauseText.setY(223);

        Text disclaimer = new Text("If the game is laggy, please add the command shown in first comment of the Main.java file to the VM options");
        disclaimer.setX(10);
        disclaimer.setY(13);

        root.getChildren().add(bgLeft.getSprite());
        root.getChildren().add(bgRight.getSprite());
        root.getChildren().add(heroUp);
        root.getChildren().add(jumpText);
        root.getChildren().add(pauseButton);
        root.getChildren().add(pauseText);
        root.getChildren().add(startMenu.getLayout());
        root.getChildren().add(mainTitle);
        root.getChildren().add(disclaimer);
        toggleHowToPlay(false);

        startMenu.getHowToPlayButton().setOnMouseClicked(mouseEvent -> toggleHowToPlay(true));
        bgLeft.getSprite().setOnMouseClicked(mouseEvent -> toggleHowToPlay(false));
        bgRight.getSprite().setOnMouseClicked(mouseEvent -> toggleHowToPlay(false));
        long prevTimeRenderNs = 0;
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long time) {
                if (time - prevTimeRenderNs > timeBetweenRenderNs) {
                    bgLeft.print(camera);
                    bgRight.print(camera);
                    camera.incrementX(0.15);
                }
            }
        };
        timer.start();
    }

    public void toggleHowToPlay(boolean state) {
        if(state) startMenu.hide();
        else startMenu.show();
        mainTitle.setVisible(!state);
        pauseButton.setVisible(state);
        pauseText.setVisible(state);
        heroUp.setVisible(state);
        jumpText.setVisible(state);
    }

    public StartMenu getStartMenu() {
        return startMenu;
    }
}
