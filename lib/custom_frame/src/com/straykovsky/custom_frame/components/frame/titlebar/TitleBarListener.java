package com.straykovsky.custom_frame.components.frame.titlebar;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TitleBarListener implements MouseListener, MouseMotionListener {

    public TitleBarPanel titleBarPanel;

    public Point mousePoint;
    public int borderSize = 7;

    public TitleBarListener(TitleBarPanel titleBarPanel) { this.titleBarPanel = titleBarPanel; }

    @Override
    public void mousePressed(MouseEvent mouseEvent) { mousePoint = titleBarPanel.getMousePosition(); }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) { mousePoint = null; }
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (titleBarPanel.isMaximized) { return; }
        if (mouseEvent.getSource().equals(titleBarPanel) || mouseEvent.getSource().equals(titleBarPanel.centerPanel.tittleButton)) {
            titleBarPanel.window.setLocation(mouseEvent.getLocationOnScreen().x - mousePoint.x - borderSize, mouseEvent.getLocationOnScreen().y - mousePoint.y - borderSize);
            if (titleBarPanel.window.getLocation().x < 0) { titleBarPanel.window.setLocation(0, titleBarPanel.window.getLocation().y); }
            if (titleBarPanel.window.getLocation().y < 0) { titleBarPanel.window.setLocation(titleBarPanel.window.getLocation().x, 0); }
        }
    }
    @Override
    public void mouseEntered(MouseEvent mouseEvent) { titleBarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)); }
    @Override
    public void mouseExited(MouseEvent mouseEvent) { titleBarPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); }
    @Override
    public void mouseMoved(MouseEvent mouseEvent) { }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) { }
}
