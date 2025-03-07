package com.test.API_CSV.controller;

import com.test.API_CSV.dto.Producto;
import com.test.API_CSV.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoControllerRest {
    @Autowired
    @Lazy
    private ProductoService productosService;


    @GetMapping
    public ResponseEntity<?> getProductos(){
        List<Producto> productos=productosService.getProductos();
        return ResponseEntity.ok(productos);
    }
}
