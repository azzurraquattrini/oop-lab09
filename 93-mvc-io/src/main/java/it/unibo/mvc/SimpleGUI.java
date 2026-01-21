package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("My third Java graphical interface");

    public SimpleGUI(SimpleController controller) {

        // Center panel configuration
        final JPanel centerPanel = new JPanel(new BorderLayout());

        // Text area
        final JTextArea textArea = new JTextArea();
        centerPanel.add(textArea, BorderLayout.CENTER);

        // Text field
        final JTextField textField = new JTextField();
        centerPanel.add(textField, BorderLayout.NORTH);
        textField.setEditable(false);

        // South panel configuration
        final JPanel southPanel = new JPanel(new FlowLayout());
        centerPanel.add(southPanel, BorderLayout.SOUTH);
        
        // Print button configuration
        final JButton print = new JButton("Print");
        print.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    controller.setNextStringToPrint(textField.getText());
                    controller.printCurrentString();
                }
            }
        );
        southPanel.add(print);

        // Show history button configuration
        final JButton showHistory = new JButton("Show history");
        showHistory.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    final List<String> history = controller.getPrintedStringsHistory();
                    final StringBuilder builder = new StringBuilder();
                    for (String string : history) {
                        builder.append(string).append("\n");
                    }
                    textArea.setText(builder.toString());
                }
            }
        );
        southPanel.add(showHistory);

        // Frame configuration
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setContentPane(centerPanel);
    }

    public void display(){
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        final SimpleGUI gui = new SimpleGUI(new SimpleController());
        gui.display();
    }
}
