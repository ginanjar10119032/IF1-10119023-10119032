
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lenovo
 */
public class frm_simulasiNA extends javax.swing.JFrame {

    model model = new model();
    private DefaultTableModel tabelModel = model.simulasiNA;

    koneksi setPanel;
    String driver, db, user, pass;
    String data[] = new String[18];
    int row = 0;

    /**
     * Creates new form frm_simulasiNA
     */
    public frm_simulasiNA() {
        initComponents();
        setLocationRelativeTo(null);

        txtKodeMK.setEditable(false);

        setPanel = new koneksi();
        driver = setPanel.settingPanel("DBDriver");
        db = setPanel.settingPanel("DBDatabase");
        user = setPanel.settingPanel("DBUsername");
        pass = setPanel.settingPanel("DBPassword");

        tabelSimulasiNA.setModel(tabelModel);
        isiBoxMK();
        nonaktif_text();

        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnSimpan.setEnabled(false);
    }

    private void setColWidth() {
        tabelSimulasiNA.getRowHeight(2);
    }

    private void tampil_field() {
        aktif_text();

        row = tabelSimulasiNA.getSelectedRow();

        String kodeMK;
        kodeMK = tabelSimulasiNA.getValueAt(row, 0).toString();

        txtNamaMK.setSelectedItem(tabelSimulasiNA.getValueAt(row, 0).toString());
        txtPersentaseAbsen.setText(tabelSimulasiNA.getValueAt(row, 1).toString());
        txtPersentaseTugas.setText(tabelSimulasiNA.getValueAt(row, 2).toString());
        txtPersentaseUTS.setText(tabelSimulasiNA.getValueAt(row, 3).toString());
        txtPersentaseUAS.setText(tabelSimulasiNA.getValueAt(row, 4).toString());
        txtKehadiran.setText(tabelSimulasiNA.getValueAt(row, 5).toString());
        txtTugas1.setText(tabelSimulasiNA.getValueAt(row, 6).toString());
        txtTugas2.setText(tabelSimulasiNA.getValueAt(row, 7).toString());
        txtTugas3.setText(tabelSimulasiNA.getValueAt(row, 8).toString());
        txtUTS.setText(tabelSimulasiNA.getValueAt(row, 9).toString());
        txtUAS.setText(tabelSimulasiNA.getValueAt(row, 10).toString());

        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
        btnBatal.setEnabled(true);
    }

    private void isiBoxMK() {
        try {
            txtNamaMK.removeAllItems();
            txtNamaMK.addItem("----- Pilih Nama Mata Kuliah -----");

            Class.forName(driver);
            Connection kon = DriverManager.getConnection(db, user, pass);

            Statement stt = kon.createStatement();
            String SQL = "select nama_mk from mata_kuliah";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                txtNamaMK.addItem(res.getString(1));
            }

            res.close();
            stt.close();
            kon.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    private void membersihkan_text() {
        txtNamaMK.setSelectedIndex(0);
        txtKodeMK.setText("");
        txtPersentaseAbsen.setText("");
        txtPersentaseTugas.setText("");
        txtPersentaseUTS.setText("");
        txtPersentaseUAS.setText("");
        txtKehadiran.setText("");
        txtTugas1.setText("");
        txtTugas2.setText("");
        txtTugas3.setText("");
        txtUTS.setText("");
        txtUAS.setText("");
    }

    private void nonaktif_text() {
        txtNamaMK.setEnabled(false);
        txtKodeMK.setEnabled(false);
        txtPersentaseAbsen.setEnabled(false);
        txtPersentaseTugas.setEnabled(false);
        txtPersentaseUTS.setEnabled(false);
        txtPersentaseUAS.setEnabled(false);
        txtKehadiran.setEnabled(false);
        txtTugas1.setEnabled(false);
        txtTugas2.setEnabled(false);
        txtTugas3.setEnabled(false);
        txtUTS.setEnabled(false);
        txtUAS.setEnabled(false);
    }

    private void aktif_text() {
        txtNamaMK.setEnabled(true);
        txtKodeMK.setEnabled(true);
        txtPersentaseAbsen.setEnabled(true);
        txtPersentaseTugas.setEnabled(true);
        txtPersentaseUTS.setEnabled(true);
        txtPersentaseUAS.setEnabled(true);
        txtKehadiran.setEnabled(true);
        txtTugas1.setEnabled(true);
        txtTugas2.setEnabled(true);
        txtTugas3.setEnabled(true);
        txtUTS.setEnabled(true);
        txtUAS.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNamaMK = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPersentaseAbsen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPersentaseTugas = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPersentaseUTS = new javax.swing.JTextField();
        txtPersentaseUAS = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtKehadiran = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTugas1 = new javax.swing.JTextField();
        txtTugas2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTugas3 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtUTS = new javax.swing.JTextField();
        txtUAS = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelSimulasiNA = new javax.swing.JTable();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        txtKodeMK = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 102, 0));

