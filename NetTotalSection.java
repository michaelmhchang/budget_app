import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class NetTotalSection {
    private JTable table;
    private JScrollPane scrollPane;
    private NetRenderer renderer; 
    private Color defaultColor;

    public JScrollPane createNetTotalTable() {
        Object[] colNames = {"Total", "Amount"};
        Object[][] data = {{"Net Total: ", 1}};

        defaultColor = new Color(238, 238, 238);

        renderer = new NetRenderer();
        table = new JTable();
        scrollPane = new JScrollPane(table);

        table.setModel(new NetModel(data, colNames));
        table.setTableHeader(null);
        table.setRowSelectionAllowed(false);
        table.setShowVerticalLines(false);

        for(int col = 0; col < colNames.length; col++) {
            table.getColumnModel().getColumn(col).setCellRenderer(renderer);
        }

        int maxRowHeight = table.getRowHeight(0);

        for(int col = 0; col < colNames.length; col++) {
            Component cell = table.prepareRenderer(renderer, 0, col);
            table.getColumnModel().getColumn(col).setPreferredWidth(
                    cell.getPreferredSize().width + 1);
            
            maxRowHeight = Math.max(maxRowHeight, cell.getPreferredSize().height);
        }

        table.setBackground(defaultColor);
        table.setRowHeight(maxRowHeight);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        return scrollPane;
    }

    private class NetModel extends DefaultTableModel {

        public NetModel(Object[][] data, Object[] colNames) {
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

    private class NetRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int col) {
            JLabel renderer = (JLabel) super.getTableCellRendererComponent(
                    table, value,
                    isSelected, hasFocus,
                    row, col);
            
            renderer.setFont(renderer.getFont().deriveFont(20f));

            if(value == "Net Total: ") {
                renderer.setHorizontalAlignment(JLabel.RIGHT);
                renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
            }

            int renderWidth = renderer.getPreferredSize().width;

            return renderer;
        }
    }
}
