package wirtualny_swiat.wyswietlanie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;

import wirtualny_swiat.WPROWADZENIE;
import wirtualny_swiat.swiat.Swiat;

public class OknoProgramu extends JFrame implements KeyListener
{
	private PlanszaOrganizmow plansza;
	private TabelaInformacji tabelaInformacji;
	private DolneMenu dolneMenu;
	private Swiat swiat;
	public OknoProgramu(Swiat swiat)
	{
		super("Oskar Kowalewski 160359");
		setSize(1600, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panelGlowny = new JPanel();
		panelGlowny.setLayout(new BorderLayout());
		panelGlowny.setFocusable(true);
		plansza = new PlanszaOrganizmow(Swiat.WYSOKOSC, Swiat.SZEROKOSC, swiat);
		plansza.setFocusable(true);
		tabelaInformacji = new TabelaInformacji();
		tabelaInformacji.setFocusable(true);
		dolneMenu = new DolneMenu(swiat);
		dolneMenu.setFocusable(true);
		panelGlowny.add(tabelaInformacji, BorderLayout.CENTER);
		panelGlowny.add(plansza, BorderLayout.WEST);
		panelGlowny.add(dolneMenu, BorderLayout.SOUTH);
		setFocusable(true);
		this.addKeyListener(this);
		add(panelGlowny);
		setVisible(true);
		this.swiat = swiat;
	}
	public PlanszaOrganizmow getPlansza()
	{
		return plansza;
	}
	public TabelaInformacji getTabelaInformacji()
	{
		return tabelaInformacji;
	}
	public DolneMenu getDolneMenu()
	{
		return dolneMenu;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			swiat.setAktualneWprowadzenie(WPROWADZENIE.STRZALKA_GORA);
			swiat.wykonajTure();
			break;
		case KeyEvent.VK_DOWN:
			swiat.setAktualneWprowadzenie(WPROWADZENIE.STRZALKA_DOL);
			swiat.wykonajTure();
			break;
		case KeyEvent.VK_LEFT:
			swiat.setAktualneWprowadzenie(WPROWADZENIE.STRZALKA_LEWO);
			swiat.wykonajTure();
			break;
		case KeyEvent.VK_RIGHT:
			swiat.setAktualneWprowadzenie(WPROWADZENIE.STRZALKA_PRAWO);
			swiat.wykonajTure();
			break;
		case KeyEvent.VK_U:
			swiat.setAktualneWprowadzenie(WPROWADZENIE.UMIEJETNOSC);
			swiat.wykonajTure();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
