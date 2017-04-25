package wirtualny_swiat.organizmy.zwierzeta;

import java.awt.Point;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.organizmy.Organizm;
import wirtualny_swiat.swiat.Swiat;

public class Antylopa extends Zwierze
{
	public Antylopa(int x, int y)
	{
		sila = 4;
		inicjatywa = 4;
		wiek = 0;
		aktualnePolozenie = new Point(x, y);
		poprzedniePolozenie = new Point(x, y);
		typ = TYP_ORGANIZMU.ANTYLOPA;
	}
	public Antylopa(Point aktualne, Point poprzednie, int sila, int wiek)
	{
		this.sila = sila;
		inicjatywa = 5;
		this.wiek = wiek;
		aktualnePolozenie = aktualne;
		poprzedniePolozenie = poprzednie;
		typ = TYP_ORGANIZMU.ANTYLOPA;
	}
	public void walcz(Swiat swiat, Organizm napastnik)
	{
		boolean ucieczka = swiat.getGeneratorLosowych().nextBoolean();
		if (ucieczka)
		{
			Point nowePole = swiat.wylosujWolnePole(aktualnePolozenie);
			if (nowePole.x > 0 && nowePole.y > 0)
			{
				swiat.setPole(napastnik.getAktualnePolozenie(), napastnik.getTyp());
				swiat.setPole(napastnik.getPoprzedniePolozenie(), TYP_ORGANIZMU.PUSTE);
				poprzedniePolozenie = aktualnePolozenie;
				aktualnePolozenie = nowePole;
				swiat.setPole(aktualnePolozenie, typ);
				return;
			}
		}
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
	public void akcja(Swiat swiat)
	{
		Point mozliwePola[] = new Point[4];
		int iloscWolnych = 0;
		if (aktualnePolozenie.y > 1)
		{
			mozliwePola[iloscWolnych] = new Point(aktualnePolozenie.x, aktualnePolozenie.y - 2);
			iloscWolnych++;
		}
		if (aktualnePolozenie.x < Swiat.SZEROKOSC - 2)
		{
			mozliwePola[iloscWolnych] = new Point(aktualnePolozenie.x + 2, aktualnePolozenie.y);
			iloscWolnych++;
		}
		if (aktualnePolozenie.y < Swiat.WYSOKOSC - 2)
		{
			mozliwePola[iloscWolnych] = new Point(aktualnePolozenie.x, aktualnePolozenie.y + 2);
			iloscWolnych++;
		}
		if (aktualnePolozenie.x > 1)
		{
			mozliwePola[iloscWolnych] = new Point(aktualnePolozenie.x - 2, aktualnePolozenie.y);
			iloscWolnych++;
		}
		int losowa = swiat.getGeneratorLosowych().nextInt(iloscWolnych);
		poruszSie(swiat, mozliwePola[losowa]);
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
		return "Antylopa";
	}
}
