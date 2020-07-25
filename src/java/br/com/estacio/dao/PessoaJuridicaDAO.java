/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estacio.dao;

import br.com.estacio.bean.PessoaJuridica;
import br.com.estacio.util.ValidacaoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camilla Bim <camillabim@hotmail.com.br>
 */
public class PessoaJuridicaDAO {

    private final String SQL_LISTAR_PESSOA_JURIDICA = "SELECT * FROM pessoa_juridica";

    public static void excluir(Integer codPessoaJuridica) throws SQLException, ClassNotFoundException {
        //Inicio a conexão com o banco de dados
        Connection conexao = ConnectionFactory.getConnection();
        //Inicio o lancamento de informações com o banco de dados
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM pessoa_juridica WHERE codigo = ?");
        ps.setInt(1, codPessoaJuridica);
        ps.execute();
        ps.close();

    }

    public static void salvar(PessoaJuridica pj) throws SQLException {
        //Inicio a conexão com o banco de dados
        Connection conexao = ConnectionFactory.getConnection();
        //Inicio o lancamento de informações com o banco de dados
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO pessoa_juridica (nome_fantasia, "
                + "razao_social, cnpj, ie, im)VALUES (?,?,?,?,?)");
        ps.setString(1, pj.getNomeFantasia());
        ps.setString(2, pj.getRazaoSocial());
        ps.setString(3, pj.getCnpj());
        ps.setString(4, pj.getInscricaoEstadual());
        ps.setString(5, pj.getInscricaoMunicipal());
        ps.execute();
        ps.close();

    }

    public List<PessoaJuridica> getPessoaJuridica() throws SQLException {
        //Inicio a conexão com o banco de dados
        Connection conexao = ConnectionFactory.getConnection();

        //Inicio o lancamento de informações com o banco de dados
        PreparedStatement ps = conexao.prepareStatement(SQL_LISTAR_PESSOA_JURIDICA);
        ResultSet rs = ps.executeQuery();
        List<PessoaJuridica> pjs = new ArrayList<>();
        while (rs.next()) {
            pjs.add(new PessoaJuridica(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

        }
        return pjs;
    }

    public PessoaJuridica getPessoaJuridicaID(Integer codPessoaJuridica) throws SQLException, ValidacaoException{
        //Inicio a conexão com o banco de dados
        Connection conexao = ConnectionFactory.getConnection();
        //Inicio o lancamento de informações com o banco de dados
        PreparedStatement ps = conexao.prepareStatement("SELECT * FROM pessoa_juridica WHERE codigo = ?");
        ps.setInt(1, codPessoaJuridica);
        //inicio a query/consulta
        ResultSet rs = ps.executeQuery();
        //retorno apenas um fornecedor
        if (rs.next()) {
            return new PessoaJuridica(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

        }
        throw new ValidacaoException("Não foi encontrado fornecedor de código: " + codPessoaJuridica);
    }
    

    public void atualizar(PessoaJuridica pj) throws SQLException, ValidacaoException  {
        //Inicio a conexão com o banco de dados
        Connection conexao = ConnectionFactory.getConnection();
        //Inicio o lancamento de informações com o banco de dados
        PreparedStatement ps = conexao.prepareStatement("UPDATE pessoa_juridica SET nome_fantasia = ?, razao_social = ?, "
                + "cnpj = ?, ie = ?, im = ? WHERE codigo = ?");
        
        ps.setString(1, pj.getNomeFantasia());
        ps.setString(2, pj.getRazaoSocial());
        ps.setString(3, pj.getCnpj());
        ps.setString(4, pj.getInscricaoEstadual());
        ps.setString(5, pj.getInscricaoMunicipal());
        ps.setInt(6, pj.getCodigo());
        ps.execute();
        ps.close();
    }

}
