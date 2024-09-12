package com.strajag.custom_frame.components.frame.titlebar;

import com.strajag.custom_frame.Settings;
import com.strajag.custom_frame.components.Panel;
import com.strajag.custom_frame.components.frame.titlebar.components.CenterPanel;
import com.strajag.custom_frame.components.frame.titlebar.components.LeftPanel;
import com.strajag.custom_frame.components.frame.titlebar.components.RightPanel;

import java.awt.*;

public class TitleBarPanel extends Panel
{

    public Window window;

    public TitleBarListener titleBarListener;

    public LeftPanel leftPanel;
    public CenterPanel centerPanel;
    public RightPanel rightPanel;

    public boolean isMaximized = false;

    public TitleBarPanel(Window window) {
        this.window = window;

        leftPanel = new LeftPanel(this);
        centerPanel = new CenterPanel(this);
        rightPanel = new RightPanel(this);

        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        setBorder(Settings.Borders.titleBarBorder);
        titleBarListener = new TitleBarListener(this);
        addMouseListener(titleBarListener);
        addMouseMotionListener(titleBarListener);
        centerPanel.tittleButton.addMouseListener(titleBarListener);
        centerPanel.tittleButton.addMouseMotionListener(titleBarListener);
    }

    @Override
    public Dimension getPreferredSize() { return new Dimension(getParent().getWidth(), Settings.Dimensions.titleBarHeight); }
}
