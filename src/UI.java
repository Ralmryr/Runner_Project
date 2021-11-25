import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class UI {

    private double x;
    private double y;
    private double width;
    private double height;
    private Text text;
    private ImageView sprite;
    private Rectangle rect;
    private StackPane pane;

    public UI(String filePath,double x, double y){
        sprite = new ImageView(filePath);
        this.x = x;
        this.y = y;
    }

    public UI(String text, double width, double height, double x, double y) {
        this.x = x;
        this.y = y;
        this.rect = new Rectangle(width, height, Color.AQUA);
        this.text = new Text(text);

    }

    public void printSprite(){
        sprite.setX(x);
        sprite.setY(y);
    }

    public void printRect() {
        this.text.setX(x+(width-this.text.getLayoutX())/2);
        this.text.setY(y+(height-this.text.getLayoutY())/2);
        this.rect.setX(x);
        this.rect.setY(y);
    }

    public void hide(){
        sprite.setVisible(false);
    }

    public void show(){
        sprite.setVisible(true);
    }

    public void hideRect(){
        rect.setVisible(false);
        text.setVisible(false);
    }

    public void showRect() {
        rect.setVisible(true);
        text.setVisible(true);
    }

    public ImageView getSprite() {
        return sprite;
    }

    public Rectangle getRect() {
        return this.rect;
    }

    public Text getText() {
        return this.text;
    }
}
