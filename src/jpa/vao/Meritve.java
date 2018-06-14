package jpa.vao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Meritve implements Cloneable{
	private int sifra;
	private Calendar datum_mer;
	private int visina;
	private int obseg;
	private int teza;
	private int id;
	
	@Override
	public Meritve clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Meritve)super.clone();
	}
	
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Meritve() {
		datum_mer=new GregorianCalendar();
	}

	public Meritve(int sifra, int visina, int obseg, int teza) {
		this();
		this.sifra=sifra;
		this.visina=visina;
		this.obseg=obseg;
		this.teza=teza;
	}
	public Calendar getDatum_mer() {
		return datum_mer;
	}
	public void setDatum_mer(Calendar datum_mer) {
		this.datum_mer = datum_mer;
	}
	public int getVisina() {
		return visina;
	}
	public void setVisina(int visina) {
		this.visina = visina;
	}
	public int getObseg() {
		return obseg;
	}
	public void setObseg(int obseg) {
		this.obseg = obseg;
	}
	public int getTeza() {
		return teza;
	}
	public void setTeza(int teza) {
		this.teza = teza;
	}
	public int getSifra() {
		return sifra;
	}
	public void setSifra(int sifra) {
		this.sifra = sifra;
	}
}