package com.strajag.log_the_dog.gui.ownerpanel.components;

import com.strajag.log_the_dog.Settings;
import com.strajag.custom_frame.components.other.ImagePanel;
import com.strajag.custom_frame.components.Panel;
import com.strajag.log_the_dog.gui.ownerpanel.OwnerPanel;

import java.awt.*;

public class TopPanel extends Panel {

    public OwnerPanel ownerPanel;

    public FieldsPanel fieldsPanel;
    public ImagePanel imagePanel;

    public TopPanel(OwnerPanel ownerPanel) {
        this.ownerPanel = ownerPanel;

        fieldsPanel = new FieldsPanel(this);
        imagePanel = new ImagePanel(ownerPanel.gui.ownerPanelFrame);

        add(fieldsPanel);
        add(imagePanel, BorderLayout.EAST);

        imagePanel.setBorder(Settings.Borders.getBevelTitleBorder("PHOTO"));
    }

    public void setData() {
        if (ownerPanel.owner == null) { imagePanel.setImage(null); } else { imagePanel.setImage(ownerPanel.owner.image); }
        fieldsPanel.setData();
    }
}
