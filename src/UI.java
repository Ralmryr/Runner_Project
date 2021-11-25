import javafx.scene.image.ImageView;

public class UI {

    private final double x;
    private final double y;
    private final ImageView sprite;

    public UI(String filePath,double x, double y){
        sprite = new ImageView(filePath);
        this.x = x;
        this.y = y;
    }

    public void printSprite(){
        sprite.setX(x);
        sprite.setY(y);
    }

    public void hide(){
        sprite.setVisible(false);
    }

    public void show(){
        sprite.setVisible(true);
    }

    public ImageView getSprite() {
        return sprite;
    }
}
