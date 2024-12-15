package CV6;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon; 
import javax.swing.JButton; 
import javax.swing.JFileChooser; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JTextArea; 
import javax.swing.JToolBar;

public class FileChooserDialogExample extends JFrame {
	private JPanel panel; 
	private JTextArea textArea; 
	public FileChooserDialogExample() {
		setTitle("File Chooser Dialog Example"); 
		panel = new JPanel(); 
		panel.setLayout(new BorderLayout()); 
		ImageIcon openIcon = new ImageIcon("open.png"); 
		ImageIcon exitIcon = new ImageIcon("exit.png");
		ImageIcon saveIcon = new ImageIcon("save.png");
		JToolBar toolbar = new JToolBar(); 
		JButton openButton = new JButton(openIcon); 
		JButton saveButton = new JButton(saveIcon);
		JButton exitButton = new JButton(exitIcon); 
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) { 
				// Toto je kon�truktor dial�gov�ho okna. 
				JFileChooser fileChooser = new JFileChooser(); 
				// Zadefiujeme filter pre s�bory (*.txt a *.java) 
				// K dispoz�cii m�me tie� �tandardn� filter (*.*) 
		//		FileFilter fileFilter = new FileNameExtensionFilter(".txt and .java files", "txt", "java"); 
		//		fileChooser.addChoosableFileFilter(fileFilter); 
				// Zobraz�me dial�gov� okno pre v�ber s�boru. 
				// Po kliknut� na tla�idlo "Open" n�m met�da vr�ti kon�tantu 
				// JFileChooser.APPROVE_OPTION potvrdzuj�cu zvolenie s�boru 
				int returnCode = fileChooser.showDialog(panel, "Open File"); 
				// N�sledne z�skame meno s�boru z in�tancie File 
				// Potom m��eme pre��ta� obsah s�boru ako text, 
				// ktor� umiestnime do objektu typu JTextArea. 
				if (returnCode == JFileChooser.APPROVE_OPTION) { 
					File selectedFile = fileChooser.getSelectedFile(); 
					// Na��tanie riadkov zo s�boru 
					String text = null; 
					StringBuffer stringBuffer = null; 
					String fileData = null; 
					String line = null;
					try { 
						FileReader fileReader = new FileReader(selectedFile); 
						BufferedReader bufferedReader = new BufferedReader(fileReader); 
						stringBuffer = new StringBuffer() ; 
						// z�sobn�k re�azcov 
						while ((line = bufferedReader.readLine()) != null) { 
							// v cykle na��tavame riadky zo s�boru a vklad�me do buffera 
							stringBuffer.append(line + System.getProperty("line.separator")); 
						} 
						fileReader.close(); 
						text = stringBuffer.toString();
					} 
					catch (IOException e) { 
						/* o�etrenie v�nimky */ 
					} 
					textArea.setText(text); // aktualizujeme text v textArea 
					} 
				} 
			});
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {	
				  JFrame parentFrame = new JFrame();
				  JFileChooser fileChooser = new JFileChooser(); 
				  fileChooser.setDialogTitle("Specify a file to save");
				  int userSelection = fileChooser.showSaveDialog(parentFrame); 
				  if (userSelection == JFileChooser.APPROVE_OPTION) { 
					  File fileToSave = fileChooser.getSelectedFile(); 
					  System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			          BufferedWriter outFile;
					try {
						outFile = new BufferedWriter(new FileWriter(fileToSave));
				        outFile.write(textArea.getText()); 
				        outFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

			        }
			}
			});
		toolbar.add(openButton);
		toolbar.add(saveButton);
		toolbar.add(exitButton); 
		textArea = new JTextArea(); 
		textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		JScrollPane scrollPane = new JScrollPane(); 
		scrollPane.getViewport().add(textArea); 
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		panel.add(scrollPane); 
		add(panel); 
		add(toolbar, BorderLayout.NORTH); 
		setSize(400, 400); 
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true); 
		} 
	public static void main(String[] args) { 
		new FileChooserDialogExample(); 
		} 
	}
						