package com.strajag.log_the_dog.gui.dogpanel;

import com.strajag.log_the_dog.application.Dog;
import com.strajag.log_the_dog.application.Owner;
import com.strajag.log_the_dog.gui.GUI;
import com.strajag.log_the_dog.gui.dogpanel.componenets.BottomPanel;
import com.strajag.log_the_dog.gui.dogpanel.componenets.CenterPanel;
import com.strajag.log_the_dog.gui.dogpanel.componenets.TopPanel;
import com.strajag.custom_frame.components.Panel;

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
