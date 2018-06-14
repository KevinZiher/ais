package strategija;

import java.sql.SQLException;

import Clani.ClanarinaDAO;
import models.mail.Mail;

public class Mesecna implements IClanarina {

	@Override
	public String getIzbrana() {
		// TODO Auto-generated method stub
		return "Mesecna";
	}
	
	@Override
	public void shrani(int sifra,Mail mail) {
		try {
			ClanarinaDAO.getInstanca().dodajClanarino(sifra, getIzbrana());
			mail.send();
		} catch (SQLException e) {
			e.printStackTrace();
		};
		
	}
}
