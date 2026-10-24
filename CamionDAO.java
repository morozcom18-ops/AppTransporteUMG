package umg.DAO;

import umg.modelo.Camion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamionDAO {

    public void insertar(Camion c) throws SQLException {
        String sql = "INSERT INTO camion (matricula, modelo, tipo, potencia) VALUES (?, ?, ?, ?)";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getMatricula());
            ps.setString(2, c.getModelo());
            ps.setString(3, c.getTipo());
            ps.setInt(4, c.getPotencia());
            ps.executeUpdate();
        }
    }

    public List<Camion> listar() throws SQLException {
        List<Camion> lista = new ArrayList<>();
        String sql = "SELECT * FROM camion";
        try (Connection cn = ConexionBD.abrir();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Camion(
                    rs.getString("matricula"),
                    rs.getString("modelo"),
                    rs.getString("tipo"),
                    rs.getInt("potencia")
                ));
            }
        }
        return lista;
    }

    public void actualizar(Camion c) throws SQLException {
        String sql = "UPDATE camion SET modelo=?, tipo=?, potencia=? WHERE matricula=?";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getModelo());
            ps.setString(2, c.getTipo());
            ps.setInt(3, c.getPotencia());
            ps.setString(4, c.getMatricula());
            ps.executeUpdate();
        }
    }

    public void eliminar(String matricula) throws SQLException {
        String sql = "DELETE FROM camion WHERE matricula=?";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, matricula);
            ps.executeUpdate();
        }
    }
}