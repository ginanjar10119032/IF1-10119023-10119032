
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lenovo
 */
public class frm_transaksi extends javax.swing.JFrame {

    model model = new model();
    private final DefaultTableModel tabelModel = model.listMakanan;
    private final DefaultTableModel tabelModel2 = model.pesanan;

    koneksi setPanel;
    String driver, db, user, pass;
    String dataHargaTotal[] = new String[3];
    String dataListMakanan[] = new String[3];
    int banyakMakananDipesan;
    String dataPesanan[];
    int jumlahPesanan[];
    int rowListMakanan = 0;
    int rowPesanan = 0;

    /**
     * Creates new form frm_transaksi
     */
    public frm_transaksi() {
        initComponents();
        setLocationRelativeTo(null);
        setPanel = new koneksi();
        driver = setPanel.settingPanel("DBDriver");
        db = setPanel.settingPanel("DBDatabase");
        user = setPanel.settingPanel("DBUsername");
        pass = setPanel.settingPanel("DBPassword");

        tabelListMakanan.setModel(tabelModel);
        tabelPesanan.setModel(tabelModel2);

        alignmentTableColumn();
        nonaktif_teks();

        isiTabelListMakanan();
        isiTabelPesanan();

        setColWidthModel1();
        btnUbah.setEnabled(false);
        btnSelesai.setEnabled(false);
        btnSimpan.setEnabled(false);
    }

