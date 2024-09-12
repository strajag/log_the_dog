package com.straykovsky.custom_frame.components;

import com.straykovsky.custom_frame.Settings;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Table extends JTable implements MouseListener {

    public Table(TableModel tableModel) {
        super(tableModel);

        setFont(Settings.Fonts.font);
        setBackground(Settings.Colors.backgroundColor);
        setForeground(Settings.Colors.fontColor);
        setSelectionBackground(Settings.Colors.selectionColor);
        setSelectionForeground(Settings.Colors.fontColor);
        setGridColor(Settings.Colors.timePickerSpinnerLineColor);
        getTableHeader().setBackground(Settings.Colors.backgroundColor);

        setAutoCreateRowSorter(true);
        setRowHeight(30);
        setFocusable(false);
        setAutoResizeMode(Table.AUTO_RESIZE_ALL_COLUMNS);

        DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
        tableRenderer.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, tableRenderer);

        final TableCellRenderer tcrOs = getTableHeader().getDefaultRenderer();
        getTableHeader().setDefaultRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JLabel lbl = (JLabel) tcrOs.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            lbl.setBackground(Settings.Colors.backgroundColor);
            lbl.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lbl.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            if (isSelected) {
                lbl.setForeground(Settings.Colors.borderTitleFontColor);
                lbl.setFont(Settings.Fonts.buttonFont);
            } else {
                lbl.setForeground(Settings.Colors.borderTitleFontColor);
                lbl.setFont(Settings.Fonts.buttonFont);
            }
            return lbl;
        });

        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Settings.Colors.backgroundColor));
        addMouseListener(this);
    }

    public Table() { this(null); }

    public void refresh() { ((AbstractTableModel) getModel()).fireTableDataChanged(); }

    public Object getSelectedItem(int columnIndex) { return getModel().getValueAt(convertRowIndexToModel(getSelectedRow()), columnIndex); }

    public Object[] getSelectedItems(int columnIndex) {
        int[] selectedIndexes = getSelectedRows();
        Object[] objects = new Object[selectedIndexes.length];
        for (int i = 0; i < objects.length; i++) { objects[i] = getModel().getValueAt(convertRowIndexToModel(selectedIndexes[i]), columnIndex); }
        return objects;
    }

    @Override
    public JTableHeader getTableHeader() { if (tableHeader.getParent() != null) { tableHeader.getParent().setBackground(Settings.Colors.backgroundColor); } return super.getTableHeader(); }
    @Override
    public Dimension getMinimumSize() { return getPreferredSize(); }
    @Override
    public Dimension getMaximumSize() { return getPreferredSize(); }
    @Override
    public void mouseEntered(MouseEvent mouseEvent) { setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); }
    @Override
    public void mouseExited(MouseEvent mouseEvent) { setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) { }
    @Override
    public void mousePressed(MouseEvent mouseEvent) { }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) { }
}
