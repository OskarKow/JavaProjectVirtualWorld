package wirtualny_swiat.organizmy.zwierzeta;

import java.awt.Point;

import javax.swing.ImageIcon;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.WPROWADZENIE;
import wirtualny_swiat.organizmy.Organizm;
import wirtualny_swiat.swiat.Swiat;
import wirtualny_swiat.wyjatki.WyjatekPoruszanie;
import wirtualny_swiat.wyjatki.WyjatekUmiejetnosc;
import wirtualny_swiat.wyjatki.WyjatekWprowadzenie;

public class Czlowiek extends Zwierze
{
	private int pozostalyCooldown;
	public Czlowiek(int x, int y)
	{
		pozostalyCooldown = 0;
		sila = 5;
		inicjatywa = 10;
		wiek = 0;
		aktualnePolozenie = new Point(x, y);
		poprzedniePolozenie = new Point(x, y);
		typ = TYP_ORGANIZMU.CZLOWIEK;
	}
	public Czlowiek(Point aktualne, Point poprzednie, int sila, int wiek, int pozostalyCooldown)
	{
		this.sila = sila;
		inicjatywa = 5;
		this.wiek = wiek;
		this.pozostalyCooldown = pozostalyCooldown;
		aktualnePolozenie = aktualne;
		poprzedniePolozenie = poprzednie;
		typ = TYP_ORGANIZMU.CZLOWIEK;
	}
	public void Umiejetnosc(Swiat swiat)
	{
		pozostalyCooldown = 5;
		Point sasiedniePola[] = new Point[8];
		Organizm doZniszczenia;
		sasiedniePola[0] = new Point(aktualnePolozenie.x - 1, aktualnePolozenie.y - 1);
		sasiedniePola[1] = new Point(aktualnePolozenie.x, aktualnePolozenie.y - 1);
		sasiedniePola[2] = new Point(aktualnePolozenie.x + 1, aktualnePolozenie.y - 1);
		sasiedniePola[3] = new Point(aktualnePolozenie.x + 1, aktualnePolozenie.y);
		sasiedniePola[4] = new Point(aktualnePolozenie.x + 1, aktualnePolozenie.y + 1);
		sasiedniePola[5] = new Point(aktualnePolozenie.x, aktualnePolozenie.y + 1);
		sasiedniePola[6] = new Point(aktualnePolozenie.x - 1, aktualnePolozenie.y + 1);
		sasiedniePola[7] = new Point(aktualnePolozenie.x - 1, aktualnePolozenie.y);
		for (int i = 0; i < 8; i++)
		{
			if (sasiedniePola[i].x >= 0 && sasiedniePola[i].x < Swiat.SZEROKOSC && 
					sasiedniePola[i].y >= 0 && sasiedniePola[i].y < Swiat.WYSOKOSC)
			{
				if (swiat.getPole(sasiedniePola[i].x, sasiedniePola[i].y) != TYP_ORGANIZMU.PUSTE)
				{
					doZniszczenia = swiat.getOrganizm(sasiedniePola[i]);
					swiat.getDoUsuniecia().dodajOrganizm(doZniszczenia);
					swiat.setPole(sasiedniePola[i], TYP_ORGANIZMU.PUSTE);
				}
			}
		}
	}
	public void akcja(Swiat swiat)
	{
		if(pozostalyCooldown > 0) pozostalyCooldown--;
		try
		{
			poprawneWprowadzenie(swiat);
		}
		catch(WyjatekWprowadzenie e)
		{
			e.ustawKomunikat();
			e.wyswietlKomunikat(swiat.getOkno());
			return;
		}
		Point nowePole = new Point(0,0);
		switch (swiat.getAktualneWprowadzenie())
		{
		case UMIEJETNOSC:
			Umiejetnosc(swiat);
			break;
		case STRZALKA_DOL:
			nowePole = new Point(aktualnePolozenie.x, aktualnePolozenie.y + 1);
			break;
		case STRZALKA_GORA:
			nowePole = new Point(aktualnePolozenie.x, aktualnePolozenie.y - 1);
			break;
		case STRZALKA_LEWO:
			nowePole = new Point(aktualnePolozenie.x - 1, aktualnePolozenie.y);
			break;
		case STRZALKA_PRAWO:
			nowePole = new Point(aktualnePolozenie.x + 1, aktualnePolozenie.y);
			break;
		}
		
		if (swiat.getAktualneWprowadzenie() != WPROWADZENIE.UMIEJETNOSC) poruszSie(swiat, nowePole);
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
	public String toString()
	{
		String opis;
		opis = typJakoString() + " " + sila + " " + wiek + " " + aktualnePolozenie.x + " "
			+ aktualnePolozenie.y + " " + poprzedniePolozenie.x + " " + poprzedniePolozenie.y
			+ " " + pozostalyCooldown + "\r\n";
		return opis;
	}
	public String typJakoString()
	{
		return "Czlowiek";
	}
	public boolean poprawneWprowadzenie(Swiat swiat) throws WyjatekWprowadzenie
	{
		WPROWADZENIE wpr = swiat.getAktualneWprowadzenie();
		switch(wpr)
		{
		case UMIEJETNOSC:
			if(pozostalyCooldown > 0)
				throw new WyjatekUmiejetnosc(pozostalyCooldown);
			break;
		case STRZALKA_GORA:
			if(aktualnePolozenie.y == 0)
				throw new WyjatekPoruszanie(aktualnePolozenie);
			break;
		case STRZALKA_DOL:
			if(aktualnePolozenie.y == Swiat.WYSOKOSC - 1)
				throw new WyjatekPoruszanie(aktualnePolozenie);
			break;
		case STRZALKA_LEWO:
			if(aktualnePolozenie.x == 0)
				throw new WyjatekPoruszanie(aktualnePolozenie);
			break;
		case STRZALKA_PRAWO:
			if(aktualnePolozenie.x == Swiat.SZEROKOSC - 1)
				throw new WyjatekPoruszanie(aktualnePolozenie);
			break;
		}
		return true;
	}
}
