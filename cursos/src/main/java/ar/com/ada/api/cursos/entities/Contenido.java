package ar.com.ada.api.cursos.entities;

import javax.persistence.*;

@Entity
@Table(name = "contenido")
public class Contenido {

    @Id
    @Column(name = "contenido_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contenidoId;
    private String descripcion;
    @Column(name = "descripcion_larga")
    private String descripcionLarga;
    private String payload;
    @Column(name = "payload_simple")
    private String payloadSimple;
    @ManyToOne
    @JoinColumn(name = "clase_id", referencedColumnName = "curso_id")
    private Clase clase;

    public enum TipoContenidoEnum {
        URL(1), PDF(2), VIDEO(3);

        private final Integer value;

        // Enum constructor tiene que ser privado
        private TipoContenidoEnum(int value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoContenidoEnum parse(Integer id) {
            TipoContenidoEnum status = null;
            for (TipoContenidoEnum item : TipoContenidoEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    public Integer getContenidoId() {
        return contenidoId;
    }

    public void setContenidoId(Integer contenidoId) {
        this.contenidoId = contenidoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getPayloadSimple() {
        return payloadSimple;
    }

    public void setPayloadSimple(String payloadSimple) {
        this.payloadSimple = payloadSimple;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

}
