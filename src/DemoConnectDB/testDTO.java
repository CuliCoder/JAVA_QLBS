/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DemoConnectDB;

/**
 *
 * @author NGOC THUC
 */
public class testDTO {
    private String tenTL, trangThai, maTL;

    public testDTO(String tenTL, String trangThai, String maTL) {
        this.tenTL = tenTL;
        this.trangThai = trangThai;
        this.maTL = maTL;
    }

    public String getTenTL() {
        return tenTL;
    }

    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaTL() {
        return maTL;
    }

    public void setMaTL(String maTL) {
        this.maTL = maTL;
    }

    @Override
    public String toString() {
        return "testDTO{" + "tenTL=" + tenTL + ", trangThai=" + trangThai + ", maTL=" + maTL + '}';
    }

    
    
   
}
