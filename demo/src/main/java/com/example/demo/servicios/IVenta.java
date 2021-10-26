package com.example.demo.servicios;

import com.example.demo.modelo.Venta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by d.zambrano_preving on 14/10/2021.
 */
@Repository
public interface IVenta extends JpaRepository<Venta, Integer> { //Cuidado con escribir la clase diferente, no la reconoce
    
    //public int vendedorConMayorCantidadVentas();
    
    @Query(value = "select * from " +
"(select codigo_producto, cantidad_vendida_producto, forma_pago, numero_vendedor, fecha_venta, id_venta " +
"from ventas " +
"where forma_pago = 1 " +
"order by numero_vendedor) tabla1 " +
"inner join " +
"(SELECT ventas.codigo_producto, round(precio_unitario * cantidad_Vendida_producto,2) as precio_final_venta, precio_unitario " +
"FROM productos " +
"INNER JOIN ventas ON (productos.id_producto = ventas.codigo_producto)) tabla2 " +
"on tabla1.codigo_producto = tabla2.codigo_producto;",nativeQuery = true) //Consultas, hacer igual con las demás
    public List<Venta> listarOrdenadoVentasDebito();
    
    
    //public List<Venta> comprobarVentaRealizadaPorVendedor();
    
    //public List<Venta> DatosVentaMayorTarjetaCredito();
    
    //public float montoTotalVentas();
}
