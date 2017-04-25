package wirtualny_swiat.wyswietlanie;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import wirtualny_swiat.swiat.Swiat;

public class Pole extends JLabel implements MouseListener
{
	private int x;
	private int y;
	private Swiat swiat;
	
	public Pole(int x, int y, Swiat swiat)
	{
		super();
		this.swiat = swiat;
		this.x = x;
		this.y = y;
		addMouseListener(this);
		setIcon(null);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setFocusable(true);
	}
	public int getWspolrzednaX()
	{
		return x;
	}
	public int getWspolrzednaY()
	{
		return y;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(getIcon() == null)
		{
			OknoWyboruOrganizmu okno = new OknoWyboruOrganizmu(x, y, swiat);
			okno.setLocationRelativeTo(this);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
