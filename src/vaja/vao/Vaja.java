package vaja.vao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vaja {

	public Vaja() {
	}

	public Vaja(String sifra, String naziv, String cilj, String naprava, String opis, String slika,	String video) {

	}
	private String sifra;
	private String cilj;
	private String naprava;
	private String opis;
	private String slika;
	private String video;

	@Id
	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getCilj() {
		return cilj;
	}

	public void setCilj(String cilj) {
		this.cilj = cilj;
	}

	public String getNaprava() {
		return naprava;
	}

	public void setNaprava(String naprava) {
		this.naprava = naprava;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}	
}