import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class StaticThing {

    final double x;
    final double y;
    final ImageView image;

    public StaticThing(String filePath, double x, double y) {
        image = new ImageView(filePath);
        this.x = x;
        this.y = y;
    }

    public void print(Camera camera) {
        double imageHeight = image.getImage().getHeight();
        double imageWidth = image.getImage().getWidth();
        double camX = camera.getX();
        double camY = camera.getY();

        image.setViewport(new Rectangle2D(
                Math.max(0, camX%800 - x),
                camY,
                imageWidth + x - camX%imageWidth,
                imageHeight-camY));

        image.setX(Math.max(0, x - camX % imageWidth));
    }

    public ImageView getSprite() {
        return image;
    }
}
