package vaja.ejb;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import vaja.vao.Vaja;

@Local
@Stateless
public class VajaEJB implements IVaja{

	@PersistenceContext
	EntityManager em;

	@Override
	public Vaja najdi(String sifra) {
		return em.find(Vaja.class, sifra);
	}

	@Override
	public void shrani(Vaja vaja) {
		em.persist(vaja);

	}

	@Override
	public void uredi(Vaja vaja) {
		em.merge(vaja);

	}

	@Override
	public void izbrisi(Vaja vaja) {
		em.remove(em.contains(vaja)?vaja:em.merge(vaja));

	}

	@Override
	public List<Vaja> vrniVse() {
		return em.createQuery("select v from Vaja v").getResultList();
	}

}
