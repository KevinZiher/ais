package jpa.jsf;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import jpa.ejb.IClan;
import jpa.vao.Clani;

@ManagedBean(name="Clani")
@SessionScoped
public class ClanBean {
	
	@EJB
	private IClan ejb;
	
	private Clani novClan=new Clani();
	private Clani izbranClan=new Clani();
	private int izbranClanim;
	
	public Clani getNovClan() {
		return novClan;
	}
	public void setNovClan(Clani novClan) {
		this.novClan = novClan;
	}
	public Clani getIzbranClan() {
		return izbranClan;
	}
	
	
	public String setIzbranClan(Clani izbranClan) {
		this.izbranClan = izbranClan;
		return "urediclana";
	}
	
	public void dodajClana() {
		try {
			ejb.shrani(novClan);
			novClan=new Clani();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getIzbranClanim() {
		return izbranClanim;
	}
	public void setIzbranClanim(int izbranClanim) {
		this.izbranClanim = izbranClanim;
		try {
			izbranClan=ejb.najdi(izbranClanim);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<Clani> getVseClane(){
		return ejb.vrniVse();
	}
	
	public String zbrisi(Clani izbranClan) {
		ejb.izbrisi(izbranClan);
		return "clani";
	}
	public String posodobi() {
		if (ejb.najdi(novClan.getSifra())==null) {
			ejb.shrani(novClan);
			novClan=new Clani();
		}
		ejb.uredi(novClan);
		return "clani";
	}
	
}
