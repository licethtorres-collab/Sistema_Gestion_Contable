package co.uptc.edu.co.modelo;

import java.time.LocalDate;

public class MovimientoInventario {

    private String codigoMovimiento;
    private String codigoProducto;
    private String tipoMovimiento;
    private int cantidad;
    private LocalDate fechaMovimiento;
    private String descripcion;

    public MovimientoInventario() {
    }

    public MovimientoInventario(String codigoMovimiento, String codigoProducto,
            String tipoMovimiento, int cantidad,
            LocalDate fechaMovimiento, String descripcion) {
        this.codigoMovimiento = codigoMovimiento;
        this.codigoProducto = codigoProducto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.descripcion = descripcion;
    }

    public String getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(String codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}