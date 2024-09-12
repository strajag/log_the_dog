package com.straykovsky.logthedog.gui.datapanel;

import com.straykovsky.logthedog.gui.GUI;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.logthedog.gui.datapanel.components.BottomPanel;
import com.straykovsky.logthedog.gui.datapanel.components.CenterPanel;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends Panel {

    public GUI gui;

    public CenterPanel centerPanel;
    public BottomPanel bottomPanel;

    public boolean isOwner;

    public DataPanel(GUI gui) {
        this.gui = gui;

        centerPanel = new CenterPanel(this);
        bottomPanel = new BottomPanel(this);

        add(centerPanel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setData(boolean isOwner) {
        this.isOwner = isOwner;
        centerPanel.setData();
        gui.dataPanelFrame.setVisible(true);
    }
}
