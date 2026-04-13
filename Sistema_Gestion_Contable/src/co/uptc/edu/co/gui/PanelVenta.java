package co.uptc.edu.co.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelVenta extends JPanel {

    private JLabel etiquetaTitulo;
    private JLabel etiquetaTotalVentas;
    

    private JButton botonNuevaVenta;
    private JButton botonAnularVenta;
    private JButton botonVerDetalle;
    private JButton botonFactura;
    
    private JTextField campoBuscar;
    private JComboBox<String> comboCliente;
    private JComboBox<String> comboPago;
    
    private JTable tablaVentas;
    private DefaultTableModel modeloTabla;
    
    private JPanel panelSuperior;
    private JPanel panelBotones;
    private JPanel panelFiltros;
    private JPanel panelInferior;
    
   public PanelVenta(){
	   
	   inicializarComponentes();
	   configurarPanel();
	   agregarComponentes();
	   
	   
   }
   
   private void inicializarComponentes(){
	  
	   etiquetaTitulo = new JLabel("Gestion de ventas");
	   etiquetaTotalVentas = new JLabel("Total de ventas:  0 ");
	   
	   botonNuevaVenta = new JButton("Nueva Venta");
	   botonAnularVenta = new JButton("Anular");
	   botonVerDetalle = new JButton("Ver Detalle");
	   botonFactura = new JButton("Factura");
	   
	   campoBuscar = new JTextField(20);
	   
	   comboCliente = new JComboBox<>();
	   comboCliente.addItem("Todos");
	   comboCliente.addItem("Cliente 1");
	   comboCliente.addItem("Cliente 2");
	   
	   comboPago = new JComboBox<>();
	   comboPago.addItem("todos");
	   comboPago.addItem("Efectivo");
	   comboPago.addItem("Tarjeta");
	   comboPago.addItem("Trasferencia");
	   
	   String[] columnas = {
               "Factura",
               "Fecha",
               "Hora",
               "Cliente",
               "Forma de Pago",
               "Medio de Pago",
               "IVA",
               "Retenciones",
               "Total",
               "Estado"
       };
	   
	   // tabla no editable
       modeloTabla = new DefaultTableModel(columnas, 0) {
           @Override
           public boolean isCellEditable(int row, int column) {
               return false;
           }
       };
       
       
       tablaVentas = new JTable(modeloTabla);
	   
       panelSuperior = new JPanel();
       panelBotones = new JPanel();
       panelFiltros = new JPanel();
       panelInferior = new JPanel();
	   
   }
   private void configurarPanel(){
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

       tablaVentas.setRowHeight(25);
       tablaVentas.getTableHeader().setReorderingAllowed(false);
       tablaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	   
       botonNuevaVenta.setBackground(Color.WHITE);
       botonAnularVenta.setBackground(Color.WHITE);
       botonVerDetalle.setBackground(Color.WHITE);
       botonFactura.setBackground(Color.WHITE);
      
   }
   
   private void agregarComponentes(){
	   
	   
	   panelBotones.add(botonNuevaVenta);
	   panelBotones.add(botonAnularVenta);
	   panelBotones.add(botonVerDetalle);
	   panelBotones.add(botonFactura);
	   
	   panelFiltros.add(new JLabel("Buscar Factura:"));
       panelFiltros.add(campoBuscar);
       
       panelFiltros.add(new JLabel("Cliente"));
       panelFiltros.add(comboCliente);
       
       panelFiltros.add(new JLabel("Pago"));
       panelFiltros.add(comboPago);
       
       JPanel panelCabecera = new JPanel(new BorderLayout());
       panelCabecera.setBackground(Color.WHITE);
       
       panelCabecera.add(etiquetaTitulo, BorderLayout.NORTH);
       panelCabecera.add(panelBotones, BorderLayout.CENTER);
       panelCabecera.add(panelFiltros, BorderLayout.SOUTH);


	   
       panelSuperior.add(panelCabecera, BorderLayout.CENTER);

       JScrollPane scroll = new JScrollPane(tablaVentas);

       panelInferior.add(etiquetaTotalVentas);

       add(panelSuperior, BorderLayout.NORTH);
       add(scroll, BorderLayout.CENTER);
       add(panelInferior, BorderLayout.SOUTH);
   }
	   

   
}