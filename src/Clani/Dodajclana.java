package Clani;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="dodajclana")
@SessionScoped
public class Dodajclana {	
	private List<Clani> clan=new ArrayList<>();
	private Clani novi= new Clani();
	
	public void dodajclana() {
		try {
			ClaniDAO.getInstanca().izdelaj(novi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		novi=new Clani();
	}
	public Clani getNovi() {
		return novi;
	}
	public void setNovi(Clani novi) {
		this.novi=novi;
	}
	public List<Clani> getOsebe() {
		try {
			return clan=ClaniDAO.getInstanca().Return();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setOsebe(List<Clani> osebe) {
		this.clan = osebe;
	}
	
}

