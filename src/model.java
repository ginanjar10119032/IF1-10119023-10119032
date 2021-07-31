/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 
 * Nama : Muhammad Farhan R <farhan.10119023@mahasiswa.unikom.ac.id>
 * NIM  : 10119023
 * Kelas: IF1
 * 
 */
public class model {
    public DefaultTableModel mhs,mk;
    
    public model(){
        this.mhs = modelMhs();
        this.mk = modelMk();
    }
    
    private DefaultTableModel modelMhs(){
        return new DefaultTableModel(
                new Object[][] {},
                new String[] {
                    "NIM",
                    "Nama",
                    "Tempat Lahir",
                    "Tanggal Lahir",
                    "Alamat"
                }
                
        )
        {
          boolean[] canEdit = new boolean[]{
            false,false,false,false,false
          };
          
          public boolean tableCellEdit(int rowIndex, int colIndex){
              return canEdit[colIndex];
          }
          
        };
    }
    
    private DefaultTableModel modelMk(){
        return new DefaultTableModel(
                new Object[][] {},
                new String[] {
                    "Nomor M.K.",
                    "Nama M.K."
                }
                
        )
        {
          boolean[] canEdit = new boolean[]{
            false,false
          };
          
          public boolean tableCellEdit(int rowIndex, int colIndex){
              return canEdit[colIndex];
          }
          
        };
    }
}
