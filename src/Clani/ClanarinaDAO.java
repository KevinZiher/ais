package Clani;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ClanarinaDAO {
	private DataSource baza;
	private ClanarinaDAO() {
		try {
			baza = (DataSource) new InitialContext().lookup("java:jboss/datasources/fitnes");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static ClanarinaDAO instanca = null;

	public static synchronized ClanarinaDAO getInstanca() {
		if (instanca == null)
			instanca = new ClanarinaDAO();
		return instanca;
	}

	public void dodajClanarino(int sifra, String clanarina) throws SQLException {
		Connection conn = null;
		try {
			conn = baza.getConnection();
			PreparedStatement SQL_stavek = conn
					.prepareStatement("INSERT INTO clanarina(Vrsta ,KdoimaID, datumpridobitve) values (?,?,?)");
			SQL_stavek.setString(1, clanarina);
			SQL_stavek.setInt(2, sifra);
			SQL_stavek.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			SQL_stavek.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
	
	public String najdiClanarino(int sifra) throws SQLException {
		String vrni = null;
		Connection conn = null;

		try {
			conn = baza.getConnection();
			PreparedStatement SQL_stavek = conn.prepareStatement("SELECT * FROM Clanarina WHERE KdoimaID=? ");
			SQL_stavek.setInt(1, sifra);
			ResultSet result = SQL_stavek.executeQuery();
			while (result.next()) {
				vrni = result.getString("Vrsta");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return vrni;

	}
	
	public List<Meritve> Return() throws SQLException {
		List<Meritve> ret = new ArrayList<Meritve>();
		Connection conn = null;

		try {
			conn = baza.getConnection();
			ResultSet result = conn.createStatement().executeQuery("select * from Meritve");
			while (result.next()) {
				Meritve meritve = new Meritve(result.getInt("Sifra"), result.getInt("Visina"), result.getInt("Obseg"),
						result.getInt("Teza"));
				meritve.getDatum_mer().setTimeInMillis(result.getTimestamp("Datum_mer").getTime());
				ret.add(meritve);
			}
			result.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			conn.close();
		}
		return ret;
	}
}
