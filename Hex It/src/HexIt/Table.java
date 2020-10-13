package HexIt;


import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class Table extends JTable {
    public void setColumnWidths(int widths[]) {
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setPreferredWidth(widths[i]);
            }
            else break;
        }
    }
    public Component prepareRenderer( TableCellRenderer renderer, int row, int column) {
        JComponent jc = (JComponent)super.prepareRenderer(renderer, row, column);
        jc.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        return jc;
    }
}