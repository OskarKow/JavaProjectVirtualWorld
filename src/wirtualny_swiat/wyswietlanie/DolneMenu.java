package wirtualny_swiat.wyswietlanie;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.JPanel;

import wirtualny_swiat.swiat.Swiat;

import javax.swing.JLabel;
import javax.swing.JButton;

public class DolneMenu extends JPanel
{
	private Swiat swiat;
	public DolneMenu(Swiat swiat)
	{
		super();
		this.swiat = swiat;
		setLayout(new GridLayout(2, 2, 0, 50));
		add(new JLabel("Sterowanie:         strzalki - poruszanie          U - u¿ycie umiejêtnoœci"));
		add(new JLabel(""));
		add(new PrzyciskZapisz(swiat));
		add(new PrzyciskWczytaj(swiat));
	}
}
