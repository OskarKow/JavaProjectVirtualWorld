package wirtualny_swiat.wyswietlanie;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import wirtualny_swiat.TYP_ORGANIZMU;

public class IkonaOrganizmuDoWyboru extends JLabel implements MouseListener
{
	private TYP_ORGANIZMU typ;
	private OknoWyboruOrganizmu oknoKontener;
	public IkonaOrganizmuDoWyboru(TYP_ORGANIZMU typ, OknoWyboruOrganizmu kontener)
	{
		super();
		oknoKontener = kontener;
		addMouseListener(this);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setFocusable(true);
		this.typ = typ;
		switch(typ)
		{
		case WILK:
			setIcon(new ImageIcon("wolf.png"));
			break;
		case ZOLW:
			setIcon(new ImageIcon("turtle.png"));
			break;
		case LIS:
			setIcon(new ImageIcon("fox.png"));
			break;
		case ANTYLOPA:
			setIcon(new ImageIcon("antelope.png"));
			break;
		case OWCA:
			setIcon(new ImageIcon("sheep.png"));
			break;
		case TRAWA:
			setIcon(new ImageIcon("bush.png"));
			break;
		case MLECZ:
			setIcon(new ImageIcon("mlecz.png"));
			break;
		case GUARANA:
			setIcon(new ImageIcon("guarana.png"));
			break;
		case JAGODY:
			setIcon(new ImageIcon("berries.png"));
			break;
		default:
			setIcon(null);
			break;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		oknoKontener.stworzOrganizm(typ);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
