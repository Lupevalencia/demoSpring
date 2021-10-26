package com.example.demo.servicios;

import com.example.demo.modelo.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by d.zambrano_preving on 14/10/2021.
 */
public interface IVendedor extends JpaRepository<Vendedor, Integer> {
}
