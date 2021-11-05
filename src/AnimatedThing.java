import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public abstract class AnimatedThing{
    private double posX;
    private double posY;
    private int attitude;
    private double durationBetweenFrames;
    private int animationMaxIndex;
    private int spriteWidth;
    private int spriteHeight;
    private int offsetBetweenFramesX;
    private int offsetBetweenFramesY;
    private ImageView sprite;

    public AnimatedThing(String filePath, double durationBetweenFrames, int animationMaxIndex, int spriteHeight, int spriteWidth, int offsetBetweenFramesX, int offsetBetweenFramesY, double x, double y){
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
        sprite.setX(getPosX()-camera.getX());
        sprite.setY(getPosY()-camera.getY());
    }

    private Rectangle2D chooseSprite(long time){
        int index = (int) (time*1e-9%(animationMaxIndex*durationBetweenFrames)/durationBetweenFrames);
        int minX = (index % animationMaxIndex) * (spriteWidth + offsetBetweenFramesX);
        int minY = attitude*(spriteHeight+offsetBetweenFramesY);
        return new Rectangle2D(minX, minY, spriteWidth, spriteHeight);
    }

    public Rectangle2D chooseSprite(int indexX, int indexY){
        long minX = (long) (indexX)*(spriteWidth + offsetBetweenFramesX);
        long minY = (long) (indexY)*(spriteHeight + offsetBetweenFramesY);
        return new Rectangle2D(minX, minY, spriteWidth, spriteHeight);
    }

    public Rectangle2D getHitBox(){
        return new Rectangle2D(posX+5, posY+5, spriteWidth-10, spriteHeight-10);
    }

    public double getDurationBetweenFrames() {
        return durationBetweenFrames;
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
}
