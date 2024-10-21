
import java.awt.event.ActionEvent; import java.awt.event.ActionListener;
import javax.swing.JButton; import javax.swing.ImageIcon;
import javax.swing.JFrame; import javax.swing.JPanel;
public class SimpleEvent extends JFrame {
    public SimpleEvent() {
        setTitle("Simple Event");
        JPanel panel = new JPanel(); panel.setLayout(null);
// Tlačidlo je zdroj udalosti tzn. bude generovať udalosti
        JButton exitButton = new JButton("Exit",new ImageIcon("exit.png"));
        exitButton.setBounds(100, 100, 200, 130); // pozícia a rozmer tlačidla
// Tu zaregistrujeme poslucháča udalosti s tlačidlom.
// Týmto spôsobom sú udalosti posielané do cieľa udalostí.
// Cieľ udalosti je v našom prípade trieda ActionListener.
// V tomto kóde použijeme anonymnú vnútornú triedu.
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0); // po kliknutí nastane ukončenie aplikácie
            }
        });
        panel.add(exitButton); add(panel); setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); setVisible(true);
    }
    public static void main(String[] args) {
        new SimpleEvent();
    }
}
