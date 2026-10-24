package umg.DAO;

import umg.modelo.CamioneroCamion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamioneroCamionDAO {

    public void insertar(CamioneroCamion cc) throws SQLException {
        String sql = "INSERT INTO camionero_camion (dni_camionero, matricula_camion, fecha) VALUES (?, ?, ?)";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, cc.getDniCamionero());
            ps.setString(2, cc.getMatriculaCamion());
            ps.setDate(3, cc.getFecha());
            ps.executeUpdate();
        }
    }

    public List<CamioneroCamion> listar() throws SQLException {
        List<CamioneroCamion> lista = new ArrayList<>();
        String sql = "SELECT * FROM camionero_camion";
        try (Connection cn = ConexionBD.abrir();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new CamioneroCamion(
                    rs.getInt("id"),
                    rs.getString("dni_camionero"),
                    rs.getString("matricula_camion"),
                    rs.getDate("fecha")
                ));
            }
        }
        return lista;
    }

    public void actualizar(CamioneroCamion cc) throws SQLException {
        String sql = "UPDATE camionero_camion SET dni_camionero=?, matricula_camion=?, fecha=? WHERE id=?";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, cc.getDniCamionero());
            ps.setString(2, cc.getMatriculaCamion());
            ps.setDate(3, cc.getFecha());
            ps.setInt(4, cc.getId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM camionero_camion WHERE id=?";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
