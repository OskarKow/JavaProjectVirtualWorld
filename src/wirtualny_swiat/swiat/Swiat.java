package wirtualny_swiat.swiat;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.WPROWADZENIE;
import wirtualny_swiat.organizmy.KontenerOrganizmow;
import wirtualny_swiat.organizmy.Organizm;
import wirtualny_swiat.organizmy.rosliny.Guarana;
import wirtualny_swiat.organizmy.rosliny.Mlecz;
import wirtualny_swiat.organizmy.rosliny.Trawa;
import wirtualny_swiat.organizmy.rosliny.WilczeJagody;
import wirtualny_swiat.organizmy.zwierzeta.Antylopa;
import wirtualny_swiat.organizmy.zwierzeta.Czlowiek;
import wirtualny_swiat.organizmy.zwierzeta.Lis;
import wirtualny_swiat.organizmy.zwierzeta.Owca;
import wirtualny_swiat.organizmy.zwierzeta.Wilk;
import wirtualny_swiat.organizmy.zwierzeta.Zolw;
import wirtualny_swiat.wyswietlanie.OknoProgramu;

public class Swiat
{
	public static int SZEROKOSC = 20;
	public static int WYSOKOSC = 20;
	
	private KontenerOrganizmow kontener;
	private KontenerOrganizmow doDodania;
	private KontenerOrganizmow doUsuniecia;
	private int numerTury;
	private TYP_ORGANIZMU[][] plansza;
	private OknoProgramu okno;
	private Random generatorLosowych;
	private WPROWADZENIE aktualneWprowadzenie;
	
