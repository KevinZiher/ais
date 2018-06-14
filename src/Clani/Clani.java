package Clani;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clani {

	private String ime;
	private String priimek;
	private String spol;
	private Calendar datum_roj;
	private Calendar datum_vpisa;
	private int sifra;
	private String email;
	
	public Clani() {
		datum_roj=new GregorianCalendar();
		datum_vpisa= new GregorianCalendar();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Clani(String ime, String priimek, int sifra,String email) {
		this();
		this.ime=ime;
		this.priimek=priimek;
		this.sifra=sifra;
		this.email=email;
	}

	public void setIme(String ime) {
		this.ime=ime;
	}
	public String getIme() {
		return ime;
	}	
	public void setPriimek(String priimek) {
		this.priimek=priimek;
	}
	public String getPriimek() {
		return priimek;
	}	
	public void setSpol(String spol) {
		this.spol=spol;
	}
	public String getSpol() {
		return spol;
	}
	public void setDatum_roj(Calendar datum_roj) {
		this.datum_roj=datum_roj;
	}
	public Calendar getDatum_roj() {
		return datum_roj;
	}
	public void setDatum_vp(Calendar datum_vpisa) {
		this.datum_vpisa=datum_vpisa;
	}
	public Calendar getDatum_vp() {
		return datum_vpisa;
	}
	public void setSifra(int sifra) {
		this.sifra=sifra;
	}
	public int getSifra() {
		return sifra;
	}	
}
