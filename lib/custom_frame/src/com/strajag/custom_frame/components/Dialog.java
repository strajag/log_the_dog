package com.strajag.custom_frame.components;

import com.strajag.custom_frame.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class Dialog extends JDialog implements WindowFocusListener {

    public Frame frame;

    public Dialog(Frame frame, boolean modal) {
        super(frame, modal);
        this.frame = frame;

        setUndecorated(true);

        setLayout(new BorderLayout());
        getContentPane().setBackground(Settings.Colors.backgroundColor);

        if (!modal) { addWindowFocusListener(this); }
    }

    @Override
    public void windowLostFocus(WindowEvent e) { dispose(); }
    @Override
    public void windowGainedFocus(WindowEvent e) { }
}
