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
 * Clase principal (Main) del Componente B — integrantes del CIPA:
 * SAID ALEJANDRO MARTELO DIAZ – <CÓDIGO>
 * JOHANN DAVID MORENO CASSIANI – <CÓDIGO>
 * JUAN DAVID ALZATE GALVIS – <CÓDIGO>
 * DAVID SANTIAGO ESCAMILLA CASTRO – <CÓDIGO>
 * MOISES BANDA PEREZ – <CÓDIGO>
 */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Ventana principal de la aplicación (interfaz gráfica en Java Swing).
 *
 * Esta clase se entrega completa y NO debe modificarse. Arma toda la
 * interfaz, carga y evalúa automáticamente las expresiones de prueba
 * desde JSON al iniciar, y conecta el botón "Evaluar" con el método
 * ya resuelto {@code ArbolExpresion.procesarExpresion(String)}, que a
 * su vez llama a los métodos TODO que el estudiante debe completar. Es
 * la clase que se debe ejecutar para PROBAR el trabajo.
 */
public class VentanaPrincipal extends JFrame {

    private ArbolExpresion arbol;
    private JTextField campoExpresion;
    private JTextArea areaResultados;
    private PanelArbol panelArbol;

    public VentanaPrincipal() {
        super("Motor de Evaluacion de Formulas - Arbol de Expresion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        arbol = new ArbolExpresion();

        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        campoExpresion = new JTextField();
        JButton btnEvaluar = new JButton("Evaluar");
        btnEvaluar.addActionListener(e -> evaluarExpresionDigitada());
        panelSuperior.add(new JLabel("Expresion:"), BorderLayout.WEST);
        panelSuperior.add(campoExpresion, BorderLayout.CENTER);
        panelSuperior.add(btnEvaluar, BorderLayout.EAST);
        add(panelSuperior, BorderLayout.NORTH);

        panelArbol = new PanelArbol();
        add(new JScrollPane(panelArbol), BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setPreferredSize(new Dimension(380, 600));
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        panelDerecho.add(new JScrollPane(areaResultados), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton btnHistorial = new JButton("Ver historial");
        btnHistorial.addActionListener(e -> mostrarHistorial());
        JButton btnGuardar = new JButton("Guardar historial en binario");
        btnGuardar.addActionListener(e -> {
            arbol.guardarHistorialBinario();
            mostrarMensaje("Se solicito guardar el historial en datos/historial_expresiones.dat");
        });
        panelBotones.add(btnHistorial);
        panelBotones.add(btnGuardar);
        panelDerecho.add(panelBotones, BorderLayout.SOUTH);

        add(panelDerecho, BorderLayout.EAST);

        cargarFormulasDePruebaAlIniciar();
    }

    private void cargarFormulasDePruebaAlIniciar() {
        try {
            String[] expresiones = LectorFormulasJSON.leer("datos/formulas_prueba.json");
            mostrarMensaje("Evaluando " + expresiones.length + " expresiones de prueba al iniciar...\n");
            for (String expresion : expresiones) {
                ResultadoExpresion r = arbol.procesarExpresion(expresion);
                mostrarMensaje(r.toString());
                mostrarMensaje("----------------------------------------");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "No se pudieron cargar las formulas de prueba: " + e.getMessage(),
                    "Error de carga", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void evaluarExpresionDigitada() {
        String infija = campoExpresion.getText().trim();
        if (infija.isEmpty()) return;

        try {
            ResultadoExpresion r = arbol.procesarExpresion(infija);
            mostrarMensaje(r.toString());
            mostrarMensaje("----------------------------------------");
            panelArbol.mostrarArbol(r.raiz);
            arbol.agregarRegistroAlHistorial(
                    new RegistroExpresion(r.expresionOriginal, r.expresionPostfija, r.resultado));
        } catch (Exception ex) {
            mostrarMensaje("Error al evaluar la expresion '" + infija + "': " + ex.getMessage());
        }
    }

    private void mostrarHistorial() {
        Lista<RegistroExpresion> historial = arbol.getHistorial();
        if (historial.estaVacia()) {
            mostrarMensaje("El historial de expresiones evaluadas manualmente esta vacio.");
            return;
        }
        mostrarMensaje("=== HISTORIAL DE EXPRESIONES EVALUADAS ===");
        for (RegistroExpresion r : historial) {
            mostrarMensaje(r.toString());
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
