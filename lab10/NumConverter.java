/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10;

/**
 *
 * @author vanting
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import javax.swing.*;

public class NumConverter extends JFrame {

    static final int DEFAULT_FRAME_WIDTH = 500;
    static final int DEFAULT_FRAME_HEIGHT = 250;
    static final int DISPLAY_SIZE = 20;
    static final int NUMBER_OF_CTL_BUTTONS = 2;
    static final String[] KEY_LABELS = {"0", "1", "2", "3", "4",
        "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "CLR", "DEL"};
    static final int[] RADIX_VALUES = {2, 8, 10, 16};
    static final String[] RADIX_LABELS = {"Bin", "Oct", "Dec", "Hex"};

    private final JButton[] buttons;
    private final JTextField display;
    private final JRadioButton[] radioButtons;
    private int radix;

    public static void main(String[] args) {
        NumConverter myFrame = new NumConverter();
        myFrame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        myFrame.setTitle("Number Format Conversion");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }

    public NumConverter() {

        JPanel numberPanel = new JPanel(new GridLayout(4, 4));
        buttons = new JButton[KEY_LABELS.length];
        for (int i = 0; i < KEY_LABELS.length; i++) {
            buttons[i] = new JButton(KEY_LABELS[i]);
            if (i < KEY_LABELS.length - NUMBER_OF_CTL_BUTTONS) {
                numberPanel.add(buttons[i]);
            }
        }

        display = new JTextField(DISPLAY_SIZE);
        display.setHorizontalAlignment(JTextField.RIGHT);  //text is right-justified
        display.setFont(new Font("Dialog", Font.PLAIN, 14)); //select font and size
        display.setEditable(false); //user is not allowed to edit the text display
        display.setText("0");

        JPanel textPanel = new JPanel();
        textPanel.add(buttons[16]);
        textPanel.add(display);
        textPanel.add(buttons[17]);

        JPanel controlPanel = new JPanel(new GridLayout(4, 1));
        ButtonGroup group = new ButtonGroup();
        radioButtons = new JRadioButton[4];
        for (int i = 0; i < RADIX_VALUES.length; i++) {
            radioButtons[i] = new JRadioButton(RADIX_LABELS[i]);
            radioButtons[i].setActionCommand(String.valueOf(RADIX_VALUES[i]));
            group.add(radioButtons[i]);
            controlPanel.add(radioButtons[i]);
        }
        radioButtons[2].setSelected(true);  // default radix = decimal
        radix = RADIX_VALUES[2];

        add(textPanel, BorderLayout.NORTH);
        add(numberPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.WEST);

        // Add your codes here to complete the design of the constructor
        ActionListener btnListener = new ButtonListener();
        for (JButton b : buttons) {
            b.addActionListener(btnListener);
        }

        ActionListener radioListener = new RadioButtonListener();
        for (JRadioButton b : radioButtons) {
            b.addActionListener(radioListener);
        }
    }

    private class RadioButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            String number = display.getText();
            int newRadix = Integer.parseInt(event.getActionCommand());

            if (newRadix != radix) {
                BigInteger value = new BigInteger(number, radix);
                number = value.toString(newRadix);
                radix = newRadix;
                if (radix == RADIX_VALUES[3]) {
                    number = number.toUpperCase();
                }
            }

            display.setText(number);
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
        
            Object source = event.getSource();
            String number = display.getText();

            int key = 0;
            for (int i = 0; i < KEY_LABELS.length; i++) {
                if (source == buttons[i]) {
                    key = i;
                    break;
                }
            }

            if (key < 16) {
                if (key >= radix) {
                    return;     // invalid key
                }

                if (number.equals("0")) {
                    number = KEY_LABELS[key];
                } else {
                    number = number + KEY_LABELS[key];
                }
            } else if (key == 16) {
                number = "0";   // CLR     
            } else {
                // DEL
                if (number.length() == 1) {
                    number = "0";
                } else {
                    number = number.substring(0, number.length() - 1);
                }
            }

            display.setText(number);
        }
    }
}
