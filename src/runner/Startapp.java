package runner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Gen;
import model.Generation;

public class Startapp {

	public static void main(String[] args) {

		// 0 - value, 1 - weight, 2 - capacity

		final int POPULATION = 50;
		final double TOTAL_AVAILABLE_CAPACITY = 100;
		final int SELECT_COMBINATION = 0;
		String combination = "";
		final double[] bestGenCriteria = new double[3];
		List<Gen> resultSecondFunction = new ArrayList<Gen>();

		final JFrame frame;
		final JFrame frame2;
		final JFrame frame3;
		JPanel panel;
		JPanel panel2;
		JPanel panel3;
		JButton button1;
		JButton button2;
		JButton button3;
		JButton nextTo2;
		JButton nextTo3;
		JLabel label1;
		final TextField choose;
		final TextArea initArea;
		final TextArea resultNonPref;
		final TextArea resultPref;

		Startapp start = new Startapp();
		Scanner scanner = new Scanner(System.in);

		final Generation generation = new Generation(POPULATION, TOTAL_AVAILABLE_CAPACITY);
		bestGenCriteria[0] = generation.selectAndReturnGenWithBestValue();
		bestGenCriteria[1] = generation.selectAndReturnGenWithBestWeight();
		bestGenCriteria[2] = generation.selectAndReturnGenWithBestCapacity();

		for (int i = 0; i < bestGenCriteria.length; i++) {
			System.out.println("IKI : " + bestGenCriteria[i]);
		}
		final Gen firstAlgorithm = generation.firstAlgorithm(bestGenCriteria);
		// final List Algorithm = generation.firstAlgorithm(bestGenCriteria);
		// System.out.println("Gen : "+firstAlgorithm);

		//
		// do{
		// System.out.println("Integer should be number 1-6");
		// System.out.println("Check combination :");
		// System.out.println("1-VWC , 2-VCW, 3-WCV , 4-WVC, 5-CVW, 6-CWV Podaj
		// wybor ->");
		// while (!scanner.hasNextInt()) {
		// String input = scanner.next();
		// System.out.printf("%s is not a valid number.\n", input);
		// System.out.println("Check combination :");
		// System.out.println("1-VWC , 2-VCW, 3-WCV , 4-WVC, 5-CVW, 6-CWV");
		// }
		// selectCombination = scanner.nextInt();
		// }while(selectCombination<=0 || selectCombination>6);
		//
		// System.out.println("Final result :
		// "+generation.secondAlgorithm(selectCombination,
		// bestGenCriteria).get(0));
		//
		resultNonPref = new TextArea(10, 30);
		initArea = new TextArea(10, 30);
		resultPref = new TextArea(10, 30);
		choose = new TextField();
		frame = new JFrame("SWD2016-Project");
		frame2 = new JFrame("SWD2016-Project-Non-Preference");
		frame3 = new JFrame("SWD2016-Project-Preference");
		frame.setVisible(true);
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(600, 400);
		frame2.setResizable(false);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.setSize(600, 400);
		frame3.setResizable(false);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel2.setBackground(Color.YELLOW);
		panel3.setBackground(Color.YELLOW);

		nextTo2 = new JButton("-->");
		nextTo3 = new JButton("-->");
		button1 = new JButton("Initialization and best criteria");
		button2 = new JButton("Simulate Non-Preference");
		button3 = new JButton("Simulate Preference");
		label1 = new JLabel("<-- Podaj wybor 1-VWC , 2-VCW, 3-WCV , 4-WVC, 5-CVW, 6-CWV");

		panel.add(button1, BorderLayout.EAST);
		panel.add(nextTo2);
		// panel.add(label1);
		panel.add(initArea);

		panel2.add(button2, BorderLayout.EAST);
		panel2.add(nextTo3);
		// panel2.add(label1);
		panel2.add(resultNonPref);

		panel3.add(button3, BorderLayout.EAST);
		panel3.add(choose, BorderLayout.EAST);
		panel3.add(label1);
		panel3.add(resultPref);

		frame.add(panel);
		frame.add(initArea);
		frame.add(panel, BorderLayout.SOUTH);
		frame2.add(panel2);
		frame2.add(resultNonPref);
		frame2.add(panel2, BorderLayout.SOUTH);
		frame3.add(panel3);
		frame3.add(resultPref);
		frame3.add(panel3, BorderLayout.SOUTH);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				initArea.append(generation.getGenList().toString());
				initArea.append("\n");
				initArea.append("-------------------------------------------------------------------------");
				initArea.append("\n");
				initArea.append("BEST VALUE: " + bestGenCriteria[0]);
				initArea.append("\n");
				initArea.append("BEST WEIGHT: " + bestGenCriteria[1]);
				initArea.append("\n");
				initArea.append("BEST CAPACITY: " + bestGenCriteria[2]);
			}
		});
		nextTo2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// frame.setVisible(false);
				frame2.setVisible(true);
			}
		});
		nextTo3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// frame.setVisible(false);
				frame3.setVisible(true);
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resultNonPref.append("Gen : " + firstAlgorithm);
			}
		});
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectCombination2 = choose.getText();
				int selectedComb = 0;

				try {
					resultPref.setText(null);
					selectedComb = Integer.parseInt(selectCombination2);
					if (selectedComb <= 0 || selectedComb > 6) {
						JOptionPane.showMessageDialog(frame, "Integer 1-6!!", "Error", JOptionPane.ERROR_MESSAGE);

					} else if (selectedComb >= 0 || selectedComb <= 6) {
						resultPref.setText("");
						resultPref.append(generation.secondAlgorithm(selectedComb, bestGenCriteria).toString());
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Please give integer 1-6!!", "Error", JOptionPane.ERROR_MESSAGE

					);
					choose.setText("");
				}
				// System.out.println("MAM : "+selectCombination);
				// do{

			}
		});
	}
}
