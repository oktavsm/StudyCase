package utils;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class ImageUtils {
    public static BufferedImage getCircleImage(BufferedImage source, int diameter) {
        BufferedImage mask = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = mask.createGraphics();
        applyQualityRenderingHints(g2);
        g2.fill(new Ellipse2D.Double(0, 0, diameter, diameter));
        g2.dispose();

        BufferedImage circleBuffer = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        g2 = circleBuffer.createGraphics();
        applyQualityRenderingHints(g2);
        g2.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.drawImage(source, 0, 0, diameter, diameter, null);
        g2.dispose();

        return circleBuffer;
    }

    public static void applyQualityRenderingHints(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }
}
