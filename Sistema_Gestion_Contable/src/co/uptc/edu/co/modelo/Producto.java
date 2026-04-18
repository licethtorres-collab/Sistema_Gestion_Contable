package co.uptc.edu.co.modelo;

public class Producto {

    private String codigoProducto;
    private String nombreProducto;
    private String categoria;
    private double precioCompra;
    private double precioVenta;
    private int stockActual;
    private int stockMinimo;
    private int stockMaximo;
    private String estado;

    public Producto() {
    }

    public Producto(String codigoProducto, String nombreProducto, String categoria,
            double precioCompra, double precioVenta,
            int stockActual, int stockMinimo, int stockMaximo, String estado) {

        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.estado = estado;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(int stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean estaActivo() {
        return "ACTIVO".equalsIgnoreCase(estado);
    }

    public boolean stockBajoMinimo() {
        return stockActual < stockMinimo;
    }

    @Override
    public String toString() {
        return codigoProducto + " - " + nombreProducto;
    }
}