
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.ComboBox;
import javax.swing.JComboBox;
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
public class frm_nilaimhs extends javax.swing.JFrame {

    /**
     * Creates new form frm_nilaimhs
     */
    model model = new model();
    private DefaultTableModel tabelModel = model.nilaiMhs;

    koneksi setPanel;
    String driver, db, user, pass;
    String data[] = new String[15];
    int row = 0;

    public frm_nilaimhs() {
        initComponents();
        setLocationRelativeTo(null);

        txtNim.setEditable(false);
        txtKodeMK.setEditable(false);

        setPanel = new koneksi();
        driver = setPanel.settingPanel("DBDriver");
        db = setPanel.settingPanel("DBDatabase");
        user = setPanel.settingPanel("DBUsername");
        pass = setPanel.settingPanel("DBPassword");

        tabelNilaiMhs.setModel(tabelModel);
        isiBoxMhs();
        isiBoxMK();
        setIsiTabel();

        nonaktif_text();

        txtAngkatan.setDateFormatString("yyyy");

        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnSimpan.setEnabled(false);
    }

    private void isiBoxMhs() {
        try {
            txtNama.removeAllItems();
            txtNama.addItem("----- Pilih Nama Mahasiswa -----");

            Class.forName(driver);
            Connection kon = DriverManager.getConnection(db, user, pass);

            Statement stt = kon.createStatement();
            String SQL = "select nama from mahasiswa";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                txtNama.addItem(res.getString(1));
                txtAngkatan.setDate(toDate(formatter.format(date)));
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
            String SQL = "select * from nilai_mhs";
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

        row = tabelNilaiMhs.getSelectedRow();

        txtNama.setSelectedItem(tabelNilaiMhs.getValueAt(row, 0).toString());
        txtNamaMK.setSelectedItem(tabelNilaiMhs.getValueAt(row, 1).toString());
        txtKehadiran.setText(tabelNilaiMhs.getValueAt(row, 2).toString());
        txtTugas1.setText(tabelNilaiMhs.getValueAt(row, 3).toString());
        txtTugas2.setText(tabelNilaiMhs.getValueAt(row, 4).toString());
        txtTugas3.setText(tabelNilaiMhs.getValueAt(row, 5).toString());
        txtUTS.setText(tabelNilaiMhs.getValueAt(row, 6).toString());
        txtUAS.setText(tabelNilaiMhs.getValueAt(row, 7).toString());
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
        jPanel9 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtPencarian = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNama = new javax.swing.JComboBox<>();
        txtTugas3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTugas2 = new javax.swing.JTextField();
        txtTugas1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtKehadiran = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtNim = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNamaMK = new javax.swing.JComboBox<>();
        txtKodeMK = new javax.swing.JTextField();
        txtUTS = new javax.swing.JTextField();
        txtUAS = new javax.swing.JTextField();
        scrollpane = new javax.swing.JScrollPane();
        tabelNilaiMhs = new javax.swing.JTable();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        txtAngkatan = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel9.setBackground(new java.awt.Color(0, 153, 102));

        jLabel34.setFont(new java.awt.Font("Eras Demi ITC", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("FORM NILAI MAHASISWA");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("Pencarian Data Mahasiswa");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Masukkan Data");

        txtPencarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPencarianActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setText("Nama");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setText("Angkatan");

        txtNama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setText("Tugas 3");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setText("Tugas 2");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setText("Tugas 1");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setText("Nama Mata Kuliah");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setText("Kehadiran");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setText("Kode MK");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel12.setText("UTS");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("NIM");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel13.setText("UAS");

        txtNamaMK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtNamaMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaMKActionPerformed(evt);
            }
        });

        tabelNilaiMhs.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollpane.setViewportView(tabelNilaiMhs);

        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnUbah.setText("UBAH");

        btnHapus.setText("HAPUS");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollpane)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNim, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNamaMK, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUAS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUTS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKodeMK, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtKehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtTugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtTugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtKodeMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtUTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtUAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(txtAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal)
                    .addComponent(btnKeluar))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPencarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPencarianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPencarianActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

    SimpleDateFormat formatter = new SimpleDateFormat("YYYY");
    Date date = new Date(System.currentTimeMillis());

    private java.util.Date toDate(String date) {
        java.util.Date date2 = new java.util.Date();
        try {
            date2 = new SimpleDateFormat("yyyy").parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date2;
    }

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
        txtNama.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!txtNama.getSelectedItem().equals("----- Pilih Nama Mahasiswa -----")) {
                    try {
                        Class.forName(driver);
                        Connection kon = DriverManager.getConnection(db, user, pass);

                        Statement stt = kon.createStatement();
                        String SQL = "select nim from mahasiswa where nama='"
                                + txtNama.getSelectedItem() + "'";
                        ResultSet res = stt.executeQuery(SQL);
                        if (res.next()) {
                            txtNim.setText(res.getString(1));
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
                    txtNim.setText("");
                }
            }
        });
    }//GEN-LAST:event_txtNamaActionPerformed

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

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        membersihkan_text();
        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(true);
        aktif_text();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String data[] = new String[14];

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
            java.util.logging.Logger.getLogger(frm_nilaimhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_nilaimhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_nilaimhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_nilaimhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_nilaimhs().setVisible(true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JScrollPane scrollpane;
    private javax.swing.JTable tabelNilaiMhs;
    private com.toedter.calendar.JDateChooser txtAngkatan;
    private javax.swing.JTextField txtKehadiran;
    private javax.swing.JTextField txtKodeMK;
    private javax.swing.JComboBox<String> txtNama;
    private javax.swing.JComboBox<String> txtNamaMK;
    private javax.swing.JTextField txtNim;
    private javax.swing.JTextField txtPencarian;
    private javax.swing.JTextField txtTugas1;
    private javax.swing.JTextField txtTugas2;
    private javax.swing.JTextField txtTugas3;
    private javax.swing.JTextField txtUAS;
    private javax.swing.JTextField txtUTS;
    // End of variables declaration//GEN-END:variables
}
