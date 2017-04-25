package wirtualny_swiat.wyswietlanie;

import javax.swing.JPanel;

import wirtualny_swiat.organizmy.Organizm;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.Serializable;

import javax.swing.JLabel;

public class TabelaInformacji extends JPanel
{
	private static int MAKSYMALNA_ILOSC = 20;
	private int iloscKomunikatow;
	private JLabel[] komunikaty;
	public TabelaInformacji()
	{
		super();
		komunikaty = new JLabel[MAKSYMALNA_ILOSC];
		iloscKomunikatow = 0;
		setLayout(new GridLayout(MAKSYMALNA_ILOSC, 1));
	}
	
	public void dodajKomunikat(String tresc, Color kolor)
	{
		if(iloscKomunikatow == MAKSYMALNA_ILOSC)
		{
			JLabel[] nowe = new JLabel[MAKSYMALNA_ILOSC];
			nowe[0] = new JLabel(tresc);
			nowe[0].setForeground(kolor);
			for(int i=1;i<MAKSYMALNA_ILOSC;i++)
			{
				nowe[i] = komunikaty[i-1];
			}
			komunikaty = nowe;
			removeAll();
			for(int i=0;i<MAKSYMALNA_ILOSC;i++)
			{
				add(komunikaty[i]);
			}
		}
		else{
			komunikaty[iloscKomunikatow] = new JLabel(tresc);
			komunikaty[iloscKomunikatow].setForeground(kolor);
			add(komunikaty[iloscKomunikatow]);
			iloscKomunikatow++;
		}
	}
	public void dodajRezultatWalki(Organizm wygrany, Organizm przegrany, int numerTury)
	{
		String komunikat, wspolrzedne;
		wspolrzedne = "(" + wygrany.getAktualnePolozenie().x + "," + wygrany.getAktualnePolozenie().y + ")";
		komunikat = "Tura nr " + numerTury + " na polu: " + wspolrzedne + 
			wygrany.typJakoString() + " zabija " + przegrany.typJakoString() + ".";
		dodajKomunikat(komunikat, Color.BLACK);
	}
	public void dodajRozmnazanie(Point wspolrzedneNowegoOrganizmu, String typ, int numerTury)
	{
		String komunikat, wspolrzedne;
		wspolrzedne = "(" + wspolrzedneNowegoOrganizmu.x + "," + wspolrzedneNowegoOrganizmu.y + ")";
		komunikat = "Tura nr " + numerTury + " na polu: " + wspolrzedne + " urodzil sie nowy " + typ + ".";
		dodajKomunikat(komunikat, Color.BLACK);
	}
	public void dodajZjedzenie(Organizm zwierze, Organizm roslina, int numerTury)
	{
		String komunikat, wspolrzedne;
		wspolrzedne = "(" + zwierze.getAktualnePolozenie().x + "," + zwierze.getAktualnePolozenie().y + ")";
		komunikat = "Tura nr " + numerTury + " na polu: " + wspolrzedne + " " 
			+ zwierze.typJakoString() + " zjada " + roslina.typJakoString() + ".";
		dodajKomunikat(komunikat, Color.BLACK);
	}
	public void dodajZasianie(Point wspolrzedneNowegoOrganizmu, String typ, int numerTury)
	{
		String komunikat, wspolrzedne;
		wspolrzedne = "(" + wspolrzedneNowegoOrganizmu.x + "," + wspolrzedneNowegoOrganizmu.y + ")";
		komunikat = "Tura nr " + numerTury + " na polu: " + wspolrzedne + " wyrosla roslina: " + typ + ".";
		dodajKomunikat(komunikat, Color.BLACK);
	}
	public String toString()
	{
		String wynik;
		wynik = iloscKomunikatow + "\r\n";
		for (int i = 0; i < iloscKomunikatow; i++)
		{
			wynik += (komunikaty[i].getText() + "\r\n");
		}
		return wynik;
	}
}
