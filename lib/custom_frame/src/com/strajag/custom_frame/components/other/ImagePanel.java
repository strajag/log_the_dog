package com.strajag.custom_frame.components.other;

import com.strajag.custom_frame.components.Button;
import com.strajag.custom_frame.components.frame.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ImagePanel extends com.strajag.custom_frame.components.Panel implements ActionListener {

    public com.strajag.custom_frame.components.frame.Frame frame;

    public com.strajag.custom_frame.components.Panel topPanel;
    public com.strajag.custom_frame.components.Panel buttonPanel;

    public com.strajag.custom_frame.components.Button removeButton;

    ImageTransferHandler imageTransferHandler;

    public ImagePanel(Frame frame) {
        this.frame = frame;

        topPanel = new com.strajag.custom_frame.components.Panel() {
            @Override
            public void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                imageTransferHandler.paintImage(graphics);
            }
        };
        imageTransferHandler = new ImageTransferHandler(topPanel);
        setTransferHandler(imageTransferHandler);

        buttonPanel = new com.strajag.custom_frame.components.Panel(new GridBagLayout());
        removeButton = new com.strajag.custom_frame.components.Button("REMOVE");

        buttonPanel.add(removeButton);

        add(topPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        removeButton.addActionListener(this);
    }

    public void setImage(BufferedImage image) { imageTransferHandler.setImage(image); }

    public BufferedImage getImage() { return imageTransferHandler.bufferedImage; }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        com.strajag.custom_frame.components.Button pressedButton = (Button) actionEvent.getSource();
        if (pressedButton.equals(removeButton)) {
            imageTransferHandler.bufferedImage = null;
            repaint();
        }
    }
    @Override
    public Dimension getPreferredSize() { return new Dimension(getParent().getHeight() - removeButton.getPreferredSize().height, getParent().getHeight()); }
}
