package com.strajag.log_the_dog.gui.dogpanel.componenets;

import com.strajag.log_the_dog.Settings;
import com.strajag.custom_frame.components.other.ImagePanel;
import com.strajag.custom_frame.components.Panel;
import com.strajag.log_the_dog.gui.dogpanel.DogPanel;

import java.awt.*;

public class TopPanel extends Panel {

    public DogPanel dogPanel;

    public FieldsPanel fieldsPanel;
    public ImagePanel imagePanel;

    public TopPanel(DogPanel dogPanel) {
        this.dogPanel = dogPanel;

        fieldsPanel = new FieldsPanel(this);
        imagePanel = new ImagePanel(dogPanel.gui.dogPanelFrame);

        add(fieldsPanel);
        add(imagePanel, BorderLayout.EAST);

        imagePanel.setBorder(Settings.Borders.getBevelTitleBorder("PHOTO"));
    }

    public void setData() {
        if (dogPanel.dog == null) {
            imagePanel.setImage(null);
        } else {
            imagePanel.setImage(dogPanel.dog.image);
        }
        fieldsPanel.setData();
    }
}
