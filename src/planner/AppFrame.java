package planner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JTable;
import java.awt.TextArea;
import javax.swing.JEditorPane;

/*
 * The main frame for the planner application.
 */

public class AppFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame window = new AppFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 204));
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("Planner");
		titleLabel.setBounds(30, 30, 78, 33);
		frame.getContentPane().add(titleLabel);
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setForeground(new Color(255, 102, 153));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

		// Brings up a new frame to ask for inputs
		JButton AddEventButton = new JButton("Add Event");
		AddEventButton.setBounds(260, 30, 90, 33);
		AddEventButton.setForeground(new Color(255, 102, 153));
		AddEventButton.setBackground(new Color(245, 245, 245));
		AddEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventFrame e = new EventFrame();
				e.launchEventFrame();
			}
		});
		frame.getContentPane().add(AddEventButton);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.WHITE);
		textPane.setEditable(false);
		textPane.setBounds(30, 100, 320, 230);
		frame.getContentPane().add(textPane);

		String fileName = "event.txt";
		String line = null;
		String result = "";

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				result = result + line + "\n";
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			System.out.println("IO error");
		}

		textPane.setText(result);
	}
}
