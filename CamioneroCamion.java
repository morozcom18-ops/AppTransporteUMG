package umg.modelo;

import java.sql.Date;

public class CamioneroCamion {
    private int id;
    private String dniCamionero;
    private String matriculaCamion;
    private Date fecha;

    public CamioneroCamion() {}

    public CamioneroCamion(int id, String dniCamionero, String matriculaCamion, Date fecha) {
        this.id = id;
        this.dniCamionero = dniCamionero;
        this.matriculaCamion = matriculaCamion;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDniCamionero() { return dniCamionero; }
    public void setDniCamionero(String dniCamionero) { this.dniCamionero = dniCamionero; }

    public String getMatriculaCamion() { return matriculaCamion; }
    public void setMatriculaCamion(String matriculaCamion) { this.matriculaCamion = matriculaCamion; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
