import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class StartScene extends Scene {

    private final StartMenu startMenu;
    private final long timeBetweenRenderNs = (long) 1e9/60;
    private final StaticThing bgLeft, bgRight;
    private final Camera camera;

    public StartScene(Group root, double width, double height) {
        super(root, width, height);
        bgLeft = new StaticThing("desert.png", 0, 0);
        bgRight = new StaticThing("desert.png", 800, 0);
        camera = new Camera(0, 50);
        startMenu = new StartMenu();

        ImageView mainTitle= new ImageView("mainTitle.png");
        mainTitle.setPreserveRatio(true);
        mainTitle.setFitWidth(400);
        mainTitle.setX((width - mainTitle.getFitWidth())/2);
        mainTitle.setY(10);

        root.getChildren().add(bgLeft.getSprite());
        root.getChildren().add(bgRight.getSprite());
        root.getChildren().add(startMenu.getLayout());
        root.getChildren().add(mainTitle);

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

    public StartMenu getStartMenu() {
        return startMenu;
    }
}
