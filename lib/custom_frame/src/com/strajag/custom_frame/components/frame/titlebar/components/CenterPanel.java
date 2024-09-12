package com.strajag.custom_frame.components.frame.titlebar.components;

import com.strajag.custom_frame.Settings;
import com.strajag.custom_frame.components.Button;
import com.strajag.custom_frame.components.frame.titlebar.TitleBarPanel;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends com.strajag.custom_frame.components.Panel
{

    public TitleBarPanel titleBarPanel;
    public com.strajag.custom_frame.components.Button tittleButton;

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
