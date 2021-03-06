package ar.com.ada.api.cursos.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @Column(name = "curso_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cursoId;
    private String nombre;
    // cursosQueDicta para diferenciar los cursos de docente de los de estudiante
    @ManyToMany(mappedBy = "cursosQueDicta")
    private List<Docente> docentes;
    @ManyToMany(mappedBy = "cursosQueAsiste")
    private List<Estudiante> estudiantes;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Clase> clases;
    @ManyToMany(mappedBy = "cursos")
    private List<Categoria> categorias;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Inscripcion> inscripciones;
    @Column(name = "duracion_horas")
    private Integer duracionHoras;

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public void asignarDocente(Docente docente) {
        // this refiere a la instancia de la clase cursos actualmente ejecutando el
        // codigo (el metodo asignar docente)
        // docentes refiere al atributo lista de docentes que est??n asignados a ese
        // curso
        this.docentes.add(docente);
        // le "avisamos al docente" que tiene que dictar ese curso
        docente.getCursosQueDicta().add(this);
    }

    public void asignarEstudiante(Estudiante estudiante) {
        // this refiere a la instancia de la clase cursos actualmente ejecutando el
        // codigo(el metodo asignar estudiante)
        // estudiantes refiere al atributo lista de estudiantes que est??n asignados a
        // ese curso
        this.estudiantes.add(estudiante);
        // le "avisamos al estudiante" que tiene que asistir a ese curso
        estudiante.getCursosQueAsiste().add(this);
    }

    public void agregarClase(Clase clase) {
        this.clases.add(clase);
        // curso no es una lista, por lo que se setea
        clase.setCurso(this);
    }

    public void agregarCategorias(List<Categoria> categoriasList) {
        if (this.categorias == null)
            this.categorias = new ArrayList<Categoria>();
        this.categorias.addAll(categoriasList);
        for (Categoria c : categorias) {
            c.getCursos().add(this);
        }
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        this.inscripciones.add(inscripcion);
        inscripcion.setCurso(this);
    }

    public Integer getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(Integer duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

}
