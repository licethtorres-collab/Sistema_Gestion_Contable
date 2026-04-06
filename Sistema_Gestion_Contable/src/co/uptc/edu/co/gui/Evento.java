package co.uptc.edu.co.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Evento implements ActionListener {
	public static final String CMD_CLIENTES = "Clientes";
	public static final String CMD_PRODUCTOS = "Productos";
	public static final String CMD_NUEVO_PRODUCTO = "NuevoProducto";
	public static final String CMD_CONFIRMAR_PRODUCTO = "ConfirmarProducto";
	public static final String CMD_EDITAR_PRODUCTO = "EditarProducto";
	public static final String CMD_INACTIVAR_PRODUCTO = "InactivarProducto";
	public static final String CMD_ACTUALIZAR_PRECIO_PRODUCTO = "ActualizarPrecioProducto";
	public static final String CMD_MOVIMIENTO_INVENTARIO = "MovimientoInventarioProducto";
	public static final String CMD_PROVEEDORES = "Proveedores";
	public static final String CMD_VENTAS = "Ventas";
	public static final String CMD_COMPRAS = "Compras";
	public static final String CMD_CONTABILIDAD = "Contabilidad";
	public static final String CMD_REPORTES = "Reportes";
	public static final String CMD_CONSULTAS = "Consultas";

	private VentanaPrincipal ventana;

	public Evento(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();

		switch (comando) {

		case CMD_CLIENTES:
			ventana.mostrarPanel(new PanelCliente());
			break;

		case CMD_PRODUCTOS:
			PanelProducto panelProducto = new PanelProducto();
			panelProducto.inicializarEventos(this);
			ventana.mostrarPanel(panelProducto);
			break;
		case CMD_NUEVO_PRODUCTO:
			DialogProducto dialogProducto = new DialogProducto(ventana, this);
			dialogProducto.setVisible(true);
			break;
		case CMD_CONFIRMAR_PRODUCTO:
			JOptionPane.showMessageDialog(ventana, "Registro de producto exitoso");
			Component componente = (Component) e.getSource();
			SwingUtilities.getWindowAncestor(componente).dispose();
			break;
		case CMD_EDITAR_PRODUCTO:
		case CMD_INACTIVAR_PRODUCTO:
		case CMD_ACTUALIZAR_PRECIO_PRODUCTO:
		case CMD_MOVIMIENTO_INVENTARIO:
			JOptionPane.showMessageDialog(ventana, "En procesos");
			break;
		case CMD_PROVEEDORES:
			ventana.mostrarPanel(new PanelProveedor());
			break;

		case CMD_VENTAS:
			ventana.mostrarPanel(new PanelVenta());
			break;

		case CMD_COMPRAS:
			ventana.mostrarPanel(new PanelCompra());
			break;

		case CMD_CONTABILIDAD:
			ventana.mostrarPanel(new PanelContabilidad());
			break;

		case CMD_REPORTES:
			ventana.mostrarPanel(new PanelReportes());
			break;

		case CMD_CONSULTAS:
			ventana.mostrarPanel(new PanelConsultas());
		}
	}
}