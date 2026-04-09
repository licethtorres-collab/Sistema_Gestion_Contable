package co.uptc.edu.co.gui;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JPanel panelEncabezado;
    private JPanel panelContenido;

    private JButton botonProductos;
    private JButton botonClientes;
    private JButton botonProveedores;
    private JButton botonVentas;
    private JButton botonCompras;
    private JButton botonContabilidad;
    private JButton botonReportes;
    private JButton botonConsultas;

    private Evento evento;

    public VentanaPrincipal() {
        inicializarComponentes();
        configurarVentana();
        agregarComponentes();
        inicializarEventos();
        mostrarPanel(new PanelInicio());
    }

    private void inicializarComponentes() {
        panelEncabezado = new JPanel();
        panelContenido = new JPanel();

        botonProductos = new JButton("Productos");
        botonClientes = new JButton("Clientes");
        botonProveedores = new JButton("Proveedores");
        botonVentas = new JButton("Ventas");
        botonCompras = new JButton("Compras");
        botonContabilidad = new JButton("Contabilidad");
        botonReportes = new JButton("Reportes");
        botonConsultas = new JButton("Consultas del Sistema");
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestion Contable y Comercial");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void agregarComponentes() {
        panelEncabezado.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelEncabezado.setBackground(new Color(20, 45, 75));

        JButton[] botones = {
            botonProductos, botonClientes, botonProveedores,
            botonVentas, botonCompras, botonContabilidad,
            botonReportes, botonConsultas
        };

        for (JButton boton : botones) {
            boton.setForeground(Color.WHITE);
            boton.setBackground(new Color(30, 60, 100));
            panelEncabezado.add(boton);
        }

        panelContenido.setBackground(Color.WHITE);
        panelContenido.setLayout(new BorderLayout());

        add(panelEncabezado, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);
    }

    private void inicializarEventos() {
        evento = new Evento(this);

        botonProductos.setActionCommand(Evento.PRODUCTOS);
        botonProductos.addActionListener(evento);

        botonClientes.setActionCommand(Evento.CLIENTES);
        botonClientes.addActionListener(evento);

        botonProveedores.setActionCommand(Evento.PROVEEDORES);
        botonProveedores.addActionListener(evento);

        botonVentas.setActionCommand(Evento.VENTAS);
        botonVentas.addActionListener(evento);

        botonCompras.setActionCommand(Evento.COMPRAS);
        botonCompras.addActionListener(evento);

        botonContabilidad.setActionCommand(Evento.CONTABILIDAD);
        botonContabilidad.addActionListener(evento);

        botonReportes.setActionCommand(Evento.REPORTES);
        botonReportes.addActionListener(evento);

        botonConsultas.setActionCommand(Evento.CONSULTAS);
        botonConsultas.addActionListener(evento);
    }

    public void mostrarPanel(JPanel panel) {
        panelContenido.removeAll();
        panelContenido.add(panel, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    public void irProductos() {
        PanelProducto panel = new PanelProducto();
        panel.inicializarEventos(evento);
        mostrarPanel(panel);
    }

    public void irClientes() {
        mostrarPanel(new PanelCliente());
    }

    public void irProveedores() {
        mostrarPanel(new PanelProveedor());
    }

    public void irVentas() {
        mostrarPanel(new PanelVenta());
    }

    public void irCompras() {
        mostrarPanel(new PanelCompra());
    }

    public void irContabilidad() {
        mostrarPanel(new PanelContabilidad());
    }

    public void irReportes() {
        mostrarPanel(new PanelReportes());
    }

    public void irConsultas() {
        mostrarPanel(new PanelConsultas());
    }

    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}