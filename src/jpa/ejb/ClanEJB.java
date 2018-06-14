package jpa.ejb;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpa.vao.Clani;

@Local
@Stateless
public class ClanEJB implements IClan{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Clani najdi(int sifra) {
		return em.find(Clani.class, sifra);
	}

	@Override
	public void shrani(Clani clan) {
		em.persist(clan);	
	}

	@Override
	public void uredi(Clani clan) {
		em.merge(clan);
		
	}

	@Override
	public void izbrisi(Clani clan) {
		em.remove(em.contains(clan)?clan:em.merge(clan));
		
	}

	@Override
	public List<Clani> vrniVse() {
		return em.createQuery("select c from Clani c").getResultList();
	}

}
