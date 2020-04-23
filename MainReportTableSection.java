import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.lang.*;

public class MainReportTableSection {
    private JTable table;
    private JScrollPane scrollPane;
    private JTableHeader header;
    private Object[] colNames;
    private Object[][] data;

    public MainReportTableSection(ArrayList<Expense> expenses) {
        colNames = new Object[4];

        colNames[0] = "Date";
        colNames[1] = "Name";
        colNames[2] = "Income";
        colNames[3] = "Spendings";

        data = new Object[expenses.size()][4];

        for(int expense = 0; expense < expenses.size(); expense++) { 
            Expense currExpense = expenses.get(expense);

            if(currExpense.getType() == "Income") {
                data[expense][0] = currExpense.getDate();
                data[expense][1] = currExpense.getName();
                data[expense][2] = currExpense.getAmount();
                data[expense][3] = null;
            } else {
                data[expense][0] = currExpense.getDate();
                data[expense][1] = currExpense.getName();
                data[expense][2] = null;
                data[expense][3] = currExpense.getAmount();
            }
        }
    }

    public JScrollPane createMainTable() {

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
            Object doubleValue = 1.11;
            if(getValueAt(0,c) == null) {
                return doubleValue.getClass();
            } else {
                return getValueAt(0,c).getClass();
            }
        }
    }
}
