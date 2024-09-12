package com.straykovsky.custom_frame.components;

import com.straykovsky.custom_frame.Settings;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class TextPane extends JTextPane {

    public TextPane() {
        setFont(Settings.Fonts.font);

        setBackground(Settings.Colors.backgroundColor);
        setForeground(Settings.Colors.fontColor);

        StyledDocument doc = getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }
}
