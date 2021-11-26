import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PauseMenu extends Menu{

    private final StackPane resumeButton;
    private final StackPane restartButton;
    private final StackPane menuButton;

    public PauseMenu() {
        super(300, 250, Color.DARKCYAN);
        restartButton = new StackPane();
        menuButton = new StackPane();
        resumeButton = new StackPane();
        Color border = Color.WHITE;
        Color bg = Color.LIGHTSTEELBLUE;
        configureTextBox(resumeButton, "Resume", 70, bg, border);
        configureTextBox(restartButton, "Restart", 130, bg, border);
        configureTextBox(menuButton, "Menu", 190, bg, border);

        Text text = new Text("Pause");
        text.setFont(new Font("Engravers MT", 35));
        text.setX(center(text.getLayoutBounds().getWidth(), layoutWidth));
        text.setY(50);
        layout.getChildren().add(text);

        layout.setVisible(false);
    }

    public StackPane getResumeButton() {
        return resumeButton;
    }

    public StackPane getRestartButton() {
        return restartButton;
    }

    public StackPane getMenuButton() {
        return menuButton;
    }
}
