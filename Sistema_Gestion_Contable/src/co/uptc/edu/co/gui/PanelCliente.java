package co.uptc.edu.co.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import co.uptc.edu.co.modelo.Cliente;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.util.ArrayList;
import java.util.List;

public class PanelCliente extends JPanel {

    private JLabel etiquetaTitulo;
    private JLabel etiquetaTotal;
    

    private JButton botonNuevo;
    private JButton botonEditar;
    private JButton botonInactivar;
    private JButton botonHistorial;

    private JTextField campoBuscar;
    private JComboBox<String> comboTipo;

    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    private JPanel panelSuperior;
    private JPanel panelBotones;
    private JPanel panelFiltros;
    private JPanel panelInferior;

    private List<Cliente> clientesCargados;

    public PanelCliente() {
        clientesCargados = new ArrayList<>();
        inicializarComponentes();
        configurarPanel();
        agregarComponentes();
        inicializarFiltros();
    }

    private void inicializarComponentes() {

        etiquetaTitulo = new JLabel("Gestión de Clientes");
        etiquetaTotal = new JLabel("Total de clientes: 0");

        botonNuevo = new JButton("Nuevo");
        botonEditar = new JButton("Editar");
        botonInactivar = new JButton("Inactivar");
        botonHistorial = new JButton("Ver Historial");

        campoBuscar = new JTextField(20);

        comboTipo = new JComboBox<>();
        comboTipo.addItem("Todos");
        comboTipo.addItem("Mayorista");
        comboTipo.addItem("Minorista");

        String[] columnas = {
                "Código",
                "Nombre/Razón Social",
                "Tipo ID",
                "N° Identificación",
                "Dirección",
                "Teléfono",
                "Tipo Cliente",
                "Estado"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaClientes = new JTable(modeloTabla);

        panelSuperior = new JPanel();
        panelBotones = new JPanel();
        panelFiltros = new JPanel();
        panelInferior = new JPanel();
    }

    private void configurarPanel() {

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.setBackground(Color.WHITE);

        panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBotones.setBackground(Color.WHITE);

        panelFiltros.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelFiltros.setBackground(Color.WHITE);

        panelInferior.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelInferior.setBackground(Color.WHITE);

        tablaClientes.setRowHeight(20);
        tablaClientes.getTableHeader().setReorderingAllowed(false);
        tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void agregarComponentes() {

        panelBotones.add(botonNuevo);
        panelBotones.add(botonEditar);
        panelBotones.add(botonInactivar);
        panelBotones.add(botonHistorial);

        panelFiltros.add(new JLabel("Buscar:"));
        panelFiltros.add(campoBuscar);
        panelFiltros.add(new JLabel("Tipo:"));
        panelFiltros.add(comboTipo);

        JPanel panelCabecera = new JPanel();
        panelCabecera.setLayout(new BorderLayout());
        panelCabecera.setBackground(Color.WHITE);

        panelCabecera.add(etiquetaTitulo, BorderLayout.NORTH);
        panelCabecera.add(panelBotones, BorderLayout.CENTER);
        panelCabecera.add(panelFiltros, BorderLayout.SOUTH);

        botonNuevo.setBackground(Color.WHITE);
        botonEditar.setBackground(Color.WHITE);
        botonInactivar.setBackground(Color.WHITE);
        botonHistorial.setBackground(Color.WHITE);

        panelSuperior.add(panelCabecera, BorderLayout.CENTER);

        JScrollPane scrollTablaClientes = new JScrollPane(tablaClientes);

        panelInferior.add(etiquetaTotal);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollTablaClientes, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public void inicializarEventos(Evento evento) {
        botonNuevo.setActionCommand(Evento.CMD_NUEVO_CLIENTE);
        botonNuevo.addActionListener(evento);

        botonEditar.setActionCommand(Evento.CMD_EDITAR_CLIENTE);
        botonEditar.addActionListener(evento);

        botonInactivar.setActionCommand(Evento.CMD_ESTADO_CLIENTE);
        botonInactivar.addActionListener(evento);

        botonHistorial.setActionCommand(Evento.CMD_HISTORIAL_CLIENTE);
        botonHistorial.addActionListener(evento);
    }

    public void cargarClientes(List<Cliente> clientes) {
        clientesCargados = new ArrayList<>(clientes);
        aplicarFiltros();
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }

    private void aplicarFiltros() {
        limpiarTabla();

        String textoBusqueda = campoBuscar.getText().trim().toLowerCase();
        String tipoSeleccionado = comboTipo.getSelectedItem().toString();

        int totalFiltrados = 0;

        for (Cliente cliente : clientesCargados) {
            boolean coincideBusqueda =
                    cliente.getCodigo().toLowerCase().contains(textoBusqueda) ||
                    cliente.getNombre().toLowerCase().contains(textoBusqueda) ||
                    cliente.getNumeroIdentificacion().toLowerCase().contains(textoBusqueda);

            boolean coincideTipo =
                    tipoSeleccionado.equals("Todos") ||
                    cliente.getTipoCliente().equalsIgnoreCase(tipoSeleccionado);

            if (coincideBusqueda && coincideTipo) {
                Object[] fila = {
                        cliente.getCodigo(),
                        cliente.getNombre(),
                        cliente.getTipoIdentificacion(),
                        cliente.getNumeroIdentificacion(),
                        cliente.getDireccion(),
                        cliente.getTelefono(),
                        cliente.getTipoCliente(),
                        cliente.getEstado()
                };

                modeloTabla.addRow(fila);
                totalFiltrados++;
            }
        }

        etiquetaTotal.setText("Total de clientes: " + totalFiltrados);
    }

    private void inicializarFiltros() {
        campoBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                aplicarFiltros();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aplicarFiltros();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aplicarFiltros();
            }
        });

        comboTipo.addActionListener(e -> aplicarFiltros());
    }

    public boolean haySeleccion() {
        return tablaClientes.getSelectedRow() != -1;
    }

    public String obtenerCodigoSeleccionado() {
        int filaSeleccionada = tablaClientes.getSelectedRow();

        if (filaSeleccionada == -1) {
            return null;
        }

        return modeloTabla.getValueAt(filaSeleccionada, 0).toString();
    }
}