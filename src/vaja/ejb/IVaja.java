package vaja.ejb;

import java.util.List;

import vaja.vao.Vaja;

public interface IVaja {
	Vaja najdi(String sifra);
	void shrani(Vaja vaja);
	void uredi(Vaja vaja);
	void izbrisi(Vaja vaja);
	List <Vaja> vrniVse();
}
