package com.straykovsky.custom_frame.components.other;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class ImageTransferHandler extends TransferHandler {

    public Component component;

    public BufferedImage bufferedImage;

    public static final DataFlavor FILE_FLAVOR = DataFlavor.javaFileListFlavor;

    public ImageTransferHandler(Component component) { this.component = component; }

    public void paintImage(Graphics graphics) {
        if (bufferedImage != null) {
            if (component.getWidth() < component.getHeight()) {
                graphics.drawImage(bufferedImage, 0, (component.getHeight() / 2) - (component.getWidth() / 2), component.getWidth(), component.getWidth(), null);
            } else {
                graphics.drawImage(bufferedImage, (component.getWidth() / 2) - (component.getHeight() / 2), 0, component.getHeight(), component.getHeight(), null);
            }
        }
    }

    public void setImage(BufferedImage image) {
        if (image == null) { bufferedImage = null; return; }
        bufferedImage = image;

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (width == 500 && height == 500) { return; }

        if (width < height) {
            bufferedImage = bufferedImage.getSubimage(0, (height / 2) - (width / 2), width, width);
        } else {
            bufferedImage = bufferedImage.getSubimage((width / 2) - (height / 2), 0, height, height);
        }

        BufferedImage resized = new BufferedImage(500, 500, bufferedImage.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, 500, 500, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
        g.dispose();
        bufferedImage = resized;

        BufferedImage output = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), 100, 100));
        //g2.fill(new RoundRectangle2D.Float(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), 1000, 1000));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(bufferedImage, 0, 0, null);
        g2.dispose();
        bufferedImage = output;

        component.repaint();
    }

    public boolean transferFlavor(DataFlavor[] flavors, DataFlavor flavor) {
        boolean found = false;
        for (int i = 0; i < flavors.length && !found; i++) { found = flavors[i].equals(flavor); }
        return found;
    }
    @Override
    public boolean importData(JComponent c, Transferable t) {
        if (canImport(c, t.getTransferDataFlavors())) {
            if (transferFlavor(t.getTransferDataFlavors(), FILE_FLAVOR)) {
                try {
                    List<File> fileList = (List<File>) t.getTransferData(FILE_FLAVOR);
                    setImage(ImageIO.read(fileList.get(0)));
                    return true;
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return false;
    }
    @Override
    public int getSourceActions(JComponent c) { return COPY_OR_MOVE; }
    @Override
    public void exportDone(JComponent c, Transferable data, int action) { c.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); }
    @Override
    public boolean canImport(JComponent c, DataFlavor[] flavors) {
        for (int i = 0; i < flavors.length; i++) { if (FILE_FLAVOR.equals(flavors[i])) { return true; } }
        return false;
    }
}