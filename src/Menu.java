import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;

public class Menu{

    private StackPane layout;

    public Menu() {
        layout = new StackPane();
        layout.setStyle("-fx-background-color:blue");
        layout.setPrefSize(500, 100);
        layout.setLayoutX(200);
    }

    public void addTextBox(String text) {
        Label label = new Label(text);
        //label.setStyle("-fx-background-color:olive");
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setLayoutX(300);
        layout.getChildren().add(label);
    }

    public StackPane getLayout() {
        return layout;
    }
}
