package com.straykovsky.logthedog.gui;

import com.straykovsky.logthedog.application.Owner;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class OwnersTableModel extends AbstractTableModel {

    public String[] columnNames;
    public List<Owner> ownerList;

    public OwnersTableModel(List<Owner> ownerList, String[] columnNames) { this.ownerList = ownerList; this.columnNames = columnNames; }

    @Override
    public int getRowCount() { return ownerList.size(); }

    @Override
    public int getColumnCount() { return columnNames.length; }

    @Override
    public String getColumnName(int column) { return columnNames[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Owner owner = ownerList.get(rowIndex);
        return switch (getColumnName(columnIndex)) {
            case "NAME" -> owner;
            case "PHONE" -> owner.phoneNumber;
            case "DOGS" -> owner.dogList.size();
            case "ADDRESS" -> owner.address;
            case "EMAIL" -> owner.email;
            case "NOTES" -> owner.notes;
            default -> "OUT OF BOUND";
        };
    }

    @Override public boolean isCellEditable(int row, int column) { return false; }
}
