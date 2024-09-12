package com.straykovsky.logthedog.gui.datapanel.components;

import com.straykovsky.logthedog.application.Dog;
import com.straykovsky.logthedog.application.Owner;
import com.straykovsky.logthedog.Settings;
import com.straykovsky.custom_frame.components.Button;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.custom_frame.components.ScrollPane;
import com.straykovsky.custom_frame.components.Table;
import com.straykovsky.logthedog.gui.OwnersTableModel;
import com.straykovsky.logthedog.gui.DogsTableModel;
import com.straykovsky.logthedog.gui.datapanel.DataPanel;

import javax.swing.*;
import java.awt.event.*;

public class CenterPanel extends Panel implements ActionListener, MouseListener {

    public DataPanel dataPanel;

    public OwnersTableModel ownersTableModel;
    public DogsTableModel dogsTableModel;

    public Table ownersTable;
    public Table dogsTable;

    public ScrollPane tableScrollPane;

    public Button frameTittleButton;

    public CenterPanel(DataPanel dataPanel) {
        this.dataPanel = dataPanel;

        ownersTableModel = new OwnersTableModel(dataPanel.gui.dataManager.ownerList, Settings.Text.ownerColumnNames);
        dogsTableModel = new DogsTableModel(dataPanel.gui.dataManager.dogList, Settings.Text.dogColumnNames);

        ownersTable = new Table(ownersTableModel);
        dogsTable = new Table(dogsTableModel);

        tableScrollPane = new ScrollPane();
        frameTittleButton = dataPanel.gui.dataPanelFrame.titleBarPanel.centerPanel.tittleButton;

        add(tableScrollPane);

        setBorder(Settings.Borders.getBevelTitleBorder(""));
        frameTittleButton.addActionListener(this);
        ownersTable.addMouseListener(this);
        dogsTable.addMouseListener(this);
        tableScrollPane.addMouseListener(this);

        for (int i = 0; i < ownersTable.getColumnCount(); i++) { ownersTable.getColumnModel().getColumn(i).setPreferredWidth(Settings.Dimensions.ownerColumnWidths[i]); }
        for (int i = 0; i < dogsTable.getColumnCount(); i++) { dogsTable.getColumnModel().getColumn(i).setPreferredWidth(Settings.Dimensions.dogColumnWidths[i]); }
    }

    public void setData() {
        tableScrollPane.getViewport().removeAll();
        tableScrollPane.getViewport().add(getCurrentTable());
        if (dataPanel.isOwner) { frameTittleButton.setText("OWNER LIST"); } else { frameTittleButton.setText("DOG LIST"); }
    }

    public Table getCurrentTable() { if (dataPanel.isOwner) { return ownersTable; } else { return dogsTable; } }

    @Override
    public void actionPerformed(ActionEvent actionEvent) { Button pressedButton = (Button) actionEvent.getSource(); if (pressedButton.equals(frameTittleButton)) { dataPanel.isOwner = !dataPanel.isOwner; setData(); } }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Object clickedObject = mouseEvent.getSource();
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            if (clickedObject.equals(tableScrollPane)) {
                getCurrentTable().clearSelection();
            } else if (mouseEvent.getClickCount() == 2) {
                if (((Table) clickedObject).getSelectedRows().length == 1) {
                    if (dataPanel.isOwner) {
                        dataPanel.gui.ownerPanel.setData((Owner) getCurrentTable().getSelectedItem(0));
                    } else {
                        dataPanel.gui.dogPanel.setData((Dog) getCurrentTable().getSelectedItem(0), null);
                    }
                }
            }

        } else if (SwingUtilities.isRightMouseButton(mouseEvent)) { getCurrentTable().clearSelection(); }
    }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}
