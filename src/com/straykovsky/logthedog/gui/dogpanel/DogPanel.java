package com.straykovsky.logthedog.gui.dogpanel;

import com.straykovsky.logthedog.application.Dog;
import com.straykovsky.logthedog.application.Owner;
import com.straykovsky.logthedog.gui.GUI;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.logthedog.gui.dogpanel.componenets.BottomPanel;
import com.straykovsky.logthedog.gui.dogpanel.componenets.CenterPanel;
import com.straykovsky.logthedog.gui.dogpanel.componenets.TopPanel;

import java.awt.*;

public class DogPanel extends Panel {

    public GUI gui;

    public Dog dog;
    public Owner owner;

    public TopPanel topPanel;
    public CenterPanel centerPanel;
    public BottomPanel bottomPanel;

    public DogPanel(GUI gui) {
        this.gui = gui;

        topPanel = new TopPanel(this);
        centerPanel = new CenterPanel(this);
        bottomPanel = new BottomPanel(this);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setData(Dog dog, Owner owner) {
        this.dog = dog;
        this.owner = owner;

        topPanel.setData();
        centerPanel.setData();
        bottomPanel.setData();

        gui.dogPanelFrame.setVisible(true);
    }
}
