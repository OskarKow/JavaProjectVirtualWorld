package wirtualny_swiat.wyswietlanie;

import javax.swing.JPanel;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.swiat.Swiat;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class PlanszaOrganizmow extends JPanel
{
	private ImageIcon zolw;
	private ImageIcon antylopa;
	private ImageIcon wilk;
	private ImageIcon owca;
	private ImageIcon lis;
	private ImageIcon czlowiek;
	private ImageIcon trawa;
	private ImageIcon mlecz;
	private ImageIcon guarana;
	private ImageIcon jagody;
	
	private Swiat swiat;
	private Pole[][] pola;
	private int wiersze, kolumny;
	
	public PlanszaOrganizmow(int wiersze, int kolumny, Swiat swiat)
	{
		this.swiat = swiat;
		zolw = new ImageIcon("turtle.png");
		antylopa = new ImageIcon("antelope.png");
		wilk = new ImageIcon("wolf.png");
		owca = new ImageIcon("sheep.png");
		lis = new ImageIcon("fox.png");
		czlowiek = new ImageIcon("human.png");
		trawa = new ImageIcon("bush.png");
		mlecz = new ImageIcon("mlecz.png");
		guarana = new ImageIcon("guarana.png");
		jagody = new ImageIcon("berries.png");
		
		if(wiersze > 40) this.wiersze = 20;
		else this.wiersze = wiersze;
		if(kolumny > 40) this.kolumny = 20;
		else this.kolumny = kolumny;
		
		setLayout(new GridLayout(this.wiersze, this.kolumny, 0, 0));
		pola = new Pole[this.wiersze][this.kolumny];
		for(int i=0;i<this.wiersze;i++)
		{
			for(int j=0;j<this.kolumny;j++)
			{
				pola[i][j] = new Pole(i, j, swiat);
				add(pola[i][j]);
			}
		}
	}
	public int getWiersze()
	{
		return wiersze;
	}
	public int getKolumny()
	{
		return kolumny;
	}
	
	public void setIkona(int wiersz, int kolumna, TYP_ORGANIZMU typ)
	{
		switch(typ)
		{
		case ZOLW:
			pola[wiersz][kolumna].setIcon(zolw);
			break;
		case LIS:
			pola[wiersz][kolumna].setIcon(lis);
			break;
		case ANTYLOPA:
			pola[wiersz][kolumna].setIcon(antylopa);
			break;
		case OWCA:
			pola[wiersz][kolumna].setIcon(owca);
			break;
		case CZLOWIEK:
			pola[wiersz][kolumna].setIcon(czlowiek);
			break;
		case WILK:
			pola[wiersz][kolumna].setIcon(wilk);
			break;
		case TRAWA:
			pola[wiersz][kolumna].setIcon(trawa);
			break;
		case MLECZ:
			pola[wiersz][kolumna].setIcon(mlecz);
			break;
		case GUARANA:
			pola[wiersz][kolumna].setIcon(guarana);
			break;
		case JAGODY:
			pola[wiersz][kolumna].setIcon(jagody);
			break;
		case PUSTE:
			pola[wiersz][kolumna].setIcon(null);
			break;
		}
	}
	public void setIkona(int wiersz, int kolumna, ImageIcon ikona)
	{
		if(wiersz >=0 && wiersz <= Swiat.WYSOKOSC - 1 && kolumna >=0 && kolumna <= Swiat.SZEROKOSC - 1)
		{
			pola[wiersz][kolumna].setIcon(ikona);
		}
	}
	
	
	
	
	
	
	
}
