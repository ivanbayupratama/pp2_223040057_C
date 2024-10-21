/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Latihan_5;

/**
 *
 * @author ivanbayupratama
 */

import javax.swing.*;
import java.awt.event.*;
public class ComboBoxExample {
public static void main(String[] args) {
JFrame frame = new JFrame("JComboBox Example");
String[] options = { "aurel cantik", "aurel aur aur", "windah basudara", "jadianakjanganbacot"
        + "" };
// Membuat JComboBox dengan opsi
JComboBox<String> comboBox = new JComboBox<>(options);
// Tambahkan pendengar aksi
comboBox.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
// Mengambil item yang dipilih
String selected = (String) comboBox.getSelectedItem();
System.out.println("Selected: " + selected);
}
});
// Atur layout dan tambahkan ke frame
frame.setLayout(null);
comboBox.setBounds(50, 50, 150, 20);
frame.add(comboBox);
// Konfigurasi frame
frame.setSize(300, 200);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
}
}
