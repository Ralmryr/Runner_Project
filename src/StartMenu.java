import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class StartMenu extends Menu {

    private final StackPane startButton;
    private final StackPane highScoreButton;
    private final StackPane quitButton;

    public StartMenu() {
        super(400, 300, Color.TRANSPARENT);
        startButton = new StackPane();
        highScoreButton = new StackPane();
        quitButton = new StackPane();
        Color bgColor = Color.LIGHTGREY;
        Color borderColor = Color.BLACK;
        configureTextBox(startButton, "Start", 120, bgColor, borderColor);
        configureTextBox(highScoreButton, "High scores", 180, bgColor, borderColor);
        configureTextBox(quitButton, "Quit", 240, bgColor, borderColor);
    }

    public StackPane getStartButton() {
        return startButton;
    }

    public StackPane getHighScoreButton() {
        return highScoreButton;
    }

    public StackPane getQuitButton() {
        return quitButton;
    }
}
