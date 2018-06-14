package Clani;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ClaniDAO {
	private	DataSource baza ;
	
	private ClaniDAO() {

		try {
			baza= (DataSource) new InitialContext().lookup("java:jboss/datasources/fitnes");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private static ClaniDAO instanca=null;
	
	public static synchronized ClaniDAO getInstanca() {
		if (instanca==null) instanca=new ClaniDAO();
		return instanca;
	}
	
	public void izdelaj(Clani clani)throws SQLException{
		Connection conn =null;
		try {
			conn=baza.getConnection();
			PreparedStatement SQL_stavek = conn.prepareStatement("INSERT INTO Clani(Sifra ,Ime, Priimek, Spol, datum_Rojstva, datum_Vpisa, email) values (?,?,?,?,?,?,?)");
			SQL_stavek.setInt(1, clani.getSifra());
			SQL_stavek.setString(2, clani.getIme());
			SQL_stavek.setString(3, clani.getPriimek());
			SQL_stavek.setString(4, clani.getSpol());
			SQL_stavek.setTimestamp(5, new Timestamp(clani.getDatum_roj().getTimeInMillis()));
			SQL_stavek.setTimestamp(6, new Timestamp(clani.getDatum_vp().getTimeInMillis()));
			SQL_stavek.setString(7, clani.getEmail());
			SQL_stavek.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}

	public List<Clani> Return() throws SQLException {
		List<Clani> ret = new ArrayList<Clani>();
		Connection conn = null;

		try {
			conn = baza.getConnection();
			ResultSet result = conn.createStatement().executeQuery("select * from Clani");
			while(result.next()) {
				Clani clani = new Clani (result.getString("Ime"),result.getString("Priimek"),result.getInt("Sifra"),result.getString("email") );
				clani.setSpol(result.getString("Spol"));
				clani.getDatum_vp().setTimeInMillis(result.getTimestamp("datum_Vpisa").getTime());
				clani.getDatum_roj().setTimeInMillis(result.getTimestamp("datum_Rojstva").getTime());
				ret.add(clani);
			}
			result.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			conn.close();
		}
		return ret;
	}

	public Clani najdi(int sifra) throws SQLException {
		Clani vrni = null;
		Connection conn = null;

		try {
			conn =baza.getConnection();
			PreparedStatement SQL_stavek = conn.prepareStatement("SELECT * FROM clan WHERE Sifra=? ");
			SQL_stavek.setInt(1, sifra);
			ResultSet result = SQL_stavek.executeQuery();
			while(result.next()) {
				vrni = new Clani(result.getString("Ime"),result.getString("Priimek"), sifra, result.getString("email"));
				vrni.setSpol(result.getString("Spol"));
				vrni.getDatum_vp().setTimeInMillis(result.getTimestamp("datum_Vpisa").getTime());
				vrni.getDatum_roj().setTimeInMillis(result.getTimestamp("datum_Rojstva").getTime());
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return vrni;

	}

}