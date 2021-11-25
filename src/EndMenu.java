import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndMenu extends Menu{

    private final StackPane restartButton;
    private final StackPane menuButton;
    private final StackPane scoreButton;

    public EndMenu(){
        super(300, 250, Color.web("#BE7C4D"));
        restartButton = new StackPane();
        menuButton = new StackPane();
        scoreButton = new StackPane();
        Color border = Color.web("#353238");
        Color bg = Color.web("#BE5A38");
        configureTextBox(restartButton, "Restart", 70, bg, border);
        configureTextBox(menuButton, "Main menu", 130, bg, border);
        configureTextBox(scoreButton, "High scores", 190, bg, border);

        Text text = new Text("You died !");
        text.setFont(new Font("Engravers MT", 35));
        text.setFill(Color.web("#353238"));
        text.setX(center(text.getLayoutBounds().getWidth(), 300));
        text.setY(50);
        layout.getChildren().add(text);
        layout.setVisible(false);
    }

    public StackPane getRestartButton() {
        return restartButton;
    }

    public StackPane getMenuButton() {
        return menuButton;
    }

    public StackPane getScoreButton() {
        return scoreButton;
    }
}
