import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

public class TotalSection {
    private JTable table;
    private JTableHeader header;
    private JScrollPane scrollPane;
    private Color defaultColor;
    private Object[] colNames;
    private Object[][] data;

    public TotalSection(ArrayList<Expense> expenses) {
        colNames = new Object[3];
        data = new Object[1][3];
        
        double income = 0;
        double spending = 0;

        colNames[0] = "Total";
        colNames[1] = "Income Total";
        colNames[2] = "Spending Total";

        for(Expense ex: expenses) {
            if(ex.getType() == "Income") {
                income += ex.getAmount();
            } else {
                spending += ex.getAmount();
            }
        }

        data[0][0] = "Income/Spending Total: ";
        data[0][1] = income;
        data[0][2] = spending;

    }

    public JScrollPane createTotalTable() {
        defaultColor = new Color(238, 238, 238);

        table = new JTable();
        table.setModel(new TotalModel(data, colNames));
        table.setRowSelectionAllowed(false);
        table.setTableHeader(null);

        table.getColumnModel().getColumn(0).setCellRenderer(new TotalRenderer());

        table.getColumnModel().getColumn(0).setPreferredWidth(400);
        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        table.getColumnModel().getColumn(2).setPreferredWidth(90);

        table.setBackground(defaultColor);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        scrollPane = new JScrollPane(table);

        return scrollPane;
    }

    private class TotalModel extends DefaultTableModel {

        public TotalModel(Object[][] data, Object[] colNames) {
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

    private class TotalRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int col) {
            JLabel renderer = (JLabel) super.getTableCellRendererComponent(
                    table, value,
                    isSelected, hasFocus,
                    row, col);

            renderer.setFont(renderer.getFont().deriveFont(20));

            if(value == "Income/Spending Total: ") {
                renderer.setHorizontalAlignment(JLabel.RIGHT);
                renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
            }

            return renderer;
        }
    }




}
