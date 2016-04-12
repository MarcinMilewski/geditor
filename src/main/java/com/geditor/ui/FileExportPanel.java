package com.geditor.ui;

import com.geditor.io.exporter.FileExporter;
import com.geditor.io.exporter.exception.FileExportException;
import com.geditor.io.exporter.factory.FileExporterFactory;
import com.geditor.io.importer.exception.InvalidExtensionException;
import com.geditor.io.util.FileExtension;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by marcin on 19.03.16.
 */
public class FileExportPanel extends JPanel
        implements ActionListener {
    static private final String newline = "\n";
    JButton openButton;
    JTextArea actionHistory;
    JFileChooser fileChooser;
    private Editor editor;

    public FileExportPanel(Editor editor) {
        super(new BorderLayout());

        this.editor = editor;
        actionHistory = new JTextArea(5, 20);
        actionHistory.setMargin(new Insets(5, 5, 5, 5));
        actionHistory.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(actionHistory);

        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("ppmp3", "ppmp3"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("ppmp6", "ppmp6"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));

        openButton = new JButton("Save a File...");
        openButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);

        //Add the buttons and the actionHistory to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            int returnVal = fileChooser.showSaveDialog(FileExportPanel.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                FileExtension fileExtension = FileExtension.valueOfIgnoreCase(fileChooser.getFileFilter());
                actionHistory.append("Opening: " + file.getName() + "." + newline);
                try {
                    FileExporter fileExporter = FileExporterFactory.getFileExporter(fileExtension);
                    fileExporter.export(editor.getImage(), file);
                } catch (InvalidExtensionException e1) {
                    actionHistory.append("File extension incorrect");
                    e1.printStackTrace();
                }  catch (FileExportException e1) {
                    actionHistory.append("Export error.");
                    e1.printStackTrace();
                }
            } else {
                actionHistory.append("Export command cancelled by user." + newline);
            }
            actionHistory.setCaretPosition(actionHistory.getDocument().getLength());
        }
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileExportPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}