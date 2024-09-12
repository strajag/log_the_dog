package com.straykovsky.custom_frame.components.datetimepicker.timepicker;

import com.straykovsky.custom_frame.Settings;
import com.straykovsky.custom_frame.components.Dialog;
import com.straykovsky.custom_frame.components.TextField;
import com.straykovsky.custom_frame.components.datetimepicker.timepicker.components.DialogPanel;
import com.straykovsky.custom_frame.components.frame.Frame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

public class TimePickerTextField extends TextField implements MouseListener {

    public Dialog dialog;
    public DialogPanel dialogPanel;

    public Date date;

    public TimePickerTextField(Frame frame) {
        dialogPanel = new DialogPanel(this);

        dialog = new Dialog(frame, false);
        dialog.setSize(Settings.Dimensions.timePickerDialogWidth, Settings.Dimensions.timePickerDialogHeight);
        dialog.add(dialogPanel);

        setEditable(false);
        addMouseListener(this);

        setTime(System.currentTimeMillis());
    }

    public void update() {
        date.setHours(Math.round(23 * dialogPanel.hoursSliderPanel.sliderButtonPercent));
        date.setMinutes(Math.round(59 * dialogPanel.minutesSliderPanel.sliderButtonPercent));
        setText(date.getHours() + ":" + date.getMinutes());
    }

    public void setTime(long time) {
        date = new Date(time);
        setText(date.getHours() + ":" + date.getMinutes());
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this)) {
            dialog.setLocationRelativeTo(this);
            dialog.setLocation(dialog.getLocation().x, dialog.getLocation().y + (dialog.getHeight() / 2) + (getHeight() / 2));
            dialog.setVisible(true);
            dialogPanel.hoursSliderPanel.setSliderButtonPosition(1 / (23f / date.getHours()));
            dialogPanel.minutesSliderPanel.setSliderButtonPosition(1 / (59f / date.getMinutes()));
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) { setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); }
    @Override
    public void mouseExited(MouseEvent e) { setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
}


