import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class AnimatedThing{
    private double posX;
    private double posY;
    private int attitude;
    private double durationBetweenFrames;
    private int[] animationMaxIndex;
    private int spriteWidth;
    private int spriteHeight;
    private int offsetBetweenFramesX;
    private int offsetBetweenFramesY;
    private ImageView sprite;

    protected static final int X = 0;
    protected static final int Y = 1;
    protected static final int VELOCX = 2;
    protected static final int VELOCY = 3;
    protected static final int ACCELX = 4;
    protected static final int ACCELY = 5;

    public AnimatedThing(String filePath, double durationBetweenFrames, int[] animationMaxIndex, int spriteHeight, int spriteWidth, int offsetBetweenFramesX, int offsetBetweenFramesY, double x, double y){
        sprite = new ImageView(filePath);
        this.posX = x;
        this.posY = y;
        this.durationBetweenFrames = durationBetweenFrames;
        this.animationMaxIndex = animationMaxIndex;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
        this.offsetBetweenFramesX = offsetBetweenFramesX;
        this.offsetBetweenFramesY = offsetBetweenFramesY;
        this.attitude = 0;
    }

    public void update(long time){
        sprite.setViewport(chooseSprite(time));
    }

    public void print(Camera camera){
        sprite.setX(posX-camera.getX());
        sprite.setY(posY-camera.getY());
    }

    private Rectangle2D chooseSprite(long time){
        int index = (int) (time*1e-9%(animationMaxIndex[attitude]*durationBetweenFrames)/durationBetweenFrames);
        int minX = (index % animationMaxIndex[attitude]) * (spriteWidth + offsetBetweenFramesX);
        int minY = attitude*(spriteHeight+offsetBetweenFramesY);
        return new Rectangle2D(minX, minY, spriteWidth, spriteHeight);
    }

    public Rectangle2D getHitBox(){
        return new Rectangle2D(posX+5, posY+5, spriteWidth-10, spriteHeight-10);
    }

    public double getPosX(){
        return this.posX;
    }

    public double getPosY() {
        return posY;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setPosX(double posX){
        this.posX = posX;
    }

    public void setPosY(double posY){
        this.posY = posY;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }
}
