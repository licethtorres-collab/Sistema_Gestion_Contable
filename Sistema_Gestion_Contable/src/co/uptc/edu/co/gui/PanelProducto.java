package co.uptc.edu.co.gui;

import co.uptc.edu.co.modelo.Producto;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelProducto extends JPanel {

    private JLabel etiquetaTitulo;
    private JLabel etiquetaTotal;

    private JButton botonNuevo;
    private JButton botonEditar;
    private JButton botonEstadoProducto;
    private JButton botonActualizarPrecio;
    private JButton botonMovimientoInventario;

    private JTextField campoBuscar;
    private JComboBox<String> comboCategoria;

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    private JPanel panelSuperior;
    private JPanel panelBotones;
    private JPanel panelFiltros;
    private JPanel panelInferior;

    private List<Producto> productosCargados;

    public PanelProducto() {
        productosCargados = new ArrayList<>();
        inicializarComponentes();
        configurarPanel();
        agregarComponentes();
        inicializarFiltros();
    }

    private void inicializarComponentes() {
        etiquetaTitulo = new JLabel("Gestión de Productos");
        etiquetaTotal = new JLabel("Total de productos: 0");

        botonNuevo = new JButton("Nuevo");
        botonEditar = new JButton("Editar");
        botonEstadoProducto = new JButton("Estado");
        botonActualizarPrecio = new JButton("Actualizar Precio");
        botonMovimientoInventario = new JButton("Movimiento Inventario");

        campoBuscar = new JTextField(20);

        comboCategoria = new JComboBox<>();
        comboCategoria.addItem("Todos");
        comboCategoria.addItem("Aseo");
        comboCategoria.addItem("Alimentos");
        comboCategoria.addItem("Bebidas");
        comboCategoria.addItem("Papelería");

        String[] columnas = {
                "Código",
                "Nombre",
                "Categoría",
                "Precio Compra",
                "Precio Venta",
                "Stock Actual",
                "Stock Mínimo",
                "Stock Maximo",
                "Estado"
        };
        // tabla no editable
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaProductos = new JTable(modeloTabla);

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

        tablaProductos.setRowHeight(25);
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        botonNuevo.setBackground(Color.WHITE);
        botonEditar.setBackground(Color.WHITE);
        botonEstadoProducto.setBackground(Color.WHITE);
        botonActualizarPrecio.setBackground(Color.WHITE);
        botonMovimientoInventario.setBackground(Color.WHITE);
    }

    private void agregarComponentes() {
        panelBotones.add(botonNuevo);
        panelBotones.add(botonEditar);
        panelBotones.add(botonEstadoProducto);
        panelBotones.add(botonActualizarPrecio);
        panelBotones.add(botonMovimientoInventario);

        panelFiltros.add(new JLabel("Buscar:"));
        panelFiltros.add(campoBuscar);
        panelFiltros.add(new JLabel("Categoria:"));
        panelFiltros.add(comboCategoria);

        JPanel panelCabecera = new JPanel(new BorderLayout());
        panelCabecera.setBackground(Color.WHITE);

        panelCabecera.add(etiquetaTitulo, BorderLayout.NORTH);
        panelCabecera.add(panelBotones, BorderLayout.CENTER);
        panelCabecera.add(panelFiltros, BorderLayout.SOUTH);

        panelSuperior.add(panelCabecera, BorderLayout.CENTER);

        JScrollPane scroll = new JScrollPane(tablaProductos);

        panelInferior.add(etiquetaTotal);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    // inicializar filtros
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

        comboCategoria.addActionListener(e -> aplicarFiltros());
    }
    
    // inicializar eventos
    public void inicializarEventos(Evento evento) {
        botonNuevo.setActionCommand(Evento.CMD_NUEVO_PRODUCTO);
        botonNuevo.addActionListener(evento);

        botonEditar.setActionCommand(Evento.CMD_EDITAR_PRODUCTO);
        botonEditar.addActionListener(evento);

        botonEstadoProducto.setActionCommand(Evento.CMD_ESTADO_PRODUCTO);
        botonEstadoProducto.addActionListener(evento);

        botonActualizarPrecio.setActionCommand(Evento.CMD_ACTUALIZAR_PRECIO_PRODUCTO);
        botonActualizarPrecio.addActionListener(evento);

        botonMovimientoInventario.setActionCommand(Evento.CMD_MOVIMIENTO_INVENTARIO);
        botonMovimientoInventario.addActionListener(evento);
    }
    public void cargarProductos(List<Producto> productos) {
        productosCargados = new ArrayList<>(productos);
        aplicarFiltros();
    }

    private void aplicarFiltros() {
        limpiarTabla();

        String textoBusqueda = campoBuscar.getText().trim().toLowerCase();
        String categoriaSeleccionada = comboCategoria.getSelectedItem().toString();

        int totalFiltrados = 0;

        for (Producto producto : productosCargados) {
            boolean coincideBusqueda =
                    producto.getCodigo().toLowerCase().contains(textoBusqueda) ||
                    producto.getNombreProducto().toLowerCase().contains(textoBusqueda);

            boolean coincideCategoria =
                    categoriaSeleccionada.equals("Todos") ||
                    producto.getCategoria().equalsIgnoreCase(categoriaSeleccionada);

            if (coincideBusqueda && coincideCategoria) {
                Object[] fila = {
                        producto.getCodigo(),
                        producto.getNombreProducto(),
                        producto.getCategoria(),
                        producto.getPrecioCompra(),
                        producto.getPrecioVenta(),
                        producto.getStockActual(),
                        producto.getStockMinimo(),
                        producto.getStockMaximo(),
                        producto.getEstado()
                };
                modeloTabla.addRow(fila);
                totalFiltrados++;
            }
        }

        etiquetaTotal.setText("Total de productos: " + totalFiltrados);
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }

    public String obtenerCodigoSeleccionado() {
        int filaSeleccionada = tablaProductos.getSelectedRow();

        if (filaSeleccionada == -1) {
            return null;
        }

        return modeloTabla.getValueAt(filaSeleccionada, 0).toString();
    }

    public boolean haySeleccion() {
        return tablaProductos.getSelectedRow() != -1;
    }
}