	public Swiat()
	{
		kontener = new KontenerOrganizmow();
		doDodania = new KontenerOrganizmow();
		doUsuniecia = new KontenerOrganizmow();
		numerTury = 0;
		okno = new OknoProgramu(this);
		generatorLosowych = new Random();
		plansza = new TYP_ORGANIZMU[WYSOKOSC][SZEROKOSC];
		for(int i=0;i<WYSOKOSC;i++)
		{
			for(int j=0;j<SZEROKOSC;j++)
			{
				plansza[i][j] = TYP_ORGANIZMU.PUSTE;
			}
		}
		TYP_ORGANIZMU[] typy = {TYP_ORGANIZMU.WILK, TYP_ORGANIZMU.OWCA, TYP_ORGANIZMU.ZOLW, TYP_ORGANIZMU.ANTYLOPA, 
				TYP_ORGANIZMU.LIS, TYP_ORGANIZMU.TRAWA, TYP_ORGANIZMU.MLECZ, TYP_ORGANIZMU.GUARANA, 
				TYP_ORGANIZMU.JAGODY, TYP_ORGANIZMU.CZLOWIEK};
		
		Point wspolrzedne[] = new Point[19];
		boolean powtarzaSie = false;
		int randX, randY;
		for (int i = 0; i < 19; i++)
		{
			do
			{
				randX = generatorLosowych.nextInt(SZEROKOSC);
				randY = generatorLosowych.nextInt(WYSOKOSC);
				wspolrzedne[i] = new Point(randX, randY);
				for (int j = 0; j < i; j++)
				{
					if (wspolrzedne[i].x == wspolrzedne[j].x && wspolrzedne[i].y == wspolrzedne[j].y)
					{
						powtarzaSie = true;
						break;
					}
					if (j == i - 1) powtarzaSie = false;
				}
			} while (powtarzaSie);
			doDodania.dodajOrganizm(stworzOrganizm(randX, randY, typy[i / 2]));
			przepiszDoDodania();
			aktualizujPlansze();
		}
	}
	public void aktualizujPlansze()
	{
		Organizm current = null;
		for(int i=0;i<WYSOKOSC;i++)
		{
			for(int j=0;j<SZEROKOSC;j++)
			{
				okno.getPlansza().setIkona(i, j, TYP_ORGANIZMU.PUSTE);
			}
		}
		for(int i=0;i<kontener.getIloscOrganizmow();i++)
		{
			current = kontener.getOrganizm(i);
			okno.getPlansza().setIkona(current.getAktualnePolozenie().y, current.getAktualnePolozenie().x, current.getTyp());
		}
	}
	public void przepiszDoDodania()
	{
		Organizm current = null;
		Point currentCoord;
		for (int i = 0; i < doDodania.getIloscOrganizmow(); i++)
		{
			current = doDodania.getOrganizm(i);
			currentCoord = current.getAktualnePolozenie();

			kontener.dodajOrganizm(current);
			plansza[currentCoord.y][currentCoord.x] = current.getTyp();//ustawiam pole w planszy
		}
		doDodania.wyczysc();
	}
	private void usunDoUsuniecia()
	{
		for (int i = 0; i < doUsuniecia.getIloscOrganizmow(); i++)
		{
			kontener.usunOrganizm(doUsuniecia.getOrganizm(i));
		}
		doUsuniecia.wyczysc();
	}
	public void wykonajTure()
	{
		numerTury++;
		Organizm aktualny = null;
		for (int i = 0; i < kontener.getIloscOrganizmow(); i++)
		{
			aktualny = kontener.getOrganizm(i);
			if (!doUsuniecia.czyZawiera(aktualny))
			{
				aktualny.akcja(this);
			}
		}
		przepiszDoDodania();
		usunDoUsuniecia();
		aktualizujPlansze();
	}
	public void zapiszStanSwiata()
	{
		try {
			System.out.println("jest");
			PrintWriter zapis = new PrintWriter("StanSwiata.txt");
			zapis.print(numerTury + "\r\n");
			zapis.print(SZEROKOSC + " " + WYSOKOSC + "\r\n");
			zapis.print(kontener.toString());
			zapis.print(doDodania.toString());
			zapis.print(doUsuniecia.toString());
			zapis.print(okno.getTabelaInformacji().toString());
			zapis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("wyjatek zapis");
		}
	}
	public void wczytajStanSwiata()
	{
		File plik = new File("StanSwiata.txt");
		try {
			Scanner wczytanie = new Scanner(plik);
			int rozmiarKontenera, iloscKomunikatow;
			int sila, wiek, pozostalyCooldown = 0;
			int aktX, aktY, poprzX, poprzY;
			String typString;
			Point aktualne, poprzednie;
			TYP_ORGANIZMU typ;
			numerTury = wczytanie.nextInt();
			SZEROKOSC = wczytanie.nextInt();
			WYSOKOSC = wczytanie.nextInt();
			wyczyscStanSwiata();
			//3 kontenery
			for(int i=0;i<3;i++)
			{
				rozmiarKontenera = wczytanie.nextInt();
				for(int j=0;j<rozmiarKontenera;j++)
				{
					typString = wczytanie.next();
					typ = Organizm.StringToTyp(typString);
					sila = wczytanie.nextInt();
					wiek = wczytanie.nextInt();
					aktX = wczytanie.nextInt();
					aktY = wczytanie.nextInt();
					poprzX = wczytanie.nextInt();
					poprzY = wczytanie.nextInt();
					aktualne = new Point(aktX, aktY);
					poprzednie = new Point(poprzX, poprzY);
					if(typString.equals("Czlowiek")) pozostalyCooldown = wczytanie.nextInt();
					if (i < 2)
					{
						doDodania.dodajOrganizm(stworzOrganizm(aktualne, poprzednie, sila, wiek, typ, pozostalyCooldown));
					}
					else
					{
						doUsuniecia.dodajOrganizm(stworzOrganizm(aktualne, poprzednie, sila, wiek, typ, pozostalyCooldown));
					}
				}
			}
			iloscKomunikatow = wczytanie.nextInt();
			for(int i=0;i<iloscKomunikatow;i++)
			{
				okno.getTabelaInformacji().dodajKomunikat(wczytanie.nextLine(), Color.BLACK);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		przepiszDoDodania();
		aktualizujPlansze();
	}
	//ustawia swiat na pusty
	public void wyczyscStanSwiata()
	{
		plansza = new TYP_ORGANIZMU[WYSOKOSC][SZEROKOSC];
		for(int i=0;i<WYSOKOSC;i++)
		{
			for(int j=0;j<SZEROKOSC;j++)
			{
				plansza[i][j] = TYP_ORGANIZMU.PUSTE;
			}
		}
		kontener = new KontenerOrganizmow();
		doDodania = new KontenerOrganizmow();
		doUsuniecia = new KontenerOrganizmow();
		numerTury = 0;
		okno.setVisible(false);
		okno = new OknoProgramu(this);
		generatorLosowych = new Random();
	}
	//zwraca organizm na podanych wspolrzednych
	public Organizm getOrganizm(Point wspolrzedne)
	{
		Organizm aktualny = null;
		for (int i = 0; i < kontener.getIloscOrganizmow(); i++)
		{
			aktualny = kontener.getOrganizm(i);
			if (aktualny.getAktualnePolozenie().x == wspolrzedne.x && aktualny.getAktualnePolozenie().y == wspolrzedne.y)
			{
				if (!doUsuniecia.czyZawiera(aktualny)) return aktualny;
			}
		}
		return null;
	}
	//zwraca organizm na x,y pomijajac ten podany jako argument
	public Organizm getOrganizm(Point wspolrzedne, Organizm do_pominiecia)
	{
		Organizm aktualny = null;
		for (int i = 0; i < kontener.getIloscOrganizmow(); i++)
		{
			aktualny = kontener.getOrganizm(i);
			if (aktualny.getAktualnePolozenie().x == wspolrzedne.x && aktualny.getAktualnePolozenie().y == wspolrzedne.y)
			{
				if (aktualny != do_pominiecia)
				{
					if(!doUsuniecia.czyZawiera(aktualny)) return aktualny;
				}
			}
		}
		return null;
	}
	public TYP_ORGANIZMU getPole(int x, int y)
	{
		return plansza[y][x];
	}
	public void setPole(Point pole, TYP_ORGANIZMU typ)
	{
		plansza[pole.y][pole.x] = typ;
	}
	//losuje wolne pole sasiadujace z centralnym
	public Point wylosujWolnePole(Point centralne)
	{
		int iloscWolnych = 0;
		Point wolne[] = new Point[4];
		
		if (centralne.y > 0 && plansza[centralne.y - 1][centralne.x] == TYP_ORGANIZMU.PUSTE)
		{
			wolne[iloscWolnych] = new Point(centralne.x, centralne.y - 1);
			iloscWolnych++;
		}
		if (centralne.x < SZEROKOSC - 1 && plansza[centralne.y][centralne.x + 1] == TYP_ORGANIZMU.PUSTE)
		{
			wolne[iloscWolnych] = new Point(centralne.x + 1, centralne.y);
			iloscWolnych++;
		}
		if (centralne.y < WYSOKOSC - 1 && plansza[centralne.y + 1][centralne.x] == TYP_ORGANIZMU.PUSTE)
		{
			wolne[iloscWolnych] = new Point(centralne.x, centralne.y + 1);
			iloscWolnych++;
		}
		if (centralne.x > 0 && plansza[centralne.y][centralne.x - 1] == TYP_ORGANIZMU.PUSTE)
		{
			wolne[iloscWolnych] = new Point(centralne.x - 1, centralne.y);
			iloscWolnych++;
		}
		//zwracamy flage -1, -1 gdy nie znaleziono wolnego pola
		if (iloscWolnych == 0)
		{
			wolne[0] = new Point(-1, -1);
			return wolne[0];
		}
		int indeks = generatorLosowych.nextInt(iloscWolnych);
		return wolne[indeks];
	}
	public Organizm stworzOrganizm(int x, int y, TYP_ORGANIZMU typ)
	{
		switch (typ)
		{
		case WILK:
			return new Wilk(x, y);
		case ANTYLOPA:
			return new Antylopa(x, y);
		case OWCA:
			return new Owca(x, y);
		case LIS:
			return new Lis(x, y);
		case ZOLW:
			return new Zolw(x, y);
		case CZLOWIEK:
			return new Czlowiek(x, y);
		case TRAWA:
			return new Trawa(x, y);
		case MLECZ:
			return new Mlecz(x, y);
		case GUARANA:
			return new Guarana(x, y);
		case JAGODY:
			return new WilczeJagody(x, y);
		default: return null;
		}
	}
	public KontenerOrganizmow getKontener()
	{
		return kontener;
	}
	public int getNumerTury()
	{
		return numerTury;
	}
	public KontenerOrganizmow getDoDodania()
	{
		return doDodania;
	}
	public KontenerOrganizmow getDoUsuniecia()
	{
		return doUsuniecia;
	}
	public Random getGeneratorLosowych()
	{
		return generatorLosowych;
	}
	public OknoProgramu getOkno()
	{
		return okno;
	}
	public WPROWADZENIE getAktualneWprowadzenie()
	{
		return aktualneWprowadzenie;
	}
	public void setAktualneWprowadzenie(WPROWADZENIE wpr)
	{
		aktualneWprowadzenie = wpr;
	}
	//do wczytywania
	Organizm stworzOrganizm(Point aktualne, Point poprzednie, int sila, int wiek, TYP_ORGANIZMU typ, int pozostalyCooldown)
	{
		switch (typ)
		{
		case WILK:
			return new Wilk(aktualne, poprzednie, sila, wiek);
		case ANTYLOPA:
			return new Antylopa(aktualne, poprzednie, sila, wiek);
		case OWCA:
			return new Owca(aktualne, poprzednie, sila, wiek);
		case LIS:
			return new Lis(aktualne, poprzednie, sila, wiek);
		case ZOLW:
			return new Zolw(aktualne, poprzednie, sila, wiek);
		case CZLOWIEK:
			return new Czlowiek(aktualne, poprzednie, sila, wiek, pozostalyCooldown);
		case TRAWA:
			return new Trawa(aktualne, poprzednie, sila, wiek);
		case MLECZ:
			return new Mlecz(aktualne, poprzednie, sila, wiek);
		case GUARANA:
			return new Guarana(aktualne, poprzednie, sila, wiek);
		case JAGODY:
			return new WilczeJagody(aktualne, poprzednie, sila, wiek);
		}
		//flaga
		return new Mlecz(aktualne, poprzednie, sila, wiek);
	}
}