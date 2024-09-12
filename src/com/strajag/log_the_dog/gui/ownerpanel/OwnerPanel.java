package com.strajag.log_the_dog.gui.ownerpanel;

import com.strajag.log_the_dog.gui.ownerpanel.components.TopPanel;
import com.strajag.log_the_dog.application.Owner;
import com.strajag.log_the_dog.gui.GUI;
import com.strajag.custom_frame.components.Panel;
import com.strajag.log_the_dog.gui.ownerpanel.components.BottomPanel;
import com.strajag.log_the_dog.gui.ownerpanel.components.CenterPanel;

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
