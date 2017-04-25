package wirtualny_swiat.wyswietlanie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import wirtualny_swiat.swiat.Swiat;

public class PrzyciskZapisz extends JButton implements ActionListener
{
	private Swiat swiat;
	public PrzyciskZapisz(Swiat swiat)
	{
		super("Zapisz");
		this.swiat = swiat;
		setFocusable(false);
		addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		swiat.zapiszStanSwiata();
	}

}
