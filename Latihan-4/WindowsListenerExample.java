/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan4;

/**
 *
 * @author ivanbayu
 */ 

import javax.swing.*;
import java.awt.event.*;

public class WindowsListenerExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame ("WindowListener Example");
        JLabel label = new JLabel("Lakukan Operasi pada Jendela.");
        label.setBounds(50,50,300,30);

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                label.setText("Window Opened. ");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                label.setText("Window Closing. ");
            }

            @Override
            public void windowClosed(WindowEvent e) {
                label.setText("Window CLosed. ");
            }
            @Override
            public void windowIconified(WindowEvent e) {
                label.setText("Window Minimized. ");
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
                label.setText("Window Restored. ");
            }
            @Override
            public void windowActivated(WindowEvent e) {
                label.setText("Window Activated. ");
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
                label.setText("Window Deactivate. ");
            }
        }); 

        frame.add(label);
        frame.setSize(400,200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}