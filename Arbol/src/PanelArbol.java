/****************************************************************************
 * Palabra de honor:
 * - No he discutido ni mostrado el código de mi programa con alguien que no sea mi
 *   compañero de CIPA, Profesor o con el monitor asignado a este curso.
 * - No he utilizado código obtenido de otro u otros estudiantes,
 *   o cualquier otra fuente no autorizada, ya sea modificado o sin modificar.
 * - Si llego a hacer uso de código de IA Generativa, debo indicar con exactitud
 *   qué análisis del código realicé.
 * - Si cualquier código o documentación utilizada en mi programa no es de mi autoría,
 *   debo señalarlo claramente con una cita apropiada en los comentarios de mi programa.
 * - No copio y pego código de IA generativa sin analizarlo.
 *
 * SAID ALEJANDRO MARTELO DIAZ – <7502410025> (integrante 1)
 * JOHANN DAVID MORENO CASSIANI – <7502423002> (integrante 2)
 * JUAN DAVID ALZATE GALVIS – <7502410040> (integrante 3)
 * DAVID SANTIAGO ESCAMILLA CASTRO – <7502410012> (integrante 4)
 * MOISES BANDA PEREZ – <7502320047> (integrante 5)
 *
 ****************************************************************************/

import javax.swing.*;
import java.awt.*;

/**
 * Componente Swing que dibuja un árbol binario de expresión ya
 * construido, organizado por niveles (la raíz arriba, las hojas abajo).
 *
 * Se entrega completa. Usa un recorrido inorden para asignar la
 * posición horizontal de cada nodo (los nodos del subárbol izquierdo
 * quedan a la izquierda, los del derecho a la derecha), y el nivel de
 * profundidad para la posición vertical.
 */
public class PanelArbol extends JPanel {

    private NodoArbol raiz;
    private int contadorX;

    public PanelArbol() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 400));
    }

    public void mostrarArbol(NodoArbol raiz) {
        this.raiz = raiz;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        contadorX = 40;
        dibujarNodo(g2, raiz, 0);
    }

    private int dibujarNodo(Graphics2D g2, NodoArbol nodo, int nivel) {
        if (nodo == null) return -1;

        int xIzquierdo = dibujarNodo(g2, nodo.getIzquierdo(), nivel + 1);

        int miX = contadorX;
        contadorX += 60;
        int miY = 40 + nivel * 70;

        int xDerecho = dibujarNodo(g2, nodo.getDerecho(), nivel + 1);

        g2.setColor(Color.GRAY);
        if (xIzquierdo != -1) g2.drawLine(miX, miY, xIzquierdo, 40 + (nivel + 1) * 70);
        if (xDerecho != -1) g2.drawLine(miX, miY, xDerecho, 40 + (nivel + 1) * 70);

        boolean esOperador = nodo.esOperador();
        g2.setColor(esOperador ? new Color(255, 224, 178) : new Color(197, 225, 255));
        g2.fillOval(miX - 18, miY - 18, 36, 36);
        g2.setColor(Color.BLACK);
        g2.drawOval(miX - 18, miY - 18, 36, 36);

        FontMetrics fm = g2.getFontMetrics();
        String texto = nodo.getDato();
        g2.drawString(texto, miX - fm.stringWidth(texto) / 2, miY + fm.getAscent() / 2 - 2);

        return miX;
    }
}
