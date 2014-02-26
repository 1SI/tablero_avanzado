/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author fran
 */
public abstract class Casilla extends JPanel{
    
    protected ImageIcon fondo;
    
    public abstract void setFondo(ImageIcon fondo);
    public abstract ImageIcon getFondo();
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(fondo.getImage(), 0,0,this.getWidth(),this.getHeight(),this);
    }
}
