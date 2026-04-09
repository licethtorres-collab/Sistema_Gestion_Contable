package co.uptc.edu.co.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogInactivarProducto extends JDialog {

    private JTextField campoCodigo;
    private JTextField campoMotivo;

    private JButton botonInactivar;
    private JButton botonCancelar;

    public DialogInactivarProducto(Frame propietario) {
        this(propietario, null);
    }

    public DialogInactivarProducto(Frame propietario, Evento evento) {
        super(propietario, "Inactivar Producto", true);
        inicializarComponentes();
        configurarDialogo();
        agregarComponentes();
        inicializarEventos(evento);
    }

    private void inicializarComponentes() {
        campoCodigo = new JTextField(25);
        campoMotivo = new JTextField(25);

        botonInactivar = new JButton("Inactivar");
        botonCancelar = new JButton("Cancelar");

        botonInactivar.setBackground(new Color(183, 28, 28));
        botonInactivar.setForeground(Color.WHITE);

        botonCancelar.setBackground(new Color(220, 220, 220));
    }

    private void configurarDialogo() {
        setSize(360, 230);
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

        panelPrincipal.add(new JLabel("Código del producto:"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(campoCodigo, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(8, 0, 5, 0);
        panelPrincipal.add(new JLabel("Motivo:"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        panelPrincipal.add(campoMotivo, gbc);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.add(botonInactivar);
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
            botonInactivar.setActionCommand(Evento.CMD_CONFIRMAR_INACTIVACION_PRODUCTO);
            botonInactivar.addActionListener(evento);
        } else {
            botonInactivar.addActionListener(e -> dispose());
        }
    }
}