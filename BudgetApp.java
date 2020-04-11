import java.util.*;
import javax.swing.*;
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
    private JButton report;

    // Submenu
    private static JTextField nameField;
    private static JTextField amountField;

    // Calculating final report
    private static ArrayList<Expense> expenseList;


    public BudgetApp() {
        expenseList = new ArrayList<Expense>();
    }

    public static void main(String[] args) {
        BudgetApp testApp = new BudgetApp();
        testApp.activateMenu();
    }

// --------------------------------------- ACTIVATE GRAPHICS ---------------------------------------  
    private void activateMenu() {
        // Main menu UI

        frame = new JFrame("Budget app v1.0.0");
        mainPanel = new JPanel();
        title = new JLabel("<html>Mo money <br/>" + " mo problems <html>");
        addIncome = new JButton("Add income");
        addSpending = new JButton("Add spending");
        report = new JButton("Calculate report");

        mainPanel.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title.setFont(new Font("Alex Brush", Font.BOLD, 40));

        mainPanel.add(title, createMainGbc(0,0));
        mainPanel.add(addIncome, createMainGbc(1,1));
        mainPanel.add(addSpending, createMainGbc(1,2));
        mainPanel.add(report, createMainGbc(0,3));

        addIncome.addActionListener(new AddIncomeListener());
        addSpending.addActionListener(new AddSpendingListener());
        report.addActionListener(new ReportListener());

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 400);
        frame.setVisible(true);

        addIncome.setPreferredSize(addSpending.getPreferredSize());
    }


    private void reactivateMenu() {
        // For going back to the main menu again

        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        title = new JLabel("<html>Mo money <br/>" + " mo problems <html>");
        addIncome = new JButton("Add income");
        addSpending = new JButton("Add spending");
        report = new JButton("Calculate report");

        mainPanel.setLayout(new GridBagLayout());

        title.setFont(new Font("Alex Brush", Font.BOLD, 30));

        mainPanel.add(title, createMainGbc(0,0));
        mainPanel.add(addIncome, createMainGbc(1,1));
        mainPanel.add(addSpending, createMainGbc(1,2));
        mainPanel.add(report, createMainGbc(0,3));

        addIncome.addActionListener(new AddIncomeListener());
        addSpending.addActionListener(new AddSpendingListener());
        report.addActionListener(new ReportListener());

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
            mainPanel.add(name, createSubGbc(0,0));
            mainPanel.add(nameField, createSubGbc(1,0));
            mainPanel.add(amount, createSubGbc(0,1));
            mainPanel.add(amountField, createSubGbc(1,1));
            mainPanel.add(saveButton, createSubGbc(0,2));
            mainPanel.add(cancelButton, createSubGbc(1,2));

            cancelButton.setPreferredSize(saveButton.getPreferredSize());

            nameField.setPreferredSize(cancelButton.getPreferredSize());
            amountField.setPreferredSize(cancelButton.getPreferredSize());

            saveButton.addActionListener(new SaveListener(type));
            cancelButton.addActionListener(new CancelButtonListener());
        }

    }

// ----------------------------------------------------------------------------------------------------  


// --------------------------------------- GRID BAG CONSTRAINTS ---------------------------------------  
    private GridBagConstraints createMainGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        final Insets SPACING = new Insets(0, 5, 5 ,5);

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = SPACING; 
        gbc.fill = GridBagConstraints.NONE;

        return gbc;
    }

    private GridBagConstraints createSubGbc(int x, int y) {
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

// ------------------------------------------------------------------------------------------------  


// --------------------------------------- ACTION LISTENERS ---------------------------------------  

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
            expenseList.add(new Expense(name, "Income", amount));

            nameField.setText("");
            amountField.setText("");
        }
    }

    
    // Need to continue updating (used for testing for now)
    class ReportListener implements ActionListener {
        private JFrame reportFrame;
        private JPanel reportPanel;
        private JTable reportTable;
        private JScrollPane scrollReport;

        public void actionPerformed(ActionEvent ev) {
            reportFrame =  new JFrame("Report");
            reportPanel = new JPanel();

            String[] colNames = {"Date", "Name", "Income", "Spendings"};
            Object[][] data = {{1,2,3,4}};
            
            reportTable = new JTable(data, colNames);
            scrollReport = new JScrollPane(reportTable);

            reportPanel.add(scrollReport);

            reportFrame.getContentPane().add(BorderLayout.CENTER, reportPanel);
            reportFrame.pack();
            reportFrame.setVisible(true);
            
        }
    }
    

    class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            reactivateMenu();
        }
    }

// ------------------------------------------------------------------------------------------------  
}
