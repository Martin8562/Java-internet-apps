import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameListApp {
    private JFrame frame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JComboBox<String> titleComboBox;
    private JList<String> nameList;
    private DefaultListModel<String> listModel;

    public NameListApp() {
        // Inicializácia komponentov
        frame = new JFrame("Name List Application");
        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        titleComboBox = new JComboBox<>(new String[]{"Mr.", "Ms.", "M.Eng.", "Ing.", "Bc.", "Doc.", "Prof.", "Dr.", "RNDr."});
        listModel = new DefaultListModel<>();
        nameList = new JList<>(listModel);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton clearButton = new JButton("Clear");

        // Nastavenie layoutu
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Title:"));
        panel.add(titleComboBox);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(clearButton);
        panel.add(new JScrollPane(nameList));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Akcia pre pridanie mena
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String title = (String) titleComboBox.getSelectedItem();
                if (!firstName.isEmpty() && !lastName.isEmpty()) {
                    String fullName = title + " " + firstName + " " + lastName;
                    listModel.addElement(fullName);
                    firstNameField.setText("");
                    lastNameField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter both first and last name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Akcia pre odstránenie vybranej položky
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = nameList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select an item to remove.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Akcia pre vymazanie celého zoznamu
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NameListApp::new);
    }
}