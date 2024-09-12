package com.straykovsky.logthedog.gui.ownerpanel.components;

import com.straykovsky.logthedog.Settings;
import com.straykovsky.custom_frame.components.Panel;
import com.straykovsky.custom_frame.components.ScrollPane;
import com.straykovsky.custom_frame.components.TextArea;
import com.straykovsky.logthedog.gui.ownerpanel.OwnerPanel;

import java.awt.*;

public class CenterPanel extends Panel {

    public OwnerPanel ownerPanel;

    public TextArea notesTextArea;
    public ScrollPane notesScrollPane;

    public TablePanel tablePanel;

    public CenterPanel(OwnerPanel ownerPanel) {
        this.ownerPanel = ownerPanel;

        notesTextArea = new TextArea();
        notesScrollPane = new ScrollPane(notesTextArea);
        tablePanel = new TablePanel(this);

        notesScrollPane.setBorder(Settings.Borders.getBevelTitleBorder("NOTES"));
        tablePanel.setBorder(Settings.Borders.getBevelTitleBorder("DOGS"));
    }

    public void setData() {
        removeAll();
        if (ownerPanel.owner == null) {
            setLayout(new BorderLayout());
            add(notesScrollPane);

            notesTextArea.setText("");
        } else {
            setLayout(new GridLayout(2, 0));
            add(notesScrollPane);
            add(tablePanel);

            tablePanel.setData();

            notesTextArea.setText(ownerPanel.owner.notes.replace("\\n", "\n"));
        }
    }
}
