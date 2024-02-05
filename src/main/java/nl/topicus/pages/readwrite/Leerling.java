package nl.topicus.pages.readwrite;

import java.io.Serializable;

public class Leerling implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String voornaam, achternaam;

	public String getVoornaam()
	{
		return voornaam;
	}

	public void setVoornaam(String voornaam)
	{
		this.voornaam = voornaam;
	}

	public String getAchternaam()
	{
		return achternaam;
	}

	public void setAchternaam(String achternaam)
	{
		this.achternaam = achternaam;
	}
}
