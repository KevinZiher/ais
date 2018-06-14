package vaja.jsf;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import vaja.ejb.VajaEJB;
import vaja.ejb.IVaja;
import vaja.vao.Vaja;


@ManagedBean(name="vaja")
@SessionScoped
public class VajaBean {

	@EJB
	private IVaja ejb;

	private Vaja novaVaja=new Vaja();
	private Vaja izbranaVaja=new Vaja();
	private String izbranaVajaim;

	public void setIzbranaVajaIm(String vaja) {
		this.izbranaVajaim=vaja;
		try {
			izbranaVaja=ejb.najdi(vaja);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Vaja> getVseVaje(){
		return ejb.vrniVse();
	}
	
	public String getIzbranaVajaIm() {
		return izbranaVajaim;
	}

	public void dodajVajo() {
		try {
			ejb.shrani(novaVaja);
			novaVaja=new Vaja();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vaja getNovaVaja() {
		return novaVaja;
	}

	public void setNovaVaja(Vaja novaVaja) {
		this.novaVaja = novaVaja;
	}

	public Vaja getIzbranaVaja() {
		return izbranaVaja;
	}

	public String setIzbranaVaja(Vaja izbranaVaja) {
		this.izbranaVaja = izbranaVaja;
		return "uredivajo";
	}
	
	public String zbrisi(Vaja izbranaVaja) {
		ejb.izbrisi(izbranaVaja);
		return "vaje";
	}
	public String posodobi(){
		if (ejb.najdi(novaVaja.getSifra())==null) {
			ejb.shrani(novaVaja);
			novaVaja=new Vaja();
		}
		ejb.uredi(novaVaja);
		return "vaje";
	}

}
