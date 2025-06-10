/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacegrafica.dao;

import interfacegrafica.bd.ConexaoBD;
import Packages.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
    public class ContatoDao {
    private final Connection conexao;
    
    public ContatoDao() {
        this.conexao = new ConexaoBD().getConnection();
    }
    
    public void adicionaContato(Contato contato) {
        String sql = "INSERT INTO Contato " +
                    "(nome, telefone, email, datanascimento, usuario, " +
                    "senha, cpf) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());
            ps.setString(4, contato.getDataNascimento());
            ps.setString(5, contato.getUsuario());
            ps.setString(6, contato.getSenha());
            ps.setString(7, contato.getCpf());
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    public boolean login(String usuario, String senha) {
        String sql = "SELECT * FROM Contato WHERE usuario = ?"
                + " AND senha = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Contato> listaContatos() {
        List<Contato> listaContatos = new ArrayList();
        String sql = "SELECT * FROM Contato";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getInt("id"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setEmail(rs.getString("email"));
                contato.setDataNascimento(rs.getString("datanascimento"));
                contato.setUsuario(rs.getString("usuario"));
                contato.setSenha(rs.getString("senha"));
                contato.setCpf(rs.getString("Cpf"));
                listaContatos.add(contato);
            }
            return listaContatos;
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Contato getContatoById(int id) {
        Contato contato = new Contato();
        String sql = "SELECT * FROM Contato WHERE id = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contato.setId(rs.getInt("id"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setEmail(rs.getString("email"));
                contato.setDataNascimento(rs.getString("dataNascimento"));
                contato.setUsuario(rs.getString("usuario"));
                contato.setSenha(rs.getString("senha"));
                contato.setCpf(rs.getString("Cpf"));
            }
            rs.close();
            ps.close();
            return contato;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void atualizaContato(Contato contato) {
        String sql = "UPDATE Contato SET nome=?, telefone=?,"
                + "email=?, dataNascimento=?, usuario=?,"
                + "senha=?, cpf = ? WHERE id=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());
            ps.setString(4, contato.getDataNascimento());
            ps.setString(5, contato.getUsuario());
            ps.setString(6, contato.getSenha());
            ps.setString(7, contato.getCpf());
            ps.setInt(8, contato.getId());
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeContato(Integer id) {
        String sql = "DELETE FROM Contato WHERE id=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
