package jpa.jsf;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import jpa.ejb.IMeritve;
import jpa.vao.Clani;
import jpa.vao.Meritve;

@ManagedBean(name="Meritve")
@SessionScoped
public class MeritveBean{
	
	@EJB
	private IMeritve ejb;
	
	private Meritve novaMeritev=new Meritve();
	private Meritve izbranaMeritev=new Meritve();
	private Clani izbranClanim =new Clani();
	private Meritve zadnjaMeritev = new Meritve();
	private int izbranSifra;
	
	
	public int getIzbranSifra() {
		return izbranSifra;
	}
	public Meritve getnovaMeritev() {
		return novaMeritev;
	}
	public void setnovaMeritev(Meritve novaMeritev) {
		this.novaMeritev = novaMeritev;
	}
	public Meritve getizbranaMeritev() {
		return izbranaMeritev;
	}
	
	public String setizbranaMeritev(Meritve izbranaMeritev) {
		this.izbranaMeritev = izbranaMeritev;
		return "urediclana";
	}
	
	public void dodajMeritev() {
		try {
			zadnjaMeritev.setSifra(izbranClanim.getSifra());
			novaMeritev=zadnjaMeritev.clone();
			novaMeritev.setDatum_mer(new GregorianCalendar());
			novaMeritev.setSifra(izbranClanim.getSifra());
			novaMeritev.setObseg(novaMeritev.getObseg());
			novaMeritev.setTeza(novaMeritev.getTeza());
			novaMeritev.setVisina(novaMeritev.getVisina());
			ejb.shrani(novaMeritev);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Meritve getZadnjaMeritev() {
		return zadnjaMeritev;
	}
	public void setZadnjaMeritev() {
		int idzadnji=ejb.vrniVseMeritve().size()-1;		
		this.zadnjaMeritev = ejb.vrniZadnjo(idzadnji);
	}
	public Clani getIzbranClanim() {
		return izbranClanim;
	}
	public String IzbranClanim(Clani izbranClanim) {
		this.izbranClanim = izbranClanim;
		this.izbranSifra=izbranClanim.getSifra();
		return "meritve";
	}
	
	public List<Meritve> vrniVseMeritve(int izbranClanim){
		return ejb.vrniVse(izbranClanim);
	}
	public List<Meritve> getVse() {
		return ejb.vrniVse(izbranSifra);
		
	}
	
	public String zbrisi(Meritve izbranClan) {
		ejb.izbrisi(izbranClan);
		return "clani";
	}
}
