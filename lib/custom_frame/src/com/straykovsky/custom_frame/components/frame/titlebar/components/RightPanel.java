package com.straykovsky.custom_frame.components.frame.titlebar.components;

import com.straykovsky.custom_frame.Settings;
import com.straykovsky.custom_frame.components.Button;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.custom_frame.components.frame.titlebar.TitleBarPanel;

import java.awt.*;

public class RightPanel extends Panel {

    public TitleBarPanel titleBarPanel;
    public Button minimizeButton;
    public Button maximizeButton;
    public Button exitButton;

    public RightPanel(TitleBarPanel titleBarPanel) {
        this.titleBarPanel = titleBarPanel;

        minimizeButton = new Button("▽");
        maximizeButton = new Button("▢");
        exitButton = new Button("✕");

        setLayout(new GridLayout(0, 3));
        add(minimizeButton);
        add(maximizeButton);
        add(exitButton);

        minimizeButton.setFont(Settings.Fonts.titleBarButtonsFont);
        maximizeButton.setFont(Settings.Fonts.titleBarButtonsFont);
        exitButton.setFont(Settings.Fonts.titleBarButtonsFont);
    }

    @Override
    public Dimension getPreferredSize() { return new Dimension(Settings.Dimensions.titleBarSidePanelsWidth, Settings.Dimensions.titleBarHeight); }
}
