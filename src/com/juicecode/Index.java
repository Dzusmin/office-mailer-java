package com.juicecode;

import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class Index {
    private JPanel jPanel;

    private JTextField filePathTextField;
    private JButton chooseFileButton;
    private JFileChooser fc;

    private JTextField fromTextField;
    private JPasswordField fromPasswordField;
    private JCheckBox checkPasswordCheckBox;

    private JRadioButton secondColumnRadioButton;
    private JRadioButton sameContentRadioButton;
    private JTextArea mailContentTextArea;

    private JButton sendButton;
    private JProgressBar sendProgressBar;
    private JTextField hostTextField;
    private JTextField portTextField;
    private JTextField subjectTextField;


    public Index() {
        fc = new JFileChooser();

        EditableToggler mailContentToggler = new EditableToggler(
                sameContentRadioButton,
                mailContentTextArea,
                subjectTextField
        );

        SendButtonActionListener sendButtonActionListener = new SendButtonActionListener(
                fromTextField,
                fromPasswordField,
                hostTextField,
                portTextField,
                filePathTextField,
                mailContentToggler
        );

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(jPanel);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    filePathTextField.setText(file.getAbsolutePath());
                }
            }
        });

        checkPasswordCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fromPasswordField.setEchoChar((char) 0);
                } else {
                    fromPasswordField.setEchoChar((char) '•');
                }
            }
        });

        sameContentRadioButton.addItemListener(mailContentToggler);
        secondColumnRadioButton.addItemListener(mailContentToggler);
        sendButton.addActionListener(sendButtonActionListener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Office Mailer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new Index().jPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
