package ar.com.ada.api.cursos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.entities.Categoria;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.CategoriaService;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    // Post: que recibimos algo, que nos permite instanciar una Categoria y ponerle
    // datos.
    @PostMapping("/categorias")
    public ResponseEntity<GenericResponse> crearCategoria(@RequestBody Categoria categoria) {
        categoriaService.crearCategoria(categoria);

        GenericResponse r = new GenericResponse();
        r.isOK = true;
        r.message = "Categoria creada con exito";
        r.id = categoria.getCategoriaId();

        return ResponseEntity.ok(r);

    }

}
