import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaskManagerApp {

    private static DefaultComboBoxModel<String> categoryModel;
    private static ArrayList<String> customCategories = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskManagerApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Task Manager App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        // Create manage categories menu
        JMenu categoryMenu = new JMenu("Categories");
        JMenuItem manageCategoriesItem = new JMenuItem("Manage Categories");
        manageCategoriesItem.addActionListener(e -> openCategoryManager(frame));
        categoryMenu.add(manageCategoriesItem);
        menuBar.add(categoryMenu);

        // Create help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("Instructions");
        aboutMenuItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "Task Manager App Instructions:\n\n" +
                            "1. To add a task, enter the task name, select a category, and set a deadline.\n" +
                            "2. Click 'Add Task' to add the task to the list.\n" +
                            "3. To mark a task as completed, select the task and click 'Complete Task'.\n" +
                            "4. To delete a task, select the task and click 'Delete Task'.\n" +
                            "5. Use the 'Manage Categories' menu to add or delete categories.\n" +
                            "6. Enjoy managing your tasks!");
        });
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu); // Add Help menu last

        frame.setJMenuBar(menuBar);

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create task list panel
        DefaultListModel<String> taskListModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        JTextField taskTextField = new JTextField(20);
        categoryModel = new DefaultComboBoxModel<>(new String[]{"School", "Work", "Personal", "Shopping"});
        JComboBox<String> categoryComboBox = new JComboBox<>(categoryModel);
        JTextField deadlineTextField = new JTextField(10);
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Complete Task");
        JButton deleteButton = new JButton("Delete Task");

        // Add task action
        addButton.addActionListener(e -> {
            String task = taskTextField.getText();
            String category = (String) categoryComboBox.getSelectedItem();
            String deadline = deadlineTextField.getText();
            if (!task.isEmpty() && category != null && !deadline.isEmpty()) {
                String formattedTask = String.format("%s [%s] (Deadline: %s)", task, category, deadline);
                taskListModel.addElement(formattedTask);
                taskTextField.setText("");
                categoryComboBox.setSelectedIndex(0); // Reset to first category
                deadlineTextField.setText("");
            }
        });

        // Complete task action
        completeButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String completedTask = taskListModel.get(selectedIndex);
                taskListModel.set(selectedIndex, completedTask + " [Completed]");
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a task to mark as completed.");
            }
        });

        // Delete task action
        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a task to delete.");
            }
        });

        // Add components to control panel
        controlPanel.add(new JLabel("Task:"));
        controlPanel.add(taskTextField);
        controlPanel.add(new JLabel("Category:"));
        controlPanel.add(categoryComboBox);
        controlPanel.add(new JLabel("Deadline (DD-MM-YYYY):"));
        controlPanel.add(deadlineTextField);
        controlPanel.add(addButton);
        controlPanel.add(completeButton);
        controlPanel.add(deleteButton);

        // Add panels to main panel
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);

        // Show frame
        frame.setVisible(true);
    }

    private static void openCategoryManager(JFrame parentFrame) {
        JDialog categoryDialog = new JDialog(parentFrame, "Manage Categories", true);
        categoryDialog.setSize(300, 200);
        categoryDialog.setLayout(new BorderLayout());

        JList<String> categoryList = new JList<>(categoryModel);
        JScrollPane scrollPane = new JScrollPane(categoryList);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JTextField newCategoryField = new JTextField(10);
        JButton addCategoryButton = new JButton("Add");
        JButton deleteCategoryButton = new JButton("Delete");

        // Add category action
        addCategoryButton.addActionListener(e -> {
            String newCategory = newCategoryField.getText();
            if (!newCategory.isEmpty() && !categoryModel.getSelectedItem().equals(newCategory)) {
                categoryModel.addElement(newCategory);
                customCategories.add(newCategory); // Save the new category
                newCategoryField.setText("");
            }
        });

        // Delete category action
        deleteCategoryButton.addActionListener(e -> {
            int selectedIndex = categoryList.getSelectedIndex();
            if (selectedIndex != -1) {
                String category = categoryModel.getElementAt(selectedIndex);
                if (!category.matches("School|Work|Personal|Shopping")) {
                    categoryModel.removeElementAt(selectedIndex);
                    customCategories.remove(category); // Remove from saved custom categories
                } else {
                    JOptionPane.showMessageDialog(categoryDialog, "Cannot delete default categories.");
                }
            }
        });

        buttonPanel.add(new JLabel("New Category:"));
        buttonPanel.add(newCategoryField);
        buttonPanel.add(addCategoryButton);
        buttonPanel.add(deleteCategoryButton);

        categoryDialog.add(scrollPane, BorderLayout.CENTER);
        categoryDialog.add(buttonPanel, BorderLayout.SOUTH);
        categoryDialog.setVisible(true);
    }
}
