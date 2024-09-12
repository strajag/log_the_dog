package com.straykovsky.custom_frame.components.datetimepicker.timepicker.components;

import com.straykovsky.custom_frame.Settings;
import com.straykovsky.custom_frame.components.Panel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SliderPanel extends Panel implements MouseListener, MouseMotionListener {

    public DialogPanel dialogPanel;

    public int sliderButtonX;
    public String sliderButtonText;
    public float sliderButtonPercent;

    public int stringWidth;
    public int min;
    public int max;
    public int size;

    public SliderPanel(DialogPanel dialogPanel, String sliderButtonText) {
        this.dialogPanel = dialogPanel;
        this.sliderButtonText = sliderButtonText;

        sliderButtonX = 0;

        stringWidth = getFontMetrics(Settings.Fonts.timePickerSliderFont).stringWidth(sliderButtonText);
        min = stringWidth / 2;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setSliderButtonPosition(float percent) {
        sliderButtonPercent = percent;
        sliderButtonX = Math.round((getWidth() - stringWidth) * sliderButtonPercent) + min;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(Settings.Colors.timePickerSpinnerLineColor);
        graphics.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        int fontHeight = getFontMetrics(Settings.Fonts.timePickerSliderFont).getHeight() / 4 / 2;
        graphics.drawLine(getWidth() / 2, (getHeight() / 2) - fontHeight, getWidth() / 2, (getHeight() / 2) + fontHeight);
        graphics.drawLine(getWidth() / 4, (getHeight() / 2) - fontHeight, getWidth() / 4, (getHeight() / 2) + fontHeight);
        graphics.drawLine(getWidth() - (getWidth() / 4), (getHeight() / 2) - fontHeight, getWidth() - (getWidth() / 4), (getHeight() / 2) + fontHeight);

        graphics.setColor(Settings.Colors.fontColor);
        graphics.setFont(Settings.Fonts.timePickerSliderFont);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString(sliderButtonText, sliderButtonX - (fontMetrics.stringWidth(sliderButtonText) / 2), (getHeight() / 2) + (fontMetrics.getHeight() / 4));
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        max = getWidth() - (stringWidth / 2);
        size = getWidth() - stringWidth;
        sliderButtonX = mouseEvent.getX();
        if (sliderButtonX < min) { sliderButtonX = min; } else if (sliderButtonX > max) { sliderButtonX = max; }
        sliderButtonPercent = 1 / ((float) size / (sliderButtonX - min));
        repaint();
        dialogPanel.timePickerTextField.update();
    }
    @Override
    public void mouseDragged(MouseEvent mouseEvent) { mousePressed(mouseEvent); }
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
    @Override
    public void mouseMoved(MouseEvent e) { }
}
