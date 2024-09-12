package com.straykovsky.custom_frame.components.datetimepicker.datepicker;

import com.straykovsky.custom_frame.Settings;
import com.straykovsky.custom_frame.components.Dialog;
import com.straykovsky.custom_frame.components.TextField;
import com.straykovsky.custom_frame.components.datetimepicker.datepicker.components.DialogPanel;
import com.straykovsky.custom_frame.components.frame.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DatePickerTextField extends TextField implements MouseListener {

    public Dialog dialog;
    public DialogPanel dialogPanel;

    public Date date;
    public LocalDate localDate;

    public DatePickerTextField(Frame frame) {
        date = new Date(System.currentTimeMillis());

        dialogPanel = new DialogPanel(this);

        dialog = new Dialog(frame, false);
        dialog.setSize(Settings.Dimensions.datePickerDialogWidth, Settings.Dimensions.datePickerDialogHeight);
        dialog.add(dialogPanel);

        setEditable(false);
        addMouseListener(this);
        setBorder(BorderFactory.createEmptyBorder());

        update();
    }

    public void update() {
        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        setText(localDate.getDayOfMonth() + "." + localDate.getMonthValue() + "." + localDate.getYear());
        dialogPanel.topPanel.yearMonthButton.setText(localDate.getMonth().toString().substring(0, 3));
        dialogPanel.centerPanel.update();
    }

    public void setTime(long time) {
        date = new Date(time);
        update();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        dialog.setLocationRelativeTo(this);
        dialog.setLocation(dialog.getLocation().x, dialog.getLocation().y + (dialog.getHeight() / 2) + (getHeight() / 2));
        dialog.setVisible(true);
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
