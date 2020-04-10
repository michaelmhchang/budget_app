import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class BudgetApp {
    // Main Menu
    private GridBagConstraints gbc;
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


    public void activateMenu() {
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


    public void reactivateMenu() {
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


    class AddIncomeListener implements ActionListener {
        private JButton cancelButton;
        private JButton saveButton;
        private JLabel name;
        private JLabel amount;
       

        public void actionPerformed(ActionEvent ev) {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();

            mainPanel.setLayout(new GridBagLayout());

            name = new JLabel("Income Label: ");
            nameField = new JTextField(10);
            amount = new JLabel("<html>Amount of money <br/>in da bank!  </html>");
            amountField = new JTextField(10);
            saveButton = new JButton("Report income");
            cancelButton = new JButton("Return to main menu");

            mainPanel.setBorder(BorderFactory.createTitledBorder("Income"));
            mainPanel.add(name, createSubGbc(0,0));
            mainPanel.add(nameField, createSubGbc(1,0));
            mainPanel.add(amount, createSubGbc(0,1));
            mainPanel.add(amountField, createSubGbc(1,1));
            mainPanel.add(saveButton, createSubGbc(0,2));
            mainPanel.add(cancelButton, createSubGbc(1,2));

            saveButton.addActionListener(new SaveIncomeActionListener());
            
            frame.setSize(500, 300);

            cancelButton.addActionListener(new CancelButtonListener());
        }


        class SaveIncomeActionListener implements ActionListener {
            private Expense income;
            private String name;
            private double amount;

            public void actionPerformed(ActionEvent ev) {
                name = nameField.getText();
                amount = Double.parseDouble(amountField.getText()); 
                
                income = new Expense(name, "Income", amount);

                expenseList.add(income);

                nameField.setText("");
                amountField.setText("");
            }
        }
    }


    class AddSpendingListener implements ActionListener {
        private JButton cancelButton;
        private JButton saveButton;
        private JLabel name;
        private JLabel amount;

        public void actionPerformed(ActionEvent ev) {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            name = new JLabel("Spending label");
            nameField = new JTextField(10);
            amount = new JLabel("<html>Amount you <br/>wasting!!!!</html>");
            amountField = new JTextField(10);
            saveButton = new JButton("Report income");
            cancelButton = new JButton("Return to main menu");

            mainPanel.setBorder(BorderFactory.createTitledBorder("Spending"));
            mainPanel.add(name, createSubGbc(0,0));
            mainPanel.add(nameField, createSubGbc(1,0));
            mainPanel.add(amount, createSubGbc(0,1));
            mainPanel.add(amountField, createSubGbc(1,1));
            mainPanel.add(saveButton, createSubGbc(0,2));
            mainPanel.add(cancelButton, createSubGbc(1,2));

            saveButton.addActionListener(new SaveSpendingActionListener());
            
            frame.setSize(500, 300);

            cancelButton.addActionListener(new CancelButtonListener());
        }

        class SaveSpendingActionListener implements ActionListener {
            private Expense income;
            private String name;
            private double amount;

            public void actionPerformed(ActionEvent ev) {
                name = nameField.getText();
                amount = Double.parseDouble(amountField.getText()) * -1; 
                
                income = new Expense(name, "Income", amount);

                expenseList.add(income);

                nameField.setText("");
                amountField.setText("");
            }
        }
    }

    
    // Need to continue updating (used for testing for now)
    class ReportListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            for(Expense ex: expenseList) {
                System.out.print(ex.getName());
                System.out.println(" " + ex.getAmount());
            }
        }
    }


    class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            reactivateMenu();
        }
    }
}
