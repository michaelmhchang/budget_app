import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.border.*;

class ReportGraphic {
    private JFrame reportFrame;
    private JPanel reportPanel;
    private JPanel buttonPanel;
    private JButton returnToMain;
    private JButton saveToPdf;

      public static void main(String[] args) {
        ReportGraphic report = new ReportGraphic();
        report.activate();
    }

    public void activate() {
       

        Date date = new Date();
        Object[] colNames = {"Date", "Name", "Income", "Spendings"};
        Object[][] data = {
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
            {date,"Test",3,4},
        };

        reportFrame = new JFrame("Report");
        reportPanel = new JPanel();
        buttonPanel = new JPanel();

        reportPanel.setLayout(new GridBagLayout());
        buttonPanel.setLayout(new GridBagLayout());

        reportPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        MainReportTableSection mainSection = new MainReportTableSection(data, colNames);
        TotalSection totalSection = new TotalSection();
        NetTotalSection netSection = new NetTotalSection();

        JScrollPane mainPane = mainSection.createMainTable();
        JScrollPane totalPane = totalSection.createTotalTable();
        JScrollPane netTotalPane = netSection.createNetTotalTable();

        JLabel space = new JLabel(" ");
        JLabel space2 = new JLabel(" ");
        JLabel buttonSpace = new JLabel("<html><br/><br/><br/></html>");

        returnToMain = new JButton("Return to the Main Menu");
        saveToPdf = new JButton("Save to PDF");

        Border defaultButtonBorder = returnToMain.getBorder();

        saveToPdf.setEnabled(false);

        buttonPanel.add(saveToPdf, reportGbc(0,0));
        buttonPanel.add(buttonSpace, reportGbc(1,0));
        buttonPanel.add(returnToMain, reportGbc(2,0));

        reportPanel.add(mainPane, reportGbc(0,0));
        reportPanel.add(totalPane, reportGbc(0,1));
        reportPanel.add(space, reportGbc(0,2));
        reportPanel.add(netTotalPane, reportGbc(0,3));
        reportPanel.add(space2, reportGbc(0,4));
        reportPanel.add(buttonPanel, reportGbc(0,5));

        reportFrame.getContentPane().add(reportPanel);
        reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reportFrame.pack();
        reportFrame.setVisible(true);
        reportFrame.setResizable(false);

        Border defaultPaneBorder = totalPane.getBorder();

        int scrollBarWidth = mainPane.getVerticalScrollBar().getSize().width;
        netTotalPane.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(0, 0, 0, scrollBarWidth),
                defaultPaneBorder));
    }

    private GridBagConstraints reportGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = x;
        gbc.gridy = y;

        if(y == 1) {
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;
        } else if(y == 3) {
            gbc.anchor = GridBagConstraints.EAST;
            gbc.weightx = 1;
        }

        return gbc;
    }
}
