package wirtualny_swiat.organizmy;

import java.awt.Point;
import java.io.Serializable;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.swiat.Swiat;

public abstract class Organizm
{
	protected int sila;
	protected int inicjatywa;
	protected int wiek;
	protected Point aktualnePolozenie;
	protected Point poprzedniePolozenie;
	protected TYP_ORGANIZMU typ;
	
	public abstract void akcja(Swiat swiat);
	public abstract void walcz(Swiat swiat, Organizm napastnik);
	public abstract String typJakoString();
	
	public int getSila()
	{
		return sila;
	}
	public void zwiekszSile(int wartosc)
	{
		sila += wartosc;
	}
	
	public int getInicjatywa()
	{
		return inicjatywa;
	}
	public int getWiek()
	{
		return wiek;
	}
	public void zwiekszWiek()
	{
		wiek++;
	}
	public Point getAktualnePolozenie()
	{
		return aktualnePolozenie;
	}
	public void setAktualnePolozenie(Point nowe)
	{
		aktualnePolozenie = nowe;
	}
	public Point getPoprzedniePolozenie()
	{
		return poprzedniePolozenie;
	}
	public void setPoprzedniePolozenie(Point nowe)
	{
		poprzedniePolozenie = nowe;
	}
	public TYP_ORGANIZMU getTyp()
	{
		return typ;
	}
	public String toString()
	{
		String opis;
		opis = typJakoString() + " " + sila + " " + wiek + " " + aktualnePolozenie.x + " "
			+ aktualnePolozenie.y + " " + poprzedniePolozenie.x + " " + poprzedniePolozenie.y + "\r\n";
		return opis;
	}
	public static TYP_ORGANIZMU StringToTyp(String napis)
	{
		if(napis.equals("Wilk")) return TYP_ORGANIZMU.WILK;
		if(napis.equals("Owca")) return TYP_ORGANIZMU.OWCA;
		if(napis.equals("Zolw")) return TYP_ORGANIZMU.ZOLW;
		if(napis.equals("Antylopa")) return TYP_ORGANIZMU.ANTYLOPA;
		if(napis.equals("Czlowiek")) return TYP_ORGANIZMU.CZLOWIEK;
		if(napis.equals("Lis")) return TYP_ORGANIZMU.LIS;
		if(napis.equals("Trawa")) return TYP_ORGANIZMU.TRAWA;
		if(napis.equals("Mlecz")) return TYP_ORGANIZMU.MLECZ;
		if(napis.equals("Guarana")) return TYP_ORGANIZMU.GUARANA;
		if(napis.equals("Jagody")) return TYP_ORGANIZMU.JAGODY;
		
		return TYP_ORGANIZMU.PUSTE;
	}
	
}
