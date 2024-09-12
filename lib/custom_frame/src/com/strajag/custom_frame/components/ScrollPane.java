package com.strajag.custom_frame.components;

import com.strajag.custom_frame.Settings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ScrollPane extends JScrollPane {

    public ScrollPane(Component component) {
        super(component);

        setBackground(Settings.Colors.backgroundColor);
        getViewport().setBackground(Settings.Colors.backgroundColor);

        getVerticalScrollBar().setPreferredSize(new Dimension(12, 0));
        getVerticalScrollBar().setBackground(Settings.Colors.backgroundColor);
        getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Settings.Colors.scrollBarColor;
                this.trackColor = Settings.Colors.backgroundColor;
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                Dimension zeroDim = new Dimension(0,0);
                button.setPreferredSize(zeroDim);
                button.setMinimumSize(zeroDim);
                button.setMaximumSize(zeroDim);
                return button;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return this.incrButton;
            }
        });

        getHorizontalScrollBar().setPreferredSize(new Dimension(0, 12));
        getHorizontalScrollBar().setBackground(Settings.Colors.backgroundColor);
        getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Settings.Colors.scrollBarColor;
                this.trackColor = Settings.Colors.backgroundColor;
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                Dimension zeroDim = new Dimension(0,0);
                button.setPreferredSize(zeroDim);
                button.setMinimumSize(zeroDim);
                button.setMaximumSize(zeroDim);
                return button;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return this.incrButton;
            }
        });

        setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public ScrollPane() { this(null); }
}
