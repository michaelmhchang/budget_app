import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;

class ReportGraphic {
    private JFrame reportFrame;
    private JPanel reportPanel;
    private JTable reportTable;
    private uneditModel reportModel;
    private JTableHeader header;
    JButton b1;
    JScrollPane tableScroll;

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
        tableScroll = new JScrollPane(reportTable);
        reportModel = new uneditModel(data, colNames);
        // ---------------------------------------------------------------------
        
        reportPanel.setLayout(new GridBagLayout());
        // reportPanel.setLayout(new GridLayout(2,1));

        // panel formating
        // ----------------------------------------------------------------------
        reportPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // ----------------------------------------------------------------------


        // table formatting 
        // ----------------------------------------------------------------------
        header.setDefaultRenderer(new HeaderRenderer(reportTable));
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);

        reportTable.setModel(reportModel);

        reportTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        reportTable.getColumnModel().getColumn(1).setPreferredWidth(225);
        reportTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        reportTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        
        reportTable.setShowHorizontalLines(false);
        
        if(reportTable.getPreferredSize().height <= 240) {
            reportTable.setPreferredScrollableViewportSize(reportTable.getPreferredSize());
        } else {
            reportTable.setPreferredScrollableViewportSize(
                    new Dimension(reportTable.getPreferredSize().width, 240));
        }
        // ----------------------------------------------------------------------
        
        b1 = new JButton("test");

        b1.addActionListener(new B1Listener());

        reportPanel.add(tableScroll, reportGbc(0,0));
        reportPanel.add(b1, reportGbc(0,1));
       
        reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reportFrame.getContentPane().add(reportPanel);
        reportFrame.setVisible(true);
        reportFrame.setSize(600, reportFrame.getPreferredSize().height);
    }

    // Gridbagconstraint
    // ----------------------------------------------------------------------
    private GridBagConstraints reportGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
         
        gbc.gridx = x;
        gbc.gridy = y;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
            
        return gbc;
    }
    // ----------------------------------------------------------------------


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


    // Overriding table model
    // ----------------------------------------------------------------------
    private class uneditModel extends DefaultTableModel {

        public uneditModel(Object[][] data, Object[] col) {
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
            System.out.println(reportFrame.getPreferredSize());
            System.out.println(reportFrame.getSize());
        }
    }

}
