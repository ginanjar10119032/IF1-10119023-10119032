/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 * Nama : Muhammad Farhan R <farhan.10119023@mahasiswa.unikom.ac.id>
 * NIM  : 10119023
 * Kelas: IF1
 * 
 */

import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

public class koneksi {
    public Properties myPanel,myLanguage;
    private String strNamaPanel;
    public koneksi(){
        
    }
    
    public String settingPanel(String nmPanel){
        try {
            myPanel = new Properties();
            myPanel.load(new FileInputStream("database/database.ini"));
            strNamaPanel = myPanel.getProperty(nmPanel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            System.err.println(e.getMessage());
            System.exit(0);
        }
        
        return strNamaPanel;
    }
}
