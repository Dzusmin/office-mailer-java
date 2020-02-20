package com.juicecode;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EditableToggler implements ItemListener {

    private boolean toggled;
    private JRadioButton sameContentButton;
    private JTextComponent[] components;

    public EditableToggler(JRadioButton togglerButton, JTextComponent... components) {
        this.toggled = false;
        this.components = components;
        this.sameContentButton = togglerButton;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItem() == sameContentButton && e.getStateChange() == ItemEvent.SELECTED) {
            this.toggleMailContent(true);
            this.toggled = true;
        } else {
            this.toggleMailContent(false);
            this.toggled = false;
        }
    }

    public boolean getToggled() {
        return this.toggled;
    }

    private void toggleMailContent(boolean value) {
        for (JTextComponent component :
                this.components) {
            component.setEnabled(value);
            component.setEditable(value);
        }
    }
}
