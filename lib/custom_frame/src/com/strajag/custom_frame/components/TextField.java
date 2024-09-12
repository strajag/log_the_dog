package com.strajag.custom_frame.components;

import com.strajag.custom_frame.Settings;

import javax.swing.*;

public class TextField extends JTextField {

    public TextField() {
        setFont(Settings.Fonts.font);

        setHorizontalAlignment(CENTER);

        setBackground(Settings.Colors.backgroundColor);
        setForeground(Settings.Colors.fontColor);
        setCaretColor(Settings.Colors.fontColor);
    }

    public TextField(String text) {
        this();
        setText(text);
    }
}
