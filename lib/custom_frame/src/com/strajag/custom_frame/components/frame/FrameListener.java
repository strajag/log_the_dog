package com.strajag.custom_frame.components.frame;

import com.strajag.custom_frame.Settings;
import com.strajag.custom_frame.components.frame.titlebar.TitleBarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameListener implements MouseListener, MouseMotionListener, ActionListener {

    public Frame frame;
    public TitleBarPanel titleBarPanel;

    public Dimension frameSize;
    public Point frameLocation;
    public Point mouseLocationScreen;

    public Point lastLocation;
    public Dimension lastDimension;

    public FrameListener(Frame frame) {
        this.frame = frame;
        titleBarPanel = frame.titleBarPanel;
        titleBarPanel.rightPanel.minimizeButton.addActionListener(this);
        titleBarPanel.rightPanel.maximizeButton.addActionListener(this);
        titleBarPanel.rightPanel.exitButton.addActionListener(this);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (titleBarPanel.isMaximized) { return; }
        frameSize = frame.getSize();
        frameLocation = frame.getLocation();
        mouseLocationScreen = mouseEvent.getLocationOnScreen();
        if (mouseLocationScreen.x >= frameLocation.x && mouseLocationScreen.x < frameLocation.x + 20 && mouseLocationScreen.y >= frameLocation.y && mouseLocationScreen.y < frameLocation.y + 20) {
            frame.setCursor(Cursor.NW_RESIZE_CURSOR);
        } else if (mouseLocationScreen.x >= frameLocation.x && mouseLocationScreen.x < frameLocation.x + 20 && mouseLocationScreen.y <= frameLocation.y + frameSize.height && mouseLocationScreen.y > frameLocation.y + frameSize.height - 20) {
            frame.setCursor(Cursor.SW_RESIZE_CURSOR);
        } else if (mouseLocationScreen.x <= frameLocation.x + frame.getWidth() && mouseLocationScreen.x > frameLocation.x + frameSize.width - 20 && mouseLocationScreen.y >= frameLocation.y && mouseLocationScreen.y < frameLocation.y + 20) {
            frame.setCursor(Cursor.NE_RESIZE_CURSOR);
        } else if (mouseLocationScreen.x <= frameLocation.x + frame.getWidth() && mouseLocationScreen.x > frameLocation.x + frameSize.width - 20 && mouseLocationScreen.y <= frameLocation.y + frameSize.height && mouseLocationScreen.y > frameLocation.y + frameSize.height - 20) {
            frame.setCursor(Cursor.SE_RESIZE_CURSOR);
        } else if (mouseLocationScreen.x >= frameLocation.x && mouseLocationScreen.x < frameLocation.x + 5) {
            frame.setCursor(Cursor.W_RESIZE_CURSOR);
        } else if (mouseLocationScreen.x <= frameLocation.x + frame.getWidth() && mouseLocationScreen.x > frameLocation.x + frameSize.width - 5) {
            frame.setCursor(Cursor.E_RESIZE_CURSOR);
        } else if (mouseLocationScreen.y >= frameLocation.y && mouseLocationScreen.y < frameLocation.y + 5) {
            frame.setCursor(Cursor.N_RESIZE_CURSOR);
        } else if (mouseLocationScreen.y <= frameLocation.y + frameSize.height && mouseLocationScreen.y > frameLocation.y + frameSize.height - 5) {
            frame.setCursor(Cursor.S_RESIZE_CURSOR);
        } else {
            frame.setCursor(Cursor.DEFAULT_CURSOR);
        }
    }
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (titleBarPanel.isMaximized) { return; }

        int frameCursorType = frame.getCursor().getType();
        int[] frameCursorTypes;

        switch (frameCursorType) {
            case Cursor.NW_RESIZE_CURSOR -> frameCursorTypes = new int[]{Cursor.W_RESIZE_CURSOR, Cursor.N_RESIZE_CURSOR};
            case Cursor.SW_RESIZE_CURSOR -> frameCursorTypes = new int[]{Cursor.W_RESIZE_CURSOR, Cursor.S_RESIZE_CURSOR};
            case Cursor.NE_RESIZE_CURSOR -> frameCursorTypes = new int[]{Cursor.E_RESIZE_CURSOR, Cursor.N_RESIZE_CURSOR};
            case Cursor.SE_RESIZE_CURSOR -> frameCursorTypes = new int[]{Cursor.E_RESIZE_CURSOR, Cursor.S_RESIZE_CURSOR};
            default -> frameCursorTypes = new int[]{frameCursorType};
        }

        for (int i = 0; i < frameCursorTypes.length; i++) {
            frameCursorType = frameCursorTypes[i];
            if (frameCursorType == Cursor.W_RESIZE_CURSOR) {
                if (frame.getSize().width - mouseEvent.getX() >= frame.getMinimumSize().width) {
                    frame.setSize(frame.getSize().width - mouseEvent.getX(), frame.getSize().height);
                    frame.setLocation(frame.getLocation().x + mouseEvent.getX(), frame.getLocation().y);
                }
            }
            if (frameCursorType == Cursor.E_RESIZE_CURSOR) {
                if (mouseEvent.getX() >= frame.getMinimumSize().width) {
                    frame.setSize(mouseEvent.getX(), frame.getSize().height);
                }
            }
            if (frameCursorType == Cursor.N_RESIZE_CURSOR) {
                if (frame.getSize().height - mouseEvent.getY() >= frame.getMinimumSize().height) {
                    frame.setSize(frame.getSize().width, frame.getSize().height - mouseEvent.getY());
                    frame.setLocation(frame.getLocation().x, frame.getLocation().y + mouseEvent.getY());
                }
            }
            if (frameCursorType == Cursor.S_RESIZE_CURSOR) {
                if (mouseEvent.getY() >= frame.getMinimumSize().height) {
                    frame.setSize(frame.getSize().width, mouseEvent.getY());
                }
            }
        }

        frame.revalidate();
        frame.repaint();
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) { }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) { }
    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }
    @Override
    public void mouseExited(MouseEvent mouseEvent) { }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) { }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton pressedButton = (JButton) actionEvent.getSource();
        if (pressedButton.equals(titleBarPanel.rightPanel.minimizeButton)) {
            frame.setState(Frame.ICONIFIED);
        } else if (pressedButton.equals(titleBarPanel.rightPanel.maximizeButton)) {
            if (titleBarPanel.isMaximized) {
                frame.getRootPane().setBorder(Settings.Borders.frameBorder2);
                frame.setSize(lastDimension);
                frame.setLocation(lastLocation);
            } else {
                lastDimension = frame.getSize();
                lastLocation = frame.getLocation();
                frame.getRootPane().setBorder(null);
                frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            }
            titleBarPanel.isMaximized = !titleBarPanel.isMaximized;
        } else if (pressedButton.equals(titleBarPanel.rightPanel.exitButton)) {
            if (frame.getDefaultCloseOperation() == Frame.EXIT_ON_CLOSE) {
                System.exit(0);
            } else if (frame.getDefaultCloseOperation() == Frame.DISPOSE_ON_CLOSE) {
                frame.dispose();
            } else if (frame.getDefaultCloseOperation() == Frame.HIDE_ON_CLOSE) {
                frame.setVisible(false);
            }
        }
    }
}
