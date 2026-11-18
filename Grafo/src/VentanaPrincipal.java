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
 * MOISES BANDA PEREZ – <CÓDIGO> (integrante 5)
 *
 ****************************************************************************/

/*
 * Clase principal (Main) del Componente A — integrantes del CIPA:
 * SAID ALEJANDRO MARTELO DIAZ – <CÓDIGO>
 * JOHANN DAVID MORENO CASSIANI – <CÓDIGO>
 * JUAN DAVID ALZATE GALVIS – <CÓDIGO>
 * DAVID SANTIAGO ESCAMILLA CASTRO – <CÓDIGO>
 * MOISES BANDA PEREZ – <CÓDIGO>
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Ventana principal de la aplicación (interfaz gráfica en Java Swing).
 *
 * Esta clase se entrega completa y NO debe modificarse. Arma toda la
 * interfaz, carga el mapa de prueba desde JSON al iniciar, y conecta
 * cada botón con el método correspondiente de la clase Grafo. Es la
 * clase que se debe ejecutar para PROBAR los métodos completados en
 * Grafo.java.
 */
public class VentanaPrincipal extends JFrame {

    private Grafo grafo;
    private PanelMapa panelMapa;
    private JTextArea areaResultados;
    private JComboBox<String> comboOrigen;
    private JComboBox<String> comboDestino;
    private JTextField campoNombreVertice;
    private JTextField campoX;
    private JTextField campoY;
    private JTextField campoOrigenArista;
    private JTextField campoDestinoArista;
    private JTextField campoDistanciaArista;

    public VentanaPrincipal() {
        super("Sistema de Rutas de Entrega Urbana - Grafos y Dijkstra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);

        grafo = new Grafo();
        cargarMapaInicial();

        setLayout(new BorderLayout(10, 10));
        add(construirPanelControles(), BorderLayout.WEST);

        panelMapa = new PanelMapa();
        panelMapa.mostrarGrafo(grafo);
        add(new JScrollPane(panelMapa), BorderLayout.CENTER);

        areaResultados = new JTextArea(10, 40);
        areaResultados.setEditable(false);
        add(new JScrollPane(areaResultados), BorderLayout.SOUTH);

        actualizarCombos();
        mostrarMensaje("Aplicacion iniciada. Mapa de prueba cargado con "
                + grafo.cantidadVertices() + " puntos.");
    }

    private void cargarMapaInicial() {
        try {
            DatosMapa datos = LectorMapaJSON.leer("datos/mapa_prueba.json");
            grafo.cargarDesdeJSON(datos);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo cargar el mapa de prueba: " + e.getMessage(),
                    "Error de carga", JOptionPane.WARNING_MESSAGE);
        }
    }

