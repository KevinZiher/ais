package Clani;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.mail.Mail;
import strategija.IClanarina;
import strategija.Letna;
import strategija.Mesecna;
import strategija.Obicajna;

@ManagedBean(name = "dodajmeritev")
@SessionScoped
public class Dodajmeritev {
	private List<Meritve> meritve = new ArrayList<>();
	private Meritve nove = new Meritve();
	private Clani izbranaOseba = new Clani();
	private Calendar od = new GregorianCalendar();
	private Calendar doo = new GregorianCalendar();
	private boolean prikazitm = true;
	private boolean prikazteza = false;
	private String izbranaclanarina;
	

	public String getIzbrana() {
		return izbranaclanarina;
	}

	public void setIzbrana(String izbranaclanarina) {
		this.izbranaclanarina = izbranaclanarina;
		System.out.println(izbranaclanarina);
	}

	public Mail poslimail() {
		Mail mail = new Mail();
		mail.setProperties(Mail.defaultProperties());;
		mail.setEmailAdress(izbranaOseba.getEmail());
		mail.setSubject("Potrditev pridobitve èlannarine");
		mail.setMessage("Spoštovani " + izbranaOseba.getIme() + "! \nSporoèamo, da ste uspešno pridobili " + izbranaclanarina + " èlanarino dne: " + new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())+
				". Poteèe vam: "+(izbranaclanarina.equals("Letna")? (new SimpleDateFormat("yyyy-MM-dd").format(cas(Calendar.YEAR,1))): 
					(izbranaclanarina.equals("Mesecna")? (new SimpleDateFormat("yyyy-MM-dd").format(cas(Calendar.MONTH,1))):
						"Ni implementirano"))+". \nHvala, da ste izbrali naš fitnes.\n\nSLP Hubibubi. \nNa ta mail prosim ne odgovarjajte.");
		mail.user("fitneshubilebubile@gmail.com", "bubifitnes");
		return mail;
	}
	public Date cas(int tip,int vrednost) {
		Calendar c = Calendar.getInstance();
		c.add(tip, vrednost);
		return c.getTime();
	}
	public void dodajclanarino() {
		IClanarina clanarina;
		if(izbranaclanarina.equals("Obicajna")) {
			clanarina=new Obicajna();
		}else if(izbranaclanarina.equals("Mesecna")) {
			clanarina=new Mesecna();
		}else {
			clanarina=new Letna();
		}
		clanarina.shrani(izbranaOseba.getSifra(), poslimail());
		
	}

	public void preklopi() {
		prikazitm = !prikazitm;
		prikazteza = !prikazteza;
	}

	public boolean isPrikazitm() {
		return prikazitm;
	}

	public void setPrikazitm(boolean prikazitm) {
		this.prikazitm = prikazitm;
	}

	public boolean isPrikazteza() {
		return prikazteza;
	}

	public void setPrikazteza(boolean prikazteza) {
		this.prikazteza = prikazteza;
	}

	public Calendar getOd() {
		return od;
	}

	public void setOd(Calendar od) {
		this.od = od;
	}

	public Calendar getDoo() {
		return doo;
	}

	public void setDoo(Calendar doo) {
		this.doo = doo;
	}

	public String izberiOsebo(Clani clani) {
		izbranaOseba = clani;
		return "meritve";
	}

	public Clani getIzbranaOseba() {
		return izbranaOseba;
	}

	public void setIzbranaOseba(Clani izbranaOseba) {
		this.izbranaOseba = izbranaOseba;
	}

	public void dodajmeritve() {
		nove.setSifra(izbranaOseba.getSifra());
		try {
			MeritveDAO.getInstanca().izdelaj(nove);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nove = new Meritve();
	}

	public Meritve getNove() {
		return nove;
	}

	public void setNove(Meritve nove) {
		this.nove = nove;
	}

	public List<Meritve> getMeritve() {
		try {
			return meritve = MeritveDAO.getInstanca().najdi(izbranaOseba.getSifra());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setMeritve(List<Meritve> meritve) {
		this.meritve = meritve;
	}

}
