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
 * DAVID SANTIAGO ESCAMILLA CASTRO – 7502410012 (integrante 4)
 * MOISES BANDA PEREZ – <7502320047> (integrante 5)
 *
 ****************************************************************************/

import javax.swing.*;
import java.awt.*;

/**
 * Componente Swing que dibuja el mapa: los vértices como círculos con
 * su nombre, y las aristas como líneas etiquetadas con su distancia.
 * Si se le indica una ruta (lista de nombres de vértices en orden),
 * la resalta con otro color y mayor grosor.
 *
 * Se entrega completa. Lee los datos del Grafo a través de sus
 * consultas ya resueltas (cantidadVertices, obtenerVertice, obtenerPeso),
 * por lo que dibuja automáticamente lo que exista en el grafo sin
 * importar en qué momento del desarrollo se complete cada método TODO.
 */
public class PanelMapa extends JPanel {

    private Grafo grafo;
    private Lista<String> rutaResaltada;

    public PanelMapa() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 480));
    }

    public void mostrarGrafo(Grafo grafo) {
        this.grafo = grafo;
        repaint();
    }

    public void resaltarRuta(Lista<String> ruta) {
        this.rutaResaltada = ruta;
        repaint();
    }

    public void limpiarResaltado() {
        this.rutaResaltada = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (grafo == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int n = grafo.cantidadVertices();

        // 1) Dibujar todas las aristas en gris claro
        g2.setColor(new Color(200, 200, 200));
        g2.setStroke(new BasicStroke(1.5f));
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double peso = grafo.obtenerPeso(i, j);
                if (peso != Grafo.SIN_CONEXION) {
                    Vertice vi = grafo.obtenerVertice(i);
                    Vertice vj = grafo.obtenerVertice(j);
                    g2.drawLine(vi.getX(), vi.getY(), vj.getX(), vj.getY());
                    int mx = (vi.getX() + vj.getX()) / 2;
                    int my = (vi.getY() + vj.getY()) / 2;
                    g2.setColor(Color.DARK_GRAY);
                    g2.drawString(String.valueOf(peso), mx, my);
                    g2.setColor(new Color(200, 200, 200));
                }
            }
        }

        // 2) Resaltar la ruta calculada, si existe
        if (rutaResaltada != null && rutaResaltada.tamano() > 1) {
            g2.setColor(new Color(220, 60, 60));
            g2.setStroke(new BasicStroke(3.5f));
            for (int i = 0; i < rutaResaltada.tamano() - 1; i++) {
                Vertice a = buscarPorNombre(rutaResaltada.obtener(i));
                Vertice b = buscarPorNombre(rutaResaltada.obtener(i + 1));
                if (a != null && b != null) {
                    g2.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
                }
            }
        }

        // 3) Dibujar los vértices
        for (int i = 0; i < n; i++) {
            Vertice v = grafo.obtenerVertice(i);
            boolean enRuta = estaEnRuta(v.getNombre());
            g2.setColor(enRuta ? new Color(255, 224, 178) : new Color(197, 225, 255));
            g2.fillOval(v.getX() - 22, v.getY() - 16, 44, 32);
            g2.setColor(Color.BLACK);
            g2.drawOval(v.getX() - 22, v.getY() - 16, 44, 32);
            FontMetrics fm = g2.getFontMetrics();
            String nombre = v.getNombre();
            g2.drawString(nombre, v.getX() - fm.stringWidth(nombre) / 2, v.getY() + 4);
        }
    }

    private boolean estaEnRuta(String nombre) {
        if (rutaResaltada == null) return false;
        for (String n : rutaResaltada) {
            if (n.equals(nombre)) return true;
        }
        return false;
    }

    private Vertice buscarPorNombre(String nombre) {
        for (int i = 0; i < grafo.cantidadVertices(); i++) {
            if (grafo.obtenerVertice(i).getNombre().equals(nombre)) return grafo.obtenerVertice(i);
        }
        return null;
    }
}
