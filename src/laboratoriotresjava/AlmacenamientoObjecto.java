/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoriotresjava;

import java.util.HashMap;

public class AlmacenamientoObjecto {

    private String xyEspaciosLimpioss;
    private String xyEspaciosSucioss;
    private String xyEspaciosObstaculoss;
    private Integer cantidadPosicionesRecorridass;
    private Integer porcentajeSuciedadd;
    
    public static HashMap<Integer, AlmacenamientoObjecto> informacionMatricesEIndices = new HashMap<>(); 

    public AlmacenamientoObjecto(String xyEspacioLimpios, String xyEspaciosSucios, String xyEspaciosOstaculos, Integer cantidadPosicionesRecorridas, Integer porcentajeSuciedad) {
        this.xyEspaciosLimpioss = xyEspacioLimpios;
        this.xyEspaciosSucioss = xyEspaciosSucios;
        this.xyEspaciosObstaculoss = xyEspaciosOstaculos;
        this.cantidadPosicionesRecorridass = cantidadPosicionesRecorridas;
        this.porcentajeSuciedadd = porcentajeSuciedad;
    }

    public String getXyEspaciosLimpioss() {
        return xyEspaciosLimpioss;
    }

    public void setXyEspaciosLimpioss(String xyEspaciosLimpioss) {
        this.xyEspaciosLimpioss = xyEspaciosLimpioss;
    }

    public String getXyEspaciosSucioss() {
        return xyEspaciosSucioss;
    }

    public void setXyEspaciosSucioss(String xyEspaciosSucioss) {
        this.xyEspaciosSucioss = xyEspaciosSucioss;
    }

    public String getXyEspaciosObstaculoss() {
        return xyEspaciosObstaculoss;
    }

    public void setXyEspaciosObstaculoss(String xyEspaciosObstaculoss) {
        this.xyEspaciosObstaculoss = xyEspaciosObstaculoss;
    }

    public Integer getCantidadPosicionesRecorridass() {
        return cantidadPosicionesRecorridass;
    }

    public void setCantidadPosicionesRecorridass(Integer cantidadPosicionesRecorridass) {
        this.cantidadPosicionesRecorridass = cantidadPosicionesRecorridass;
    }

    public Integer getPorcentajeSuciedadd() {
        return porcentajeSuciedadd;
    }

    public void setPorcentajeSuciedadd(Integer porcentajeSueciedadd) {
        this.porcentajeSuciedadd = porcentajeSueciedadd;
    }
}
