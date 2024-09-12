package com.strajag.custom_frame.components.frame;

import com.strajag.custom_frame.Settings;
import com.strajag.custom_frame.components.OptionPane;
import com.strajag.custom_frame.components.frame.titlebar.TitleBarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Frame extends JFrame implements WindowListener {

    public TitleBarPanel titleBarPanel;

    public FrameListener frameListener;

    public boolean isUndercoated;

    public Frame() {
        titleBarPanel = new TitleBarPanel(this);

        isUndercoated = true;
        super.setUndecorated(true);
        setUndecorated(false);
        setLayout(new BorderLayout());

        getContentPane().setBackground(Settings.Colors.backgroundColor);
        getRootPane().setBorder(Settings.Borders.frameBorder2);
        frameListener = new FrameListener(this);
        addMouseListener(frameListener);
        addMouseMotionListener(frameListener);
        addWindowListener(this);
    }

    public Frame(String title) {
        this();
        setTitle(title);
    }

    public void setComponent(Component component) {
        getContentPane().removeAll();
        if (isUndercoated) {
            getContentPane().add(component);
        } else {
            getContentPane().add(titleBarPanel, BorderLayout.NORTH);
            getContentPane().add(component, BorderLayout.CENTER);
        }
        revalidate();
        repaint();
    }

    public void throwException(Exception exception) {
        StackTraceElement[] stackTraceElements = exception.getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(exception.getMessage());
        for (int i = stackTraceElements.length - 1; i > 0; i--) {
            if (!stackTraceElements[i].toString().startsWith("com.strajag")) { break; }
            stringBuilder.append("\n").append(stackTraceElements[i]);
        }
        OptionPane.showMessageDialog(this, this, "Exception", stringBuilder.toString());
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        titleBarPanel.centerPanel.tittleButton.setText(title);
    }

    @Override
    public void setUndecorated(boolean isUndercoated) {
        if (isUndercoated) {
            getContentPane().remove(titleBarPanel);
            revalidate();
            repaint();
            this.isUndercoated = true;
        } else {
            getContentPane().add(titleBarPanel, BorderLayout.NORTH, 0);
            revalidate();
            repaint();
            this.isUndercoated = false;
        }
    }
    /*@Override
    public boolean isUndecorated() { return isUndercoated; }*/
    @Override
    public void setVisible(boolean isVisible) { super.setVisible(isVisible); if (getState() == Frame.ICONIFIED) { setState(Frame.NORMAL); } }
    @Override
    public void windowClosed(WindowEvent e) { System.exit(1); }
    @Override
    public void windowOpened(WindowEvent e) { }
    @Override
    public void windowClosing(WindowEvent e) { }
    @Override
    public void windowIconified(WindowEvent e) { }
    @Override
    public void windowDeiconified(WindowEvent e) { }
    @Override
    public void windowActivated(WindowEvent e) { }
    @Override
    public void windowDeactivated(WindowEvent e) { }
}
