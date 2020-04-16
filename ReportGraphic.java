import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;

class ReportGraphic {
    private JFrame reportFrame;
    private JPanel reportPanel;

    // Main table 
    // ----------------------------------------------------------------------
    private JTable reportTable;
    private UneditModel reportModel;
    private JTableHeader header;
    private JScrollPane tableScroll;
    // ----------------------------------------------------------------------

    private JTable totalTable;
    private UneditModel totalModel;
    private JScrollPane totalScroll;
    private DefaultTableCellRenderer rightRender;

    JButton b1;
    JButton b2;


    public static void main(String[] args) {
        ReportGraphic report = new ReportGraphic();
        report.activate();
    }

    public void activate() {
        reportFrame = new JFrame("Report");

        Color defaultColor = new Color(238,238,238);
        // panel formating
        // ----------------------------------------------------------------------
        reportPanel = new JPanel();
        reportPanel.setLayout(new GridBagLayout());
        reportPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        // ----------------------------------------------------------------------


        // table formatting 
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
        reportModel = new UneditModel(data, colNames);

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

        // Income/spending total section
        // ----------------------------------------------------------------------
        Object[] totalName = {"Total", "Income Total", "Spending total"};
        Object[][] totalData = {{"Income/Spending Total: ", 1000, 200}};
        
        rightRender = new DefaultTableCellRenderer();
        rightRender.setHorizontalAlignment(SwingConstants.RIGHT);

        totalTable = new JTable();
        totalModel = new UneditModel(totalData, totalName);
        totalTable.setRowSelectionAllowed(false);
        totalTable.setTableHeader(null);

        totalTable.setModel(totalModel);
        
        totalTable.getColumnModel().getColumn(0).setCellRenderer(rightRender);

        totalTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        totalTable.getColumnModel().getColumn(1).setPreferredWidth(90);
        totalTable.getColumnModel().getColumn(2).setPreferredWidth(90);

        totalTable.setBackground(defaultColor);

        totalTable.setPreferredScrollableViewportSize(totalTable.getPreferredSize());

        totalScroll = new JScrollPane(totalTable);
        // ----------------------------------------------------------------------
        
        b1 = new JButton("before");
        b2 = new JButton("after");
        JLabel test = new JLabel(" ");

        b1.addActionListener(new B1Listener());
        
        reportPanel.add(tableScroll, reportGbc(0,0));
        reportPanel.add(totalScroll, reportGbc(0,1));
        reportPanel.add(test, reportGbc(0,2));
        reportPanel.add(b1, reportGbc(0,3));
        reportPanel.add(b2, reportGbc(0,4));
        
        reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reportFrame.getContentPane().add(reportPanel);
        reportFrame.pack();
        reportFrame.setVisible(true);
        reportFrame.setResizable(false);

        System.out.println(reportPanel.getBackground());
        b2.addActionListener(new B2Listener());
        
    }

    // Gridbagconstraint
    // ----------------------------------------------------------------------
    private GridBagConstraints reportGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
         
        gbc.gridx = x;
        gbc.gridy = y;
        
        if(y == 0 || y == 1) {
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
        }
            
        return gbc;
    }
    // ----------------------------------------------------------------------


    // overriding header rendering
    // ----------------------------------------------------------------------
    private static class HeaderRenderer implements TableCellRenderer {
        DefaultTableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(SwingConstants.LEFT);
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


    // Overriding table model
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
