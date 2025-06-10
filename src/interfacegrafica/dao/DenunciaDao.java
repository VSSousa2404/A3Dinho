package interfacegrafica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Packages.Denuncia;
import java.util.UUID;
public class DenunciaDao {
    private Connection conexao;

    public DenunciaDao(Connection conexao) {
        this.conexao = conexao;
    }

    public String cadastrarDenuncia(Denuncia denuncia) throws SQLException {
        String protocolo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        denuncia.setProtocolo(protocolo);

        String sql = "INSERT INTO denuncia (titulo, profissional, data, local, descricao, protocolo) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, denuncia.getTitulo());
        stmt.setString(2, denuncia.getNomeProfissionalOuInstituicao());
        stmt.setString(3, denuncia.getDataOcorrido());
        stmt.setString(4, denuncia.getLocalOcorrido());
        stmt.setString(5, denuncia.getDescricaoDetalhada());
        stmt.setString(6, protocolo);

        stmt.executeUpdate();
        stmt.close();

        return protocolo;
    }
    
    public Denuncia buscarPorProtocolo(String protocolo) throws SQLException {
        String sql = "SELECT * FROM denuncia WHERE protocolo = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, protocolo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Denuncia denuncia = new Denuncia();
            denuncia.setProtocolo(rs.getString("protocolo"));
            denuncia.setTitulo(rs.getString("titulo"));
            denuncia.setNomeProfissionalOuInstituicao(rs.getString("profissional"));
            denuncia.setDataOcorrido(rs.getString("data"));
            denuncia.setLocalOcorrido(rs.getString("local"));
            denuncia.setDescricaoDetalhada(rs.getString("descricao"));
            return denuncia;
        }

    return null;
}
}
