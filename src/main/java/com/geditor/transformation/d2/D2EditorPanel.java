package com.geditor.transformation.d2;

import com.geditor.ui.editor.Editor;
import lombok.Getter;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class D2EditorPanel extends JPanel {
    private static final Logger logger = Logger.getLogger(Editor.class.getName());
    private Graphics2D currentGraphics;
    private Graphics2D imageGraphics;
    @Getter
    private BufferedImage image;

    public D2EditorPanel() {
        setDoubleBuffered(false);
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800,600));
        setMaximumSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentGraphics = (Graphics2D) g;

        if (image == null) {
            image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
            imageGraphics = (Graphics2D) image.getGraphics();
            imageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            imageGraphics.setBackground(Color.WHITE);
            clearDrawArea();
            drawCoordinateSystem();
        }
        g.drawImage(image, 0, 0, null);
    }

    private void drawCoordinateSystem() {
        imageGraphics.drawLine(0, 300, 800, 300); // x axis
        imageGraphics.drawLine(400, 0, 400, 600); // y axis
        imageGraphics.translate(400, 300);
        imageGraphics.drawString("0", 0, 0);
    }

    public void clearDrawArea() {
        imageGraphics.setColor(Color.white);
        imageGraphics.fillRect(0, 0, getSize().width, getSize().height);
        imageGraphics.setColor(Color.black);
    }
}
