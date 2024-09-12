package com.strajag.log_the_dog.gui.datapanel;

import com.strajag.log_the_dog.gui.datapanel.components.BottomPanel;
import com.strajag.log_the_dog.gui.GUI;
import com.strajag.custom_frame.components.Panel;
import com.strajag.log_the_dog.gui.datapanel.components.CenterPanel;

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
