package jpa.ejb;

import java.util.List;

import jpa.vao.Meritve;

public interface IMeritve {
	Meritve najdi(int sifra);
	void shrani(Meritve meritev);
	void uredi(Meritve meritev);
	void izbrisi(Meritve meritev);
	List <Meritve> vrniVse(int sifra);
	List <Meritve> vrniVseMeritve();
	Meritve vrniZadnjo(int id);
}
