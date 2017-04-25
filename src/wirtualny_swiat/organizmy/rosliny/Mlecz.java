package wirtualny_swiat.organizmy.rosliny;

import java.awt.Point;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.swiat.Swiat;

public class Mlecz extends Roslina
{
	public Mlecz(int x, int y)
	{
		sila = 0;
		inicjatywa = 0;
		aktualnePolozenie = new Point(x, y);
		poprzedniePolozenie = new Point(x, y);
		typ = TYP_ORGANIZMU.MLECZ;
		wiek = 0;
	}
	public Mlecz(Point aktualne, Point poprzednie, int sila, int wiek)
	{
		this.sila = sila;
		this.wiek = wiek;
		inicjatywa = 0;
		aktualnePolozenie = aktualne;
		poprzedniePolozenie = poprzednie;
		typ = TYP_ORGANIZMU.MLECZ;
	}
	public void akcja(Swiat swiat)
	{
		int losowa;
		boolean probaUdana = false;
		for (int i = 0; i < 3; i++)
		{
			losowa = swiat.getGeneratorLosowych().nextInt(100 / SZANSA_ZASIANIA);
			if (losowa == 0) probaUdana = true;
		}
		if (probaUdana) rozprzestrzeniajSie(swiat);
	}
	public String typJakoString()
	{
		return "Mlecz";
	}
	
	
	
	
}
