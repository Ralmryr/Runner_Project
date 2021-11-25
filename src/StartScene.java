import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;

public class StartScene extends Scene {

    private final StartMenu startMenu;
    private final long timeBetweenRenderNs = (long) 1e9/60;
    private final StaticThing bgLeft, bgRight;
    private final Camera camera;
    private final AnimationTimer timer;

    public StartScene(Group root, double width, double height) {
        super(root, width, height);
        bgLeft = new StaticThing("desert.png", 0, 0);
        bgRight = new StaticThing("desert.png", 800, 0);
        camera = new Camera(0, 50);
        startMenu = new StartMenu();

        root.getChildren().add(bgLeft.getSprite());
        root.getChildren().add(bgRight.getSprite());
        root.getChildren().add(startMenu.getLayout());

        long prevTimeRenderNs = 0;
        timer = new AnimationTimer(){
            public void handle(long time){
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

    public void stopTimer() {
        timer.stop();
    }

    public void startTimer() {
        timer.start();
    }
}
