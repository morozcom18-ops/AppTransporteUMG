package umg.DAO;

import umg.modelo.Camionero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamioneroDAO {

    public void insertar(Camionero c) throws SQLException {
        String sql = "INSERT INTO camionero (dni, nombre, telefono, direccion, poblacion, salario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());
            ps.setString(5, c.getPoblacion());
            ps.setDouble(6, c.getSalario());
            ps.executeUpdate();
        }
    }

    public List<Camionero> listar() throws SQLException {
        List<Camionero> lista = new ArrayList<>();
        String sql = "SELECT * FROM camionero";
        try (Connection cn = ConexionBD.abrir();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Camionero(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("poblacion"),
                    rs.getDouble("salario")
                ));
            }
        }
        return lista;
    }

    public void actualizar(Camionero c) throws SQLException {
        String sql = "UPDATE camionero SET nombre=?, telefono=?, direccion=?, poblacion=?, salario=? WHERE dni=?";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getPoblacion());
            ps.setDouble(5, c.getSalario());
            ps.setString(6, c.getDni());
            ps.executeUpdate();
        }
    }

    public void eliminar(String dni) throws SQLException {
        String sql = "DELETE FROM camionero WHERE dni=?";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, dni);
            ps.executeUpdate();
        }
    }
}
