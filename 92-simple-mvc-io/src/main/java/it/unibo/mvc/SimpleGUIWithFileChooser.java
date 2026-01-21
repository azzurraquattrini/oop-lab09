package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("My second Java graphical interface");

    public SimpleGUIWithFileChooser(final Controller controller) {
        
        // Center panel configuration
        final JPanel centerPanel = new JPanel(new BorderLayout());

        // Text area
        final JTextArea textArea = new JTextArea();
        centerPanel.add(textArea, BorderLayout.CENTER);

        // Save button
        final JButton save = new JButton("Save");
        save.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    try {
                        controller.save(textArea.getText());
                    } catch (final IOException e) {
                         JOptionPane.showMessageDialog(null, e.getMessage(), 
                         "An error occurred", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
            }
        );
        centerPanel.add(save, BorderLayout.SOUTH);

        // North panel configuration
        final JPanel northPanel = new JPanel(new BorderLayout());
        centerPanel.add(northPanel, BorderLayout.NORTH);

        // Text field
        final JTextField filepath = new JTextField(controller.getCurrentFile().toString());
        filepath.setEditable(false);
        northPanel.add(filepath, BorderLayout.CENTER);

        // Browse button
        final JButton browse = new JButton("Browse...");
        browse.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    final JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Choose where to save");
                    fileChooser.setSelectedFile(controller.getCurrentFile());
                    final int result = fileChooser.showSaveDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        final File newDest = fileChooser.getSelectedFile();
                        controller.setCurrentFile(newDest);
                        filepath.setText(newDest.getPath());
                    }
                    else if (result == JFileChooser.CANCEL_OPTION) {
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, result, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        );
        northPanel.add(browse, BorderLayout.EAST);

        // Frame configuration
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw/2, sh/2);
        frame.setLocationByPlatform(true);
        frame.setContentPane(centerPanel);

    }


    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }

}
