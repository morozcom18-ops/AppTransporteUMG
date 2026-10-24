package umg.DAO;

import umg.modelo.Paquete;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaqueteDAO {

    public void insertar(Paquete p) throws SQLException {
        String sql = "INSERT INTO paquete (descripcion, destinatario, direccion_destinatario, dni_camionero) VALUES (?, ?, ?, ?)";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getDescripcion());
            ps.setString(2, p.getDestinatario());
            ps.setString(3, p.getDireccionDestinatario());
            if (p.getDniCamionero() != null && !p.getDniCamionero().trim().isEmpty()) {
                ps.setString(4, p.getDniCamionero());
            } else {
                ps.setNull(4, Types.VARCHAR);
            }
            ps.executeUpdate();
        }
    }

    public List<Paquete> listar() throws SQLException {
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM paquete";
        try (Connection cn = ConexionBD.abrir();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Paquete(
                    rs.getInt("codigo"),
                    rs.getString("descripcion"),
                    rs.getString("destinatario"),
                    rs.getString("direccion_destinatario"),
                    rs.getString("dni_camionero")
                ));
            }
        }
        return lista;
    }

    public void actualizar(Paquete p) throws SQLException {
        String sql = "UPDATE paquete SET descripcion=?, destinatario=?, direccion_destinatario=?, dni_camionero=? WHERE codigo=?";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getDescripcion());
            ps.setString(2, p.getDestinatario());
            ps.setString(3, p.getDireccionDestinatario());
            if (p.getDniCamionero() != null && !p.getDniCamionero().trim().isEmpty()) {
                ps.setString(4, p.getDniCamionero());
            } else {
                ps.setNull(4, Types.VARCHAR);
            }
            ps.setInt(5, p.getCodigo());
            ps.executeUpdate();
        }
    }

    public void eliminar(int codigo) throws SQLException {
        String sql = "DELETE FROM paquete WHERE codigo=?";
        try (Connection cn = ConexionBD.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        }
    }
}
