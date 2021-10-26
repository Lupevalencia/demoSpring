/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.servicios;

import com.example.demo.modelo.Producto;
import com.example.demo.modelo.Venta;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author g.valencia_preving
 */
@Service
public class LocalComercialImpl implements ILocalComercial{

    @Autowired
    IProductos datoProductos;

    @Autowired
    IVenta datoVenta;

    @Autowired
    IVendedor datoVendedor;

    @Override
    public void ingresarProductos(Producto productoAAgregar) {
        datoProductos.save(productoAAgregar);
    }

        @Override
    public void ingresarVentas(Venta ventaAAgregar) {
        datoVenta.save(ventaAAgregar);
    }
    //3
    //@Override
    //public int vendedorConMayorCantidadVentas() {
      //  return datoVenta.vendedorConMayorCantidadVentas();
    //}
    //4
    @Override
    public List<Venta> listarOrdenadoVentasDebito() {
        return datoVenta.listarOrdenadoVentasDebito();
    }

    //5
    @Override
    public List<Venta> comprobarVentaRealizadaPorVendedor() {
        return null; // provisional
        //return datoVenta.comprobarVentaRealizadaPorVendedor();
    }
    
    //6
    //@Override
    //public float montoTotalVentas() {
      //  return datoVenta.montoTotalVentas();
    //}
    
    //7
    //@Override
    //public List<Venta> DatosVentaMayorTarjetaCredito() {
      //  return datoVenta.DatosVentaMayorTarjetaCredito();
    //}
    // Lo necesitamos para el 5, siempre que busquemos algo en concreto
    
    
    
    @Override
    public Optional<Producto> obtenerProductoPorId(int id) {
         Optional<Producto> p = datoProductos.findById(id);
         return p;
    }
    
    // @Override
    //public Optional<Venta> obtenerVentaPorId(int id) {
      //   Optional<Venta> v = datoVenta.findById(id);
        // return v;
    //}
    
    //8
    @Override
    public void borrarProducto(int id) {
        datoProductos.deleteById(id);
    }
    
   // @Override  Comentada porque no debe llamarse así 
   // public List<Venta> obtenerMaiorVenta() {
     //   return datoVenta.obtenerMaiorVenta();
    //}


    @Override
    public boolean comprobarNumeroTeclado(String codigo) {
        try{
            Integer.parseInt(codigo);
            return false;
        }catch(NumberFormatException e){
            //System.out.println("ERROR. No se ha introducido el valor esperado");
            return true;
        }        
    }
    @Override
    public boolean comprobarFloatTeclado(String precio) {
        try{
            Float.parseFloat(precio);
            return false;
        }catch(NumberFormatException e){
            //System.out.println("ERROR. No se ha introducido el valor esperado");
            return true;
        }        
    }
    
       
}
