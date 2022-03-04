package sudoku;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

public class SudokuController {

	public SudokuController(String title){
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(10,9));
		ArrayList<JTextField> board = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				JTextField textField = new JTextField();
				textField.setHorizontalAlignment(JTextField.CENTER);
				if (((i < 3 || i > 5) && (j > 2 && j < 6)) || (i > 2 && i < 6 && (j < 3 || j > 5))) {
					textField.setBackground(Color.RED);
				}
				board.add(textField);
				panel.add(textField);
			}	
		}
		JButton solve = new JButton("solve");
		JButton clear = new JButton("clear");
		panel.add(solve);
		panel.add(clear);
		
		clear.addActionListener(e -> {
			for (JTextField textField : board) {
				textField.setText(null);
			}
		});
		solve.addActionListener(e -> {
			int[][] b = new int[9][9];
			int n = 0;
			for (JTextField textField : board) {
				if (!textField.getText().equals("")) {
					try {
						int i = Integer.valueOf(textField.getText());
						if (i > 0 && i <= 9) {
							b[n/9][n%9] = i;
						}else {
							throw new Exception();
						}
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Du har angivit ett felaktigt tecken. prova ange en siffra 1 - 9");
						return;
					}
				}else {
					b[n/9][n%9] = 0;
				}
				n++;
			}
			Sudoku SudokuSolver = new Sudoku(b);
			if (SudokuSolver.isValid() && SudokuSolver.solve()) {
				System.out.print(SudokuSolver.get(0,0));
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (SudokuSolver.get(i, j) != 0) {
							board.get(i * 9 + j).setText(String.valueOf(SudokuSolver.get(i, j)));
							System.out.print(SudokuSolver.get(i,j));
						}else {
							board.get(i * 9 + j).setText("0");
						}
						
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "det fins ingen lösning");
			}
//			SudokuSolver.solve();
//			for (int i = 0; i < 9; i++) {
//				for (int j = 0; j < 9; j++) {
//					board.get(i * 9 + j).setText(String.valueOf(SudokuSolver.get(i, j)));
//				}
//			}
			
				
			
		});
		
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}
