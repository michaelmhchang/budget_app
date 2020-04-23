import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class BudgetApp {
    // Main Menu
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel title;
    private JTextField incomeInput;
    private JTextField spendingInput;
    private JButton addIncome;
    private JButton addSpending;
    private JButton reportButton;

    // Submenu
    private  JTextField nameField;
    private  JTextField amountField;

    // Calculating final report
    private  ArrayList<Expense> expenseList;


    public BudgetApp() {
        expenseList = new ArrayList<Expense>();
    }

    public static void main(String[] args) {
        BudgetApp testApp = new BudgetApp();
        testApp.activateMenu();
    }


    // ACTIVATE GRAPHICS
    // -----------------------------------------------------------------------------------------
    private void activateMenu() {
        // Main menu UI

        frame = new JFrame("Budget app v1.0");
        mainPanel = new JPanel();
        title = new JLabel("<html>Mo money <br/>" + " mo problems <html>");
        addIncome = new JButton("Add income");
        addSpending = new JButton("Add spending");
        reportButton = new JButton("Calculate report");
        
        mainPanel.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title.setFont(new Font("Alex Brush", Font.BOLD, 40));

        mainPanel.add(title, maingGbc(0,0));
        mainPanel.add(addIncome, maingGbc(1,1));
        mainPanel.add(addSpending, maingGbc(1,2));
        mainPanel.add(reportButton, maingGbc(0,3));

        addIncome.addActionListener(new AddIncomeListener());
        addSpending.addActionListener(new AddSpendingListener());
        reportButton.addActionListener(new ReportListener());

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setResizable(false);

        addIncome.setPreferredSize(addSpending.getPreferredSize());
    }


    public void reactivateMenu() {
        // For going back to the main menu again
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();

        title = new JLabel("<html>Mo money <br/>" + " mo problems <html>");
        addIncome = new JButton("Add income");
        addSpending = new JButton("Add spending");
        reportButton = new JButton("Calculate report");

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        title.setFont(new Font("Alex Brush", Font.BOLD, 30));

        mainPanel.add(title, maingGbc(0,0));
        mainPanel.add(addIncome, maingGbc(1,1));
        mainPanel.add(addSpending, maingGbc(1,2));
        mainPanel.add(reportButton, maingGbc(0,3));

        addIncome.addActionListener(new AddIncomeListener());
        addSpending.addActionListener(new AddSpendingListener());
        reportButton.addActionListener(new ReportListener());

        addIncome.setPreferredSize(addSpending.getPreferredSize());

        frame.setSize(400, 400);
    }

    
    class SubMenu {
        private JLabel name;
        private JLabel amount;
        private JButton saveButton;
        private JButton cancelButton;

        public SubMenu(String name, String amount, String saveButton) {
            this.name = new JLabel(name);
            nameField = new JTextField();
            this.amount = new JLabel(amount);
            amountField = new JTextField();
            this.saveButton = new JButton(saveButton);
            cancelButton = new JButton("Cancel");
        }

        public void setup(String type) {
            mainPanel.setLayout(new GridBagLayout());

            mainPanel.setBorder(BorderFactory.createTitledBorder(type));
            mainPanel.add(name, subGbc(0,0));
            mainPanel.add(nameField, subGbc(1,0));
            mainPanel.add(amount, subGbc(0,1));
            mainPanel.add(amountField, subGbc(1,1));
            mainPanel.add(saveButton, subGbc(0,2));
            mainPanel.add(cancelButton, subGbc(1,2));

            cancelButton.setPreferredSize(saveButton.getPreferredSize());

            nameField.setPreferredSize(cancelButton.getPreferredSize());
            amountField.setPreferredSize(cancelButton.getPreferredSize());

            saveButton.addActionListener(new SaveListener(type));
            cancelButton.addActionListener(new CancelButtonListener());
        }

    }


    class ReportGraphic {
        private JPanel buttonPanel;
        private JButton returnToMainButton;
        private JButton saveToPdfButton;
    
        public void createReportGraphic() {
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
            };
    
            buttonPanel = new JPanel();
    
            mainPanel.setLayout(new GridBagLayout());
            buttonPanel.setLayout(new GridBagLayout());
    
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    
            MainReportTableSection mainSection = new MainReportTableSection(expenseList);
            TotalSection totalSection = new TotalSection(expenseList);
            NetTotalSection netSection = new NetTotalSection(expenseList);
    
            JScrollPane mainPane = mainSection.createMainTable();
            JScrollPane totalPane = totalSection.createTotalTable();
            JScrollPane netTotalPane = netSection.createNetTotalTable();
    
            JLabel space = new JLabel(" ");
            JLabel space2 = new JLabel(" ");
            JLabel buttonSpace = new JLabel("<html><br/><br/><br/></html>");
    
            returnToMainButton = new JButton("Return to the Main Menu");
            saveToPdfButton = new JButton("Save to PDF");
    
            Border defaultButtonBorder = returnToMainButton.getBorder();
    
            returnToMainButton.addActionListener(new CancelButtonListener());
   
            saveToPdfButton.setEnabled(false);
    
            buttonPanel.add(saveToPdfButton, reportGbc(0,0));
            buttonPanel.add(buttonSpace, reportGbc(1,0));
            buttonPanel.add(returnToMainButton, reportGbc(2,0));
    
            mainPanel.add(mainPane, reportGbc(0,0));
            mainPanel.add(totalPane, reportGbc(0,1));
            mainPanel.add(space, reportGbc(0,2));
            mainPanel.add(netTotalPane, reportGbc(0,3));
            mainPanel.add(space2, reportGbc(0,4));
            mainPanel.add(buttonPanel, reportGbc(0,5));
    
            frame.pack();
    
            Border defaultPaneBorder = totalPane.getBorder();
    
            int scrollBarWidth = mainPane.getVerticalScrollBar().getSize().width;
            netTotalPane.setBorder(BorderFactory.createCompoundBorder(
                    new EmptyBorder(0, 0, 0, scrollBarWidth),
                    defaultPaneBorder));
        }
    }
    // -----------------------------------------------------------------------------------------

    // GridBagConstraints 
    // -----------------------------------------------------------------------------------------

    private GridBagConstraints maingGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        final Insets SPACING = new Insets(0, 5, 5 ,5);

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = SPACING; 
        gbc.fill = GridBagConstraints.NONE;

        return gbc;
    }

    private GridBagConstraints subGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        final Insets SPACING = new Insets(0, 5, 5 ,5);

        gbc.gridx = x;
        gbc.gridy = y;

        if(y == 0 || y == 1) {
            gbc.anchor = GridBagConstraints.WEST;
        }

        gbc.insets = SPACING;

        return gbc;
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

    // -----------------------------------------------------------------------------------------


    // ACTION LISTENERS
    // -----------------------------------------------------------------------------------------

    class AddIncomeListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            frame.setSize(500, 300);

            SubMenu incomeMenu = new SubMenu(
                    "Income Label",
                    "<html>Amount of money <br/>in da bank!  </html>",
                    "Report income"); 

            incomeMenu.setup("Income");
        }
    }


    class AddSpendingListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            frame.setSize(500, 300);

            SubMenu spendingMenu = new SubMenu(
                    "Spending label",
                    "<html>Amount you <br/>wasting!!!!</html>",
                    "Report spending");

            spendingMenu.setup("Spending");
        }
    }


    class SaveListener implements ActionListener {
        private String name;
        private double amount;
        private String type;

        public SaveListener(String type) {
            this.type = type;
        }

        public void actionPerformed(ActionEvent ev) {
            name = nameField.getText();
            amount = Double.parseDouble(amountField.getText());
            expenseList.add(new Expense(name, type, amount));

            nameField.setText("");
            amountField.setText("");
        }
    }

    
    // Need to continue updating (used for testing for now)
    class ReportListener implements ActionListener {
        private ReportGraphic report;

        public void actionPerformed(ActionEvent ev) {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            report = new ReportGraphic();
            report.createReportGraphic();
        }
    }

    class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            reactivateMenu();
        }
    }

    // -----------------------------------------------------------------------------------------
}
