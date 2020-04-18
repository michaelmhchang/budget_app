import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;

class ReportGraphic {
    private JFrame reportFrame;
    private JPanel reportPanel;

    // Main Table 
    // ----------------------------------------------------------------------
    private JTable reportTable;
    private UneditModel reportModel;
    private JTableHeader header;
    private JScrollPane tableScroll;
    // ----------------------------------------------------------------------

    // Income/Spending Total
    // ----------------------------------------------------------------------
    private JTable totalTable;
    private UneditModel totalModel;
    private JScrollPane totalScroll;
    // ----------------------------------------------------------------------

    // Net Total
    // ----------------------------------------------------------------------
    private JTable netTable;
    private JScrollPane netScroll;
    // ----------------------------------------------------------------------


    JButton b1;
    JButton b2;


    public static void main(String[] args) {
        ReportGraphic report = new ReportGraphic();
        report.activate();
    }

    public void activate() {
        reportFrame = new JFrame("Report");

        Color defaultColor = new Color(238,238,238);

        // Panel Formatting
        // ----------------------------------------------------------------------
        reportPanel = new JPanel();
        reportPanel.setLayout(new GridBagLayout());
        reportPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        // ----------------------------------------------------------------------


        // Table Formatting 
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
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8},
            {5,6,7,8}};

        reportTable = new JTable();
        header = reportTable.getTableHeader();
        tableScroll = new JScrollPane(reportTable);
        DefaultTableModel reportModel = new DefaultTableModel(data, colNames) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        reportTable.setModel(reportModel);

        header.setDefaultRenderer(new HeaderRenderer(reportTable));
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);


        reportTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        reportTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        reportTable.getColumnModel().getColumn(2).setPreferredWidth(90);
        reportTable.getColumnModel().getColumn(3).setPreferredWidth(90);
        
        reportTable.setShowHorizontalLines(false);
        
        if(reportTable.getPreferredSize().height <= 240) {
            reportTable.setPreferredScrollableViewportSize(reportTable.getPreferredSize());
        } else {
            reportTable.setPreferredScrollableViewportSize(
                    new Dimension(reportTable.getPreferredSize().width, 240));
        }
        // ----------------------------------------------------------------------

        // Income/Spending Total Section
        // ----------------------------------------------------------------------
        Object[] totalName = {"Total", "Income Total", "Spending total"};
        Object[][] totalData = {{"Income/Spending Total: ", 1000, 200}};

        totalTable = new JTable();
        totalModel = new UneditModel(totalData, totalName);
        totalTable.setRowSelectionAllowed(false);
        totalTable.setTableHeader(null);

        totalTable.setModel(totalModel);
        CellRenderer totalRenderer = new CellRenderer();
        totalRenderer.setHorizontalAlignment(JLabel.RIGHT);
        totalTable.getColumnModel().getColumn(0).setCellRenderer(totalRenderer);

        totalTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        totalTable.getColumnModel().getColumn(1).setPreferredWidth(90);
        totalTable.getColumnModel().getColumn(2).setPreferredWidth(90);

        totalTable.setBackground(defaultColor);

        totalTable.setPreferredScrollableViewportSize(totalTable.getPreferredSize());

        totalScroll = new JScrollPane(totalTable);
        // ----------------------------------------------------------------------
        
        // Net Income Section 
        // ----------------------------------------------------------------------
        Object[] netName = {"Net Total", "Total"};
        Object[][] netTotal = {{"Net Total: ", 10000}};

        netTable = new JTable(netTotal, netName);
        
        netTable.setTableHeader(null);
        netTable.setShowVerticalLines(false);
        netTable.setPreferredScrollableViewportSize(netTable.getPreferredSize());

        CellRenderer netRenderer = new CellRenderer();
        netTable.getColumnModel().getColumn(0).setCellRenderer(netRenderer);

        netScroll = new JScrollPane(netTable);
        // ----------------------------------------------------------------------


        b1 = new JButton("before");
        b2 = new JButton("after");
        JLabel test = new JLabel(" ");

        b1.addActionListener(new B1Listener());
        
        reportPanel.add(tableScroll, reportGbc(0,0));
        reportPanel.add(totalScroll, reportGbc(0,1));
        reportPanel.add(test, reportGbc(0,2));
        reportPanel.add(netScroll, reportGbc(0,3));
        reportPanel.add(b1, reportGbc(0,4));
        reportPanel.add(b2, reportGbc(0,5));
        
        reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reportFrame.getContentPane().add(reportPanel);
        reportFrame.pack();
        reportFrame.setVisible(true);
        reportFrame.setResizable(false);

        b2.addActionListener(new B2Listener());
        
    }

    // GridBagConstraint
    // ----------------------------------------------------------------------
    private GridBagConstraints reportGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
         
        gbc.gridx = x;
        gbc.gridy = y;
        
        if(y == 0 || y == 1) {
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
        }else if(y == 3) {
            gbc.anchor = GridBagConstraints.EAST;
            gbc.weightx = 1;
        }
            
        return gbc;
    }
    // ----------------------------------------------------------------------


// HEADER RENDERING
    // Header renderer (Aligning left)
    // ----------------------------------------------------------------------
    private class HeaderRenderer implements TableCellRenderer {
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
    
    // Cell renderer (Bold)
    // ----------------------------------------------------------------------
    public class CellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(
                JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int col) {
            Component renderer = super.getTableCellRendererComponent(
                    table, value,
                    isSelected, hasFocus,
                    row, col);

            if(value == "Income/Spending Total: ") {
                renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
            } else if(value == "Net Total: ") {
                renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
            }

            return renderer;
        }
        
    }
    // ----------------------------------------------------------------------


    // Table Model (No editting)
    // ----------------------------------------------------------------------
    private class UneditModel extends DefaultTableModel {

        public UneditModel(Object[][] data, Object[] col) {
            super(data, col);
        }
            
        @Override
        public boolean isCellEditable(int row, int col) {
                return false;
        }
    }
    // ----------------------------------------------------------------------

    public class B1Listener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            System.out.println("------------------------------------------------");
            System.out.println(tableScroll.getPreferredSize());
            System.out.println(reportTable.getPreferredSize());
            System.out.println(reportPanel.getPreferredSize());
            System.out.println(reportFrame.getPreferredSize());
            System.out.println("------------------------------------------------");
            System.out.println(reportPanel.getSize());
            System.out.println(reportFrame.getSize());
            
        }
    }

    public class B2Listener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        }
                    
    }

}
