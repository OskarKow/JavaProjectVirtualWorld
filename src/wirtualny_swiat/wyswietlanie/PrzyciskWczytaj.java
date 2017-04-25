package wirtualny_swiat.wyswietlanie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import wirtualny_swiat.swiat.Swiat;

public class PrzyciskWczytaj extends JButton implements ActionListener
{
	private Swiat swiat;
	public PrzyciskWczytaj(Swiat swiat)
	{
		super("Wczytaj");
		this.swiat = swiat;
		setFocusable(false);
		addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		swiat.wczytajStanSwiata();
	}

}
