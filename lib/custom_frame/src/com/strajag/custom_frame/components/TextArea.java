package com.strajag.custom_frame.components;

import com.strajag.custom_frame.Settings;

import javax.swing.*;

public class TextArea extends JTextArea {

    public TextArea() {
        set();
    }

    private void set() {
        setFont(Settings.Fonts.font);

        setBackground(Settings.Colors.backgroundColor);
        setForeground(Settings.Colors.fontColor);
        setCaretColor(Settings.Colors.fontColor);

        setLineWrap(true);
    }
}
