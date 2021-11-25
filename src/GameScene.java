import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Random;

import java.util.ArrayList;

public class GameScene extends Scene {

    private Camera camera;
    private StaticThing bgLeft, bgRight;
    private Hero hero;
    private ArrayList<Foe> listOfFoes;
    private ArrayList<UI> listOfHearts;
    private UI blackScreen;
    private AnimationTimer timer;
    private long timeBetweenUpdatesNs = (long) 1e9/100;
    private long timeBetweenRenderNs = (long) 1e9/60;
    private Group root;

    private UI rectDeadMenu;
    private UI restartButton;
    private UI menuButton;

    private Menu menu;

    public GameScene(Group root, double windowWidth, double windowHeight) {
        super(root, windowWidth, windowHeight);

        this.root = root;
        this.bgLeft = new StaticThing("desert.png", 0, 0);
        this.bgRight = new StaticThing("desert.png", 800, 0);
        this.hero = new Hero(300, 250);
        this.camera = new Camera(0, 100);

        this.listOfFoes = new ArrayList<>();
        Random rand = new Random();
        for(int i=2;i<500;i++) this.listOfFoes.add(new Foe(1500*i-rand.nextInt(500)-300, 300));

        this.listOfHearts = new ArrayList<>();
        for(int i=0;i<hero.getNumberOfLives();i++){
            listOfHearts.add(new UI("heart.png", 800-40*(i+1), 5));
        }

        blackScreen = new UI("blackScreen.png", 0, 0);
        blackScreen.getSprite().setOpacity(0.5);
        blackScreen.hide();

        rectDeadMenu = new UI("", 500, 200, 100, 50);
        restartButton = new UI("Restart", 200, 50, 200, 150);
        menuButton = new UI("Menu", 200, 50, 200, 250);
        rectDeadMenu.hideRect();
        restartButton.hideRect();
        menuButton.hideRect();

        menu = new Menu();
        menu.addTextBox("Hello !");
        menu.addTextBox("Hello again !");

        addToRoot();
        root.getChildren().add(menu.getLayout());
        root.setOnMouseClicked(mouseEvent -> hero.jump());

        this.timer = new AnimationTimer()
        {
            private long prevTimeRenderNs = 0;
            private long prevTimeUpdateNs = 0;

            public void handle(long time){
                if(time - prevTimeUpdateNs > timeBetweenUpdatesNs){
                    if(!hero.isDead()){
                        updateScene(time);
                        if(!hero.isInvincible()) checkCollisions();
                    }
                    else{
                        printMenu();
                    }
                    prevTimeUpdateNs = time;
                }

                if (time - prevTimeRenderNs > timeBetweenRenderNs) {
                    printScene();
                    prevTimeRenderNs = time;
                    System.out.println("Hello");
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
        root.getChildren().add(blackScreen.getSprite());
        root.getChildren().add(rectDeadMenu.getRect());
        root.getChildren().add(rectDeadMenu.getText());
        root.getChildren().add(menuButton.getRect());
        root.getChildren().add(menuButton.getText());
        root.getChildren().add(restartButton.getRect());
        root.getChildren().add(restartButton.getText());
    }

    private void checkCollisions(){
        for (Foe foe : listOfFoes) {
            if (hero.getHitBox().intersects(foe.getHitBox())){
                hero.takeDamage();
                root.getChildren().remove(listOfHearts.get(hero.getNumberOfLives()).getSprite());
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
        blackScreen.printSprite();
    }

    public void updateScene(long time){
        hero.update(time);
        camera.update(hero, time);
        listOfFoes.forEach(foe -> foe.update(time));
    }

    public void printMenu() {
        blackScreen.show();
        rectDeadMenu.showRect();
        menuButton.showRect();
        restartButton.showRect();
        rectDeadMenu.printRect();
        menuButton.printRect();
        restartButton.printRect();
    }

    public void startTimer(){
        this.timer.start();
    }

    public void stopTimer(){
        this.timer.stop();
    }
}
