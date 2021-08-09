/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nama : Muhammad Farhan R <farhan.10119023@mahasiswa.unikom.ac.id>
 * NIM : 10119023 Kelas: IF1
 *
 */
public class model {

    public DefaultTableModel mhs, mk, nilaiMhs, pesanan, listMakanan;

    public model() {
        this.mhs = modelMhs();
        this.mk = modelMk();
        this.nilaiMhs = modelNilaiMhs();
        this.pesanan = modelPesanan();
        this.listMakanan = modelListMakanan();
    }

    private DefaultTableModel modelMhs() {
        return new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "NIM",
                    "Nama",
                    "Tempat Lahir",
                    "Tanggal Lahir",
                    "Alamat"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }

        };
    }

    private DefaultTableModel modelMk() {
        return new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nomor M.K.",
                    "Nama M.K."
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }

        };
    }

    private DefaultTableModel modelNilaiMhs() {
        return new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nama", "Nama M.K.",
                    "Absen", "Tgs1", "Tgs2", "Tgs3",
                    "UTS", "UAS", "Nilai Absen",
                    "Nilai Tugas", "Nilai UTS", "Nilai UAS",
                    "Nilai Akhir", "Index", "Keterangan"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false,
                 false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }

        };
    }       
    
    private DefaultTableModel modelPesanan() {
        return new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nomor Meja", "Daftar Pesanan",
                    "Jumlah", "Total Harga"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }

        };
    }
    
    private DefaultTableModel modelListMakanan() {
        return new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nama Makanan", "Harga"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }

        };
    }
}
