package wirtualny_swiat.organizmy.zwierzeta;

import java.awt.Point;
import java.util.Random;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.organizmy.Organizm;
import wirtualny_swiat.swiat.Swiat;

public abstract class Zwierze extends Organizm
{
	protected Point wylosujSasiedniePole()
	{
		int losowa;
		Random generator = new Random();
		Point nowe = new Point(0,0);
		do
		{
			losowa = generator.nextInt(4);
			switch (losowa)
			{
			case 0:
				nowe.setLocation(aktualnePolozenie.x, aktualnePolozenie.y - 1);
				break;
			case 1:
				nowe.setLocation(aktualnePolozenie.x + 1, aktualnePolozenie.y);
				break;
			case 2:
				nowe.setLocation(aktualnePolozenie.x, aktualnePolozenie.y + 1);
				break;
			case 3:
				nowe.setLocation(aktualnePolozenie.x - 1, aktualnePolozenie.y);
				break;
			}
		} while (nowe.x < 0 || nowe.x > Swiat.SZEROKOSC - 1 || nowe.y < 0 || nowe.y > Swiat.WYSOKOSC - 1);
		return nowe;
	}
	public abstract void kolizja(Swiat swiat);
	public void poruszSie(Swiat swiat, Point docelowe)
	{
		poprzedniePolozenie = aktualnePolozenie;
		aktualnePolozenie = docelowe;
		if (swiat.getPole(docelowe.x, docelowe.y) == TYP_ORGANIZMU.PUSTE)
		{
			swiat.setPole(poprzedniePolozenie, TYP_ORGANIZMU.PUSTE);
			swiat.setPole(aktualnePolozenie, typ);
		}
		else {
			kolizja(swiat);
		}
	}
	public void rozmnazajSie(Swiat swiat)
	{
		aktualnePolozenie = poprzedniePolozenie;
		Point wspolrzedne = swiat.wylosujWolnePole(aktualnePolozenie);
		if (wspolrzedne.x > 0 && wspolrzedne.y > 0)
		{
			swiat.getDoDodania().dodajOrganizm(swiat.stworzOrganizm(wspolrzedne.x, wspolrzedne.y, typ));
			swiat.getOkno().getTabelaInformacji().dodajRozmnazanie(wspolrzedne, this.typJakoString(), swiat.getNumerTury());
		}
	}
	//Ogolny sposob walki dla wiekszosci zwierzat
	public void walcz(Swiat swiat, Organizm napastnik)
	{
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
	//Zwraca true gdy organizm wygra walke z organizmem podanym jako argument
	public boolean wygranaWalka(Organizm napastnik)
	{
		if (this.sila > napastnik.getSila()) return true;
		else return false;
	}
}
