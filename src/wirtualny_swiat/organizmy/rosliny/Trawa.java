package wirtualny_swiat.organizmy.rosliny;

import java.awt.Point;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.swiat.Swiat;

public class Trawa extends Roslina
{
	public Trawa(int x, int y)
	{
		sila = 0;
		inicjatywa = 0;
		aktualnePolozenie = new Point(x, y);
		poprzedniePolozenie = new Point(x, y);
		typ = TYP_ORGANIZMU.TRAWA;
		wiek = 0;
	}
	public Trawa(Point aktualne, Point poprzednie, int sila, int wiek)
	{
		this.sila = sila;
		this.wiek = wiek;
		inicjatywa = 0;
		aktualnePolozenie = aktualne;
		poprzedniePolozenie = poprzednie;
		typ = TYP_ORGANIZMU.TRAWA;
	}
	public void akcja(Swiat swiat)
	{
		int losowa = swiat.getGeneratorLosowych().nextInt(100 / SZANSA_ZASIANIA);
		if (losowa == 0)
		{
			rozprzestrzeniajSie(swiat);
		}
	}
	public String typJakoString() {
		return "Trawa";
	}
}
