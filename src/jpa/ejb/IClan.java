package jpa.ejb;

import java.util.List;

import jpa.vao.Clani;

public interface IClan {
	Clani najdi(int sifra);
	void shrani(Clani clan);
	void uredi(Clani clan);
	void izbrisi(Clani clan);
	List <Clani> vrniVse();
}
