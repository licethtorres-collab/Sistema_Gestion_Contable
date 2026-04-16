package co.uptc.edu.co.gui;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import co.uptc.edu.co.modelo.Producto;
import co.uptc.edu.co.negocio.GestionProducto;
import co.uptc.edu.co.gui.dialog.DialogActualizarPrecio;
import co.uptc.edu.co.gui.dialog.DialogEditarProducto;
import co.uptc.edu.co.gui.dialog.DialogMovimientoInventario;
import co.uptc.edu.co.gui.dialog.DialogProducto;
import co.uptc.edu.co.modelo.Cliente;
import co.uptc.edu.co.negocio.GestionCliente;

public class Evento implements ActionListener {

    public static final String PRODUCTOS = "Productos";
    public static final String CLIENTES = "Clientes";
    public static final String PROVEEDORES = "Proveedores";
    public static final String VENTAS = "Ventas";
    public static final String COMPRAS = "Compras";
    public static final String CONTABILIDAD = "Contabilidad";
    public static final String REPORTES = "Reportes";
    public static final String CONSULTAS = "Consultas";

    public static final String CMD_NUEVO_PRODUCTO = "NuevoProducto";
    public static final String CMD_EDITAR_PRODUCTO = "EditarProducto";
    public static final String CMD_ESTADO_PRODUCTO = "InactivarProducto";
    public static final String CMD_ACTUALIZAR_PRECIO_PRODUCTO = "ActualizarPrecioProducto";
    public static final String CMD_MOVIMIENTO_INVENTARIO = "MovimientoInventarioProducto";

    public static final String CMD_CONFIRMAR_PRODUCTO = "ConfirmarProducto";
    public static final String CMD_CONFIRMAR_EDICION_PRODUCTO = "ConfirmarEdicionProducto";
    public static final String CMD_CONFIRMAR_INACTIVACION_PRODUCTO = "ConfirmarInactivacionProducto";
    public static final String CMD_CONFIRMAR_ACTUALIZACION_PRECIO_PRODUCTO = "ConfirmarActualizacionPrecioProducto";
    public static final String CMD_CONFIRMAR_MOVIMIENTO_INVENTARIO = "ConfirmarMovimientoInventario";
    
    public static final String CMD_NUEVO_CLIENTE = "NuevoCliente";
    public static final String CMD_EDITAR_CLIENTE = "EditarCliente";
    public static final String CMD_ESTADO_CLIENTE = "EstadoCliente";
    public static final String CMD_HISTORIAL_CLIENTE = "HistorialCliente";

    private VentanaPrincipal ventana;
    private GestionProducto gestionProducto;

    public Evento(VentanaPrincipal ventana) {
        this.ventana = ventana;
        this.gestionProducto = new GestionProducto();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {

            case PRODUCTOS:
                ventana.irProductos();
                refrescarTablaProductos();
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
                abrirDialogoNuevoProducto();
                break;

            case CMD_CONFIRMAR_PRODUCTO:
                registrarProducto(e);
                break;

            case CMD_EDITAR_PRODUCTO:
                abrirDialogoEditarProducto();
                break;

            case CMD_CONFIRMAR_EDICION_PRODUCTO:
                editarProducto(e);
                break;

            case CMD_ESTADO_PRODUCTO:
                cambiarEstadoProductoSeleccionado();
                break;

            case CMD_ACTUALIZAR_PRECIO_PRODUCTO:
                abrirDialogoActualizarPrecio();
                break;

            case CMD_CONFIRMAR_ACTUALIZACION_PRECIO_PRODUCTO:
                actualizarPrecioProducto(e);
                break;

            case CMD_MOVIMIENTO_INVENTARIO:
                abrirDialogoMovimientoInventario();
                break;

            case CMD_CONFIRMAR_MOVIMIENTO_INVENTARIO:
                registrarMovimientoInventario(e);
                break;

            default:
                break;
        }
    }

    private void abrirDialogoNuevoProducto() {
        DialogProducto dialog = new DialogProducto(ventana, this);
        dialog.setVisible(true);
    }

