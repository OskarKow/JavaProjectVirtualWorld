package wirtualny_swiat.organizmy.rosliny;

import java.awt.Point;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.organizmy.Organizm;
import wirtualny_swiat.swiat.Swiat;

public class WilczeJagody extends Roslina
{
	public WilczeJagody(int x, int y)
	{
		sila = 99;
		inicjatywa = 0;
		aktualnePolozenie = new Point(x, y);
		poprzedniePolozenie = new Point(x, y);
		typ = TYP_ORGANIZMU.JAGODY;
		wiek = 0;
	}
	public WilczeJagody(Point aktualne, Point poprzednie, int sila, int wiek)
	{
		this.sila = sila;
		this.wiek = wiek;
		inicjatywa = 0;
		aktualnePolozenie = aktualne;
		poprzedniePolozenie = poprzednie;
		typ = TYP_ORGANIZMU.JAGODY;
	}
	public void akcja(Swiat swiat)
	{
		int losowa = swiat.getGeneratorLosowych().nextInt(100 / SZANSA_ZASIANIA);
		if (losowa == 0)
		{
			rozprzestrzeniajSie(swiat);
		}
	}
	public void walcz(Swiat swiat, Organizm napastnik)
	{
		swiat.setPole(napastnik.getPoprzedniePolozenie(), TYP_ORGANIZMU.PUSTE);
		swiat.getDoUsuniecia().dodajOrganizm(napastnik);
		swiat.getDoUsuniecia().dodajOrganizm(this);
		swiat.setPole(aktualnePolozenie, TYP_ORGANIZMU.PUSTE);
		swiat.getOkno().getTabelaInformacji().dodajZjedzenie(napastnik, this, swiat.getNumerTury());
	}
	public String typJakoString()
	{
		return "Jagody";
	}
}
