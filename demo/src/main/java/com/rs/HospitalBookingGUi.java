package com.rs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.rs.entity.*;
import com.rs.service.Bookable;


class HospitalBookingGUI extends JFrame {

    JTextField txtName, txtID;
    JComboBox<String> comboDept, comboDoctor, comboDay, comboTime;
    JTable table;
    DefaultTableModel model;
    JButton btnAdd, btnEdit, btnDelete;
    JComboBox<String> comboFilterDept;
    int selectedIndex = -1;
    String selectedDept = null;

    String[] departments = {
            "Toraks & Kardiovaskular", "Neurologi", "Pulmonologi",
            "Umum", "Bedah", "Anak", "Kulit & Kelamin",
            "Gigi", "Psikiatri", "THT" };
    String[] days = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"};
    String[] times = {"08-09", "10-11", "13-14", "16-17", "19-20"};

    HashMap<String, String[]> doctorMap = new HashMap<>();
    HashMap<String, String[][]> patientMap = new HashMap<>();

    public HospitalBookingGUI() {
        setTitle("Rumah Sakit Bamjji Sehat");
        setSize(950, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initDoctor();
        initPatientDummy();
        initUI(); }

    // dokter
    void initDoctor() {
        doctorMap.put("Toraks & Kardiovaskular", new String[]{"dr. Kento Yamazaki", "dr. Andika Pratama", "dr. Zayne Perkasa"});
        doctorMap.put("Neurologi", new String[]{"dr. Rio Gunawan", "dr. Inna Lutfiah Fatih"});
        doctorMap.put("Pulmonologi", new String[]{"dr. Asep Buhori", "dr. Alifian Revan"});
        doctorMap.put("Umum", new String[]{"dr. Amelia Rahman", "dr. Fina Nailatul", "dr. Sarah Nurfadillah", "dr. Hafizatul Rahmadania"});
        doctorMap.put("Bedah", new String[]{"dr. Theo Gunadi", "dr. Park Jisung", "dr. Muhammad Fathir"});
        doctorMap.put("Anak", new String[]{"dr. Gita Gutawa", "dr. Zaidan Dziaulfawwaz", "dr. Rakha Cibul"});
        doctorMap.put("Kulit & Kelamin", new String[]{"dr. Kevin Geovani", "dr. Herlyana Fediani", "dr. Belvaria Hendriyani"});
        doctorMap.put("Gigi", new String[]{"dr. Hendra Nandra", "dr. Waguri Waifu", "dr. Vania Winola"});
        doctorMap.put("Psikiatri", new String[]{"dr. Maya Cantika", "dr. Iman Paryudi", "dr. Rhys Meadows"});
        doctorMap.put("THT", new String[]{"dr. Fatimah", "dr. Jihaan Hanifah"}); }

    // pasien
    void initPatientDummy() {
    patientMap.put("Toraks & Kardiovaskular", new String[][]{
            {"Aditya kencana", "TK01", "dr. Kento Yamazaki", "Senin", "8-9"},
            {"Siti Aminah", "TK02", "dr. Zayne Perkasa", "Selasa", "8-9"},
            {"Budi Santoso", "TK03", "dr. Andika Pratama", "Rabu", "10-11"},
            {"Dewi Lestari", "TK04", "dr. Kento Yamazaki", "Kamis", "13-14"},
            {"Reza Rahadian", "TK05", "dr. Zayne Perkasa", "Jumat", "09-10"},
            {"Anisa Putri", "TK06", "dr. Andika Pratama", "Senin", "11-12"},
            {"Fajar Nugroho", "TK07", "dr. Kento Yamazaki", "Selasa", "14-15"},
            {"Maya Indah", "TK08", "dr. Zayne Perkasa", "Rabu", "08-09"},
            {"Rizky Hidayat", "TK09", "dr. Andika Pratama", "Kamis", "10-11"},
            {"Ghea Salsabila", "TK10", "dr. Kento Yamazaki", "Jumat", "15-16"} });

    patientMap.put("Neurologi", new String[][]{
            {"Lukas Müller", "NR01", "dr. Rio Gunawan", "Senin", "08-09"},
            {"Hannah Schmidt", "NR02", "dr. Inna Lutfiah Fatih", "Selasa", "09-10"},
            {"Maximilian Fischer", "NR03", "dr. Rio Gunawan", "Rabu", "10-11"},
            {"Sophie Weber", "NR04", "dr. Inna Lutfiah Fatih", "Kamis", "11-12"},
            {"Jonas Wagner", "NR05", "dr. Rio Gunawan", "Jumat", "13-14"},
            {"Leonie Becker", "NR06", "dr. Inna Lutfiah Fatih", "Senin", "14-15"},
            {"Felix Hoffmann", "NR07", "dr. Rio Gunawan", "Selasa", "08-09"},
            {"Elena Schwarz", "NR08", "dr. Inna Lutfiah Fatih", "Rabu", "09-10"},
            {"Tim Schneider", "NR09", "dr. Rio Gunawan", "Kamis", "10-11"},
            {"Clara Meyer", "NR10", "dr. Inna Lutfiah Fatih", "Jumat", "11-12"} });

    patientMap.put("Pulmonologi", new String[][]{
            {"Hiroki Tanaka", "PM01", "dr. Asep Buhori", "Senin", "08-09"},
            {"Yuki Watanabe", "PM02", "dr. Alifian Revan", "Selasa", "09-10"},
            {"Kenji Sato", "PM03", "dr. Asep Buhori", "Rabu", "10-11"},
            {"Haruka Ito", "PM04", "dr. Alifian Revan", "Kamis", "11-12"},
            {"Takashi Nakamura", "PM05", "dr. Asep Buhori", "Jumat", "13-14"},
            {"Mio Kobayashi", "PM06", "dr. Alifian Revan", "Senin", "14-15"},
            {"Ren Takahashi", "PM07", "dr. Asep Buhori", "Selasa", "08-09"},
            {"Akari Yamamoto", "PM08", "dr. Alifian Revan", "Rabu", "09-10"},
            {"Daiki Suzuki", "PM09", "dr. Asep Buhori", "Kamis", "10-11"},
            {"Yui Saito", "PM10", "dr. Alifian Revan", "Jumat", "11-12"} });

    patientMap.put("Umum", new String[][]{
            {"Zhang Wei", "UM01", "dr. Amelia Rahman", "Senin", "08-09"},
            {"Li Na", "UM02", "dr. Fina Nailatul", "Selasa", "09-10"},
            {"Wang Jun", "UM03", "dr. Sarah Nurfadillah", "Rabu", "10-11"},
            {"Chen Xiaowei", "UM04", "dr. Hafizatul Rahmadania", "Kamis", "11-12"},
            {"Liu Feng", "UM05", "dr. Amelia Rahman", "Jumat", "13-14"},
            {"Yang Ming", "UM06", "dr. Fina Nailatul", "Senin", "14-15"},
            {"Zhao Yan", "UM07", "dr. Sarah Nurfadillah", "Selasa", "08-09"},
            {"Huang Bo", "UM08", "dr. Hafizatul Rahmadania", "Rabu", "09-10"},
            {"Zhou Jie", "UM09", "dr. Amelia Rahman", "Kamis", "10-11"},
            {"Sun Lei", "UM10", "dr. Fina Nailatul", "Jumat", "11-12"} });

    patientMap.put("Bedah", new String[][]{
            {"Kim Minjun", "BD01", "dr. Theo Gunadi", "Senin", "08-09"},
            {"Lee Seoyeon", "BD02", "dr. Park Jisung", "Selasa", "09-10"},
            {"Park Jihoon", "BD03", "dr. Muhammad Fathir", "Rabu", "10-11"},
            {"Choi Yoojin", "BD04", "dr. Theo Gunadi", "Kamis", "11-12"},
            {"Jung Hyunwoo", "BD05", "dr. Park Jisung", "Jumat", "13-14"},
            {"Kang Haneul", "BD06", "dr. Muhammad Fathir", "Senin", "14-15"},
            {"Yoon Soobin", "BD07", "dr. Theo Gunadi", "Selasa", "08-09"},
            {"Shin Dongwook", "BD08", "dr. Park Jisung", "Rabu", "09-10"},
            {"Han Mirae", "BD09", "dr. Muhammad Fathir", "Kamis", "10-11"},
            {"Song Kangho", "BD10", "dr. Theo Gunadi", "Jumat", "11-12"} });

    patientMap.put("Anak", new String[][]{
            {"Bintang Kejora", "AN01", "dr. Gita Gutawa", "Senin", "08-09"},
            {"Aurel Hermansyah", "AN02", "dr. Zaidan Dziaulfawwaz", "Selasa", "09-10"},
            {"Kevin Anggara", "AN03", "dr. Rakha Cibul", "Rabu", "10-11"},
            {"Naya Anindita", "AN04", "dr. Gita Gutawa", "Kamis", "11-12"},
            {"Faris Utama", "AN05", "dr. Zaidan Dziaulfawwaz", "Jumat", "13-14"},
            {"Citra Lestari", "AN06", "dr. Rakha Cibul", "Senin", "14-15"},
            {"Tio Ramadhan", "AN07", "dr. Gita Gutawa", "Selasa", "08-09"},
            {"Ilham Mansiz", "AN08", "dr. Zaidan Dziaulfawwaz", "Rabu", "09-10"},
            {"Rani Wijaya", "AN09", "dr. Rakha Cibul", "Kamis", "10-11"},
            {"Dimas Anggara", "AN10", "dr. Gita Gutawa", "Jumat", "11-12"} });

    patientMap.put("Kulit & Kelamin", new String[][]{
            {"Andi Wijaya", "KK01", "dr. Kevin Geovani", "Senin", "08-09"},
            {"Bambang Pamungkas", "KK02", "dr. Herlyana Fediani", "Selasa", "09-10"},
            {"Cici Panda", "KK03", "dr. Belvaria Hendriyani", "Rabu", "10-11"},
            {"Dodi Triono", "KK04", "dr. Kevin Geovani", "Kamis", "11-12"},
            {"Eka Saputra", "KK05", "dr. Herlyana Fediani", "Jumat", "13-14"},
            {"Farhan Halim", "KK06", "dr. Belvaria Hendriyani", "Senin", "14-15"},
            {"Gita Savitri", "KK07", "dr. Kevin Geovani", "Selasa", "08-09"},
            {"Heri Azhari", "KK08", "dr. Herlyana Fediani", "Rabu", "09-10"},
            {"Iwan Fals", "KK09", "dr. Belvaria Hendriyani", "Kamis", "10-11"},
            {"Joko Anwar", "KK10", "dr. Kevin Geovani", "Jumat", "11-12"} });

    patientMap.put("Gigi", new String[][]{
            {"Kiki Saputri", "GG01", "dr. Hendra Nandra", "Senin", "08-09"},
            {"Lala Karmela", "GG02", "dr. Waguri Waifu", "Selasa", "09-10"},
            {"Maman Abdurrahman", "GG03", "dr. Vania Winola", "Rabu", "10-11"},
            {"Nana Mirdad", "GG04", "dr. Hendra Nandra", "Kamis", "11-12"},
            {"Oki Setiana", "GG05", "dr. Waguri Waifu", "Jumat", "13-14"},
            {"Putra Siregar", "GG06", "dr. Vania Winola", "Senin", "14-15"},
            {"Qori Sandioriva", "GG07", "dr. Hendra Nandra", "Selasa", "08-09"},
            {"Rina Nose", "GG08", "dr. Waguri Waifu", "Rabu", "09-10"},
            {"Santi Widya", "GG09", "dr. Vania Winola", "Kamis", "10-11"},
            {"Taufik Hidayat", "GG10", "dr. Hendra Nandra", "Jumat", "11-12"} });

    patientMap.put("Psikiatri", new String[][]{
            {"Umar Bin", "PS01", "dr. Maya Cantika", "Senin", "08-09"},
            {"Vina Panduwinata", "PS02", "dr. Iman Paryudi", "Selasa", "09-10"},
            {"Wati Galih", "PS03", "dr. Rhys Meadows", "Rabu", "10-11"},
            {"Xena Warrior", "PS04", "dr. Maya Cantika", "Kamis", "11-12"},
            {"Yanto Basna", "PS05", "dr. Iman Paryudi", "Jumat", "13-14"},
            {"Zizi Kirana", "PS06", "dr. Rhys Meadows", "Senin", "14-15"},
            {"Amir Syarifuddin", "PS07", "dr. Maya Cantika", "Selasa", "08-09"},
            {"Basir Bustomi", "PS08", "dr. Iman Paryudi", "Rabu", "09-10"},
            {"Caca Handika", "PS09", "dr. Rhys Meadows", "Kamis", "10-11"},
            {"Dedi Corbuzier", "PS10", "dr. Maya Cantika", "Jumat", "11-12"} });

    patientMap.put("THT", new String[][]{
            {"Entin Kartini", "TH01", "dr. Fatimah", "Senin", "08-09"},
            {"Ferry Salim", "TH02", "dr. Jihaan Hanifah", "Selasa", "09-10"},
            {"Gani Kasat", "TH03", "dr. Fatimah", "Rabu", "10-11"},
            {"Hendra Setiawan", "TH04", "dr. Jihaan Hanifah", "Kamis", "11-12"},
            {"Indra Sjafri", "TH05", "dr. Fatimah", "Jumat", "13-14"},
            {"Jajang Mulyana", "TH06", "dr. Jihaan Hanifah", "Senin", "14-15"},
            {"Kurniawan Dwi", "TH07", "dr. Fatimah", "Selasa", "08-09"},
            {"Lucky Hakim", "TH08", "dr. Jihaan Hanifah", "Rabu", "09-10"},
            {"Muchtar Riady", "TH09", "dr. Fatimah", "Kamis", "10-11"},
            {"Nurdin Halid", "TH10", "dr. Jihaan Hanifah", "Jumat", "11-12"} }); }

    void initUI() {
    setLayout(new BorderLayout(10, 10));

    // left panel
    JPanel leftPanel = new JPanel(new GridBagLayout());
    leftPanel.setBorder(BorderFactory.createTitledBorder(
            "Formulir Janji Temu Dokter RS Bamjji"));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    txtName = new JTextField(15);
    txtID = new JTextField(15);

    comboDept = new JComboBox<>(departments);
    comboDoctor = new JComboBox<>();
    comboDay = new JComboBox<>(days);
    comboTime = new JComboBox<>(times);

    loadDoctor(departments[0]);
    comboDept.addActionListener(e ->
            loadDoctor(comboDept.getSelectedItem().toString()));
    int y = 0;
    
    gbc.gridx = 0; gbc.gridy = y;
    leftPanel.add(new JLabel("Nama Pasien"), gbc);
    gbc.gridx = 1;
    leftPanel.add(txtName, gbc); y++;

    gbc.gridx = 0; gbc.gridy = y;
    leftPanel.add(new JLabel("ID Pasien"), gbc);
    gbc.gridx = 1;
    leftPanel.add(txtID, gbc); y++;

    gbc.gridx = 0; gbc.gridy = y;
    leftPanel.add(new JLabel("Departemen"), gbc);
    gbc.gridx = 1;
    leftPanel.add(comboDept, gbc); y++;

    gbc.gridx = 0; gbc.gridy = y;
    leftPanel.add(new JLabel("Dokter"), gbc);
    gbc.gridx = 1;
    leftPanel.add(comboDoctor, gbc); y++;

    gbc.gridx = 0; gbc.gridy = y;
    leftPanel.add(new JLabel("Hari"), gbc);
    gbc.gridx = 1;
    leftPanel.add(comboDay, gbc); y++;

    gbc.gridx = 0; gbc.gridy = y;
    leftPanel.add(new JLabel("Jam"), gbc);
    gbc.gridx = 1;
    leftPanel.add(comboTime, gbc); y++;

    // button
    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
    btnAdd = new JButton("Tambah");
    btnEdit = new JButton("Edit");
    btnDelete = new JButton("Hapus");

    btnPanel.add(btnAdd);
    btnPanel.add(btnEdit);
    btnPanel.add(btnDelete);

    gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
    leftPanel.add(btnPanel, gbc);

    add(leftPanel, BorderLayout.WEST);

    // right panel
    JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
    JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    comboFilterDept = new JComboBox<>(departments);
    filterPanel.add(new JLabel("Filter Departemen:"));
    filterPanel.add(comboFilterDept);

    rightPanel.add(filterPanel, BorderLayout.NORTH);

    model = new DefaultTableModel(
            new String[]{"Nama", "ID", "Departemen", "Dokter", "Hari", "Jam"}, 0);
    table = new JTable(model);
    rightPanel.add(new JScrollPane(table), BorderLayout.CENTER);

    add(rightPanel, BorderLayout.CENTER);

    // event
    table.getSelectionModel().addListSelectionListener(e -> {
    int row = table.getSelectedRow();
    if (row < 0) return;

    selectedIndex = row;
    selectedDept = model.getValueAt(row, 2).toString();

    txtName.setText(model.getValueAt(row, 0).toString());
    txtID.setText(model.getValueAt(row, 1).toString());
    comboDept.setSelectedItem(selectedDept);
    comboDoctor.setSelectedItem(model.getValueAt(row, 3).toString());
    comboDay.setSelectedItem(model.getValueAt(row, 4).toString());
    comboTime.setSelectedItem(model.getValueAt(row, 5).toString()); });

    comboFilterDept.addActionListener(e -> {
        String dept = comboFilterDept.getSelectedItem().toString();
        loadPatientByDepartment(dept); });

    btnAdd.addActionListener(e -> addData());
    btnEdit.addActionListener(e -> editData());
    btnDelete.addActionListener(e -> deleteData()); }

    void loadDoctor(String dept) {
        comboDoctor.removeAllItems();
        for (String d : doctorMap.get(dept)) {
            comboDoctor.addItem(d); } }

    void loadPatientByDepartment(String dept) {
    model.setRowCount(0);

    String[][] patients = patientMap.get(dept);
    if (patients == null) return;

    for (String[] p : patients) {
        model.addRow(new Object[]{
                p[0], 
                p[1], 
                dept,
                p[2], 
                p[3], 
                p[4]   }); } }

    void addPatientToDepartment(String dept, String name, String id,
                            String doctor, String day, String time) {

    String[][] oldData = patientMap.get(dept);

    if (oldData == null) {
        patientMap.put(dept, new String[][]{
                {name, id, doctor, day, time} });
        return; }

    String[][] newData = new String[oldData.length + 1][5];

    for (int i = 0; i < oldData.length; i++) {
        newData[i] = oldData[i]; }

    newData[oldData.length] =
            new String[]{name, id, doctor, day, time};

    patientMap.put(dept, newData); }


    ArrayList<Booking> bookingList = new ArrayList<>();

    void addData() {
    if (txtName.getText().isEmpty() || txtID.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Data wajib diisi!");
        return; }

    String name = txtName.getText();
    String id = txtID.getText();
    String dept = comboDept.getSelectedItem().toString();
    String doctor = comboDoctor.getSelectedItem().toString();
    String day = comboDay.getSelectedItem().toString();
    String time = comboTime.getSelectedItem().toString();

    addPatientToDepartment(dept, name, id, doctor, day, time);

    if (comboFilterDept.getSelectedItem() != null &&
        comboFilterDept.getSelectedItem().equals(dept)) {
        loadPatientByDepartment(dept); }

    clearForm(); }

    void editData() {
    if (selectedIndex == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data di tabel dulu!");
        return; }

    String newName = txtName.getText();
    String newId = txtID.getText();
    String newDept = comboDept.getSelectedItem().toString();
    String newDoctor = comboDoctor.getSelectedItem().toString();
    String newDay = comboDay.getSelectedItem().toString();
    String newTime = comboTime.getSelectedItem().toString();

    // hapus
    String[][] oldData = patientMap.get(selectedDept);
    String[][] newData = new String[oldData.length - 1][5];
        int idx = 0;
        for (int i = 0; i < oldData.length; i++) {
            if (i == selectedIndex) continue;
            newData[idx++] = oldData[i]; 
        }
    patientMap.put(selectedDept, newData);

    // tambah data
    addPatientToDepartment(newDept, newName, newId, newDoctor, newDay, newTime);
    loadPatientByDepartment(newDept);
    clearForm();
    selectedIndex = -1;
    selectedDept = null; }

    void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            return; }
        model.removeRow(row); }

    void clearForm() {
        txtName.setText("");
        txtID.setText(""); }

    // login
    static boolean login() {
        JTextField u = new JTextField();
        JPasswordField p = new JPasswordField();
        Object[] msg = {"Username:", u, "Password:", p};
        int opt = JOptionPane.showConfirmDialog(null, msg, "Login",
                JOptionPane.OK_CANCEL_OPTION);
        return opt == JOptionPane.OK_OPTION &&
                u.getText().equals("admin") &&
                new String(p.getPassword()).equals("123"); }

    public static void main(String[] args) {
        if (login()) new HospitalBookingGUI().setVisible(true);
        else JOptionPane.showMessageDialog(null, "Login gagal!"); } }