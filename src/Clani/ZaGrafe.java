package Clani;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import si.test.ZrnceLocal;

@SuppressWarnings("deprecation")
@ManagedBean
public class ZaGrafe {

	private LineChartModel dateModel = null;
	public LineChartModel getTezaGraf(int sifra, Calendar f, Calendar t) {
		narediTezaGraf(sifra, f, t);
		return dateModel;
	}
	public LineChartModel getItmGraf(int sifra, Calendar f, Calendar t) {
		narediItmGraf(sifra, f, t);
		return dateModel;
	}

	public LineChartModel getDateModel() {
		if (dateModel == null) {
			createDateModel();
		}
		return dateModel;
	}

	@EJB
	ZrnceLocal itm;

	private void narediItmGraf(int sifra, Calendar f, Calendar t) {
		dateModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Indeks telesne mase");
		IIterator<Meritve> terminator;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			terminator = MeritveDAO.getInstanca().getTerminator(sifra, f, t);
			while (!terminator.hasFinished()) {
				Meritve trenutna = terminator.next();
				series1.set(format.format(trenutna.getDatum_mer().getTime()), itm.iTM(trenutna.getTeza(), trenutna.getVisina() / 100d));
			}
		} catch (IteratorFinishedException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateModel.addSeries(series1);
		dateModel.setTitle("Približaj");
		dateModel.setZoom(true);
		dateModel.getAxis(AxisType.Y).setLabel("ITM");
		DateAxis axis = new DateAxis("Dnevi");
		axis.setTickAngle(-50);
		axis.setMax(format.format(t.getTime()));
		axis.setMin(format.format(f.getTime()));
		axis.setTickFormat("%b %#d, %y");

		dateModel.getAxes().put(AxisType.X, axis);
	}

	private void narediTezaGraf(int sifra, Calendar f, Calendar t) {
		dateModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Indeks telesne mase");
		IIterator<Meritve> terminator;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			terminator = MeritveDAO.getInstanca().getTerminator(sifra, f, t);
			while (!terminator.hasFinished()) {
				Meritve trenutna = terminator.next();
				System.out.println(trenutna);
				series1.set(format.format(trenutna.getDatum_mer().getTime()), trenutna.getTeza());
			}
		} catch (IteratorFinishedException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateModel.addSeries(series1);
		dateModel.setTitle("Približaj");
		dateModel.setZoom(true);
		dateModel.getAxis(AxisType.Y).setLabel("Teža");
		DateAxis axis = new DateAxis("Dnevi");
		axis.setTickAngle(-50);
		axis.setMax(format.format(t.getTime()));
		axis.setMin(format.format(f.getTime()));
		axis.setTickFormat("%b %#d, %y");

		dateModel.getAxes().put(AxisType.X, axis);
	}
	private void createDateModel() {
		dateModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set("2014-01-01", 51);
		series1.set("2014-01-06", 22);
		series1.set("2014-01-12", 65);
		series1.set("2014-01-18", 74);
		series1.set("2014-01-24", 24);
		series1.set("2014-01-30", 51);

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Series 2");

		dateModel.addSeries(series1);
		dateModel.addSeries(series2);

		dateModel.setTitle("Zoom for Details");
		dateModel.setZoom(true);
		dateModel.getAxis(AxisType.Y).setLabel("Values");
		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setMax("2014-02-01");
		axis.setTickFormat("%b %#d, %y");

		dateModel.getAxes().put(AxisType.X, axis);
	}
}