package wirtualny_swiat.organizmy.zwierzeta;

import java.awt.Point;
import java.util.Random;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.organizmy.Organizm;
import wirtualny_swiat.swiat.Swiat;

public class Zolw extends Zwierze
{
	public Zolw(int x, int y)
	{
		sila = 2;
		inicjatywa = 1;
		wiek = 0;
		aktualnePolozenie = new Point(x, y);
		poprzedniePolozenie = new Point(x, y);
		typ = TYP_ORGANIZMU.ZOLW;
	}
	public Zolw(Point aktualne, Point poprzednie, int sila, int wiek)
	{
		this.sila = sila;
		inicjatywa = 5;
		this.wiek = wiek;
		aktualnePolozenie = aktualne;
		poprzedniePolozenie = poprzednie;
		typ = TYP_ORGANIZMU.ZOLW;
	}
	public void walcz(Swiat swiat, Organizm napastnik)
	{
		if (napastnik.getSila() < 5)
		{
			napastnik.setAktualnePolozenie(napastnik.getPoprzedniePolozenie());
		}
		else {
			if (wygranaWalka(napastnik))
			{
				swiat.getDoUsuniecia().dodajOrganizm(napastnik);
				swiat.setPole(napastnik.getPoprzedniePolozenie(), TYP_ORGANIZMU.PUSTE);
				swiat.getOkno().getTabelaInformacji().dodajRezultatWalki(this, napastnik, swiat.getNumerTury());
			}
			else
			{
				swiat.getDoUsuniecia().dodajOrganizm(this);
				swiat.setPole(aktualnePolozenie, napastnik.getTyp());
				swiat.setPole(napastnik.getPoprzedniePolozenie(), TYP_ORGANIZMU.PUSTE);
				swiat.getOkno().getTabelaInformacji().dodajRezultatWalki(napastnik, this, swiat.getNumerTury());
			}
		}
	}
	public void akcja(Swiat swiat)
	{
		Random generator = new Random();
		int losowa = generator.nextInt(4);
		if (losowa == 0)
		{
			Point nowe_pole = wylosujSasiedniePole();
			poruszSie(swiat, nowe_pole);
		}
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
		return "Zolw";
	}
}
