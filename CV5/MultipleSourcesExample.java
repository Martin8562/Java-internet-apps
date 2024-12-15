// Príklad 21: MultipleSourcesExample.java
import java.awt.BorderLayout; import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; import javax.swing.BorderFactory;
import javax.swing.JButton; import javax.swing.JFrame;
import javax.swing.JLabel; import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
public class MultipleSourcesExample extends JFrame {
    JLabel statusbar;
    public MultipleSourcesExample() { // definícia konštruktora
        setTitle("Multiple Sources Example");
        JPanel panel = new JPanel();
// Stavový riadok vytvoríme ako inštanciu JLabel
        statusbar = new JLabel("Button code");
// Stavový riadok umiestnime v dolnej časti okna
        statusbar.setBorder(BorderFactory.createEtchedBorder(
            EtchedBorder.RAISED));
// tlačidla umiestnime na absolútnych súradniciach v okne
        panel.setLayout(null);
        JButton cut = new JButton("Cut");
        cut.setBounds(30, 30, 80, 25);
        cut.addActionListener(new ButtonListener());
// Každé tlačidlo bude registrované s triedou ButtonListener
        JButton copy = new JButton("Copy");
        copy.setBounds(120, 30, 80, 25);
        copy.addActionListener(new ButtonListener());
        JButton paste = new JButton("Paste");
        paste.setBounds(210, 30, 80, 25);
        paste.addActionListener(new ButtonListener());
        panel.add(cut); panel.add(copy); panel.add(paste);
        add(panel); // Panel pridáme na rám
        add(statusbar, BorderLayout.SOUTH);
        setSize(350, 150); setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); setVisible(true);
    }
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
// Tu zistíme, ktoré tlačidlo bolo stlačené
            JButton tempButton = (JButton) e.getSource(); // pretypovanie
            String buttonText = tempButton.getText();
// Aktualizujeme stavový riadok
            statusbar.setText(buttonText + " button pressed");
        }
    }
    public static void main(String[] args) {
        new MultipleSourcesExample();
    }
}
