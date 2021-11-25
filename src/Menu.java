import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public abstract class Menu{

    protected final Group layout;
    private final double layoutWidth;
    private final double layoutHeight;

    public Menu(double width, double height, Color bgColor) {
        layout = new Group();
        layoutWidth = width;
        layoutHeight = height;
        Rectangle background = genBgRect(layoutWidth, layoutHeight, bgColor, 25);
        layout.setLayoutX(center(layoutWidth, 800));
        layout.setLayoutY(center(layoutHeight, 300));
        layout.getChildren().add(background);
    }

    protected void configureTextBox(StackPane stack, String text, double y, Color bgColor, Color borderColor) {
        int margin = 15;
        Text textObject = new Text(text);
        textObject.setFont(new Font("Georgia", 15));
        Rectangle buttonBgBorder = genBgRect(textObject.getBoundsInParent().getWidth()+margin*2, textObject.getBoundsInParent().getHeight()+margin*2, borderColor, 25);
        stack.getChildren().add(buttonBgBorder);
        Rectangle buttonBg = genBgRect(textObject.getBoundsInParent().getWidth()+margin*1.5, textObject.getBoundsInParent().getHeight()+margin*1.5, bgColor, 15);
        stack.getChildren().add(buttonBg);
        stack.getChildren().add(textObject);
        StackPane.setMargin(textObject, new Insets(10, 10, 10, 10));
        stack.setLayoutX(center(stack.getBoundsInParent().getWidth(), layoutWidth));
        stack.setLayoutY(y);
        layout.getChildren().add(stack);
    }

    protected Rectangle genBgRect(double width, double height, Color color, double arc) {
        Rectangle rectangle = new Rectangle(0, 0, width, height);
        rectangle.setFill(color);
        rectangle.setArcHeight(arc);
        rectangle.setArcWidth(arc);
        return rectangle;
    }

    protected double center(double num1, double num2) {
        return Math.abs(num1 - num2) / 2;
    }

    public void show() {
        layout.setVisible(true);
    }

    public void hide(){
        layout.setVisible(false);
    }

    public Group getLayout() {
        return layout;
    }
}
