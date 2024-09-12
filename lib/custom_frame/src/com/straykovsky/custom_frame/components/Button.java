package com.straykovsky.custom_frame.components;

import com.straykovsky.custom_frame.Settings;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {

    public static Border nullBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.Colors.backgroundColor, Settings.Colors.backgroundColor, Settings.Colors.backgroundColor, Settings.Colors.backgroundColor);
    public static Border nullBorderPressed = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

    public Border border;

    public Button() {
        setBackground(Settings.Colors.backgroundColor);
        setForeground(Settings.Colors.fontColor);
        setFont(Settings.Fonts.buttonFont);

        setFocusable(false);
        setBorder(null);
        setContentAreaFilled(false);
        addMouseListener(this);
    }

    public Button(String text) {
        this();
        setText(text);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (isEnabled() == enabled) { return; }
        super.setEnabled(enabled);
        if (enabled) { addMouseListener(this); } else { removeMouseListener(this); }
    }
    @Override
    public void setBorder(Border border) { if (border == null) { this.border = BorderFactory.createEmptyBorder(); } else { this.border = border; } super.setBorder(BorderFactory.createCompoundBorder(nullBorder, border)); }
    @Override
    public Dimension getMinimumSize() { return getPreferredSize(); }
    @Override
    public Dimension getMaximumSize() { return getPreferredSize(); }
    @Override
    public void mouseEntered(MouseEvent e) { setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); super.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), border)); }
    @Override
    public void mouseExited(MouseEvent e) { setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); super.setBorder(BorderFactory.createCompoundBorder(nullBorder, border)); }
    @Override
    public void mousePressed(MouseEvent e) { super.setBorder(BorderFactory.createCompoundBorder(nullBorderPressed, border)); }
    @Override
    public void mouseReleased(MouseEvent e) { super.setBorder(BorderFactory.createCompoundBorder(nullBorder, border)); }
    @Override
    public void mouseClicked(MouseEvent e) { super.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), border)); }
}
