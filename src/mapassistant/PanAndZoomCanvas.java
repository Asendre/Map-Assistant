package mapassistant;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

public class PanAndZoomCanvas extends JComponent {

    double translateX;
    double translateY;
    double scale;
    private Interface in;
    private AffineTransform saveTransform;
    private Graphics2D ourGraphics;

    private static final RenderingHints interpolation = new RenderingHints(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);

    private static final RenderingHints noInterpolation = new RenderingHints(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

    public PanAndZoomCanvas() {
        translateX = 0;
        translateY = 0;
        scale = 1;

    }

    public void setIn(Interface in) {
        this.in = in;
    }

    public Point2D inverseTransformPoint2D(Point2D p) {
        AffineTransform tempTransform = new AffineTransform(saveTransform);
        Point2D tempPoint = tempTransform.deltaTransform(p, null);
        return tempPoint;
    }

    public void applyAddedTransform() {
        saveTransform = ourGraphics.getTransform();

        ourGraphics.setColor(Color.DARK_GRAY);
        ourGraphics.fillRect(0, 0, getWidth(), getHeight());

        in.addedTransform = new AffineTransform(saveTransform);

        in.addedTransform.translate(getWidth() / 2, getHeight() / 2);
        in.addedTransform.scale(scale, scale);

        in.addedTransform.translate(-getWidth() / 2, -getHeight() / 2);

        in.addedTransform.translate(Math.round(translateX), Math.round(translateY));
    }

    public void paintComponent(Graphics g) {
        if (in != null) {
            ourGraphics = (Graphics2D) g;

            if (scale < 1) {

                ourGraphics.setRenderingHints(interpolation);
            } else {
                ourGraphics.setRenderingHints(noInterpolation);
            }

            applyAddedTransform();

            ourGraphics.setTransform(in.addedTransform);

            ourGraphics.setColor(Color.YELLOW);

            if (in.currentMapImage != null) {
                ourGraphics.drawImage(in.currentMapImage, null, null);
            }

            ourGraphics.setStroke(new BasicStroke(1));

            ourGraphics.setTransform(saveTransform);
        }
    }
}
