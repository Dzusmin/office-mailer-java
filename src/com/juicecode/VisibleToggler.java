package com.juicecode;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VisibleToggler implements ItemListener {
    private JPasswordField passwordField;

    public VisibleToggler(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            this.passwordField.setEchoChar((char) 0);
        } else {
            this.passwordField.setEchoChar((char) '•');
        }
    }
}
