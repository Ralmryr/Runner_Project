import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.util.Random;

import java.util.ArrayList;

public class GameScene extends Scene {

    private Camera camera;
    private StaticThing bgLeft, bgRight;
    private Hero hero;
    private ArrayList<Foe> listOfFoes;
    private AnimationTimer timer;
    private long timeBetweenUpdatesNs = (long) 1e9/100;
    private long timeBetweenRenderNs = (long) 1e9/60;

    public GameScene(Parent root, double windowWidth, double windowHeight) {
        super(root, windowWidth, windowHeight);
        this.bgLeft = new StaticThing("desert.png", 0, 0);
        this.bgRight = new StaticThing("desert.png", 800, 0);
        this.hero = new Hero(300, 250);
        this.camera = new Camera(0, 100);

        this.listOfFoes = new ArrayList<>();
        Random rand = new Random();
        for(int i=2;i<500;i++) this.listOfFoes.add(new Foe(1500*i-rand.nextInt(500)-300, 300));

        root.setOnMouseClicked((mouseEvent -> hero.jump()));

        this.timer = new AnimationTimer()
        {
            private long prevTimeRender = 0;
            private long prevTimeUpdate = 0;

            public void handle(long time){
                if(time-prevTimeUpdate>timeBetweenUpdatesNs){
                    hero.update(time);
                    camera.update(hero);
                    for (Foe foe : listOfFoes) {
                        foe.update(time);
                        if(hero.isInvincible()) hero.subInvincibility(time-prevTimeUpdate);
                        else if (hero.getHitBox().intersects(foe.getHitBox())){
                            System.out.println("Collision !");
                            hero.setInvincibility(5E8);
                            break;
                        }
                    }
                    prevTimeUpdate = time;
                }
                if (time - prevTimeRender > timeBetweenRenderNs) {
                    printScene();
                    prevTimeRender = time;
                }
            }
        };
    }

    public void printScene(){
        bgLeft.print(camera);
        bgRight.print(camera);
        hero.print(camera);
        listOfFoes.forEach(foe -> foe.print(camera));
    }

    public void startTimer(){
        this.timer.start();
    }

    public void stopTimer(){
        this.timer.stop();
    }

    public ImageView getBgLeft() {
        return bgLeft.getImage();
    }

    public ImageView getBgRight() {
        return bgRight.getImage();
    }

    public ImageView getHero() {
        return hero.getSprite();
    }

    public ArrayList<Foe> getListOfFoes() {
        return listOfFoes;
    }
}
