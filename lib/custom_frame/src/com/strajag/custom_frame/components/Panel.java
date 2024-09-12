package com.strajag.custom_frame.components;

import com.strajag.custom_frame.Settings;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    public Panel() {
        super(new BorderLayout());
        setBackground(Settings.Colors.backgroundColor);
    }

    public Panel(LayoutManager layoutManager) {
        this();
        setLayout(layoutManager);
    }

    @Override
    public Dimension getMinimumSize() { return getPreferredSize(); }
    @Override
    public Dimension getMaximumSize() { return getPreferredSize(); }
}
