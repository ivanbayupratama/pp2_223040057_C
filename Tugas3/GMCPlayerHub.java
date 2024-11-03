 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivanbayupratama - 223040057
 */



public class GMCPlayerHub extends JFrame {

    private JPanel mainPanel;
    private List<Member> members;
    private JTable membersTable;
    private String[] columns = {"ID", "Nama", "Posisi", "Jadwal Latihan"};
    private Member selectedMember = null;

    public GMCPlayerHub() {
        setTitle("Registrasi Anggota GMC Basketball");
        members = new ArrayList<>();

        JMenuBar menuBar = new JMenuBar();

        // Membuat menu 'File'
        JMenu fileMenu = new JMenu("File");
        JMenuItem addMember = new JMenuItem("Add Member");
        JMenuItem viewMember = new JMenuItem("View Members");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(addMember);
        fileMenu.add(viewMember);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Membuat menu 'Edit'
        JMenu editMenu = new JMenu("Edit");
        JMenuItem editMember = new JMenuItem("Edit Member");
        JMenuItem deleteMember = new JMenuItem("Delete Member");
        editMenu.add(editMember);
        editMenu.add(deleteMember);

        // Membuat menu 'Help'
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(47, 79, 79));
        add(mainPanel, BorderLayout.CENTER);

        exitItem.addActionListener(e -> System.exit(0));
        addMember.addActionListener(e -> showAddMemberForm());
        viewMember.addActionListener(e -> showViewMemberForm());
        editMember.addActionListener(e -> editSelectedMember());
        deleteMember.addActionListener(e -> deleteSelectedMember());
        aboutItem.addActionListener(e -> showAbout());

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void showAddMemberForm() {
        mainPanel.removeAll();

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(47, 79, 79));

        formPanel.add(new JLabel("Nama :")).setForeground(Color.WHITE);
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Alamat :")).setForeground(Color.WHITE);
        JTextArea addressArea = new JTextArea();
        formPanel.add(addressArea);

        formPanel.add(new JLabel("Jenis Kelamin :")).setForeground(Color.WHITE);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton maleButton = new JRadioButton("Laki-laki");
        JRadioButton femaleButton = new JRadioButton("Perempuan");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        formPanel.add(genderPanel);

        formPanel.add(new JLabel("Posisi :")).setForeground(Color.WHITE);
        JComboBox<String> positionComboBox = new JComboBox<>(new String[]{"Power Forward", "Center", "Small Forward", "Shooting Guard", "Point Guard"});
        formPanel.add(positionComboBox);

        formPanel.add(new JLabel("Jadwal Latihan:")).setForeground(Color.WHITE);
        JList<String> scheduleList = new JList<>(new String[]{"Senin Pagi", "Selasa Sore", "Rabu Sore", "Kamis Malam", "Sabtu Pagi"});
        scheduleList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        formPanel.add(new JScrollPane(scheduleList));

        JButton submitButton = new JButton("Simpan");
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(76, 175, 80));
        formPanel.add(new JLabel());
        formPanel.add(submitButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String address = addressArea.getText();
            String gender = maleButton.isSelected() ? "Laki-Laki" : "Perempuan";
            String position = (String) positionComboBox.getSelectedItem();
            List<String> selectedSchedules = scheduleList.getSelectedValuesList();
            String scheduleText = String.join(", ", selectedSchedules);

            members.add(new Member(name, position, scheduleText));

            JOptionPane.showMessageDialog(this, "Berhasil menambah member baru:\nNama: " + name + "\nPosisi: " + position + "\nJadwal Latihan: " + scheduleText,
                    "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);

            nameField.setText("");
            addressArea.setText("");
            genderGroup.clearSelection();
            positionComboBox.setSelectedIndex(0);
            scheduleList.clearSelection();
        });

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showViewMemberForm() {
        mainPanel.removeAll();

        Object[][] data = new Object[members.size()][columns.length];
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            data[i][0] = i + 1;
            data[i][1] = member.getName();
            data[i][2] = member.getPosition();
            data[i][3] = member.getSchedule();
        }

        membersTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(membersTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void editSelectedMember() {
        int selectedRow = membersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih member untuk di edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        selectedMember = members.get(selectedRow);
        showEditMemberForm();
    }

    private void showEditMemberForm() {
        mainPanel.removeAll();

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(47, 79, 79));

        JTextField nameField = new JTextField(selectedMember.getName());
        JComboBox<String> positionComboBox = new JComboBox<>(new String[]{"Power Forward", "Center", "Small Forward", "Shooting Guard", "Point Guard"});
        positionComboBox.setSelectedItem(selectedMember.getPosition());

        JLabel nameLabel = new JLabel("Nama :");
        nameLabel.setForeground(Color.WHITE);
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        JLabel positionLabel = new JLabel("Posisi :");
        positionLabel.setForeground(Color.WHITE);
        formPanel.add(positionLabel);
        formPanel.add(positionComboBox);

        JButton saveButton = new JButton("Simpan");
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(76, 175, 80));
        formPanel.add(new JLabel());
        formPanel.add(saveButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        saveButton.addActionListener(e -> {
            selectedMember.setName(nameField.getText());
            selectedMember.setPosition((String) positionComboBox.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Update member berhasil!", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            showViewMemberForm();
        });

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void deleteSelectedMember() {
        int selectedRow = membersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih member yang ingin dihapus.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        members.remove(selectedRow);
        JOptionPane.showMessageDialog(this, "Hapus member berhasil!", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
        showViewMemberForm();
    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this, "Aplikasi Registrasi Anggota GMC Basketball\nVersion 1.0\nDeveloped by ivanbayupratama", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private class Member {
        private String name;
        private String position;
        private String schedule;

        public Member(String name, String position, String schedule) {
            this.name = name;
            this.position = position;
            this.schedule = schedule;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getSchedule() {
            return schedule;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GMCPlayerHub().setVisible(true);
        });
    }
}
