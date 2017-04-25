package wirtualny_swiat.organizmy.zwierzeta;

import java.awt.Point;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.organizmy.Organizm;
import wirtualny_swiat.swiat.Swiat;

public class Lis extends Zwierze
{
	public Lis(int x, int y)
	{
		sila = 3;
		inicjatywa = 7;
		wiek = 0;
		aktualnePolozenie = new Point(x, y);
		poprzedniePolozenie = new Point(x, y);
		typ = TYP_ORGANIZMU.LIS;
	}
	public Lis(Point aktualne, Point poprzednie, int sila, int wiek)
	{
		this.sila = sila;
		inicjatywa = 5;
		this.wiek = wiek;
		aktualnePolozenie = aktualne;
		poprzedniePolozenie = poprzednie;
		typ = TYP_ORGANIZMU.LIS;
	}
	public void akcja(Swiat swiat)
	{
		Point nowe_pole = wylosujSasiedniePole();
		if (swiat.getPole(nowe_pole.x, nowe_pole.y) != TYP_ORGANIZMU.PUSTE)
		{
			Organizm naDocelowym = swiat.getOrganizm(nowe_pole);
			if (naDocelowym.getSila() > sila) return;
		}
		poruszSie(swiat, nowe_pole);
	}
	public void kolizja(Swiat swiat)
	{
		Organizm drugi = swiat.getOrganizm(aktualnePolozenie, this);
		if (drugi != null)
		{
			if (drugi.getTyp() == typ)
			{
				rozmnazajSie(swiat);
			}
			else {
				drugi.walcz(swiat, this);
			}
		}
	}
	public String typJakoString()
	{
		return "Lis";
	}
}
