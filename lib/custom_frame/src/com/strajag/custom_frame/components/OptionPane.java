package com.strajag.custom_frame.components;

import com.strajag.custom_frame.Settings;
import com.strajag.custom_frame.components.frame.titlebar.TitleBarPanel;

import javax.swing.*;
import java.awt.*;

public class OptionPane {

    public static Dialog dialog;
    public static TitleBarPanel titleBarPanel;

    public static int answer;
    //Label iconLabel = new Label("╳❕❗!");
    public static void showMessageDialog(Frame frame, Component parentComponent, String title, String message) {
        createDialog(frame, parentComponent);

        com.strajag.custom_frame.components.Button okButton = new com.strajag.custom_frame.components.Button("OK");
        okButton.addActionListener(e -> dialog.dispose());

        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 18);
        titleBarPanel.rightPanel.exitButton.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Settings.Colors.backgroundColor));
        titleBarPanel.rightPanel.exitButton.setFont(font);
        titleBarPanel.rightPanel.exitButton.setForeground(Color.YELLOW);
        titleBarPanel.rightPanel.exitButton.setText("❗");

        TextPane textPane = new TextPane();
        textPane.setText(message);
        textPane.setDisabledTextColor(Settings.Colors.borderTitleFontColor);
        textPane.setEnabled(false);

        Panel dialogPanel = new Panel();
        dialogPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1;
        gbc.insets = new Insets(15, 0, 0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dialogPanel.add(textPane, gbc);
        gbc.insets = new Insets(0, 0, 0,0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0;
        gbc.gridy = 1;
        dialogPanel.add(okButton, gbc);
        dialog.add(dialogPanel);

        titleBarPanel.centerPanel.tittleButton.setText(title);
        dialog.setVisible(true);
    }

    public static int showConfirmDialog(Frame frame, Component parentComponent, String title, String message) {
        answer = 0;

        createDialog(frame, parentComponent);

        com.strajag.custom_frame.components.Button yesButton = new com.strajag.custom_frame.components.Button("YES");
        com.strajag.custom_frame.components.Button noButton = new Button("NO");
        yesButton.addActionListener(e -> { answer = 1; dialog.dispose(); } );
        noButton.addActionListener(e -> dialog.dispose());

        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 18);
        titleBarPanel.rightPanel.exitButton.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Settings.Colors.backgroundColor));
        titleBarPanel.rightPanel.exitButton.setFont(font);
        titleBarPanel.rightPanel.exitButton.setForeground(Color.GREEN);
        titleBarPanel.rightPanel.exitButton.setText("❓");

        TextPane textPane = new TextPane();
        textPane.setText(message);
        textPane.setDisabledTextColor(Settings.Colors.borderTitleFontColor);
        textPane.setEnabled(false);

        Panel dialogPanel = new Panel();
        dialogPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 0, 0,0);
        dialogPanel.add(textPane, gbc);
        gbc.insets = new Insets(0, 0, 0,0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 2;
        gbc.gridy = 1;
        dialogPanel.add(yesButton, gbc);
        gbc.gridy = 1;
        gbc.gridx = 1;
        dialogPanel.add(noButton, gbc);
        dialog.add(dialogPanel);

        titleBarPanel.centerPanel.tittleButton.setText(title);
        dialog.setVisible(true);

        return answer;
    }

    public static void createDialog(Frame frame, Component parentComponent) {
        dialog = new Dialog(frame, true);
        dialog.setSize(350, 140);
        dialog.setLocationRelativeTo(parentComponent);
        dialog.getRootPane().setBorder(Settings.Borders.frameBorder2);

        titleBarPanel = new TitleBarPanel(dialog);
        titleBarPanel.centerPanel.tittleButton.removeMouseListener(titleBarPanel.centerPanel.tittleButton);
        titleBarPanel.leftPanel.menuButton.removeMouseListener(titleBarPanel.leftPanel.menuButton);
        titleBarPanel.rightPanel.exitButton.removeMouseListener(titleBarPanel.rightPanel.exitButton);
        titleBarPanel.rightPanel.minimizeButton.setVisible(false);
        titleBarPanel.rightPanel.maximizeButton.setVisible(false);
        titleBarPanel.leftPanel.menuButton.setVisible(false);

        dialog.add(titleBarPanel, BorderLayout.NORTH);
    }
}