    private void registrarProducto(ActionEvent e) {
        try {
            Component componente = (Component) e.getSource();
            Window ventanaPadre = SwingUtilities.getWindowAncestor(componente);

            if (!(ventanaPadre instanceof DialogProducto)) {
                throw new Exception("Error interno: no se pudo identificar el formulario de producto.");
            }

            DialogProducto dialog = (DialogProducto) ventanaPadre;
            Producto producto = dialog.obtenerProducto();

            gestionProducto.registrarProducto(producto);

            JOptionPane.showMessageDialog(
                    ventana,
                    "Producto registrado exitosamente.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );

            refrescarTablaProductos();
            dialog.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    ventana,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void abrirDialogoEditarProducto() {
        try {
            PanelProducto panelProducto = ventana.getPanelProducto();

            if (!panelProducto.haySeleccion()) {
                throw new Exception("Debe seleccionar un producto para editar.");
            }

            String codigo = panelProducto.obtenerCodigoSeleccionado();
            Producto producto = gestionProducto.buscarProductoPorCodigo(codigo);

            if (producto == null) {
                throw new Exception("No se encontró el producto seleccionado.");
            }

            DialogEditarProducto dialog = new DialogEditarProducto(ventana, this);
            dialog.cargarProducto(producto);
            dialog.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    ventana,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void editarProducto(ActionEvent e) {
        try {
            Component componente = (Component) e.getSource();
            Window ventanaPadre = SwingUtilities.getWindowAncestor(componente);

            if (!(ventanaPadre instanceof DialogEditarProducto)) {
                throw new Exception("Error interno: no se pudo identificar el formulario de edición.");
            }

            DialogEditarProducto dialog = (DialogEditarProducto) ventanaPadre;
            Producto productoEditado = dialog.obtenerProductoEditado();

            gestionProducto.actualizarProducto(productoEditado);

            JOptionPane.showMessageDialog(
                    ventana,
                    "Producto editado exitosamente.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );

            refrescarTablaProductos();
            dialog.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    ventana,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cambiarEstadoProductoSeleccionado() {
        try {
            PanelProducto panelProducto = ventana.getPanelProducto();

            if (!panelProducto.haySeleccion()) {
                throw new Exception("Debe seleccionar un producto.");
            }

            String codigo = panelProducto.obtenerCodigoSeleccionado();
            Producto producto = gestionProducto.buscarProductoPorCodigo(codigo);

            if (producto == null) {
                throw new Exception("No se encontró el producto.");
            }

            boolean estabaActivo = producto.estaActivo();

            String mensaje = estabaActivo
                    ? "¿Está seguro de inactivar este producto?"
                    : "¿Está seguro de activar este producto?";

            int confirmacion = JOptionPane.showConfirmDialog(
                    ventana,
                    mensaje,
                    "Confirmar cambio de estado",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }

            gestionProducto.cambiarEstadoProducto(codigo);

            JOptionPane.showMessageDialog(
                    ventana,
                    estabaActivo
                            ? "Producto inactivado exitosamente."
                            : "Producto activado exitosamente.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );

            refrescarTablaProductos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    ventana,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void abrirDialogoActualizarPrecio() {
        try {
            PanelProducto panelProducto = ventana.getPanelProducto();

            if (!panelProducto.haySeleccion()) {
                throw new Exception("Debe seleccionar un producto para actualizar el precio.");
            }

            String codigo = panelProducto.obtenerCodigoSeleccionado();
            Producto producto = gestionProducto.buscarProductoPorCodigo(codigo);

            if (producto == null) {
                throw new Exception("No se encontró el producto seleccionado.");
            }

            DialogActualizarPrecio dialog = new DialogActualizarPrecio(ventana, this);
            dialog.cargarProducto(producto);
            dialog.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    ventana,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void actualizarPrecioProducto(ActionEvent e) {
        try {
            Component componente = (Component) e.getSource();
            Window ventanaPadre = SwingUtilities.getWindowAncestor(componente);

            if (!(ventanaPadre instanceof DialogActualizarPrecio)) {
                throw new Exception("Error interno: no se pudo identificar el formulario de actualización de precio.");
            }

            DialogActualizarPrecio dialog = (DialogActualizarPrecio) ventanaPadre;

            String codigo = dialog.obtenerCodigoProducto();
            double precioCompra = dialog.obtenerNuevoPrecioCompra();
            double precioVenta = dialog.obtenerNuevoPrecioVenta();

            gestionProducto.actualizarPrecioProducto(codigo, precioCompra, precioVenta);

            JOptionPane.showMessageDialog(
                    ventana,
                    "Precio actualizado exitosamente.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );

            refrescarTablaProductos();
            dialog.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    ventana,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void abrirDialogoMovimientoInventario() {
        try {
            PanelProducto panelProducto = ventana.getPanelProducto();

            if (!panelProducto.haySeleccion()) {
                throw new Exception("Debe seleccionar un producto para registrar el movimiento.");
            }

            String codigo = panelProducto.obtenerCodigoSeleccionado();
            Producto producto = gestionProducto.buscarProductoPorCodigo(codigo);

            if (producto == null) {
                throw new Exception("No se encontró el producto seleccionado.");
            }

            DialogMovimientoInventario dialog = new DialogMovimientoInventario(ventana, this);
            dialog.cargarProducto(producto.getCodigo());
            dialog.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    ventana,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void registrarMovimientoInventario(ActionEvent e) {
        try {
            Component componente = (Component) e.getSource();
            Window ventanaPadre = SwingUtilities.getWindowAncestor(componente);

            if (!(ventanaPadre instanceof DialogMovimientoInventario)) {
                throw new Exception("Error interno: no se pudo identificar el formulario de movimiento.");
            }

            DialogMovimientoInventario dialog = (DialogMovimientoInventario) ventanaPadre;

            String codigo = dialog.obtenerCodigoProducto();
            String tipoMovimiento = dialog.obtenerTipoMovimiento();
            int cantidad = dialog.obtenerCantidad();
            dialog.obtenerFecha();
            dialog.obtenerDescripcion();

            gestionProducto.registrarMovimientoInventario(codigo, tipoMovimiento, cantidad);

            JOptionPane.showMessageDialog(
                    ventana,
                    "Movimiento de inventario registrado exitosamente.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );

            refrescarTablaProductos();
            dialog.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    ventana,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void refrescarTablaProductos() {
        PanelProducto panelProducto = ventana.getPanelProducto();
        panelProducto.cargarProductos(gestionProducto.obtenerProductos());
    }
}