package com.juicecode;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SendButtonActionListener implements ActionListener {

    private JTextComponent fromTextField;
    private JPasswordField fromPasswordField;
    private JTextComponent hostTextField;
    private JTextComponent portTextField;
    private JTextComponent filePathTextField;
    private EditableToggler mailingContent;
    private JTextComponent subject;
    private JTextComponent content;

    public SendButtonActionListener(
            JTextComponent fromTextField,
            JPasswordField fromPasswordField,
            JTextComponent hostTextField,
            JTextComponent portTextField,
            JTextComponent filePathTextField,
            EditableToggler mailingContent,
            JTextComponent subject,
            JTextComponent Content
    ) {
        this.fromTextField = fromTextField;
        this.fromPasswordField = fromPasswordField;
        this.hostTextField = hostTextField;
        this.portTextField = portTextField;
        this.filePathTextField = filePathTextField;
        this.mailingContent = mailingContent;
        this.subject = subject;
        this.content = Content;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MailingListReader mailingListReader = new MailingListReader(filePathTextField);

        try {
            SendMail sendMail = new SendMail(
                    fromTextField.getText(),
                    new String(fromPasswordField.getPassword()),
                    hostTextField.getText(),
                    portTextField.getText(),
                    mailingListReader.getMailingList()
            );

            sendMail.send(this.mailingContent.getToggled(), this.subject.getText(), this.content.getText());

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
