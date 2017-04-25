package wirtualny_swiat.organizmy;

import java.io.Serializable;

public class KontenerOrganizmow
{
	private int iloscOrganizmow;
	private Organizm[] organizmy;
	
	public KontenerOrganizmow()
	{
		iloscOrganizmow = 0;
		organizmy = null;
	}
	
	public int getIloscOrganizmow()
	{
		return iloscOrganizmow;
	}
	
	public Organizm getOrganizm(int index)
	{
		return organizmy[index];
	}
	//zwraca true gdy A jest wiekszy lub rowny B, false w przeciwnym wypadku
	private boolean porownajOrganizmy(Organizm organizmA, Organizm organizmB)
	{
		if (organizmA.getInicjatywa() > organizmB.getInicjatywa()) return true;
		if (organizmA.getInicjatywa() < organizmB.getInicjatywa()) return false;
		if (organizmA.getInicjatywa() == organizmB.getInicjatywa())
		{
			if (organizmA.getWiek() >= organizmB.getWiek()) return true;
			if (organizmA.getWiek() < organizmB.getWiek()) return false;
		}
		return true;
	}
	private void posortujOrganizmy()
	{
		Organizm tmp = null;
		for (int i = 0; i < iloscOrganizmow - 1; i++)
		{
			for (int j = 0; j < iloscOrganizmow - 1; j++)
			{
				if (!porownajOrganizmy(organizmy[j], organizmy[j + 1]))
				{
					tmp = organizmy[j + 1];
					organizmy[j + 1] = organizmy[j];
					organizmy[j] = tmp;
				}
			}
		}
	}
	public void dodajOrganizm(Organizm nowy)
	{
		iloscOrganizmow++;
		Organizm[] nowa = new Organizm[iloscOrganizmow];
		for (int i = 0; i < iloscOrganizmow - 1; i++)
		{
			nowa[i] = organizmy[i];
		}
		nowa[iloscOrganizmow - 1] = nowy;
		this.organizmy = nowa;
		posortujOrganizmy();
	}
	public void usunOrganizm(Organizm doUsuniecia)
	{
		if (iloscOrganizmow > 0)
		{
			iloscOrganizmow--;
			Organizm[] nowa = new Organizm[iloscOrganizmow];
			int iloscPrzepisanych = 0;
			for (int i = 0; i < iloscOrganizmow + 1; i++)
			{
				if (organizmy[i] != doUsuniecia)
				{
					nowa[iloscPrzepisanych] = organizmy[i];
					iloscPrzepisanych++;
				}
			}
			this.organizmy = nowa;
			posortujOrganizmy();
		}
	}
	public void wyczysc()
	{
		iloscOrganizmow = 0;
		organizmy = null;
	}
	public boolean czyZawiera(Organizm szukany)
	{
		for (int i = 0; i < iloscOrganizmow; i++)
		{
			if (organizmy[i].equals(szukany)) return true;
		}
		return false;
	}
	public String toString()
	{
		String wynik;
		wynik = "" + iloscOrganizmow + "\r\n";
		for (int i = 0; i < iloscOrganizmow; i++)
		{
			wynik += organizmy[i].toString();
		}
		return wynik;
	}
}
