package co.uptc.edu.co.gui;

import javax.swing.*;
import javax.swing.JTextField;

import java.awt.*;

public class DialogProducto extends JDialog {
	private JTextField campoCodigo;
	private JTextField campoNombre;
	private JComboBox<String> comboCategoria;
	private JTextField campoPrecioVenta;
	private JTextField campoPrecioCompra;
	private JTextField campoStock;

	private JButton botonOK;
	private JButton botonCancelar;

	public DialogProducto(Frame Propietario) {
		this(Propietario, null);

	}

	public DialogProducto(Frame propietario, Evento evento) {
		super(propietario, "Registrar Producto", true);
		inicializarComponentes();
		configurarDialogo();
		agregarComponentes();
		inicializarEventos(evento);
	}

	private void inicializarComponentes() {
		campoCodigo = new JTextField(25);
		campoNombre = new JTextField(25);
		comboCategoria = new JComboBox<>();
		comboCategoria.addItem("Aseo");
		comboCategoria.addItem("Alimentos");
		comboCategoria.addItem("Bebidas");

		campoPrecioVenta = new JTextField(25);
		campoPrecioCompra = new JTextField(25);
		campoStock = new JTextField(25);

		botonOK = new JButton("OK");
		botonCancelar = new JButton("Cancelar");
	}

	private void configurarDialogo() {
		setSize(340, 410);
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
		panelPrincipal.add(new JLabel("Codigo:"), gbc);
		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panelPrincipal.add(campoCodigo, gbc);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(8, 0, 5, 0);
		panelPrincipal.add(new JLabel("Nombre:"), gbc);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 5, 0);
		panelPrincipal.add(campoNombre, gbc);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(8, 0, 5, 0);
		panelPrincipal.add(new JLabel("Categoria:"), gbc);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 5, 0);
		panelPrincipal.add(comboCategoria, gbc);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(8, 0, 5, 0);
		panelPrincipal.add(new JLabel("Precio de Venta:"), gbc);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 5, 0);
		panelPrincipal.add(campoPrecioVenta, gbc);

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
		panelPrincipal.add(new JLabel("Stock:"), gbc);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 10, 0);
		panelPrincipal.add(campoStock, gbc);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		panelBotones.add(botonOK);
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
			botonOK.setActionCommand(Evento.CMD_CONFIRMAR_PRODUCTO);
			botonOK.addActionListener(evento);
		} else {
			botonOK.addActionListener(e -> dispose());
		}
	}
}
