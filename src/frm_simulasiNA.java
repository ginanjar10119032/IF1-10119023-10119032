
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
    String data[] = new String[5];
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
        setIsiTabel();
        nonaktif_text();

        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnSimpan.setEnabled(false);
    }
    
    private void setColWidth() {
        tabelSimulasiNA.getRowHeight(2);
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
    
    private void setIsiTabel() {
        String stat = "";
        char index;
        String kelulusan;
        Double absen, tgs1, tgs2, tgs3, uts, uas, nilaiAbsen, nilaiTgs, nilaiUts, nilaiUas, na;
        try {

            Class.forName(driver);
            Connection kon = DriverManager.getConnection(db, user, pass);

            Statement stt = kon.createStatement();
            String SQL = "select nama,nama_mk,absensi,tugas1,tugas2,tugas3,uts,uas,nilai_akhir,keterangan "
                    + "from mahasiswa,mata_kuliah,nilai_mhs WHERE nilai_mhs.nim=mahasiswa.nim "
                    + "AND nilai_mhs.nomor_mk=mata_kuliah.nomor_mk";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                absen = Double.valueOf(res.getString(3));
                tgs1 = Double.valueOf(res.getString(4));
                tgs2 = Double.valueOf(res.getString(5));
                tgs3 = Double.valueOf(res.getString(6));
                uts = Double.valueOf(res.getString(7));
                uas = Double.valueOf(res.getString(8));

                nilaiAbsen = ((absen / 14) * 100 * 5) / 100;
                nilaiTgs = ((tgs1 + tgs2 + tgs3) / 3) * 0.25;
                nilaiUts = uts * 0.3;
                nilaiUas = uas * 0.4;

                if (res.getInt(9) >= 80) {
                    index = 'A';
                } else if (res.getInt(9) >= 68) {
                    index = 'B';
                } else if (res.getInt(9) >= 56) {
                    index = 'C';
                } else if (res.getInt(9) >= 45) {
                    index = 'D';
                } else {
                    index = 'E';
                }

                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                data[7] = res.getString(8);
                data[8] = String.valueOf(nilaiAbsen.intValue());
                data[9] = String.valueOf(nilaiTgs.intValue());
                data[10] = String.valueOf(nilaiUts.intValue());
                data[11] = String.valueOf(nilaiUas.intValue());
                data[12] = res.getString(9);
                data[13] = String.valueOf(index);
                data[14] = res.getString(10);
                tabelModel.addRow(data);
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

    public void tampil_field() {
        aktif_text();

        row = tabelSimulasiNA.getSelectedRow();

        String nim, kodeMK;
        nim = tabelSimulasiNA.getValueAt(row, 0).toString();
        kodeMK = tabelSimulasiNA.getValueAt(row, 1).toString();

        txtKodeMK.setText(tabelSimulasiNA.getValueAt(row, 0).toString());
        txtPersentaseAbsen.setText(tabelSimulasiNA.getValueAt(row, 1).toString());
        txtPersentaseTugas.setText(tabelSimulasiNA.getValueAt(row, 2).toString());
        txtPersentaseUTS.setText(tabelSimulasiNA.getValueAt(row, 3).toString());
        txtPersentaseUAS.setText(tabelSimulasiNA.getValueAt(row, 4).toString());
        txtTugas3.setText(tabelSimulasiNA.getValueAt(row, 5).toString());
        txtUTS.setText(tabelSimulasiNA.getValueAt(row, 6).toString());
        txtUAS.setText(tabelSimulasiNA.getValueAt(row, 7).toString());

        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(db, user, pass);

            Statement stt = kon.createStatement();
            String SQL = "select nama from mahasiswa where nim = '" + nim + "'";
            ResultSet res = stt.executeQuery(SQL);
            res.next();
            txtNama.setSelectedItem(res.getString(1));
        } catch (Exception e) {
        }

        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(db, user, pass);

            Statement stt = kon.createStatement();
            String SQL = "select nama_mk from mata_kuliah where nomor_mk = '" + kodeMK + "'";
            ResultSet res = stt.executeQuery(SQL);
            res.next();
            txtNamaMK.setSelectedItem(res.getString(1));
        } catch (Exception e) {
        }

        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
        btnBatal.setEnabled(true);
    }

    public void membersihkan_text() {
        txtNama.setSelectedIndex(0);
        txtNamaMK.setSelectedIndex(0);
        txtNim.setText("");
        txtKodeMK.setText("");
        txtKehadiran.setText("");
        txtTugas1.setText("");
        txtTugas2.setText("");
        txtTugas3.setText("");
        txtUTS.setText("");
        txtUAS.setText("");
        txtAngkatan.setDate(null);
    }

    public void nonaktif_text() {
        txtNama.setEnabled(false);
        txtNamaMK.setEnabled(false);
        txtKehadiran.setEnabled(false);
        txtTugas1.setEnabled(false);
        txtTugas2.setEnabled(false);
        txtTugas3.setEnabled(false);
        txtUTS.setEnabled(false);
        txtUAS.setEnabled(false);
        txtAngkatan.setEnabled(false);
    }

    public void aktif_text() {
        txtNama.setEnabled(true);
        txtNamaMK.setEnabled(true);
        txtKehadiran.setEnabled(true);
        txtTugas1.setEnabled(true);
        txtTugas2.setEnabled(true);
        txtTugas3.setEnabled(true);
        txtUTS.setEnabled(true);
        txtUAS.setEnabled(true);
        txtAngkatan.setEnabled(true);
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
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNamaMK, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKodeMK, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPersentaseAbsen, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPersentaseTugas, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPersentaseUTS, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPersentaseUAS, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtUAS, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtUTS, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(81, 81, 81)
                                .addComponent(txtKehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)))
                .addGap(200, 200, 200))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
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
                    .addComponent(jLabel5)
                    .addComponent(jLabel12)
                    .addComponent(txtKehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPersentaseTugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel14)
                    .addComponent(txtTugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPersentaseUTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtTugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPersentaseUAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtTugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtUTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtUAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
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
        
        String data[] = new String[15];

        char index;
        String kelulusan;
        Double absen, tgs1, tgs2, tgs3, uts, uas, nilaiAbsen, nilaiTgs, nilaiUts, nilaiUas, na;
        absen = Double.valueOf(txtKehadiran.getText());
        tgs1 = Double.valueOf(txtTugas1.getText());
        tgs2 = Double.valueOf(txtTugas2.getText());
        tgs3 = Double.valueOf(txtTugas3.getText());
        uts = Double.valueOf(txtUTS.getText());
        uas = Double.valueOf(txtUAS.getText());

        if ((txtKodeMK.getText().isEmpty()) || (txtNim.getText().isEmpty())
                || txtTugas1.getText().isEmpty() || txtTugas2.getText().isEmpty()
                || txtTugas3.getText().isEmpty() || txtUTS.getText().isEmpty()
                || txtUAS.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong", "Peringatan!", JOptionPane.WARNING_MESSAGE);

        } else if (absen > 14) {
            JOptionPane.showMessageDialog(null, "Kehadiram Maksimum adalah 14 Pertemuan", "Peringatan!", JOptionPane.WARNING_MESSAGE);
            txtKehadiran.requestFocus();
        } else if (tgs1 > 100 || (tgs2 > 100) || (tgs3 > 100)
                || (uts > 100) || (uas > 100)) {
            JOptionPane.showMessageDialog(null, "Nilai Maksimum adalah 100", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        } else if ((tgs1 < 0) || (tgs2 < 0) || (tgs3 < 0)
                || (uts < 0) || (uas < 0) || (absen < 0)) {
            JOptionPane.showMessageDialog(null, "Angka Minimum adalah 0", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        } else {
            nilaiAbsen = ((absen / 14) * 100 * 5) / 100;
            nilaiTgs = ((tgs1 + tgs2 + tgs3) / 3) * 0.25;
            nilaiUts = uts * 0.3;
            nilaiUas = uas * 0.4;
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

            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(db, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE nilai_mhs "
                        + "SET "
                        + "absensi='" + txtKehadiran.getText() + "', "
                        + "tugas1='" + txtTugas1.getText() + "', "
                        + "tugas2='" + txtTugas2.getText() + "', "
                        + "tugas3='" + txtTugas3.getText() + "', "
                        + "uts='" + txtUTS.getText() + "', "
                        + "uas='" + txtUAS.getText() + "', "
                        + "nilai_akhir='" + String.valueOf(na.intValue()) + "', "
                        + "keterangan='" + kelulusan + "' "
                        + "WHERE nim='" + tabelModel.getValueAt(row, 0)
                        + "' AND nomor_mk='" + tabelModel.getValueAt(row, 1) + "'";
                stt.executeUpdate(SQL);

                data[0] = txtNama.getSelectedItem().toString();
                data[1] = txtNamaMK.getSelectedItem().toString();
                data[2] = txtKehadiran.getText();
                data[3] = txtTugas1.getText();
                data[4] = txtTugas2.getText();
                data[5] = txtTugas3.getText();
                data[6] = txtUTS.getText();
                data[7] = txtUAS.getText();
                data[8] = String.valueOf(nilaiAbsen.intValue());
                data[9] = String.valueOf(nilaiTgs.intValue());
                data[10] = String.valueOf(nilaiUts.intValue());
                data[11] = String.valueOf(nilaiUas.intValue());
                data[12] = String.valueOf(na.intValue());
                data[13] = String.valueOf(index);
                data[14] = kelulusan;

                tabelModel.removeRow(row);
                tabelModel.insertRow(row, data);
                stt.close();
                kon.close();
                membersihkan_text();
                btnSimpan.setEnabled(false);
                btnUbah.setEnabled(false);
                btnHapus.setEnabled(false);
                btnTambah.setEnabled(true);
                nonaktif_text();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            }
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
        int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus Data Mahasiswa dengan NIM "
                .concat(txtNim.getText()), "Konfirmasi", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            Double absen, tgs1, tgs2, tgs3, uts, uas, nilaiAbsen, nilaiTgs, nilaiUts, nilaiUas, na;
            absen = Double.valueOf(txtKehadiran.getText());
            tgs1 = Double.valueOf(txtTugas1.getText());
            tgs2 = Double.valueOf(txtTugas2.getText());
            tgs3 = Double.valueOf(txtTugas3.getText());
            uts = Double.valueOf(txtUTS.getText());
            uas = Double.valueOf(txtUAS.getText());

            if ((txtKodeMK.getText().isEmpty()) || (txtNim.getText().isEmpty())
                    || txtTugas1.getText().isEmpty() || txtTugas2.getText().isEmpty()
                    || txtTugas3.getText().isEmpty() || txtUTS.getText().isEmpty()
                    || txtUAS.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong", "Peringatan!", JOptionPane.WARNING_MESSAGE);

            } else if (absen > 14) {
                JOptionPane.showMessageDialog(null, "Kehadiram Maksimum adalah 14 Pertemuan", "Peringatan!", JOptionPane.WARNING_MESSAGE);
                txtKehadiran.requestFocus();
            } else if (tgs1 > 100 || (tgs2 > 100) || (tgs3 > 100)
                    || (uts > 100) || (uas > 100)) {
                JOptionPane.showMessageDialog(null, "Nilai Maksimum adalah 100", "Peringatan!", JOptionPane.WARNING_MESSAGE);
            } else if ((tgs1 < 0) || (tgs2 < 0) || (tgs3 < 0)
                    || (uts < 0) || (uas < 0) || (absen < 0)) {
                JOptionPane.showMessageDialog(null, "Angka Minimum adalah 0", "Peringatan!", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    Class.forName(driver);
                    Connection kon = DriverManager.getConnection(db, user, pass);
                    Statement stt = kon.createStatement();
                    String SQL = "DELETE from nilai_mhs "
                            + "WHERE nim='" + tabelModel.getValueAt(row, 0).toString()
                            + "' AND nomor_mk='" + tabelModel.getValueAt(row, 1) + "'";
                    stt.executeUpdate(SQL);

                    tabelModel.removeRow(row);
                    stt.close();
                    kon.close();
                    membersihkan_text();
                    btnSimpan.setEnabled(false);
                    btnUbah.setEnabled(false);
                    btnHapus.setEnabled(false);
                    btnTambah.setEnabled(true);
                    nonaktif_text();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String data[] = new String[15];

        char index;
        String kelulusan;
        Double absen, tgs1, tgs2, tgs3, uts, uas, nilaiAbsen, nilaiTgs, nilaiUts, nilaiUas, na;

        if ((txtKodeMK.getText().trim().equals("")) || (txtNim.getText().trim().equals(""))
                || txtTugas1.getText().trim().equals("") || txtTugas2.getText().trim().equals("")
                || txtTugas3.getText().trim().equals("") || txtUTS.getText().trim().equals("")
                || txtUAS.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong", "Peringatan!", JOptionPane.WARNING_MESSAGE);

        } else if (Double.valueOf(txtKehadiran.getText()) > 14) {
            JOptionPane.showMessageDialog(null, "Kehadiram Maksimum adalah 14 Pertemuan", "Peringatan!", JOptionPane.WARNING_MESSAGE);
            txtKehadiran.requestFocus();
        } else if (Double.valueOf(txtTugas1.getText()) > 100 || (Double.valueOf(txtTugas2.getText()) > 100) || 
                (Double.valueOf(txtTugas3.getText()) > 100) || (Double.valueOf(txtUTS.getText()) > 100) || 
                (Double.valueOf(txtUAS.getText()) > 100)) {
            JOptionPane.showMessageDialog(null, "Nilai Maksimum adalah 100", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        } else if ((Double.valueOf(txtTugas1.getText()) < 0) || (Double.valueOf(txtTugas2.getText()) < 0) ||
                (Double.valueOf(txtTugas3.getText()) < 0) || (Double.valueOf(txtUTS.getText()) < 0) || 
                (Double.valueOf(txtUAS.getText()) < 0) || (Double.valueOf(txtKehadiran.getText()) < 0)) {
            JOptionPane.showMessageDialog(null, "Angka Minimum adalah 0", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        } else {

            absen = Double.valueOf(txtKehadiran.getText());
            tgs1 = Double.valueOf(txtTugas1.getText());
            tgs2 = Double.valueOf(txtTugas2.getText());
            tgs3 = Double.valueOf(txtTugas3.getText());
            uts = Double.valueOf(txtUTS.getText());
            uas = Double.valueOf(txtUAS.getText());
                    
            nilaiAbsen = ((absen / 14) * 100 * 5) / 100;
            nilaiTgs = ((tgs1 + tgs2 + tgs3) / 3) * 0.25;
            nilaiUts = uts * 0.3;
            nilaiUas = uas * 0.4;
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

            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(db, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "INSERT INTO nilai_mhs "
                        + "VALUES "
                        + "('" + txtNim.getText() + "','"
                        + txtKodeMK.getText() + "','"
                        + txtKehadiran.getText() + "','"
                        + txtTugas1.getText() + "','"
                        + txtTugas2.getText() + "','"
                        + txtTugas3.getText() + "','"
                        + txtUTS.getText() + "','"
                        + txtUAS.getText() + "','"
                        + String.valueOf(na.intValue()) + "','"
                        + kelulusan + "')";
                stt.executeUpdate(SQL);

                data[0] = txtNama.getSelectedItem().toString();
                data[1] = txtNamaMK.getSelectedItem().toString();
                data[2] = txtKehadiran.getText();
                data[3] = txtTugas1.getText();
                data[4] = txtTugas2.getText();
                data[5] = txtTugas3.getText();
                data[6] = txtUTS.getText();
                data[7] = txtUAS.getText();
                data[8] = String.valueOf(nilaiAbsen.intValue());
                data[9] = String.valueOf(nilaiTgs.intValue());
                data[10] = String.valueOf(nilaiUts.intValue());
                data[11] = String.valueOf(nilaiUas.intValue());
                data[12] = String.valueOf(na.intValue());
                data[13] = String.valueOf(index);
                data[14] = kelulusan;

                tabelModel.insertRow(0, data);
                stt.close();
                kon.close();
                membersihkan_text();
                btnSimpan.setEnabled(false);
                btnTambah.setEnabled(true);
                nonaktif_text();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            }
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
