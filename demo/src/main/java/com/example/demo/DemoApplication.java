package com.example.demo;

import com.example.demo.modelo.Producto;
import com.example.demo.modelo.Venta;
import com.example.demo.servicios.ILocalComercial;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {
    
    @Autowired
    ILocalComercial localComercial;
    
    
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    @PostConstruct  //Hemos necesitado hacer esto porque nos daba error . Internet
    public void init(){
          int opcion = -1;  //NO VOLVER A UTILIZAR VAR
          Scanner scanner = new Scanner(System.in);

while(opcion != 0){
        System.out.println("Elige una opcion : \n"

                        + "1. Ingrese los datos de los productos a la venta que dispone dicho local.\n"
                        + "2. Ingrese los datos de las ventas registradas.\n"
                        + "3. Determinar cuál fue el vendedor que realizó la mayor cantidad de ventas.\n"
                        + "4. Generar un listado, ordenado por número de vendedor, de todas las ventas realizadas por débito.\n"
                        + "5. Determinar si el vendedor X realizó una venta del producto Y.\n"
                        + "6. Determinar el monto total de ventas realizadas."
                        + "7. Mostrar los datos de la venta de mayor importe abonada con tarjeta de crédito.\n"
                        + "8. Borrar un producto.\n"
                        + "9. Actualizar un producto determinado\n"
                        + "10.Actualizar una venta determinada con un 15% de descuento\n"
                        + "0. Salir");
        opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
                case 1: //El id no se pide por teclado, es autoincremento
                        String codigoProductoString = " ";
                        while(localComercial.comprobarNumeroTeclado(codigoProductoString)){
                            System.out.println("Introduce el codigo del producto con los números que lo identifican: ");
                            codigoProductoString = scanner.nextLine();
                        }
                        int codigoProducto = Integer.parseInt(codigoProductoString);
                        
                        String descripcionProducto = "1234";
                        while(!descripcionProducto.matches("^[A-Za-z ]*$")){
                            System.out.println("Introduce la descripcion del producto: ");
                            descripcionProducto = scanner.nextLine();
                        }
                        
                        String precioProductoString = " ";
                        while(localComercial.comprobarFloatTeclado(precioProductoString)){
                            System.out.println("Introduce el precio del producto con sus correspondientes cifras decimales: ");
                            precioProductoString = scanner.nextLine();
                        }
                        float precioUnitarioProducto = Float.parseFloat(precioProductoString);

                        Producto productoAAgregar = new Producto(codigoProducto,descripcionProducto,precioUnitarioProducto);
                        
                        //Insertar
                       
                        localComercial.ingresarProductos(productoAAgregar);
                        
                        System.out.println("El producto se ha insertado con éxito ");
                        break;

                case 2:
                        //El id no se pide por teclado, es autoincremento
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        System.out.println("Introduce la fecha de Venta del producto: ");
                        String fechaVentaString = scanner.nextLine();
                        Date fechaVenta = null;
                        try {
                                fechaVenta = formato.parse(fechaVentaString);  //NECESARIO EXCEPCIÓN PARA ERRORES
                        } catch (ParseException e) {
                                e.printStackTrace();
                        }

                        String numeroVendedorString = " ";
                        while(localComercial.comprobarNumeroTeclado(numeroVendedorString)){
                            System.out.println("Introduce el número del vendedor que va a realizar la venta de este producto: ");
                            numeroVendedorString = scanner.nextLine();
                        }
                        int numeroVendedor = Integer.parseInt(numeroVendedorString);

                        
                         // AQUÍ DEBEMOS DE VALIDAR QUE EXISTE EL PRODUCTO QUE SE DESEA VENDER EN EL LOCAL COMERCIAL (CON BBDD NO??) O SI LO ESCRIBIMOS MAL
                        String codigoString = " ";
                        while(localComercial.comprobarNumeroTeclado(codigoString)){
                            System.out.println("Introduce el codigo del producto que va a ser vendido: ");
                            codigoString = scanner.nextLine();
                        }
                        int codigo = Integer.parseInt(codigoString);
                       

                        String cantidadVendidaString = " ";
                        while(localComercial.comprobarNumeroTeclado(cantidadVendidaString)){
                            System.out.println("Introduce el numero de unidades a vender de dicho producto: ");
                            cantidadVendidaString = scanner.nextLine();
                        }
                        int cantidadVendidaProducto = Integer.parseInt(cantidadVendidaString);
                        
                        
                        String formaPagoString = " ";
                        while(!formaPagoString.equals("0") && !formaPagoString.equals("1") && !formaPagoString.equals("2")){
                            System.out.println("Introduce la forma de pago con la que desea abonar esta venta(0- Efectivo; 1- Débito; 2- Tarjeta ): ");
                            formaPagoString = scanner.nextLine();
                        }
                        int formaPago = Integer.parseInt(formaPagoString );

                        Venta ventaAAgregar = new Venta(fechaVenta,numeroVendedor,codigo,cantidadVendidaProducto,formaPago);
                        
                        localComercial.ingresarVentas(ventaAAgregar);
                        System.out.println("La venta se ha insertado con éxito");
                        
                        break;
                case 3:
                        //int id=0; //PEDIR POR TECLADO EL id. NO PUEDO PASARLE un id que no CONOZCO CUÁL ES
                        //Optional<Producto> productoObtenido = localComercial.obtenerProductoPorId(id);
                        //Producto prod2 = productoObtenido.get(); //ASÍ GUARDA MEJOR EL VALOR
                        //prod2.(ya puedo acceder a todos los campos que quiera y hacer el get.())
                        //Devolver el número del vendedor que realizó mayor cantidad de ventas
                        //vendedor =localComercial.
                        break;

                case 4:  //QUIZÁS SEA EL NÚMERO DE OTRA CONSULTA REORDENARLA
                        //Listado ordenado por nº vendedor de las ventas realizadas por débito. (PrecioFinal= precioUnitario* cantidadVendidaProducto)
                        //Utilizamos el lengh solo para cadenas. Size para el resto
                        List<Venta> listaVentas = localComercial.listarOrdenadoVentasDebito();
                        
                        for(int i = 0; i<listaVentas.size(); i++){
                            System.out.println("-- Venta --");
                            System.out.println("id: " + listaVentas.get(i).getIdVenta());
                            System.out.println("fecha: " + listaVentas.get(i).getFechaVenta());
                            System.out.println("numero vendedor: " + listaVentas.get(i).getNumeroVendedor());
                            System.out.println("codigo: " + listaVentas.get(i).getCodigo());
                            System.out.println("cantidad: " + listaVentas.get(i).getCantidadVendidaProducto());
                            System.out.println("precio final venta = " + listaVentas.get(i).getPrecioFinalVenta());
                            System.out.println("-- Fin Venta --");
                        }
                        break;
                case 5:
                        // Devuelve el resultado de buscar un vendedor y una venta determinada, si existe, la mostramos junto con su PrecioFinal y sino, mostramos mensaje error
                        //Buscar por id al vendedor y por otro lado la venta y si existen, mostarlo. Sino mostrar error
                        /*System.out.println("Introduce el id de la venta que deseas verificar:"); // VALIDAR QUE LE PASAMOS UN NÚMERO
                        String id_Venta_verificar_String = scanner.nextLine();
                        int id_Venta_verificar = Integer.parseInt(id_Venta_verificar_String);
                        Optional<Venta> ventaAVerificar = localComercial.obtenerVentaPorId(id_Venta_verificar);
                    
                        //while(ventaAVerificar) Tengo que validar que si no lo encuentra devuelve un error
                        Venta ventaEncontrada = ventaAVerificar.get();
                        
                        
                        System.out.println("Introduce el id del vendedor que deseas verificar si ha realizado la venta con el id anterior"); VALIDAR QUE ES UN NÚMERO
                        String id_Vendedor_verificar_String = scanner.nextLine();
                        int id_Vendedor_verificar = Integer.parseInt(id_Vendedor_verificar_String);
                        */
                        
                        break;
                case 6:
                        // Devuelve el monto total de ventas realizadas por los vendedores
                        break;
                case 7:
                        //Devuelve los datos de la venta de mayor importe de la venta abonada con tarjeta de crédito
                        break;     
                        
                case 8: //HAY QUE COMPROBAR PREVIAMENTE QUE ESE ID EXISTE DENTRO DE LA TABLA QUE SE DESEA ACTUALIZAR. Después llamar al método para validar un número
                        System.out.println("Introduce el id del producto que deseas borrar:");
                        String id_borrar_Producto_String = scanner.nextLine();
                        int id_Producto_Borrar = Integer.parseInt(id_borrar_Producto_String);
                        localComercial.borrarProducto(id_Producto_Borrar);
                        System.out.println("Se ha borrado el producto con exito  " + id_Producto_Borrar);
                    
                case 9:
                  
                       System.out.println("Introduce el id del producto que deseas actualizar:"); 
                       String id_actualizar_Producto_String = scanner.nextLine();
                       int id_Producto_actualizar = Integer.parseInt(id_actualizar_Producto_String);
                       Optional<Producto> productoAModificar = localComercial.obtenerProductoPorId(id_Producto_actualizar);
                       Producto productoActualizado = productoAModificar.get();
                       
                       System.out.println("Introduce la nueva descripción del producto que deseas actualizar:"); //Le pasamos el id_borrar= 2 por ejemplo
                       String descripcion = scanner.nextLine();
                       productoActualizado.setDescripcion(descripcion);
                       localComercial.ingresarProductos(productoActualizado);
                case 0:
                        System.out.println("Hasta pronto!!");
                        break;
                default:
                        System.out.println("Opcion no reconocida");
                        break;

        }
        }
    }
}
