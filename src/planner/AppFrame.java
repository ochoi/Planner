package planner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

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
import javax.swing.SpringLayout;

/*
 * The main frame for the planner application.
 */

public class AppFrame {

	private JFrame frame;
	private JLabel titleLabel;
	private JButton AddEventButton;

	private JScrollPane scrollPane;
	private JPanel panel;
	private JCheckBox checkBox;
	private static final int COLS = 1;
	private int rows;

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

	private class ItemHandler implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox selected = (JCheckBox) e.getSource();
			panel.remove(selected);
			panel.revalidate();
			panel.repaint();
			// selected.getName()
			// remove a line in a file
		}
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

		titleLabel = new JLabel("Planner");
		titleLabel.setBounds(30, 30, 78, 33);
		frame.getContentPane().add(titleLabel);
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setForeground(new Color(255, 102, 153));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

		// Brings up a new frame to ask for inputs
		AddEventButton = new JButton("Add Event");
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

		// A preview text pane for all the events stored
		// JTextPane textPane = new JTextPane();
		// textPane.setBackground(Color.WHITE);
		// textPane.setEditable(false);
		// textPane.setBounds(30, 100, 320, 230);
		// frame.getContentPane().add(textPane);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 100, 320, 230);
		frame.getContentPane().add(scrollPane);

		panel = new JPanel();
		//panel.setBackground(new Color(255, 204, 204));
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(rows, COLS));
		// scrollPane.setPreferredSize(new Dimension(320,230));
		//panel.setLayout(new GridLayout(1, 0)); //to use design tab

		String fileName = "event.txt";
		String line = null;
		String result = "";

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				checkBox = new JCheckBox(" " + line);
				checkBox.addItemListener(new ItemHandler());
				rows++;
				panel.add(checkBox);
				// result = result + line + "\n";
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			System.out.println("IO error");
		}
		// only works if all the components that panel has are check boxes?

		// textPane.setText(result);
		// think about using Jtable? or something to add checkboxes and set
		// the scrolling size.
		// deleting selected checkbox and update the file by rewriting to it
		// might be inefficient..
	}
}
