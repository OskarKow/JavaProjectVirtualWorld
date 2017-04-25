package wirtualny_swiat.wyswietlanie;

import java.awt.GridLayout;

import javax.swing.JDialog;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.organizmy.Organizm;
import wirtualny_swiat.swiat.Swiat;

public class OknoWyboruOrganizmu extends JDialog
{
	private int x;
	private int y;
	private Swiat swiat;
	public OknoWyboruOrganizmu(int xPola, int yPola, Swiat swiat)
	{
		setSize(200, 400);
		setResizable(false);
		x = xPola;
		y = yPola;
		this.swiat = swiat;
		this.setLayout(new GridLayout(9, 1));
		add(new IkonaOrganizmuDoWyboru(TYP_ORGANIZMU.WILK, this));
		add(new IkonaOrganizmuDoWyboru(TYP_ORGANIZMU.OWCA, this));
		add(new IkonaOrganizmuDoWyboru(TYP_ORGANIZMU.ZOLW, this));
		add(new IkonaOrganizmuDoWyboru(TYP_ORGANIZMU.ANTYLOPA, this));
		add(new IkonaOrganizmuDoWyboru(TYP_ORGANIZMU.LIS, this));
		add(new IkonaOrganizmuDoWyboru(TYP_ORGANIZMU.TRAWA, this));
		add(new IkonaOrganizmuDoWyboru(TYP_ORGANIZMU.MLECZ, this));
		add(new IkonaOrganizmuDoWyboru(TYP_ORGANIZMU.GUARANA, this));
		add(new IkonaOrganizmuDoWyboru(TYP_ORGANIZMU.JAGODY, this));
		setVisible(true);
	}
	
	public void stworzOrganizm(TYP_ORGANIZMU typ)
	{
		swiat.getDoDodania().dodajOrganizm(swiat.stworzOrganizm(y, x, typ));
		swiat.przepiszDoDodania();
		swiat.aktualizujPlansze();
		setVisible(false);
	}
}
