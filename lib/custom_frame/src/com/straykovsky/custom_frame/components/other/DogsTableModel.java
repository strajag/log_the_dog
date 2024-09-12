package com.straykovsky.custom_frame.components.other;

import com.straykovsky.custom_frame.Settings;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class DogsTableModel extends AbstractTableModel {

    public String[] columnNames;
    public List<Dog> dogList;

    public DateFormat dateFormat = Settings.Text.dateFormat;

    public DogsTableModel(List<Dog> dogList, String[] columnNames) { this.dogList = dogList; this.columnNames = columnNames; }

    public DogsTableModel(String[] columnNames) { this(new ArrayList<>(), columnNames); }

    public void setData(List<Dog> dogList, String[] columnNames) { this.dogList = dogList; this.columnNames = columnNames; }

    @Override
    public int getRowCount() { return dogList.size(); }

    @Override
    public int getColumnCount() { return columnNames.length; }

    @Override
    public String getColumnName(int column) { return columnNames[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Dog dog = dogList.get(rowIndex);
        return switch (getColumnName(columnIndex)) {
            /*case "DOG NAME" -> dog;
            case "OWNER NAME" -> dog.owner;
            case "ARRIVED" -> dateFormat.format(new Date(dog.timeArrived));
            case "ENDS" -> dateFormat.format(new Date(dog.timeTrainingEnds));
            case "PRICE" -> dog.trainingPrice;
            case "REASON" -> dog.trainingReason;
            case "NOTES" -> dog.notes;*/
            default -> "OUT OF BOUND";
        };
    }

    @Override public boolean isCellEditable(int row, int column) { return false; }
}
