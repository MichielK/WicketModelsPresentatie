package nl.topicus.pages.getobject;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import nl.topicus.pages.BasePage;

public class DynamischModelWrapUpPage extends BasePage
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		// Probeer het maken van een nieuw model door getObject van een ander model aan te roepen te voorkomen
		IModel<Persoon> persoonModel = this::getPersoon;

		// dus niet:
		IModel<String> foutNaamModel = new Model<>(persoonModel.getObject().getNaam());
		IModel<Adres> foutAdresModel = new Model<>(persoonModel.getObject().getAdres());
		IModel<String> foutStraatModel = new Model<>(persoonModel.getObject().getAdres().getStraat());

		// maar wel:
		IModel<String> goedNaamModel = persoonModel.map(Persoon::getNaam);
		IModel<Adres> goedAdresModel = persoonModel.map(Persoon::getAdres);

		// prefereer de eerste notatie over de tweede - de eerste heeft automatische null-check
		// (De eerste geeft _geen_ NPE bij een null-adres, de 2e geeft wel een NPE bij een null-adres)
		IModel<String> goedStraatModel = persoonModel.map(Persoon::getAdres).map(Adres::getStraat);
		IModel<String> matigStraatModel = persoonModel.map(persoon -> persoon.getAdres().getStraat());

		// Waarom proberen een nieuw model icm getObject voorkomen? Omdat je op deze manier snel het
		// gevaar loopt weer (onbedoeld) een statisch model te maken.
	}

	private Persoon getPersoon()
	{
		Persoon persoon = new Persoon();
		persoon.setNaam("DummyPersoon");

		Adres adres = new Adres();
		adres.setStraat("Singel");
		adres.setHuisnummer(9);

		persoon.setAdres(adres);

		return persoon;
	}

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return DynamischModelValkuilPage.class;
	}

	public static class Persoon implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private String naam;

		private Adres adres;

		public String getNaam()
		{
			return naam;
		}

		public void setNaam(String naam)
		{
			this.naam = naam;
		}

		public Adres getAdres()
		{
			return adres;
		}

		public void setAdres(Adres adres)
		{
			this.adres = adres;
		}
	}

	public static class Adres implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private String straat;

		private Integer huisnummer;

		public String getStraat()
		{
			return straat;
		}

		public void setStraat(String straat)
		{
			this.straat = straat;
		}

		public Integer getHuisnummer()
		{
			return huisnummer;
		}

		public void setHuisnummer(Integer huisnummer)
		{
			this.huisnummer = huisnummer;
		}
	}
}
