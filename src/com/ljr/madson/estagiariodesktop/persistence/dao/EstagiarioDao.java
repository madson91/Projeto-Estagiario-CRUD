package com.ljr.madson.estagiariodesktop.persistence.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ljr.madson.estagiariodesktop.persistence.ConnectionFactory;
import com.ljr.madson.estagiariodesktop.persistence.vo.Estagiario;

public class EstagiarioDao {

	public void adciona(Estagiario estagiario) throws ClassNotFoundException,
			SQLException {

		Connection conn = ConnectionFactory.getConnection();
		String sql = "INSERT INTO ESTAGIARIO (CPF, NOME, TEL) VALUES (?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, estagiario.getCpf());
		stmt.setString(2, estagiario.getNome());
		stmt.setInt(3, estagiario.getTel());
		stmt.execute();

		stmt.close();
		conn.close();
	}

	public List<Estagiario> pegaLista() throws ClassNotFoundException,
			SQLException {

		Connection conn = ConnectionFactory.getConnection();
		String sql = "select * from estagiario";
		PreparedStatement stmt = conn.prepareStatement(sql);

		List<Estagiario> listEstagiario = new ArrayList<Estagiario>();
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Estagiario estagiario = new Estagiario();
			estagiario.setCpf(rs.getString("CPF"));
			estagiario.setNome(rs.getString("NOME"));
			estagiario.setTel(rs.getInt("TEL"));

			listEstagiario.add(estagiario);
		}
		stmt.close();
		conn.close();
		rs.close();

		return listEstagiario;
	}

	public void atualizar(Estagiario estagiario) throws ClassNotFoundException,
			SQLException {

		Connection conn = ConnectionFactory.getConnection();
		String sql = "UPDATE ESTAGIARIO SET NOME = ?, TEL = ? WHERE CPF = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, estagiario.getNome());
		stmt.setInt(2, estagiario.getTel());
		stmt.setString(3, estagiario.getCpf());

		stmt.execute();

		stmt.close();
		conn.close();
	}

	public void remove(Estagiario estagiario) throws SQLException,
			ClassNotFoundException {

		Connection conn = ConnectionFactory.getConnection();
		String sql = "DELETE FROM ESTAGIARIO WHERE CPF = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, estagiario.getCpf());

		stmt.execute();

		stmt.close();
		conn.close();
	}
}
