/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Pertemuan4;

import javax.swing.*;
import java.awt.event.*;

public class ButtonExample {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Button Example");
		JButton button = new JButton("Click Me");
		
		// Menambahkan ActionListener ke Jbutton
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button clicked!");
			}
		});
		
		button.setBounds(50,50,150,30);
		frame.add(button);
		frame.setSize(300,200);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}