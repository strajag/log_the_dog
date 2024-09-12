package com.strajag.log_the_dog.gui.dogpanel.componenets;

import com.strajag.log_the_dog.Settings;
import com.strajag.custom_frame.components.Panel;
import com.strajag.custom_frame.components.ScrollPane;
import com.strajag.custom_frame.components.TextArea;
import com.strajag.log_the_dog.gui.dogpanel.DogPanel;

public class CenterPanel extends Panel {

    public DogPanel dogPanel;

    public TextArea notesTextArea;
    public ScrollPane notesScrollPane;

    public CenterPanel(DogPanel dogPanel) {
        this.dogPanel = dogPanel;

        notesTextArea = new TextArea();
        notesScrollPane = new ScrollPane(notesTextArea);

        add(notesScrollPane);

        notesScrollPane.setBorder(Settings.Borders.getBevelTitleBorder("NOTES"));
    }

    public void setData() { if (dogPanel.dog == null) { notesTextArea.setText(""); } else { notesTextArea.setText(dogPanel.dog.notes.replace("\\n", "\n")); } }
}
