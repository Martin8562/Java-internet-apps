package CV6;// SimpleDialogExample.java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
public class SimpleDialogExample extends JFrame { public SimpleDialogExample() {
    setTitle("Simple Dialog Example");
    JMenuBar menubar = new JMenuBar();
    JMenu menuFile = new JMenu("File");
    JMenu menuHelp = new JMenu("Help");
    JMenuItem aboutItem = new JMenuItem("About");
    menuHelp.add(aboutItem);
    aboutItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
      // Vytvor�me in�tanciu dial�gov�ho okna a zobraz�me ho.
        // AboutBox aboutDialog = new AboutBox();
       // aboutDialog.setVisible(true);
    } });
    menubar.add(menuFile);
    menubar.add(menuHelp);
    setJMenuBar(menubar);
    setSize(300, 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
}
public static void main(String[] args) {
new SimpleDialogExample();
} }