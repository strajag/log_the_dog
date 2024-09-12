package com.strajag.custom_frame.components.frame.titlebar.components;

import com.strajag.custom_frame.Settings;
import com.strajag.custom_frame.components.Button;
import com.strajag.custom_frame.components.frame.titlebar.TitleBarPanel;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends com.strajag.custom_frame.components.Panel
{

    public TitleBarPanel titleBarPanel;

    public com.strajag.custom_frame.components.Button menuButton;

    public LeftPanel(TitleBarPanel titleBarPanel) {
        super();
        this.titleBarPanel = titleBarPanel;

        menuButton = new Button("≡");

        add(menuButton);

        menuButton.setFont(Settings.Fonts.titleBarMenuButtonFont);
        menuButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));
    }

    @Override
    public Dimension getPreferredSize() { return new Dimension(Settings.Dimensions.titleBarSidePanelsWidth, Settings.Dimensions.titleBarHeight); }
}
