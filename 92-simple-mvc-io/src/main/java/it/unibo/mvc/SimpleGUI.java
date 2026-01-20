package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("My first Java graphical interface");

    public SimpleGUI(final Controller controller) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw/2, sh/2); 
        frame.setLocationByPlatform(true);

        final JPanel panel = new JPanel(new BorderLayout());
        final JTextArea textArea = new JTextArea();
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
        
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);
        frame.setContentPane(panel);
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        final SimpleGUI gui = new SimpleGUI(new Controller());
        gui.display();
    }

}
