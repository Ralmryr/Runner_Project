import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.Random;

import java.util.ArrayList;

public class GameScene extends Scene {

    private final Camera camera;
    private final StaticThing bgLeft, bgRight;
    private final Hero hero;
    private final ArrayList<Foe> listOfFoes;
    private final ArrayList<UI> listOfHearts;
    private final UI pauseButton;
    private final UI blackScreen;
    private final AnimationTimer timer;
    private final long timeBetweenUpdatesNs = (long) 1e9/100;
    private final long timeBetweenRenderNs = (long) 1e9/60;
    private final Group root;
    private final EndMenu endMenu;
    private final PauseMenu pauseMenu;
    private boolean isPaused = false;

    public GameScene(Group root, double windowWidth, double windowHeight) {
        super(root, windowWidth, windowHeight);

        this.root = root;
        this.bgLeft = new StaticThing("desert.png", 0, 0);
        this.bgRight = new StaticThing("desert.png", 800, 0);
        this.hero = new Hero(300, 250);
        this.camera = new Camera(50, 100);

        this.listOfFoes = new ArrayList<>();
        Random rand = new Random();
        for(int i=2;i<100;i++) this.listOfFoes.add(new Foe(1500*i-rand.nextInt(500)-300, 300));

        this.listOfHearts = new ArrayList<>();
        for(int i=0;i<hero.getNumberOfLives();i++){
            listOfHearts.add(new UI("heart.png", 800-40*(i+1), 5));
        }

        pauseButton = new UI("pauseButton.png", 10, 10);

        blackScreen = new UI("blackScreen.png", 0, 0);
        blackScreen.getSprite().setOpacity(0.6);
        blackScreen.hide();

        endMenu = new EndMenu();
        endMenu.getLayout().setMouseTransparent(true);

        pauseMenu = new PauseMenu();
        pauseMenu.getLayout().setMouseTransparent(true);

        addToRoot();
        setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                hero.jump();
            }
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                isPaused = true;
            }
        });
        pauseButton.getSprite().setOnMouseClicked(mouseEvent -> isPaused = true);
        pauseMenu.getResumeButton().setOnMouseClicked(mouseEvent -> {
            toggleMenu(pauseMenu, false);
            isPaused = false;
        });
        pauseMenu.getRestartButton().setOnMouseClicked(mouseEvent -> {
            resetGame();
            startTimer();
            toggleMenu(pauseMenu, false);
            isPaused = false;
        });
        endMenu.getRestartButton().setOnMouseClicked(mouseEvent -> {
            resetGame();
            startTimer();
        });

        this.timer = new AnimationTimer()
        {
            private long prevTimeRenderNs = 0;
            private long prevTimeUpdateNs = 0;

            public void handle(long time){
                if(time - prevTimeUpdateNs > timeBetweenUpdatesNs){
                    if(isPaused){
                        toggleMenu(pauseMenu, true);
                        camera.updateTime(time);
                        hero.updateTime(time);
                    }
                    else if(!hero.isDead()){
                        updateScene(time);
                        if(!hero.isInvincible()) checkCollisions();
                    }
                    else toggleMenu(endMenu, true);
                    prevTimeUpdateNs = time;
                }
                if (time - prevTimeRenderNs > timeBetweenRenderNs) {
                    printScene();
                    prevTimeRenderNs = time;
                }
            }
        };
    }

    public void addToRoot() {
        root.getChildren().add(bgLeft.getSprite());
        root.getChildren().add(bgRight.getSprite());
        root.getChildren().add(hero.getSprite());
        listOfFoes.forEach(foe -> root.getChildren().add(foe.getSprite()));
        listOfHearts.forEach(heart -> root.getChildren().add(heart.getSprite()));
        root.getChildren().add(pauseButton.getSprite());
        root.getChildren().add(blackScreen.getSprite());
        root.getChildren().add(endMenu.getLayout());
        root.getChildren().add(pauseMenu.getLayout());
    }

    private void checkCollisions(){
        for (Foe foe : listOfFoes) {
            if (hero.getHitBox().intersects(foe.getHitBox())){
                hero.takeDamage();
                listOfHearts.get(hero.getNumberOfLives()).hide();
                return;
            }
        }
    }

    public void printScene(){
        bgLeft.print(camera);
        bgRight.print(camera);
        hero.print(camera);
        listOfFoes.forEach(foe -> foe.print(camera));
        listOfHearts.forEach(UI::printSprite);
        pauseButton.printSprite();
    }

    public void updateScene(long time){
        hero.update(time);
        camera.update(hero, time);
        listOfFoes.forEach(foe -> foe.update(time));
    }

    public void toggleMenu(Menu menu, boolean state) {
        if(state) {
            blackScreen.show();
            menu.show();
            menu.getLayout().setMouseTransparent(false);
        }
        else{
            blackScreen.hide();
            menu.hide();
            menu.getLayout().setMouseTransparent(true);
        }
    }

    public void resetGame() {
        stopTimer();
        hero.reset();
        camera.reset();
        listOfHearts.forEach(UI::show);
        toggleMenu(endMenu, false);
        isPaused = false;
    }

    public void startTimer(){
        this.timer.start();
    }

    public void stopTimer(){
        this.timer.stop();
    }

    public EndMenu getEndMenu() {
        return endMenu;
    }

    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }
}
