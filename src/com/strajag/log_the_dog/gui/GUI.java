package com.strajag.log_the_dog.gui;

import com.strajag.log_the_dog.Settings;
import com.strajag.log_the_dog.application.DataManager;
import com.strajag.log_the_dog.gui.datapanel.DataPanel;
import com.strajag.log_the_dog.gui.ownerpanel.OwnerPanel;
import com.strajag.custom_frame.components.frame.Frame;
import com.strajag.log_the_dog.gui.dogpanel.DogPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;

public class GUI {

    public DataManager dataManager;

    public Settings settings;

    public Frame dataPanelFrame;
    public Frame ownerPanelFrame;
    public Frame dogPanelFrame;

    public DataPanel dataPanel;
    public OwnerPanel ownerPanel;
    public DogPanel dogPanel;

    public GUI(DataManager dataManager) {
        this.dataManager = dataManager;

        settings = new Settings();
        Image image = null;
        try {
            image = ImageIO.read(new File("./data/FrameIcon.png"));
            settings.readSettings();
        } catch (Exception exception) { exception.printStackTrace(); }

        UIManager.put("Table.ascendingSortIcon", "");
        UIManager.put("Table.descendingSortIcon", "");

        dataPanelFrame = new Frame("log_the_dog") { @Override  public void windowClosed(WindowEvent e) { exit(); } };
        ownerPanelFrame = new Frame("owner");
        dogPanelFrame = new Frame("dog");

        dataPanel = new DataPanel(this);
        dataPanelFrame.setMinimumSize(new Dimension(650, 600));
        dataPanelFrame.setSize(new Dimension(1300, 800));
        dataPanelFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dataPanelFrame.setComponent(dataPanel);
        dataPanelFrame.setLocationRelativeTo(null);
        dataPanelFrame.setIconImage(image);
        dataPanel.setData(true);

        ownerPanel = new OwnerPanel(this);
        ownerPanelFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        ownerPanelFrame.setMinimumSize(new Dimension(600, 600));
        ownerPanelFrame.setSize(new Dimension(700, 800));
        ownerPanelFrame.setLocationRelativeTo(null);
        ownerPanelFrame.setIconImage(image);
        ownerPanelFrame.setComponent(ownerPanel);

        dogPanel = new DogPanel(this);
        dogPanelFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        dogPanelFrame.setMinimumSize(new Dimension(600, 600));
        dogPanelFrame.setSize(new Dimension(700, 800));
        dogPanelFrame.setLocationRelativeTo(null);
        dogPanelFrame.setIconImage(image);
        dogPanelFrame.setComponent(dogPanel);
    }

    public void exit() {
        ownerPanelFrame.setVisible(false);
        dogPanelFrame.setVisible(false);

        String[] copy = Settings.Text.ownerColumnNames.clone();
        for (int i = 0; i < dataPanel.centerPanel.ownersTable.getColumnCount(); i++) { copy[i] = dataPanel.centerPanel.ownersTableModel.getColumnName(dataPanel.centerPanel.ownersTable.convertColumnIndexToModel(i)); Settings.Dimensions.ownerColumnWidths[i] = dataPanel.centerPanel.ownersTable.getColumnModel().getColumn(i).getWidth(); }
        Settings.Text.ownerColumnNames = copy.clone();
        copy = Settings.Text.dogColumnNames.clone();
        for (int i = 0; i < dataPanel.centerPanel.dogsTable.getColumnCount(); i++) { copy[i] = dataPanel.centerPanel.dogsTableModel.getColumnName(dataPanel.centerPanel.dogsTable.convertColumnIndexToModel(i)); Settings.Dimensions.dogColumnWidths[i] = dataPanel.centerPanel.dogsTable.getColumnModel().getColumn(i).getWidth(); }
        Settings.Text.dogColumnNames = copy.clone();
        copy = Settings.Text.ownerDogColumnNames.clone();
        for (int i = 0; i < ownerPanel.centerPanel.tablePanel.table.getColumnCount(); i++) { copy[i] = ownerPanel.centerPanel.tablePanel.tableModel.getColumnName(ownerPanel.centerPanel.tablePanel.table.convertColumnIndexToModel(i)); Settings.Dimensions.ownerDogColumnWidths[i] = ownerPanel.centerPanel.tablePanel.table.getColumnModel().getColumn(i).getWidth(); }
        Settings.Text.ownerDogColumnNames = copy.clone();

        try { settings.writeSettings(); } catch (Exception exception) { dataPanelFrame.throwException(exception); } System.exit(0); }
}
