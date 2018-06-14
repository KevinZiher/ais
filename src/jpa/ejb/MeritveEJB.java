package jpa.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpa.vao.Meritve;
@Local
@Stateless
public class MeritveEJB implements IMeritve{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Meritve najdi(int sifra) {
		return em.find(Meritve.class, sifra);
	}

	@Override
	public void shrani(Meritve meritev) {
		em.merge(meritev);

	}

	@Override
	public void uredi(Meritve meritev) {
		em.merge(meritev);
		
	}

	@Override
	public void izbrisi(Meritve meritev) {
		em.remove(em.contains(meritev)?meritev:em.merge(meritev));
		
	}

	@Override
	public List<Meritve> vrniVse(int sifra) {
		return em.createQuery("select c from Meritve c where Sifra ="+sifra).getResultList();
	}

	@Override
	public Meritve vrniZadnjo(int id) {
		return (Meritve) em.createQuery("select c from Meritve c where id ="+id).getResultList();
	}

	@Override
	public List<Meritve> vrniVseMeritve() {
		return em.createQuery("select c from Meritve c").getResultList();
	}

}
