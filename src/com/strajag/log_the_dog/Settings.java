package com.strajag.log_the_dog;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Settings {

    public File settingsFile;

    public Settings() { settingsFile = new File("./data/settings"); }

    public void readSettings() throws Exception {
        List<String> lines = Files.readAllLines(settingsFile.toPath());

        Text.ownerColumnNames = lines.get(0).split(",");
        Text.dogColumnNames = lines.get(1).split(",");
        Text.ownerDogColumnNames = lines.get(2).split(",");

        Dimensions.ownerColumnWidths = Tools.toIntArray(lines.get(3).split(","));
        Dimensions.dogColumnWidths = Tools.toIntArray(lines.get(4).split(","));
        Dimensions.ownerDogColumnWidths = Tools.toIntArray(lines.get(5).split(","));
    }

    public void writeSettings() throws Exception {
        List<String> lines = new ArrayList<>();

        lines.add(Tools.toStringLine(Text.ownerColumnNames));
        lines.add(Tools.toStringLine(Text.dogColumnNames));
        lines.add(Tools.toStringLine(Text.ownerDogColumnNames));

        lines.add(Tools.toStringLine(Dimensions.ownerColumnWidths));
        lines.add(Tools.toStringLine(Dimensions.dogColumnWidths));
        lines.add(Tools.toStringLine(Dimensions.ownerDogColumnWidths));

        Files.write(settingsFile.toPath(), lines);
    }

    public static class Colors {
        public static Color backgroundColor = new Color(43, 43, 43);
        public static Color scrollBarColor = new Color(53, 53, 53);
        public static Color selectionColor = new Color(63, 63, 63);
        //public static Color fontColor = new Color(255, 150, 100);
        //public static Color fontColor = new Color(230, 150, 100);
        public static Color fontColor = new Color(255, 255, 255);
        public static Color timePickerSpinnerLineColor = new Color(128, 128, 128);
        public static Color borderTitleFontColor = new Color(170, 170, 170);
    }

    public static class Fonts {
        public static Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        public static Font titleBarButtonsFont = new Font(Font.MONOSPACED, Font.PLAIN, 20);
        public static Font titleBarMenuButtonFont = new Font(Font.MONOSPACED, Font.PLAIN, 30);
        //public static Font titleBarTitleButtonFont = new Font(Font.MONOSPACED, Font.PLAIN, 20);
        public static Font titleBarTitleButtonFont = new Font(Font.DIALOG, Font.PLAIN, 15);
        public static Font timePickerSliderFont = new Font(Font.MONOSPACED, Font.BOLD, 18);
        //public static Font borderTitleFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
        public static Font borderTitleFont = new Font(Font.DIALOG, Font.BOLD, 12);
        //public static Font buttonFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
        public static Font buttonFont = new Font(Font.DIALOG, Font.BOLD, 12);
    }

    public static class Borders {
        public static Border frameBorder1 = BorderFactory.createLineBorder(Colors.selectionColor, 1);
        public static Border frameBorder2 = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Colors.selectionColor, 1), BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Colors.backgroundColor, 5), BorderFactory.createLineBorder(Colors.selectionColor, 1)));
        public static Border titleBarBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Colors.selectionColor);
        public static Border getBevelTitleBorder(String title) { return BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), title, TitledBorder.CENTER, TitledBorder.TOP, Fonts.borderTitleFont, Colors.borderTitleFontColor); }
    }

    public static class Dimensions {
        public static int titleBarHeight = 30;
        public static int titleBarSidePanelsWidth = 100;
        public static int datePickerTopButtonsPanelWidth = 70;
        public static int datePickerDialogWidth = 200;
        public static int datePickerDialogHeight = 170;
        public static int timePickerDialogWidth = 200;
        public static int timePickerDialogHeight = 60;

        public static int[] ownerColumnWidths = {213, 213, 213, 213, 213, 217};
        public static int[] dogColumnWidths = {183, 183, 183, 183, 183, 183, 184};
        public static int[] ownerDogColumnWidths = {100, 100, 100, 100, 100, 100, 100};
    }

    public static class Text {
        public static String[] ownerColumnNames = new String[]{"NAME", "PHONE", "DOGS", "ADDRESS", "EMAIL", "NOTES"};
        public static String[] dogColumnNames = new String[]{"DOG NAME", "OWNER NAME", "ARRIVED", "ENDS", "PRICE", "REASON", "NOTES"};
        public static String[] ownerDogColumnNames = new String[]{"DOG NAME", "OWNER NAME", "ARRIVED", "ENDS", "PRICE", "REASON", "NOTES"};
        public static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - HH:mm");
    }
}
