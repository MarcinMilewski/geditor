package com.geditor.transformation.filtration.morphologic.binary;

import com.geditor.ui.controller.EditorController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MorphologicBinaryFilterFrame  extends JFrame{
    private final EditorController editorController = EditorController.getInstance();
    private final JButton dilatation3x3CrossButton = new JButton("Dilatation (3x3 cross mask)");
    private final JButton dilatation5x5CircuralButton = new JButton("Dilatation (5x5 circural mask)");

    public MorphologicBinaryFilterFrame() throws HeadlessException {
        super("Morphologic filters");
        Container container = getContentPane();
        container.setLayout(new MigLayout());
        container.add(dilatation3x3CrossButton);
        container.add(dilatation5x5CircuralButton);
        addListeners();
        setSize(new Dimension(800, 600));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addListeners() {
        dilatation3x3CrossButton.addActionListener(e -> editorController.dilatationFilter(MorphologicBinaryMask.cross3x3));
        dilatation5x5CircuralButton.addActionListener(e -> editorController.dilatationFilter(MorphologicBinaryMask.circural5x5));
    }
}
