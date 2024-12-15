// Príklad 19: InnerClass.java
import java.awt.event.ActionEvent; import java.awt.event.ActionListener;
import javax.swing.JButton; import javax.swing.ImageIcon;
import javax.swing.JFrame; import javax.swing.JPanel;
public class InnerClass extends JFrame {
    public InnerClass() {
        setTitle("Using inner class");
        JPanel panel = new JPanel(); panel.setLayout(null);
        JButton exitButton = new JButton("Exit"
            ,new ImageIcon("exit.png"));
        exitButton.setBounds(50, 50, 100, 30);
// Vytvoríme inštanciu neanonymnej vnútornej triedy.
        ButtonListener listener = new ButtonListener();
// Prepojíme poslucháča udalosti s tlačidlom
        exitButton.addActionListener(listener);
        panel.add(exitButton); add(panel); setSize(200, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); setVisible(true);
    }
    // Vytvoríme vnútornú triedu, ktorá bude implementovať ActionListener
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
// nová definícia metódy actionPerformed() z triedy ActionListener
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        new InnerClass();
    }
}
