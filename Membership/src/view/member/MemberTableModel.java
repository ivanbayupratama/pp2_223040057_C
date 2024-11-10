package Membership.src.view.member;

import javax.swing.table.*;
import java.util.List;
import Membership.src.model.Member;

public class MemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Jenis Member"};
    private List<Member> data;

    //konstruktor
    public MemberTableModel(List<Member> data) {
        this.data = data;
    }

    //mengembalikan jumlah kolom di tabel
    public int getColumnCount() {
        return columnNames.length;
    }

    //mengembalikan jumlah baris di tabel
    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Member rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
            case 1:
                value = rowItem.getJenisMember().getNama();
                break;
        }
        return value;
    }
    
    //cell tidak bisa langsung diedit
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Member value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
