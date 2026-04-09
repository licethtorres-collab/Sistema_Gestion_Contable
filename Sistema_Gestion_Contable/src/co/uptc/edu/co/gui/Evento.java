package co.uptc.edu.co.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Evento implements ActionListener {

    // Navegación principal
    public static final String PRODUCTOS = "Productos";
    public static final String CLIENTES = "Clientes";
    public static final String PROVEEDORES = "Proveedores";
    public static final String VENTAS = "Ventas";
    public static final String COMPRAS = "Compras";
    public static final String CONTABILIDAD = "Contabilidad";
    public static final String REPORTES = "Reportes";
    public static final String CONSULTAS = "Consultas";

    // Acciones de producto
    public static final String CMD_NUEVO_PRODUCTO = "NuevoProducto";
    public static final String CMD_EDITAR_PRODUCTO = "EditarProducto";
    public static final String CMD_INACTIVAR_PRODUCTO = "InactivarProducto";
    public static final String CMD_ACTUALIZAR_PRECIO_PRODUCTO = "ActualizarPrecioProducto";
    public static final String CMD_MOVIMIENTO_INVENTARIO = "MovimientoInventarioProducto";

    // Confirmaciones producto
    public static final String CMD_CONFIRMAR_PRODUCTO = "ConfirmarProducto";
    public static final String CMD_CONFIRMAR_EDICION_PRODUCTO = "ConfirmarEdicionProducto";
    public static final String CMD_CONFIRMAR_INACTIVACION_PRODUCTO = "ConfirmarInactivacionProducto";
    public static final String CMD_CONFIRMAR_ACTUALIZACION_PRECIO_PRODUCTO = "ConfirmarActualizacionPrecioProducto";

    private VentanaPrincipal ventana;

    public Evento(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {

            case PRODUCTOS:
                ventana.irProductos();
                break;

            case CLIENTES:
                ventana.irClientes();
                break;

            case PROVEEDORES:
                ventana.irProveedores();
                break;

            case VENTAS:
                ventana.irVentas();
                break;

            case COMPRAS:
                ventana.irCompras();
                break;

            case CONTABILIDAD:
                ventana.irContabilidad();
                break;

            case REPORTES:
                ventana.irReportes();
                break;

            case CONSULTAS:
                ventana.irConsultas();
                break;

            case CMD_NUEVO_PRODUCTO:
                new DialogProducto(ventana, this).setVisible(true);
                break;

            case CMD_EDITAR_PRODUCTO:
                new DialogEditarProducto(ventana, this).setVisible(true);
                break;

            case CMD_INACTIVAR_PRODUCTO:
                new DialogInactivarProducto(ventana, this).setVisible(true);
                break;

            case CMD_ACTUALIZAR_PRECIO_PRODUCTO:
                new DialogActualizarPrecio(ventana, this).setVisible(true);
                break;

            case CMD_MOVIMIENTO_INVENTARIO:
                JOptionPane.showMessageDialog(ventana, "En proceso.");
                break;

            case CMD_CONFIRMAR_PRODUCTO:
                JOptionPane.showMessageDialog(ventana, "Producto registrado exitosamente.");
                cerrarVentanaDeEvento(e);
                break;

            case CMD_CONFIRMAR_EDICION_PRODUCTO:
                JOptionPane.showMessageDialog(ventana, "Producto editado exitosamente.");
                cerrarVentanaDeEvento(e);
                break;

            case CMD_CONFIRMAR_INACTIVACION_PRODUCTO:
                JOptionPane.showMessageDialog(ventana, "Producto inactivado exitosamente.");
                cerrarVentanaDeEvento(e);
                break;

            case CMD_CONFIRMAR_ACTUALIZACION_PRECIO_PRODUCTO:
                JOptionPane.showMessageDialog(ventana, "Precio actualizado exitosamente.");
                cerrarVentanaDeEvento(e);
                break;

            default:
                break;
        }
    }

    private void cerrarVentanaDeEvento(ActionEvent e) {
        Component componente = (Component) e.getSource();
        SwingUtilities.getWindowAncestor(componente).dispose();
    }
}