package com.fangrongfu.chuhuoguanli;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectionListener implements ListSelectionListener {
    JTable table;

    SelectionListener(JTable table) {
        this.table = table;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == table.getColumnModel().getSelectionModel()&& table.getColumnSelectionAllowed() ){

            int firstRow = e.getFirstIndex();
            int lastRow = e.getLastIndex();

        }
    }
}
