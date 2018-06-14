package strategija;

import java.sql.SQLException;

import Clani.ClanarinaDAO;
import models.mail.Mail;

public class Obicajna implements IClanarina {

	@Override
	public String getIzbrana() {
		// TODO Auto-generated method stub
		return "Obicajna";
	}

	@Override
	public void shrani(int sifra, Mail mail) {
		try {
			ClanarinaDAO.getInstanca().dodajClanarino(sifra, getIzbrana());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		;

	}

}
