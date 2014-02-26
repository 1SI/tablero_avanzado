/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controlador.Tablero;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author fran
 */
public class Main extends javax.swing.JFrame implements KeyListener {

    private Tablero tablero;
    private int[] p;

    public Main() {
        initComponents();
        setLocationRelativeTo(null);
        p = tablero.getCoordenadas(tablero.buscarJugador());
    }

    private void initComponents() {
        tablero = new Tablero(10);
        addKeyListener(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        javax.swing.GroupLayout tableroLayout = new javax.swing.GroupLayout(tablero);
        tablero.setLayout(tableroLayout);
        tableroLayout.setHorizontalGroup(
                tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 349, Short.MAX_VALUE)
        );
        tableroLayout.setVerticalGroup(
                tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 349, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(tablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(0, 0))
        );
        setTitle("sanvalerocraft");
        setResizable(false);
        pack();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                p = tablero.comprobar(p, p[0] + 1, p[1]);
                break;
            case KeyEvent.VK_LEFT:
                p = tablero.comprobar(p, p[0] - 1, p[1]);
                break;
            case KeyEvent.VK_UP:
                p = tablero.comprobar(p, p[0], p[1] - 1);
                break;
            case KeyEvent.VK_DOWN:
                p = tablero.comprobar(p, p[0], p[1] + 1);
                break;
            case KeyEvent.VK_D:
                p = tablero.comprobar(p, p[0] + 1, p[1]);
                break;
            case KeyEvent.VK_A:
                p = tablero.comprobar(p, p[0] - 1, p[1]);
                break;
            case KeyEvent.VK_W:
                p = tablero.comprobar(p, p[0], p[1] - 1);
                break;
            case KeyEvent.VK_S:
                p = tablero.comprobar(p, p[0], p[1] + 1);
                break;
            case KeyEvent.VK_Q:
                p = tablero.comprobar(p, p[0] - 1, p[1] - 1);
                break;
        }
        repaint();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
