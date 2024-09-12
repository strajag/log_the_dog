package com.straykovsky.logthedog.gui.ownerpanel;

import com.straykovsky.logthedog.application.Owner;
import com.straykovsky.logthedog.gui.GUI;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.logthedog.gui.ownerpanel.components.BottomPanel;
import com.straykovsky.logthedog.gui.ownerpanel.components.CenterPanel;
import com.straykovsky.logthedog.gui.ownerpanel.components.TopPanel;

import java.awt.*;

public class OwnerPanel extends Panel {

    public GUI gui;

    public Owner owner;

    public TopPanel topPanel;
    public CenterPanel centerPanel;
    public BottomPanel bottomPanel;

    public OwnerPanel(GUI gui) {
        this.gui = gui;

        topPanel = new TopPanel(this);
        centerPanel = new CenterPanel(this);
        bottomPanel = new BottomPanel(this);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setData(Owner owner) {
        this.owner = owner;

        topPanel.setData();
        bottomPanel.setData();
        centerPanel.setData();

        gui.ownerPanelFrame.setVisible(true);
    }
}
