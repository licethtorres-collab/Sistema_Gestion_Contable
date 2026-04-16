package co.uptc.edu.co.gui.dialog;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.uptc.edu.co.gui.Evento;
import co.uptc.edu.co.modelo.Producto;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class DialogEditarProducto extends JDialog {

    private JTextField campoCodigo;
    private JTextField campoNombre;
    private JComboBox<String> comboCategoria;
    private JTextField campoPrecioCompra;
    private JTextField campoPrecioVenta;
    private JTextField campoStockActual;
    private JTextField campoStockMinimo;
    private JTextField campoStockMaximo;

    private JButton botonGuardar;
    private JButton botonCancelar;

    public DialogEditarProducto(Frame propietario) {
        this(propietario, null);
    }

    public DialogEditarProducto(Frame propietario, Evento evento) {
        super(propietario, "Editar Producto", true);
        inicializarComponentes();
        configurarDialogo();
        agregarComponentes();
        inicializarEventos(evento);
    }

    private void inicializarComponentes() {
        campoCodigo = new JTextField(25);
        campoNombre = new JTextField(25);

        comboCategoria = new JComboBox<String>();
        comboCategoria.addItem("Aseo");
        comboCategoria.addItem("Alimentos");
        comboCategoria.addItem("Bebidas");
        comboCategoria.addItem("Papelería");

        campoPrecioCompra = new JTextField(25);
        campoPrecioVenta = new JTextField(25);
        campoStockActual = new JTextField(25);
        campoStockMinimo = new JTextField(25);
        campoStockMaximo = new JTextField(25);

        botonGuardar = new JButton("Guardar");
        botonCancelar = new JButton("Cancelar");

        botonGuardar.setBackground(new Color(46, 125, 50));
        botonGuardar.setForeground(Color.WHITE);

        botonCancelar.setBackground(new Color(220, 220, 220));
    }

    private void configurarDialogo() {
        setSize(360, 540);
        setLocationRelativeTo(getOwner());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void agregarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 5, 0);

        panelPrincipal.add(new JLabel("Código interno:"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(campoCodigo, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(8, 0, 5, 0);
        panelPrincipal.add(new JLabel("Nombre del producto:"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        panelPrincipal.add(campoNombre, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(8, 0, 5, 0);
        panelPrincipal.add(new JLabel("Categoría:"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        panelPrincipal.add(comboCategoria, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(8, 0, 5, 0);
        panelPrincipal.add(new JLabel("Precio de compra:"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        panelPrincipal.add(campoPrecioCompra, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(8, 0, 5, 0);
        panelPrincipal.add(new JLabel("Precio de venta:"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        panelPrincipal.add(campoPrecioVenta, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(8, 0, 5, 0);
        panelPrincipal.add(new JLabel("Stock actual:"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        panelPrincipal.add(campoStockActual, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(8, 0, 5, 0);
        panelPrincipal.add(new JLabel("Stock mínimo:"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        panelPrincipal.add(campoStockMinimo, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(8, 0, 5, 0);
        panelPrincipal.add(new JLabel("Stock máximo:"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        panelPrincipal.add(campoStockMaximo, gbc);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.add(botonGuardar);
        panelBotones.add(botonCancelar);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 0, 0);
        panelPrincipal.add(panelBotones, gbc);

        add(panelPrincipal);
    }

    private void inicializarEventos(Evento evento) {
        botonCancelar.addActionListener(e -> dispose());

        if (evento != null) {
            botonGuardar.setActionCommand(Evento.CMD_CONFIRMAR_EDICION_PRODUCTO);
            botonGuardar.addActionListener(evento);
        } else {
            botonGuardar.addActionListener(e -> dispose());
        }
    }

    public void cargarProducto(Producto producto) {
        campoCodigo.setText(producto.getCodigo());
        campoCodigo.setEditable(false);

        campoNombre.setText(producto.getNombreProducto());
        comboCategoria.setSelectedItem(producto.getCategoria());
        campoPrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
        campoPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
        campoStockActual.setText(String.valueOf(producto.getStockActual()));
        campoStockMinimo.setText(String.valueOf(producto.getStockMinimo()));
        campoStockMaximo.setText(String.valueOf(producto.getStockMaximo()));
    }

    public Producto obtenerProductoEditado() throws Exception {
        String codigo = campoCodigo.getText().trim();
        String nombre = campoNombre.getText().trim();
        String categoria = (String) comboCategoria.getSelectedItem();

        String precioCompraTexto = campoPrecioCompra.getText().trim();
        String precioVentaTexto = campoPrecioVenta.getText().trim();
        String stockActualTexto = campoStockActual.getText().trim();
        String stockMinimoTexto = campoStockMinimo.getText().trim();
        String stockMaximoTexto = campoStockMaximo.getText().trim();

        if (nombre.isEmpty()) {
            throw new Exception("El nombre del producto es obligatorio.");
        }

        if (precioCompraTexto.isEmpty()) {
            throw new Exception("El precio de compra es obligatorio.");
        }

        if (precioVentaTexto.isEmpty()) {
            throw new Exception("El precio de venta es obligatorio.");
        }

        if (stockActualTexto.isEmpty()) {
            throw new Exception("El stock actual es obligatorio.");
        }

        if (stockMinimoTexto.isEmpty()) {
            throw new Exception("El stock mínimo es obligatorio.");
        }

        if (stockMaximoTexto.isEmpty()) {
            throw new Exception("El stock máximo es obligatorio.");
        }

        double precioCompra;
        double precioVenta;
        int stockActual;
        int stockMinimo;
        int stockMaximo;

        try {
            precioCompra = Double.parseDouble(precioCompraTexto);
        } catch (NumberFormatException e) {
            throw new Exception("El precio de compra debe ser numérico.");
        }

        try {
            precioVenta = Double.parseDouble(precioVentaTexto);
        } catch (NumberFormatException e) {
            throw new Exception("El precio de venta debe ser numérico.");
        }

        try {
            stockActual = Integer.parseInt(stockActualTexto);
        } catch (NumberFormatException e) {
            throw new Exception("El stock actual debe ser un número entero.");
        }

        try {
            stockMinimo = Integer.parseInt(stockMinimoTexto);
        } catch (NumberFormatException e) {
            throw new Exception("El stock mínimo debe ser un número entero.");
        }

        try {
            stockMaximo = Integer.parseInt(stockMaximoTexto);
        } catch (NumberFormatException e) {
            throw new Exception("El stock máximo debe ser un número entero.");
        }

        if (precioCompra < 0) {
            throw new Exception("El precio de compra no puede ser negativo.");
        }

        if (precioVenta < 0) {
            throw new Exception("El precio de venta no puede ser negativo.");
        }

        if (precioVenta < precioCompra) {
            throw new Exception("El precio de venta no puede ser menor al de compra.");
        }

        if (stockActual < 0) {
            throw new Exception("El stock actual no puede ser negativo.");
        }

        if (stockMinimo < 0) {
            throw new Exception("El stock mínimo no puede ser negativo.");
        }

        if (stockMaximo < 0) {
            throw new Exception("El stock máximo no puede ser negativo.");
        }

        if (stockMinimo > stockActual) {
            throw new Exception("El stock mínimo no puede ser mayor al actual.");
        }

        if (stockActual > stockMaximo) {
            throw new Exception("El stock actual no puede ser mayor al stock máximo.");
        }

        if (stockMinimo > stockMaximo) {
            throw new Exception("El stock mínimo no puede ser mayor al stock máximo.");
        }

        return new Producto(
                codigo,
                nombre,
                categoria,
                precioCompra,
                precioVenta,
                stockActual,
                stockMinimo,
                stockMaximo,
                "Activo"
        );
    }
}