        jLabel1.setFont(new java.awt.Font("Eras Demi ITC", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM SIMULASI NILAI AKHIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(294, 294, 294))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("Nama Mata Kuliah");

        txtNamaMK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtNamaMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaMKActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Kode MK");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setText("Persentase Absen");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("%");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setText("Persentase Tugas");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setText("Persentase UTS");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setText("Persentase UAS");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setText("%");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setText("%");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setText("%");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel12.setText("Kehadiran");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel13.setText("Pertemuan");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setText("Tugas 1");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel15.setText("Tugas 2");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setText("Tugas 3");

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel17.setText("UTS");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel18.setText("UAS");

        tabelSimulasiNA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelSimulasiNA.getTableHeader().setReorderingAllowed(false);
        tabelSimulasiNA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSimulasiNAMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelSimulasiNA);

        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnUbah.setText("UBAH");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setText("BATAL");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnKeluar.setText("KELUAR");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNamaMK, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtKodeMK, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtPersentaseUTS, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtPersentaseUAS, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtPersentaseTugas, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtPersentaseAbsen, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel11)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel5))))
                                        .addGap(67, 67, 67)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel18)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addGap(35, 35, 35))
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel12))
                                        .addGap(59, 59, 59)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtKehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel13))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtUAS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtUTS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTugas3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTugas2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTugas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 93, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtKehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtUTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtUAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNamaMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtKodeMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPersentaseAbsen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPersentaseTugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtPersentaseUTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPersentaseUAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal)
                    .addComponent(btnKeluar))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        String data[] = new String[18];

        char index;
        String kelulusan;
        Double persentaseTgs, persentaseAbsen, persentaseUts, persentaseUas;
        Double absen, tgs1, tgs2, tgs3, uts, uas, nilaiAbsen, nilaiTgs, nilaiUts, nilaiUas, na;

        if (txtKodeMK.getText().trim().equals("") || txtPersentaseAbsen.getText().trim().equals("")
                || txtPersentaseTugas.getText().isEmpty() || txtPersentaseUTS.getText().trim().equals("")
                || txtPersentaseUAS.getText().trim().equals("") || txtTugas1.getText().trim().equals("")
                || txtTugas2.getText().trim().equals("") || txtTugas3.getText().trim().equals("")
                || txtUTS.getText().trim().equals("") || txtUAS.getText().trim().equals("")) {

        } else if (Double.valueOf(txtKehadiran.getText()) > 14) {
            JOptionPane.showMessageDialog(null, "Kehadiram Maksimum adalah 14 Pertemuan", "Peringatan!", JOptionPane.WARNING_MESSAGE);
            txtKehadiran.requestFocus();
        } else if (Double.valueOf(txtTugas1.getText()) > 100 || (Double.valueOf(txtTugas2.getText()) > 100)
                || (Double.valueOf(txtTugas3.getText()) > 100) || (Double.valueOf(txtUTS.getText()) > 100)
                || (Double.valueOf(txtUAS.getText()) > 100)) {
            JOptionPane.showMessageDialog(null, "Nilai Maksimum adalah 100", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        } else if ((Double.valueOf(txtTugas1.getText()) < 0) || (Double.valueOf(txtTugas2.getText()) < 0)
                || (Double.valueOf(txtTugas3.getText()) < 0) || (Double.valueOf(txtUTS.getText()) < 0)
                || (Double.valueOf(txtUAS.getText()) < 0) || (Double.valueOf(txtKehadiran.getText()) < 0)) {
            JOptionPane.showMessageDialog(null, "Angka Minimum adalah 0", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        } else {
            persentaseAbsen = Double.valueOf(txtPersentaseAbsen.getText());
            persentaseTgs = Double.valueOf(txtPersentaseTugas.getText());
            persentaseUts = Double.valueOf(txtPersentaseUTS.getText());
            persentaseUas = Double.valueOf(txtPersentaseUAS.getText());
            absen = Double.valueOf(txtKehadiran.getText());
            tgs1 = Double.valueOf(txtTugas1.getText());
            tgs2 = Double.valueOf(txtTugas2.getText());
            tgs3 = Double.valueOf(txtTugas3.getText());
            uts = Double.valueOf(txtUTS.getText());
            uas = Double.valueOf(txtUAS.getText());

            nilaiAbsen = ((absen / 14) * 100 * persentaseAbsen) / 100;
            nilaiTgs = ((tgs1 + tgs2 + tgs3) / 3) * persentaseTgs / 100;
            nilaiUts = uts * persentaseUts / 100;
            nilaiUas = uas * persentaseUas / 100;
            na = nilaiAbsen + nilaiTgs + nilaiUts + nilaiUas;

            if (na >= 80) {
                index = 'A';
                kelulusan = "LULUS";
            } else if (na >= 68) {
                index = 'B';
                kelulusan = "LULUS";
            } else if (na >= 56) {
                index = 'C';
                kelulusan = "LULUS";
            } else if (na >= 45) {
                index = 'D';
                kelulusan = "Tidak Lulus";
            } else {
                index = 'E';
                kelulusan = "Tidak Lulus";
            }

            if (absen < 11) {
                kelulusan = "Tidak Lulus";
            }
            
            data[0] = txtNamaMK.getSelectedItem().toString();
            data[1] = String.valueOf(persentaseAbsen.intValue());
            data[2] = String.valueOf(persentaseTgs.intValue());
            data[3] = String.valueOf(persentaseUts.intValue());
            data[4] = String.valueOf(persentaseUas.intValue());
            data[5] = String.valueOf(absen.intValue());
            data[6] = String.valueOf(tgs1.intValue());
            data[7] = String.valueOf(tgs2.intValue());
            data[8] = String.valueOf(tgs3.intValue());
            data[9] = String.valueOf(uts.intValue());
            data[10] = String.valueOf(uas.intValue());
            data[11] = String.valueOf(nilaiAbsen.intValue());
            data[12] = String.valueOf(nilaiTgs.intValue());
            data[13] = String.valueOf(nilaiUts.intValue());
            data[14] = String.valueOf(nilaiUas.intValue());
            data[15] = String.valueOf(na.intValue());
            data[16] = String.valueOf(index);
            data[17] = kelulusan;

            tabelModel.removeRow(row);
            tabelModel.insertRow(0, data);
            membersihkan_text();
            btnSimpan.setEnabled(false);
            btnTambah.setEnabled(true);
            nonaktif_text();
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        membersihkan_text();
        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(true);
        aktif_text();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:       
        tabelModel.removeRow(row);
        membersihkan_text();
        nonaktif_text();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String data[] = new String[18];

        char index;
        String kelulusan;
        Double persentaseTgs, persentaseAbsen, persentaseUts, persentaseUas;
        Double absen, tgs1, tgs2, tgs3, uts, uas, nilaiAbsen, nilaiTgs, nilaiUts, nilaiUas, na;

        if (txtKodeMK.getText().trim().equals("") || txtPersentaseAbsen.getText().trim().equals("")
                || txtPersentaseTugas.getText().isEmpty() || txtPersentaseUTS.getText().trim().equals("")
                || txtPersentaseUAS.getText().trim().equals("") || txtTugas1.getText().trim().equals("")
                || txtTugas2.getText().trim().equals("") || txtTugas3.getText().trim().equals("")
                || txtUTS.getText().trim().equals("") || txtUAS.getText().trim().equals("")) {

        } else if (Double.valueOf(txtKehadiran.getText()) > 14) {
            JOptionPane.showMessageDialog(null, "Kehadiram Maksimum adalah 14 Pertemuan", "Peringatan!", JOptionPane.WARNING_MESSAGE);
            txtKehadiran.requestFocus();
        } else if (Double.valueOf(txtTugas1.getText()) > 100 || (Double.valueOf(txtTugas2.getText()) > 100)
                || (Double.valueOf(txtTugas3.getText()) > 100) || (Double.valueOf(txtUTS.getText()) > 100)
                || (Double.valueOf(txtUAS.getText()) > 100)) {
            JOptionPane.showMessageDialog(null, "Nilai Maksimum adalah 100", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        } else if ((Double.valueOf(txtTugas1.getText()) < 0) || (Double.valueOf(txtTugas2.getText()) < 0)
                || (Double.valueOf(txtTugas3.getText()) < 0) || (Double.valueOf(txtUTS.getText()) < 0)
                || (Double.valueOf(txtUAS.getText()) < 0) || (Double.valueOf(txtKehadiran.getText()) < 0)) {
            JOptionPane.showMessageDialog(null, "Angka Minimum adalah 0", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        } else {

            persentaseAbsen = Double.valueOf(txtPersentaseAbsen.getText());
            persentaseTgs = Double.valueOf(txtPersentaseTugas.getText());
            persentaseUts = Double.valueOf(txtPersentaseUTS.getText());
            persentaseUas = Double.valueOf(txtPersentaseUAS.getText());
            absen = Double.valueOf(txtKehadiran.getText());
            tgs1 = Double.valueOf(txtTugas1.getText());
            tgs2 = Double.valueOf(txtTugas2.getText());
            tgs3 = Double.valueOf(txtTugas3.getText());
            uts = Double.valueOf(txtUTS.getText());
            uas = Double.valueOf(txtUAS.getText());

            nilaiAbsen = ((absen / 14) * 100 * persentaseAbsen) / 100;
            nilaiTgs = ((tgs1 + tgs2 + tgs3) / 3) * persentaseTgs / 100;
            nilaiUts = uts * persentaseUts / 100;
            nilaiUas = uas * persentaseUas / 100;
            na = nilaiAbsen + nilaiTgs + nilaiUts + nilaiUas;

            if (na >= 80) {
                index = 'A';
                kelulusan = "LULUS";
            } else if (na >= 68) {
                index = 'B';
                kelulusan = "LULUS";
            } else if (na >= 56) {
                index = 'C';
                kelulusan = "LULUS";
            } else if (na >= 45) {
                index = 'D';
                kelulusan = "Tidak Lulus";
            } else {
                index = 'E';
                kelulusan = "Tidak Lulus";
            }

            if (absen < 11) {
                kelulusan = "Tidak Lulus";
            }

            data[0] = txtNamaMK.getSelectedItem().toString();
            data[1] = String.valueOf(persentaseAbsen.intValue());
            data[2] = String.valueOf(persentaseTgs.intValue());
            data[3] = String.valueOf(persentaseUts.intValue());
            data[4] = String.valueOf(persentaseUas.intValue());
            data[5] = String.valueOf(absen.intValue());
            data[6] = String.valueOf(tgs1.intValue());
            data[7] = String.valueOf(tgs2.intValue());
            data[8] = String.valueOf(tgs3.intValue());
            data[9] = String.valueOf(uts.intValue());
            data[10] = String.valueOf(uas.intValue());
            data[11] = String.valueOf(nilaiAbsen.intValue());
            data[12] = String.valueOf(nilaiTgs.intValue());
            data[13] = String.valueOf(nilaiUts.intValue());
            data[14] = String.valueOf(nilaiUas.intValue());
            data[15] = String.valueOf(na.intValue());
            data[16] = String.valueOf(index);
            data[17] = kelulusan;

            tabelModel.insertRow(0, data);
            membersihkan_text();
            btnSimpan.setEnabled(false);
            btnTambah.setEnabled(true);
            nonaktif_text();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        membersihkan_text();
        nonaktif_text();
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnTambah.setEnabled(true);
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtNamaMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaMKActionPerformed
        // TODO add your handling code here:
        txtNamaMK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!txtNamaMK.getSelectedItem().equals("----- Pilih Nama Mata Kuliah -----")) {
                    try {
                        Class.forName(driver);
                        Connection kon = DriverManager.getConnection(db, user, pass);

                        Statement stt = kon.createStatement();
                        String SQL = "select nomor_mk from mata_kuliah where nama_mk='"
                                + txtNamaMK.getSelectedItem() + "'";
                        ResultSet res = stt.executeQuery(SQL);
                        if (res.next()) {
                            txtKodeMK.setText(res.getString(1));
                        }

                        res.close();
                        stt.close();
                        kon.close();
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
                } else {
                    txtKodeMK.setText("");
                }
            }
        });
    }//GEN-LAST:event_txtNamaMKActionPerformed

    private void tabelSimulasiNAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSimulasiNAMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            tampil_field();
        }
    }//GEN-LAST:event_tabelSimulasiNAMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_simulasiNA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_simulasiNA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_simulasiNA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_simulasiNA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_simulasiNA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelSimulasiNA;
    private javax.swing.JTextField txtKehadiran;
    private javax.swing.JTextField txtKodeMK;
    private javax.swing.JComboBox<String> txtNamaMK;
    private javax.swing.JTextField txtPersentaseAbsen;
    private javax.swing.JTextField txtPersentaseTugas;
    private javax.swing.JTextField txtPersentaseUAS;
    private javax.swing.JTextField txtPersentaseUTS;
    private javax.swing.JTextField txtTugas1;
    private javax.swing.JTextField txtTugas2;
    private javax.swing.JTextField txtTugas3;
    private javax.swing.JTextField txtUAS;
    private javax.swing.JTextField txtUTS;
    // End of variables declaration//GEN-END:variables
}
