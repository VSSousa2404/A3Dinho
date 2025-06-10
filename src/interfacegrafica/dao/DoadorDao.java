package interfacegrafica.dao;

import interfacegrafica.bd.ConexaoBD;
import Packages.Doador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Packages.Doador;
import javax.swing.JOptionPane;


public class DoadorDao {
    private final Connection conexao;

    public DoadorDao() {
        this.conexao = new ConexaoBD().getConnection();
    }

    public void adicionaDoador(Doador doador) {
        String sql = "INSERT INTO Doador (Nome, CPF, TipoSanguineo, DataDoacao) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, doador.getNome());
            ps.setString(2, doador.getCpf());
            ps.setString(3, doador.getTipoSanguineo());
            ps.setString(4, doador.getDataDoacao());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Doador> listaDoadores(String nomeFiltro, String cpf, String tipoSanguineoFiltro) {
        List<Doador> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Doador WHERE 1=1");

        List<String> parametros = new ArrayList<>();

            if (nomeFiltro != null && !nomeFiltro.isEmpty()) {
                sql.append(" AND Nome LIKE ?");
                parametros.add("%" + nomeFiltro + "%");
            }
            if (cpf != null && !cpf.isEmpty()) {
                sql.append(" AND cpf = ?");
                parametros.add(cpf.trim());
            }
            if (tipoSanguineoFiltro != null && !tipoSanguineoFiltro.isEmpty()) {
                sql.append(" AND TipoSanguineo = ?");
                parametros.add(tipoSanguineoFiltro.trim());
            }
                        
            try {
                PreparedStatement ps = conexao.prepareStatement(sql.toString());

                for (int i = 0; i < parametros.size(); i++) {
                    ps.setString(i + 1, parametros.get(i));
                }

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Doador doador = new Doador();
                    doador.setNome(rs.getString("Nome"));
                    doador.setCpf(rs.getString("CPF"));
                    doador.setTipoSanguineo(rs.getString("TipoSanguineo"));
                    doador.setDataDoacao(rs.getString("DataDoacao"));
                    lista.add(doador);
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
    }

    return lista;
}


    public void removeDoador(String cpf) {
        String sql = "DELETE FROM Doador WHERE CPF = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizaDoador(Doador doador) {
        String sql = "UPDATE Doador SET Nome = ?, TipoSanguineo = ?, DataDoacao = ? WHERE CPF = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, doador.getNome());
            ps.setString(2, doador.getTipoSanguineo());
            ps.setString(3, doador.getDataDoacao());
            ps.setString(4, doador.getCpf());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Doador getDoadorByCPF(String cpf) {
        String sql = "SELECT * FROM Doador WHERE CPF = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Doador doador = new Doador();
                doador.setNome(rs.getString("Nome"));
                doador.setCpf(rs.getString("CPF"));
                doador.setTipoSanguineo(rs.getString("TipoSanguineo"));
                doador.setDataDoacao(rs.getString("DataDoacao"));
                return doador;
            }
            rs.close();
            ps.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
