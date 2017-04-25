package wirtualny_swiat.wyjatki;

import java.awt.Point;

public class WyjatekPoruszanie extends WyjatekWprowadzenie
{
	private Point wspolrzedne;
	public WyjatekPoruszanie(Point pole)
	{
		super("Wyjatek Poruszanie");
		wspolrzedne = pole;
	}
	public void ustawKomunikat()
	{
		komunikat = "Pr�ba wej�cia na �cian� na polu: ("+wspolrzedne.x+","+wspolrzedne.y+")";
	}
}
