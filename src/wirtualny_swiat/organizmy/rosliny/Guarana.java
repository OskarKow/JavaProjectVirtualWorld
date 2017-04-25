package wirtualny_swiat.organizmy.rosliny;

import java.awt.Point;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.organizmy.Organizm;
import wirtualny_swiat.swiat.Swiat;

public class Guarana extends Roslina
{
	public Guarana(int x, int y)
	{
		sila = 0;
		inicjatywa = 0;
		aktualnePolozenie = new Point(x, y);
		poprzedniePolozenie = new Point(x, y);
		typ = TYP_ORGANIZMU.GUARANA;
		wiek = 0;
	}
	public Guarana(Point aktualne, Point poprzednie, int sila, int wiek)
	{
		this.sila = sila;
		this.wiek = wiek;
		inicjatywa = 0;
		aktualnePolozenie = aktualne;
		poprzedniePolozenie = poprzednie;
		typ = TYP_ORGANIZMU.GUARANA;
	}
	public void walcz(Swiat swiat, Organizm napastnik)
	{
		swiat.getDoUsuniecia().dodajOrganizm(this);
		swiat.setPole(aktualnePolozenie, napastnik.getTyp());
		swiat.setPole(napastnik.getPoprzedniePolozenie(), TYP_ORGANIZMU.PUSTE);
		swiat.getOkno().getTabelaInformacji().dodajZjedzenie(napastnik, this, swiat.getNumerTury());
		napastnik.zwiekszSile(3);
	}
	public void akcja(Swiat swiat)
	{
		int losowa = swiat.getGeneratorLosowych().nextInt(100 / SZANSA_ZASIANIA);
		if (losowa == 0)
		{
			rozprzestrzeniajSie(swiat);
		}
	}
	public String typJakoString()
	{
		return "Guarana";
	}
}
