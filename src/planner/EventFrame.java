package planner;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/* 
 * The event button brings up a new window that accept inputs and store them
 * in a file to be displayed in a different format.
 */

public class EventFrame {

	private JFrame frame;

	private JLabel lblMonth;
	private JLabel lblDay;
	private JLabel lblTime;

	private JComboBox monthCB;
	private JComboBox dayCB;
	private JComboBox hourCB;
	private JComboBox minCB;

	private JRadioButton radioAM;
	private JRadioButton radioPM;
	private String storedTime;

	private JLabel lblEvent;
	private JTextField evtTitle;
	// private String storedTitle;

	private JLabel lblDescription;
	private JTextField evtDetails;
	// private String storedDetails;

	private JButton confirmBtn;
	private JButton clearBtn;

	/**
	 * Launch the application.
	 */
	public void launchEventFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventFrame window = new EventFrame();
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
	public EventFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 204));
		frame.setBounds(100, 100, 315, 301);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		// arrays for the drop down list selections
		Integer[] month = new Integer[12];
		int monthCounter = 1;
		int monthIndex = 0;
		while (monthCounter <= 12) {
			month[monthIndex] = monthCounter;
			monthIndex++;
			monthCounter++;
		}

		Integer[] day = new Integer[31];
		int dayCounter = 1;
		int dayIndex = 0;
		while (dayCounter <= 31) {
			day[dayIndex] = dayCounter;
			dayIndex++;
			dayCounter++;
		}

		Integer[] minute = new Integer[60];
		int minCounter = 0;
		while (minCounter <= 59) {
			minute[minCounter] = minCounter;
			minCounter++;
		}
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		
		
		
		
		
		// set default to current time // need to learn
		// get index == that

		lblMonth = new JLabel("Month: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblMonth, 32,
				SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMonth, 20,
				SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblMonth);

		// monthCB = new JComboBox<>(month);
		monthCB = new JComboBox(month);
		springLayout.putConstraint(SpringLayout.NORTH, monthCB, -3,
				SpringLayout.NORTH, lblMonth);
		springLayout.putConstraint(SpringLayout.WEST, monthCB, 6,
				SpringLayout.EAST, lblMonth);
		frame.getContentPane().add(monthCB);

		lblDay = new JLabel("Day: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblDay, 0,
				SpringLayout.NORTH, lblMonth);
		springLayout.putConstraint(SpringLayout.WEST, lblDay, 19,
				SpringLayout.EAST, monthCB);
		frame.getContentPane().add(lblDay);

		// dayCB = new JComboBox<>(day);
		dayCB = new JComboBox(day);
		springLayout.putConstraint(SpringLayout.NORTH, dayCB, -3,
				SpringLayout.NORTH, lblMonth);
		springLayout.putConstraint(SpringLayout.WEST, dayCB, 6, SpringLayout.EAST,
				lblDay);
		frame.getContentPane().add(dayCB);

		lblTime = new JLabel("   Time: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblTime, 20,
				SpringLayout.SOUTH, lblMonth);
		springLayout.putConstraint(SpringLayout.EAST, lblTime, 0,
				SpringLayout.EAST, lblMonth);
		frame.getContentPane().add(lblTime);

		// hourCB = new JComboBox<>(month);
		hourCB = new JComboBox(month);
		springLayout.putConstraint(SpringLayout.NORTH, hourCB, -3,
				SpringLayout.NORTH, lblTime);
		springLayout.putConstraint(SpringLayout.WEST, hourCB, 0,
				SpringLayout.WEST, monthCB);
		frame.getContentPane().add(hourCB);

		// minCB = new JComboBox<>(minute);
		minCB = new JComboBox(minute);
		springLayout.putConstraint(SpringLayout.NORTH, minCB, -3,
				SpringLayout.NORTH, lblTime);
		springLayout.putConstraint(SpringLayout.WEST, minCB, 6, SpringLayout.EAST,
				hourCB);
		frame.getContentPane().add(minCB);

		
		
		
		
		radioAM = new JRadioButton("AM");
		springLayout.putConstraint(SpringLayout.NORTH, radioAM, -4,
				SpringLayout.NORTH, lblTime);
		springLayout.putConstraint(SpringLayout.WEST, radioAM, 30,
				SpringLayout.EAST, minCB);
		frame.getContentPane().add(radioAM);

		radioPM = new JRadioButton("PM");
		springLayout.putConstraint(SpringLayout.NORTH, radioPM, -4,
				SpringLayout.NORTH, lblTime);
		springLayout.putConstraint(SpringLayout.WEST, radioPM, 6,
				SpringLayout.EAST, radioAM);
		frame.getContentPane().add(radioPM);

		storedTime = "";
		// select either AM or PM, not both.
		ButtonGroup group = new ButtonGroup();
		group.add(radioAM);
		group.add(radioPM);

		radioAM.addItemListener(new ItemHandler());
		radioPM.addItemListener(new ItemHandler());

		
		
		
		
		lblEvent = new JLabel("Event Title:");
		springLayout.putConstraint(SpringLayout.WEST, lblEvent, 0,
				SpringLayout.WEST, lblMonth);
		frame.getContentPane().add(lblEvent);

		evtTitle = new JTextField();
		evtTitle.setText("");
		springLayout.putConstraint(SpringLayout.NORTH, evtTitle, 136,
				SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblEvent, -6,
				SpringLayout.NORTH, evtTitle);
		springLayout.putConstraint(SpringLayout.WEST, evtTitle, 0,
				SpringLayout.WEST, lblMonth);
		springLayout.putConstraint(SpringLayout.EAST, evtTitle, 0,
				SpringLayout.EAST, radioPM);
		frame.getContentPane().add(evtTitle);

		lblDescription = new JLabel("Description:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDescription, 16,
				SpringLayout.SOUTH, evtTitle);
		springLayout.putConstraint(SpringLayout.WEST, lblDescription, 0,
				SpringLayout.WEST, lblMonth);
		frame.getContentPane().add(lblDescription);

		evtDetails = new JTextField();
		evtDetails.setText("");
		springLayout.putConstraint(SpringLayout.NORTH, evtDetails, 6,
				SpringLayout.SOUTH, lblDescription);
		springLayout.putConstraint(SpringLayout.WEST, evtDetails, 0,
				SpringLayout.WEST, lblMonth);
		springLayout.putConstraint(SpringLayout.EAST, evtDetails, 0,
				SpringLayout.EAST, radioPM);
		frame.getContentPane().add(evtDetails);
		
		
		
		// add function: if the boxes are empty, can't press confirm, dialog box

		confirmBtn = new JButton("Confirm");
		confirmBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// store all inputs in string variables
				String storedDetails = evtTitle.getText() + " "
						+ evtDetails.getText();
				String storedDate = monthCB.getSelectedItem().toString() + "/"
						+ dayCB.getSelectedItem().toString() + " "
						+ hourCB.getSelectedItem().toString() + ":"
						+ minCB.getSelectedItem().toString() + " " + storedTime;

				// save all inputs in a file
				File event = new File("event.txt");
				try {
					FileWriter writer = new FileWriter(event, true);
					writer.write(storedDate + " " + storedDetails);
					writer.write(System.lineSeparator());
					writer.close();
				} catch (IOException e1) {
					System.out.println("error");
				}

				frame.dispose();
			}
		});

		springLayout.putConstraint(SpringLayout.SOUTH, confirmBtn, -10,
				SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, confirmBtn, -10,
				SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(confirmBtn);

		// figure out how to overwrite one or more data inputs
		// (if date and time the same but details different, append the details
		// on the same line as the date and time?)

		clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// when clear is pressed
				// all the selection goes back to default
				// (or current time if implemented)

			}

		});
		springLayout.putConstraint(SpringLayout.WEST, clearBtn, 110,
				SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, clearBtn, -103,
				SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, confirmBtn, 5,
				SpringLayout.EAST, clearBtn);
		springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10,
				SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(clearBtn);
	}

	private class ItemHandler implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
			if (radioAM.isSelected())
				storedTime = "AM";
			else if (radioPM.isSelected())
				storedTime = "PM";
		}
	}

	// JOptionPane.showMessageDialog(null, storedTitle);
}
