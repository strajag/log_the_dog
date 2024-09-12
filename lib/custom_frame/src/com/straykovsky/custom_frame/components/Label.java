package com.straykovsky.custom_frame.components;

import com.straykovsky.custom_frame.Settings;

import javax.swing.*;

public class Label extends JLabel {

    public Label() {
        setForeground(Settings.Colors.fontColor);
        setHorizontalAlignment(JLabel.CENTER);
    }

    public Label(String text) {
        this();
        setText(text);
    }
}
