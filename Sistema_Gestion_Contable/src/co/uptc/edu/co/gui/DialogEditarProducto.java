package co.uptc.edu.co.gui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogEditarProducto extends JDialog {

	private JTextField campoCodigo;
	private JTextField campoNombre;
	private JComboBox<String> comboCategoria;
	private JTextField campoStockMinimo;

	private JButton botonOK;
	private JButton botoncancelar;

	public DialogEditarProducto(Frame propietario) {
		this(propietario, null);

	}

	public DialogEditarProducto(Frame propietario, Evento evento) {
		// TODO Auto-generated constructor stub
		super(propietario, "Editar Producto", true);
		inicializarComponentes();
		configurarDialogo();
		agregarComponentes();
		inicializarEventos(evento);

	}

	private void inicializarComponentes() {
		// TODO Auto-generated method stub
		campoCodigo = new JTextField(25);
		campoNombre = new JTextField(25);

		comboCategoria = new JComboBox<>();
		comboCategoria.addItem("Aseo");
		comboCategoria.addItem("Alimentos");
		comboCategoria.addItem("Papeleria");

		campoStockMinimo = new JTextField("");

		botonOK = new JButton("OK");
		botoncancelar = new JButton("Cancelar");
	}

	private void configurarDialogo() {
		// TODO Auto-generated method stub
		setSize(340, 300);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
	}

	private void agregarComponentes() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 5, 0);
		panelPrincipal.add(new JLabel("Codigo de producto:"), gbc);

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
		panelPrincipal.add(new JLabel("Stock minimo:"), gbc);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 10, 0);
		panelPrincipal.add(campoStockMinimo, gbc);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		panelBotones.add(botonOK);
		panelBotones.add(botoncancelar);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(5, 0, 0, 0);
		panelPrincipal.add(panelBotones, gbc);

		add(panelPrincipal);

	}
	private void inicializarEventos(Evento evento) {
        botoncancelar.addActionListener(e -> dispose());
        if (evento != null) {
            botonOK.setActionCommand(Evento.CMD_CONFIRMAR_EDICION_PRODUCTO);
            botonOK.addActionListener(evento);
        } else {
            botonOK.addActionListener(e -> dispose());
        }
    }
}


