package wirtualny_swiat.wyjatki;

import java.awt.Color;

import wirtualny_swiat.wyswietlanie.OknoProgramu;

public abstract class WyjatekWprowadzenie extends Exception
{
	protected String komunikat;
	public WyjatekWprowadzenie(String tekst)
	{
		super(tekst);
	}
	public void wyswietlKomunikat(OknoProgramu okno)
	{
		okno.getTabelaInformacji().dodajKomunikat(komunikat, Color.RED);
	}
	public abstract void ustawKomunikat();
}
