package wirtualny_swiat.wyjatki;

public class WyjatekUmiejetnosc extends WyjatekWprowadzenie
{
	private int pozostalyCooldown;
	public WyjatekUmiejetnosc(int pozostalyCooldown)
	{
		super("Wyjatek Umiejetnosc");
		this.pozostalyCooldown = pozostalyCooldown;
	}
	
	public void ustawKomunikat()
	{
		komunikat = "Nie mo¿na u¿yæ umiejêtnoœci. Pozostaly czas: " + pozostalyCooldown + " tur.";
	}
}
