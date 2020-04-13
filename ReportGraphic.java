import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;

class ReportGraphic {
    private JFrame reportFrame;
    private JPanel reportPanel;
    private JTable reportTable;
    private DefaultTableModel uneditModel;
    private JTableHeader header;

    public static void main(String[] args) {
        ReportGraphic report = new ReportGraphic();
        report.activate();
    }

    public void activate() {
        reportFrame = new JFrame("Report");
        reportPanel = new JPanel();

        Color defaultColor = new Color(213,213,213);

        // initialize table components
        // ----------------------------------------------------------------------
        Object[] colNames = {"Date", "Name", "Income", "Spendings"};
        Object[][] data = {
            {1,2,3,4},
            {5,6,7,8},
            {9,"",10,11},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8}};

        reportTable = new JTable(data, colNames);
        header = reportTable.getTableHeader();
        JScrollPane tableScroll = new JScrollPane(reportTable);

        uneditModel = new DefaultTableModel(data, colNames) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        // ---------------------------------------------------------------------
        
        reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reportPanel.setLayout(new GridLayout(2,1));

// panel formating
// ----------------------------------------------------------------------
        reportPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
// ----------------------------------------------------------------------


// table formatting 
// ----------------------------------------------------------------------
        tableScroll.setBorder(BorderFactory.createEmptyBorder());

        header.setDefaultRenderer(new HeaderRenderer(reportTable));
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);

        reportTable.setModel(uneditModel);

        reportTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        reportTable.getColumnModel().getColumn(1).setPreferredWidth(225);
        reportTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        reportTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        
        reportTable.setBackground(defaultColor);
        reportTable.setShowHorizontalLines(false);

// ----------------------------------------------------------------------
        JButton b1 = new JButton("1");
        reportPanel.add(tableScroll);
        reportPanel.add(b1);
        reportFrame.getContentPane().add(reportPanel);
        reportFrame.setSize(500, 500);
        reportFrame.setVisible(true);

        tableScroll.setSize(reportTable.getSize());

        int frameWidth = tableScroll.getSize().width;
        int frameHeight = tableScroll.getSize().height + b1.getSize().height + 20;
        System.out.println(frameHeight);
        reportFrame.setSize(frameWidth, frameHeight);
        
        System.out.println(tableScroll.getSize());
        System.out.println(reportTable.getSize());
        System.out.println(b1.getSize());
    }


// class for header rendering
// ----------------------------------------------------------------------
    private static class HeaderRenderer implements TableCellRenderer {
        DefaultTableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(JLabel.LEFT);
        }

        @Override
        public Component getTableCellRendererComponent( 
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                    table, value, 
                    isSelected, hasFocus, 
                    row, col);
        }
    }
// ----------------------------------------------------------------------
}
