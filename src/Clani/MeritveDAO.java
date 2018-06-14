package Clani;
import java.sql.Connection;
import java.sql.Date;
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

public class MeritveDAO {
	private DataSource baza;

	private MeritveDAO() {
		try {
			baza = (DataSource) new InitialContext().lookup("java:jboss/datasources/fitnes");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static MeritveDAO instanca = null;

	public static synchronized MeritveDAO getInstanca() {
		if (instanca == null)
			instanca = new MeritveDAO();
		return instanca;
	}

	// ITERMINATOR
	public IIterator<Meritve> getTerminator(int sifra, Calendar f, Calendar t)
			throws IteratorFinishedException, SQLException {
		IIterator<Meritve> meritve = new IIterator<Meritve>() {
			private List<Meritve> meritve = najdi(sifra);
			private int stevec = 0;

			@Override
			public Meritve next() throws IteratorFinishedException {
				if (hasFinished())
					throw new IteratorFinishedException();
				if (meritve.get(stevec).getDatum_mer().after(f) && meritve.get(stevec).getDatum_mer().before(t))
					return meritve.get(stevec++);
				else
					stevec++;
				return next();
			}

			@Override
			public boolean hasFinished() {
				if(stevec<meritve.size())
				return false;
				return true;
			}
		};
		return meritve;

	}

	public void izdelaj(Meritve meritve) throws SQLException {
		Connection conn = null;
		try {
			conn = baza.getConnection();
			PreparedStatement SQL_stavek = conn
					.prepareStatement("INSERT INTO Meritve(Sifra ,Datum_mer, Visina, Obseg, Teza) values (?,?,?,?,?)");
			SQL_stavek.setInt(1, meritve.getSifra());
			SQL_stavek.setTimestamp(2, new Timestamp(meritve.getDatum_mer().getTimeInMillis()));
			SQL_stavek.setInt(3, meritve.getVisina());
			SQL_stavek.setInt(4, meritve.getObseg());
			SQL_stavek.setInt(5, meritve.getTeza());
			SQL_stavek.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public List<Meritve> najdi(int sifra) throws SQLException {
		List<Meritve> ret = new ArrayList<Meritve>();
		Meritve vrni = null;
		Connection conn = null;

		try {
			conn = baza.getConnection();
			PreparedStatement SQL_stavek = conn.prepareStatement("SELECT * FROM Meritve WHERE Sifra=? ");
			SQL_stavek.setInt(1, sifra);
			ResultSet result = SQL_stavek.executeQuery();
			while (result.next()) {
				vrni = new Meritve(sifra, result.getInt("Visina"), result.getInt("Obseg"), result.getInt("Teza"));
				vrni.getDatum_mer().setTimeInMillis(result.getTimestamp("Datum_mer").getTime());
				;
				ret.add(vrni);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return ret;

	}
}