    private void alignmentTableColumn() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabelPesanan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabelPesanan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tabelListMakanan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tabelListMakanan.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        tabelPesanan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
    }

    private void isiTabelListMakanan() {
        String stat = "";
        try {

            Class.forName(driver);
            Connection kon = DriverManager.getConnection(db, user, pass);

            Statement stt = kon.createStatement();
            String SQL = "select * from list_makanan";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                dataListMakanan[0] = res.getString(1);
                dataListMakanan[1] = res.getString(2);
                dataListMakanan[2] = null;
                tabelModel.addRow(dataListMakanan);
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

    private void isiTabelPesanan() {
        String stat = "";
        try {

            Class.forName(driver);
            Connection kon = DriverManager.getConnection(db, user, pass);

            Statement stt = kon.createStatement();
            String SQL = "select * from pesanan_total_harga";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                dataHargaTotal[0] = res.getString(1);
                dataHargaTotal[1] = "---Klik 2x untuk melihat list pesanan---";
                dataHargaTotal[2] = res.getString(2);
                tabelModel2.addRow(dataHargaTotal);
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

    private void setColWidthModel1() {
        tabelPesanan.getColumnModel().getColumn(0).setPreferredWidth(70);
        tabelPesanan.getColumnModel().getColumn(0).setMaxWidth(70);
        tabelPesanan.getColumnModel().getColumn(0).setMinWidth(70);

        tabelPesanan.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabelPesanan.getColumnModel().getColumn(2).setMaxWidth(150);
        tabelPesanan.getColumnModel().getColumn(2).setMinWidth(150);
    }

    private void membersihkan_teks() {
        int i = tabelListMakanan.getRowCount();
        if (tabelListMakanan.isEditing()) {
            tabelListMakanan.getCellEditor().stopCellEditing();
        }
        while (i > 0) {
            tabelModel.setValueAt("", i - 1, 2);
            i = i - 1;
        }
        tabelListMakanan.clearSelection();
        tabelPesanan.clearSelection();
        txtNoMeja.setText("");
        txtTotal.setText("");
        btnSimpan.setEnabled(false);
        btnSelesai.setEnabled(false);
        btnUbah.setEnabled(false);
    }

    private void nonaktif_teks() {
        tabelListMakanan.enable(false);
        txtNoMeja.setEnabled(false);
    }

    private void aktif_teks() {
        tabelListMakanan.enable(true);
        txtNoMeja.setEnabled(true);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPesanan = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelListMakanan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnUbah = new javax.swing.JButton();
        btnSelesai = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnHitung = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtNoMeja = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        jLabel1.setFont(new java.awt.Font("Eras Demi ITC", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM TRANSAKSI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(24, 24, 24))
        );

        tabelPesanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPesanan.getTableHeader().setReorderingAllowed(false);
        tabelPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPesananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPesanan);

        tabelListMakanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tabelListMakanan.getTableHeader().setReorderingAllowed(false);
        tabelListMakanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelListMakananMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelListMakanan);

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnSelesai.setText("Selesai");
        btnSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelesaiActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSelesai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jLabel2.setText("TOTAL HARGA :");

        btnHitung.setText("HITUNG");
        btnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungActionPerformed(evt);
            }
        });

        jLabel3.setText("RP");

        btnSimpan.setText("Simpan Pesanan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel4.setText("No. Meja :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHitung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(8, 8, 8))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNoMeja)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(txtNoMeja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(20, 20, 20)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnHitung, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addContainerGap())
                        .addComponent(jSeparator1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        try {
            if (txtNoMeja.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Harap isi Nomor Meja", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (tabelListMakanan.isEditing()) {
                    tabelListMakanan.getCellEditor().stopCellEditing();
                }
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(db, user, pass);

                Statement stt = kon.createStatement();
                String SQL = "DELETE from pesanan WHERE no_meja='"
                        + tabelPesanan.getValueAt(rowPesanan, 0) + "'";
                stt.executeUpdate(SQL);

                int row = tabelListMakanan.getRowCount() - 1;
                int totalHarga = 0;
                int hargaTiapMakanan;
                int i = 0;
                while (row >= 0) {
                    if (tabelListMakanan.getValueAt(row, 2) == null
                            || tabelListMakanan.getValueAt(row, 2).toString().trim().equals("")) {
                        hargaTiapMakanan = 0;
                    } else {
                        i = i + 1;
                        banyakMakananDipesan = i;
                        hargaTiapMakanan = Integer.valueOf(tabelListMakanan.getValueAt(row, 1).toString())
                                * Integer.valueOf(tabelListMakanan.getValueAt(row, 2).toString());
                    }
                    totalHarga = totalHarga + hargaTiapMakanan;
                    row = row - 1;
                }
                SQL = "UPDATE pesanan_total_harga SET no_meja='"
                        + txtNoMeja.getText() + "', total_harga='"
                        + totalHarga + "' WHERE no_meja='"
                        + tabelPesanan.getValueAt(rowPesanan, 0) + "'";
                stt.executeUpdate(SQL);
                dataPesanan = new String[i];
                jumlahPesanan = new int[i];
                row = tabelListMakanan.getRowCount() - 1;
                i = 0;
                int j = 0;
                while (j <= row) {
                    if (tabelListMakanan.getValueAt(j, 2) == null
                            || tabelListMakanan.getValueAt(j, 2).toString().trim().equals("")) {
                    } else {
                        i = i + 1;
                        dataPesanan[i - 1] = tabelListMakanan.getValueAt(j, 0).toString();
                        jumlahPesanan[i - 1] = Integer.valueOf(tabelListMakanan.getValueAt(j, 2).toString());
                    }
                    j = j + 1;
                }

                String data[] = new String[3];
                i = banyakMakananDipesan;
                j = 0;
                while (j < i) {
                    SQL = "INSERT INTO pesanan VALUES('"
                            + txtNoMeja.getText() + "','"
                            + dataPesanan[j] + "','"
                            + jumlahPesanan[j] + "')";
                    stt.executeUpdate(SQL);
                    j = j + 1;
                }
                tabelModel2.removeRow(rowPesanan);
                data[0] = txtNoMeja.getText();
                data[1] = "---Klik 2x untuk melihat list pesanan---";
                data[2] = String.valueOf(totalHarga);
                tabelModel2.insertRow(tabelModel2.getRowCount(), data);
                stt.close();
                kon.close();
                membersihkan_teks();
                nonaktif_teks();
                btnUbah.setEnabled(false);
                btnSelesai.setEnabled(false);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void tabelListMakananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelListMakananMouseClicked
        // TODO add your handling code here:
        if (tabelListMakanan.isEnabled()) {
            if (evt.getClickCount() == 1) {
                rowListMakanan = tabelListMakanan.getSelectedRow();
                tabelListMakanan.editCellAt(rowListMakanan, 2);
                tabelListMakanan.getEditorComponent().requestFocus();
            }

        }
    }//GEN-LAST:event_tabelListMakananMouseClicked

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
        // TODO add your handling code here:
        if (tabelListMakanan.isEditing()) {
            tabelListMakanan.getCellEditor().stopCellEditing();
        }

        if (txtNoMeja.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Harap isi Nomor Meja", null, JOptionPane.INFORMATION_MESSAGE);
        } else {
            int row = tabelListMakanan.getRowCount() - 1;
            int totalHarga = 0;
            int hargaTiapMakanan;
            int i = 0;
            while (row >= 0) {
                if (tabelListMakanan.getValueAt(row, 2) == null
                        || tabelListMakanan.getValueAt(row, 2).toString().trim().equals("")) {
                    hargaTiapMakanan = 0;
                } else {
                    i = i + 1;
                    banyakMakananDipesan = i;
                    hargaTiapMakanan = Integer.valueOf(tabelListMakanan.getValueAt(row, 1).toString())
                            * Integer.valueOf(tabelListMakanan.getValueAt(row, 2).toString());
                }
                totalHarga = totalHarga + hargaTiapMakanan;
                row = row - 1;
            }
            dataPesanan = new String[i];
            jumlahPesanan = new int[i];
            row = tabelListMakanan.getRowCount() - 1;
            i = 0;
            int j = 0;
            while (j <= row) {
                if (tabelListMakanan.getValueAt(j, 2) == null
                        || tabelListMakanan.getValueAt(j, 2).toString().trim().equals("")) {
                } else {
                    i = i + 1;
                    dataPesanan[i - 1] = tabelListMakanan.getValueAt(j, 0).toString();
                    jumlahPesanan[i - 1] = Integer.valueOf(tabelListMakanan.getValueAt(j, 2).toString());
                }
                j = j + 1;
            }
            txtTotal.setText(String.valueOf(totalHarga));
        }
    }//GEN-LAST:event_btnHitungActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (txtNoMeja.getText().trim().equals("") || txtTotal.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Jika Sudah Mengisi Pesanan, Mohon Klik Tombol Hitung Sebelum Memasukkan Data",
                    null, JOptionPane.INFORMATION_MESSAGE);
        } else {
            String data[] = new String[3];
            int i = banyakMakananDipesan;
            int j = 0;
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(db, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "INSERT INTO pesanan_total_harga "
                        + "VALUES "
                        + "('" + txtNoMeja.getText() + "','"
                        + txtTotal.getText() + "')";
                stt.executeUpdate(SQL);

                while (j < i) {
                    SQL = "INSERT INTO pesanan VALUES('"
                            + txtNoMeja.getText() + "','"
                            + dataPesanan[j] + "','"
                            + jumlahPesanan[j] + "')";
                    stt.executeUpdate(SQL);
                    j = j + 1;
                }

                data[0] = txtNoMeja.getText();
                data[1] = "---Klik 2x untuk melihat list pesanan---";
                data[2] = txtTotal.getText();
                tabelModel2.insertRow(tabelModel2.getRowCount(), data);
                stt.close();
                kon.close();
                membersihkan_teks();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tabelPesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPesananMouseClicked
        // TODO add your handling code here:
        rowPesanan = tabelPesanan.getSelectedRow();
        if (evt.getClickCount() == 2) {
            tabelListMakanan.editCellAt(0, 2);
            membersihkan_teks();
            aktif_teks();
            btnUbah.setEnabled(true);
            btnSelesai.setEnabled(true);
            try {
                int i = tabelListMakanan.getRowCount();
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(db, user, pass);

                Statement stt = kon.createStatement();
                String SQL = "select COUNT(*) from pesanan WHERE no_meja='"
                        + tabelPesanan.getValueAt(rowPesanan, 0) + "'";
                ResultSet res = stt.executeQuery(SQL);
                res.next();
                banyakMakananDipesan = res.getInt(1);

                SQL = "select * from pesanan WHERE no_meja='"
                        + tabelPesanan.getValueAt(rowPesanan, 0) + "'";
                res = stt.executeQuery(SQL);
                while (res.next()) {
                    int j = 0;
                    while (j < i) {
                        if (tabelListMakanan.getValueAt(j, 0).toString().equals(res.getString(2))) {
                            tabelListMakanan.setValueAt(res.getString(3), j, 2);
                            j = j + 1;
                        } else {
                            j = j + 1;
                        }
                    }
                }
                txtNoMeja.setText(tabelPesanan.getValueAt(rowPesanan, 0).toString());
                txtTotal.setText(tabelPesanan.getValueAt(rowPesanan, 2).toString());
                res.close();
                stt.close();
                kon.close();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
    }//GEN-LAST:event_tabelPesananMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        aktif_teks();
        btnSimpan.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesaiActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(db, user, pass);

            Statement stt = kon.createStatement();
            String SQL = "DELETE from pesanan_total_harga WHERE no_meja='"
                    + tabelPesanan.getValueAt(rowPesanan, 0) + "'";
            stt.executeUpdate(SQL);
            
            tabelModel2.removeRow(rowPesanan);
            
            stt.close();
            kon.close();
            
            membersihkan_teks();
            nonaktif_teks();
            btnSelesai.setEnabled(false);
            btnUbah.setEnabled(false);
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_btnSelesaiActionPerformed

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
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSelesai;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabelListMakanan;
    private javax.swing.JTable tabelPesanan;
    private javax.swing.JTextField txtNoMeja;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
