package com.straykovsky.custom_frame.components;

import com.straykovsky.custom_frame.Settings;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ComboBox<T> extends JComboBox<T> implements MouseListener {

    public ComboBox(DefaultComboBoxModel<T> defaultComboBoxModel) {
        super(defaultComboBoxModel);

        setFont(Settings.Fonts.font);

        setBackground(Settings.Colors.backgroundColor);
        setForeground(Settings.Colors.fontColor);

        setFocusable(false);

        setUI(new BasicComboBoxUI() { protected JButton createArrowButton() { return new JButton() { public int getWidth() { return 0; } }; } });
        ((JLabel) getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        addMouseListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) { setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); }
    @Override
    public void mouseExited(MouseEvent e) { setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); }
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
}
