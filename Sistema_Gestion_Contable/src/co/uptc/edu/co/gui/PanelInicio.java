package co.uptc.edu.co.gui;

import javax.swing.*;
import java.awt.*;

public class PanelInicio extends JPanel {

    private JLabel etiquetaTitulo;
    private JLabel etiquetaBienvenida;
    private JLabel etiquetaInstruccion;
    
    private JPanel panelCentral;

    public PanelInicio() {
        inicializarComponentes();
        configurarPanel();
        agregarComponentes();
    }

    private void inicializarComponentes() {
        etiquetaTitulo = new JLabel("Sistema de Gestión Contable y Comercial");
        etiquetaBienvenida = new JLabel("Bienvenido");
        etiquetaInstruccion = new JLabel("Seleccione un modulo en la barra superior para comenzar");
       

        panelCentral = new JPanel();
    }

    private void configurarPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        etiquetaTitulo.setForeground(new Color(20, 45, 75));

        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 22));
        etiquetaBienvenida.setForeground(Color.DARK_GRAY);

        etiquetaInstruccion.setFont(new Font("Arial", Font.PLAIN, 18));
        etiquetaInstruccion.setForeground(Color.GRAY);

        panelCentral.setLayout(new GridBagLayout());
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        panelCentral.setPreferredSize(new Dimension(700, 250));
    }

    private void agregarComponentes() {
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;
        add(panelCentral, gbcPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridy = 0;
        panelCentral.add(etiquetaTitulo, gbc);

        gbc.gridy = 1;
        panelCentral.add(etiquetaBienvenida, gbc);

        gbc.gridy = 2;
        panelCentral.add(etiquetaInstruccion, gbc);

       
    }
}