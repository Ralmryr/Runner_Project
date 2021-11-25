import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

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
        configureTextBox(restartButton, "Restart", 150, bg, border);
        configureTextBox(menuButton, "Menu", 230, bg, border);

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
