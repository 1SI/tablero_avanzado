/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;

import javax.swing.ImageIcon;

/**
 *
 * @author fran
 */
public class Meta extends Casilla{
    
    @Override
    public void setFondo(ImageIcon fondo) {
        super.fondo = fondo;
    }

    @Override
    public ImageIcon getFondo() {
        return super.fondo;
    }
}
