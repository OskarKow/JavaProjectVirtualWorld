package wirtualny_swiat.organizmy.rosliny;

import java.awt.Point;

import wirtualny_swiat.TYP_ORGANIZMU;
import wirtualny_swiat.organizmy.Organizm;
import wirtualny_swiat.swiat.Swiat;

public abstract class Roslina extends Organizm
{
	protected static int SZANSA_ZASIANIA = 3;
	public void rozprzestrzeniajSie(Swiat swiat)
	{
		Point wolne = swiat.wylosujWolnePole(aktualnePolozenie);
		if (wolne.x > -1 && wolne.y > -1)
		{
			swiat.getDoDodania().dodajOrganizm(swiat.stworzOrganizm(wolne.x, wolne.y, typ));
			swiat.getOkno().getTabelaInformacji().dodajZasianie(wolne, typJakoString(), swiat.getNumerTury());
		}
	}
	public void walcz(Swiat swiat, Organizm napastnik)
	{
		swiat.getDoUsuniecia().dodajOrganizm(this);
		swiat.setPole(aktualnePolozenie, napastnik.getTyp());
		swiat.setPole(napastnik.getPoprzedniePolozenie(), TYP_ORGANIZMU.PUSTE);
		swiat.getOkno().getTabelaInformacji().dodajZjedzenie(napastnik, this, swiat.getNumerTury());
	}
}