    private JPanel construirPanelControles() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(320, 600));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Agregar punto:"));
        campoNombreVertice = new JTextField();
        campoX = new JTextField();
        campoY = new JTextField();
        panel.add(etiquetado("Nombre:", campoNombreVertice));
        panel.add(etiquetado("Coordenada X:", campoX));
        panel.add(etiquetado("Coordenada Y:", campoY));
        JButton btnAgregarVertice = new JButton("Agregar punto");
        btnAgregarVertice.addActionListener(this::agregarVertice);
        panel.add(btnAgregarVertice);

        JButton btnEliminarVertice = new JButton("Eliminar punto (usa 'Nombre')");
        btnEliminarVertice.addActionListener(this::eliminarVertice);
        panel.add(btnEliminarVertice);

        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Agregar / eliminar calle:"));
        campoOrigenArista = new JTextField();
        campoDestinoArista = new JTextField();
        campoDistanciaArista = new JTextField();
        panel.add(etiquetado("Origen:", campoOrigenArista));
        panel.add(etiquetado("Destino:", campoDestinoArista));
        panel.add(etiquetado("Distancia (km):", campoDistanciaArista));
        JButton btnAgregarArista = new JButton("Agregar calle");
        btnAgregarArista.addActionListener(this::agregarArista);
        panel.add(btnAgregarArista);
        JButton btnEliminarArista = new JButton("Eliminar calle");
        btnEliminarArista.addActionListener(this::eliminarArista);
        panel.add(btnEliminarArista);

        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Origen / Destino para consultas:"));
        comboOrigen = new JComboBox<>();
        comboDestino = new JComboBox<>();
        panel.add(etiquetado("Origen:", comboOrigen));
        panel.add(etiquetado("Destino:", comboDestino));

        JButton btnGrado = new JButton("Ver grado del origen");
        btnGrado.addActionListener(this::verGrado);
        panel.add(btnGrado);

        JButton btnBFS = new JButton("Recorrido BFS desde origen");
        btnBFS.addActionListener(this::ejecutarBFS);
        panel.add(btnBFS);

        JButton btnDFS = new JButton("Recorrido DFS desde origen");
        btnDFS.addActionListener(this::ejecutarDFS);
        panel.add(btnDFS);

        JButton btnDijkstra = new JButton("Calcular ruta mas corta (Dijkstra)");
        btnDijkstra.addActionListener(this::ejecutarDijkstra);
        panel.add(btnDijkstra);

        panel.add(Box.createVerticalStrut(15));
        JButton btnGuardarHistorial = new JButton("Guardar historial de rutas");
        btnGuardarHistorial.addActionListener(e -> {
            grafo.guardarHistorialBinario();
            mostrarMensaje("Se solicito guardar el historial en datos/historial_rutas.dat");
        });
        panel.add(btnGuardarHistorial);

        return panel;
    }

    private JPanel etiquetado(String texto, JComponent campo) {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel(texto), BorderLayout.NORTH);
        p.add(campo, BorderLayout.CENTER);
        p.setMaximumSize(new Dimension(300, 50));
        return p;
    }

    private void actualizarCombos() {
        comboOrigen.removeAllItems();
        comboDestino.removeAllItems();
        for (int i = 0; i < grafo.cantidadVertices(); i++) {
            String nombre = grafo.obtenerVertice(i).getNombre();
            comboOrigen.addItem(nombre);
            comboDestino.addItem(nombre);
        }
    }

    private void agregarVertice(ActionEvent e) {
        try {
            String nombre = campoNombreVertice.getText().trim();
            int x = Integer.parseInt(campoX.getText().trim());
            int y = Integer.parseInt(campoY.getText().trim());
            boolean ok = grafo.agregarVertice(nombre, x, y);
            mostrarMensaje(ok ? "Punto agregado: " + nombre : "No se pudo agregar (¿ya existe?): " + nombre);
            actualizarCombos();
            panelMapa.repaint();
        } catch (NumberFormatException ex) {
            mostrarMensaje("Coordenadas invalidas. Deben ser numeros enteros.");
        }
    }

    private void eliminarVertice(ActionEvent e) {
        String nombre = campoNombreVertice.getText().trim();
        boolean ok = grafo.eliminarVertice(nombre);
        mostrarMensaje(ok ? "Punto eliminado: " + nombre : "No se encontro el punto: " + nombre);
        actualizarCombos();
        panelMapa.repaint();
    }

    private void agregarArista(ActionEvent e) {
        try {
            String v1 = campoOrigenArista.getText().trim();
            String v2 = campoDestinoArista.getText().trim();
            double d = Double.parseDouble(campoDistanciaArista.getText().trim());
            boolean ok = grafo.agregarArista(v1, v2, d);
            mostrarMensaje(ok ? "Calle agregada entre " + v1 + " y " + v2
                    : "No se pudo agregar la calle (verifique que ambos puntos existan).");
            panelMapa.repaint();
        } catch (NumberFormatException ex) {
            mostrarMensaje("Distancia invalida. Debe ser un numero.");
        }
    }

    private void eliminarArista(ActionEvent e) {
        String v1 = campoOrigenArista.getText().trim();
        String v2 = campoDestinoArista.getText().trim();
        boolean ok = grafo.eliminarArista(v1, v2);
        mostrarMensaje(ok ? "Calle eliminada entre " + v1 + " y " + v2
                : "No se pudo eliminar (verifique los nombres).");
        panelMapa.repaint();
    }

    private void verGrado(ActionEvent e) {
        String origen = (String) comboOrigen.getSelectedItem();
        if (origen == null) return;
        int grado = grafo.grado(origen);
        mostrarMensaje("Grado de " + origen + ": " + grado);
    }

    private void ejecutarBFS(ActionEvent e) {
        String origen = (String) comboOrigen.getSelectedItem();
        if (origen == null) return;
        Lista<String> resultado = grafo.recorridoBFS(origen);
        mostrarMensaje("Recorrido BFS desde " + origen + ": " + resultado);
        panelMapa.limpiarResaltado();
    }

    private void ejecutarDFS(ActionEvent e) {
        String origen = (String) comboOrigen.getSelectedItem();
        if (origen == null) return;
        Lista<String> resultado = grafo.recorridoDFS(origen);
        mostrarMensaje("Recorrido DFS desde " + origen + ": " + resultado);
        panelMapa.limpiarResaltado();
    }

    private void ejecutarDijkstra(ActionEvent e) {
        String origen = (String) comboOrigen.getSelectedItem();
        String destino = (String) comboDestino.getSelectedItem();
        if (origen == null || destino == null) return;

        ResultadoRuta resultado = grafo.dijkstra(origen, destino);
        mostrarMensaje("Ruta mas corta de " + origen + " a " + destino + ": " + resultado);

        if (resultado.isExisteRuta()) {
            panelMapa.resaltarRuta(resultado.getRuta());
            grafo.agregarConsultaAlHistorial(new ConsultaRuta(
                    origen, destino, resultado.getRuta().toString(), resultado.getDistanciaTotal()));
        }
    }

    private void mostrarMensaje(String texto) {
        areaResultados.append(texto + "\n");
        areaResultados.setCaretPosition(areaResultados.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
