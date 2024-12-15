// Príklad 22: MultipleListenersExample.java
import java.awt.BorderLayout;
import java.awt.Color; import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; import java.awt.event.ItemEvent;
import java.awt.event.ItemListener; import java.util.Calendar;
import javax.swing.BorderFactory; import javax.swing.JButton;
import javax.swing.JCheckBox; import javax.swing.JFrame;
import javax.swing.JLabel; import javax.swing.JPanel;
import javax.swing.JSpinner; import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
public class MultipleListenersExample extends JFrame {
    private JLabel statusbar, yearLabel, monthLabel, dayLabel;
    private JSpinner yearSpinner, monthSpinner, daySpinner;
    private static int yearCounter = 0;
    private JButton increment;
    private ButtonListener1 MyButtonListener1;
    private ButtonListener2 MyButtonListener2;
    // Checkbox pre zapínanie a vypínanie obsluhy udalostí
    private JCheckBox active;
    public MultipleListenersExample() {
        setTitle("Multiple Listeners and remove Listener example");
        JPanel panel = new JPanel();
        statusbar = new JLabel("-"); // stavový riadok ako JLabel
            statusbar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        dayLabel = new JLabel("Day: "); dayLabel.setBounds(10, 10, 50, 25);
        monthLabel = new JLabel("Month: "); monthLabel.setBounds(90,10,50,25);
        yearLabel = new JLabel("Year: "); yearLabel.setBounds(180, 10, 50,25);
        panel.setLayout(null);
        increment = new JButton("Increase year");
        increment.setBounds(300, 10, 120, 25);
// Vytvoríme dve inštancie triedy ButtonListener
        MyButtonListener1 = new ButtonListener1();
        MyButtonListener2 = new ButtonListener2();
// Zaregistrujeme poslucháčov s tlačidlom
        increment.addActionListener(MyButtonListener1);
        increment.addActionListener(MyButtonListener2);
// Inicializujeme checkbox
        active = new JCheckBox("Enable button", true);
        active.setBounds(300, 30, 140, 25);
// Naprogramujeme obsluhu udalostí pre CheckBox
        active.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
// Ak je CheckBox označený potom pridáme poslucháča udalostí.
                if (active.isSelected()) {
                    increment.addActionListener(MyButtonListener1); }
                else { // v opačnom prípade odstránime poslucháča
                    increment.removeActionListener(MyButtonListener1);
                } // tlačidlo "add" bude obslúžené len druhým poslucháčom
            } });
// Vytvoríme inštanciu triedy Calendar pre zistenie aktuálneho roku
        Calendar date = Calendar.getInstance();
        int currentYear = date.get(date.YEAR);
        int currentMonth = date.get(date.MONTH);
        int currentDay = date.get(date.DAY_OF_MONTH);
// Vytvoríme dátový model pre komponent spinner
// Konštruktor modelu (SpinnerNumberModel) má štyri argumenty
// počiatočná hodnota, min. hodnota, maximálna hodnota
// a krok inkrementácie resp. dekrementácie
        SpinnerModel yearModel = new SpinnerNumberModel(currentYear,
            currentYear - 100, currentYear + 100, 1);
        SpinnerModel monthModel = new SpinnerNumberModel(currentMonth, 1, 12, 1);
        SpinnerModel dayModel = new SpinnerNumberModel(currentDay, 1, 31, 1);
// Prepojíme vizuálny komponent s jeho modelom
        yearSpinner = new JSpinner(yearModel);
// Zrušíme oddeľovač skupín čísel
        yearSpinner.setEditor(new JSpinner.NumberEditor(yearSpinner, "#"));
// Nastavíme farbu editačného okna na žltú
        ((JSpinner.NumberEditor)yearSpinner.getEditor()).getTextField().setBackground(Color
            .yellow);
        yearSpinner.setBounds(210, 10, 80, 25); // pozícia a rozmer spinnera
        monthSpinner = new JSpinner(monthModel);
        monthSpinner.setEditor(new JSpinner.NumberEditor(monthSpinner, "#"));
        monthSpinner.setBounds(130, 10, 40, 25); // pozícia a rozmer spinnera
        daySpinner = new JSpinner(dayModel);
        daySpinner.setBounds(40, 10, 40, 25); // pozícia a rozmer spinnera
        daySpinner.setEditor(new JSpinner.NumberEditor(daySpinner, "#"));
        panel.add(increment); panel.add(yearSpinner); panel.add(monthSpinner);
        panel.add(daySpinner); panel.add(dayLabel); panel.add(monthLabel);
        panel.add(yearLabel); panel.add(active);
        add(panel); add(statusbar, BorderLayout.SOUTH);
        setSize(450, 120); setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    class ButtonListener1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
// Tento button listener bude inkrementovať rok v spinneri
            Integer value = (Integer) yearSpinner.getValue();
            yearSpinner.setValue(++value);
        }
    }
    class ButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
// Tento listener bude inkrementovať počítadlo na stavovom riadku
            statusbar.setText("Button counter:"+Integer.toString(++yearCounter)+
                " Date: "+daySpinner.getValue().toString()+"."+
                monthSpinner.getValue().toString()+"."+yearSpinner.getValue().toString());
        } }
    public static void main(String[] args) {
        new MultipleListenersExample();
    }
}
