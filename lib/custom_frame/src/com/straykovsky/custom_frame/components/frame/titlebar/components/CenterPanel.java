package com.straykovsky.custom_frame.components.frame.titlebar.components;

import com.straykovsky.custom_frame.Settings;
import com.straykovsky.custom_frame.components.Button;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.custom_frame.components.frame.titlebar.TitleBarPanel;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends Panel {

    public TitleBarPanel titleBarPanel;
    public Button tittleButton;

    public CenterPanel(TitleBarPanel titleBarPanel) {
        this.titleBarPanel = titleBarPanel;

        tittleButton = new Button();

        setLayout(new GridBagLayout());
        add(tittleButton);

        tittleButton.setForeground(Settings.Colors.borderTitleFontColor);
        tittleButton.setFont(Settings.Fonts.titleBarTitleButtonFont);
        tittleButton.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
        tittleButton.removeMouseListener(tittleButton);
    }
}
