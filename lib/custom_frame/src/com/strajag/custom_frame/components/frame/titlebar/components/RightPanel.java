package com.strajag.custom_frame.components.frame.titlebar.components;

import com.strajag.custom_frame.Settings;
import com.strajag.custom_frame.components.Button;
import com.strajag.custom_frame.components.frame.titlebar.TitleBarPanel;

import java.awt.*;

public class RightPanel extends com.strajag.custom_frame.components.Panel
{

    public TitleBarPanel titleBarPanel;
    public com.strajag.custom_frame.components.Button minimizeButton;
    public com.strajag.custom_frame.components.Button maximizeButton;
    public com.strajag.custom_frame.components.Button exitButton;

    public RightPanel(TitleBarPanel titleBarPanel) {
        this.titleBarPanel = titleBarPanel;

        minimizeButton = new com.strajag.custom_frame.components.Button("▽");
        maximizeButton = new com.strajag.custom_frame.components.Button("▢");
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
