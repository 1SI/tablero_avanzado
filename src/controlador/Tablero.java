/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author fran
 */
import datos.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tablero extends JPanel {

    private ImageIcon libre, muro, jugador, meta;
    private final Casilla[][] casillas;

    public Tablero(int size) {
        setLayout(new java.awt.GridLayout(size, size));
        cargarImagenes();
        casillas = new Casilla[size][size];
        cargarLibres();
        cargarMuros();
        ponerMeta();
        ponerJugador();
    }
    //Carga en la matriz todas las casillas vacias
    private void cargarLibres() {
        int x, y;
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j] = new Libre();
                casillas[i][j].setFondo(libre);
                x = (i * 35) + 1;
                y = (j * 35) + 1;
                casillas[i][j].setBounds(x, y, 34, 34);
                this.add(casillas[i][j]);
            }
        }
    }
    
    //Carga aleatoriamente los muros en la matriz
    private void cargarMuros() {
        for (int i = 0; i < 11; i++) {
            int x = new Random().nextInt(casillas.length);
            int y = new Random().nextInt(casillas[x].length);
            while (((x > 10 || y > 10) || (x < 0 || y < 0)) && ((x != 0 && y != 0) || (x != 9 && y != 9))) {
                x = new Random().nextInt(casillas.length);
                y = new Random().nextInt(casillas[x].length);
            }
            this.remove(casillas[x][y]);
            casillas[x][y] = new Muro();
            casillas[x][y].setFondo(muro);
            int z = (x * 35) + 1;
            int a = (y * 35) + 1;
            casillas[x][y].setBounds(z, a, 34, 34);
            this.add(casillas[x][y]);
        }
    }

    //Coloca al jugador en la posicion inicial (0,0)
    private void ponerJugador() {
        int x, y;
        this.remove(casillas[0][0]);
        casillas[0][0] = new Jugador();
        casillas[0][0].setFondo(jugador);
        x = (0 * 35) + 1;
        y = (0 * 35) + 1;
        casillas[0][0].setBounds(x, y, 34, 34);
        this.add(casillas[0][0]);
    }

    //Coloca la meta en la casilla (9,9)
    private void ponerMeta() {
        int x, y;
        this.remove(casillas[9][9]);
        casillas[9][9] = new Meta();
        casillas[9][9].setFondo(meta);
        x = (9 * 35) + 1;
        y = (9 * 35) + 1;
        casillas[9][9].setBounds(x, y, 34, 34);
        this.add(casillas[9][9]);
    }

    //Carga las imagenes de la carpeta images
    private void cargarImagenes() {
        try {
            libre = this.cargarFondo("images/libre.png");
            muro = this.cargarFondo("images/muro.png");
            jugador = this.cargarFondo("images/jugador.png");
            meta = this.cargarFondo("images/meta.png");
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(this, "La imagenes no han poidido ser cargadas","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    //Devuelve un ImageIcon con la ruta del archivo de imagen
    private ImageIcon cargarFondo(String ruta) throws MalformedURLException {
        java.net.URL localizacion = new File(ruta).toURI().toURL();
        if (localizacion != null) {
            return new ImageIcon(localizacion);
        } else {
            System.err.println("No se ha encontrado el archivo: " + ruta);
            return null;
        }
    }
    
    //Cambia al jugador de casilla
    private void pintar(int x, int y, int nx, int ny) {
        //Posicion antigua del jugador, a cambiar por casilla vacia
        this.remove(casillas[x][y]);
        casillas[x][y] = new Libre();
        casillas[x][y].setFondo(libre);
        int a = (x * 35) + 1;
        int b = (y * 35) + 1;
        casillas[x][y].setBounds(a, b, 34, 34);
        this.add(casillas[x][y]);
        //Posicion nueva del jugador
        this.remove(casillas[nx][ny]);
        casillas[nx][ny] = new Jugador();
        casillas[nx][ny].setFondo(jugador);
        a = (nx * 35) + 1;
        b = (ny * 35) + 1;
        casillas[nx][ny].setBounds(a, b, 34, 34);
        this.add(casillas[nx][ny]);
    }

    //Comprueba el tipo de casilla que es y si es libre mueve al jugador
    public int[] comprobar(int[] p, int nx, int ny) {
        if ((nx <= 9 && ny <= 9) && (nx >= 0 && ny >= 0)) {
            if (casillas[nx][ny] instanceof Muro) {
                JOptionPane.showMessageDialog(this, "Con el muro te has topado","Info",JOptionPane.INFORMATION_MESSAGE);
            } else if(casillas[nx][ny] instanceof Meta){
                pintar(p[0], p[1], nx, ny);
                JOptionPane.showMessageDialog(this, "Has ganado","Victoria",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } else{
                pintar(p[0], p[1], nx, ny);
                p[0] =nx;
                p[1] =ny;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Te has salido del tablero","Error",JOptionPane.ERROR_MESSAGE);
        }
        return p;
    }

    //Devuelve las coordenadas de la casilla del jugador
    public int[] getCoordenadas(Casilla casilla) {
        int[] coordenadas = new int[2];
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (this.casillas[i][j] == casilla) {
                    coordenadas[0] = i;
                    coordenadas[1] = j;
                }
            }
        }
        return coordenadas;
    }

    //Busca al jugador en el tablero
    public Casilla buscarJugador() {
        Casilla p = new Libre();
        for (Casilla[] casilla : casillas) {
            for (Casilla casilla1 : casilla) {
                if (casilla1 instanceof Jugador) {
                    p = casilla1;
                }
            }
        }
        return p;
    }
}