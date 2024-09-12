package com.straykovsky.custom_frame.components.other;

import com.straykovsky.custom_frame.components.Button;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.custom_frame.components.frame.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ImagePanel extends Panel implements ActionListener {

    public Frame frame;

    public Panel topPanel;
    public Panel buttonPanel;

    public Button removeButton;

    ImageTransferHandler imageTransferHandler;

    public ImagePanel(Frame frame) {
        this.frame = frame;

        topPanel = new Panel() {
            @Override
            public void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                imageTransferHandler.paintImage(graphics);
            }
        };
        imageTransferHandler = new ImageTransferHandler(topPanel);
        setTransferHandler(imageTransferHandler);

        buttonPanel = new Panel(new GridBagLayout());
        removeButton = new Button("REMOVE");

        buttonPanel.add(removeButton);

        add(topPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        removeButton.addActionListener(this);
    }

    public void setImage(BufferedImage image) { imageTransferHandler.setImage(image); }

    public BufferedImage getImage() { return imageTransferHandler.bufferedImage; }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Button pressedButton = (Button) actionEvent.getSource();
        if (pressedButton.equals(removeButton)) {
            imageTransferHandler.bufferedImage = null;
            repaint();
        }
    }
    @Override
    public Dimension getPreferredSize() { return new Dimension(getParent().getHeight() - removeButton.getPreferredSize().height, getParent().getHeight()); }
}
