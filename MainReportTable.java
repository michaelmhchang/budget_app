import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Date;

public class MainReportTable {
    private JTable table;
    private JScrollPane scrollPane;
    private JTableHeader header;
    private Object[] colNames;
    private Object[][] data;

    public MainReportTable(Object[][] data, Object[] colNames) {
        this.colNames = colNames;
        this.data = data; 
    }

    public JScrollPane makeMainTable() {

        // Table Formatting
        // ----------------------------------------------------------------------
        table = new JTable();
        header = table.getTableHeader();
        scrollPane = new JScrollPane(table);

        table.setModel(new MainModel(data, colNames));

        header.setResizingAllowed(false);
        header.setReorderingAllowed(false);
                
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(90);
        table.getColumnModel().getColumn(3).setPreferredWidth(90);

        table.setShowHorizontalLines(false);

        if(table.getPreferredSize().height <= 240) {
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
        } else {
            table.setPreferredScrollableViewportSize(
                    new Dimension(table.getPreferredSize().width, 240));
        }

        // ----------------------------------------------------------------------

        return scrollPane; 

    }

    private class MainModel extends DefaultTableModel {

        public MainModel(Object[][] data, Object[] colNames) {
            super(data, colNames);
        }
        
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
        
        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0,c).getClass();
        }
    }
}
