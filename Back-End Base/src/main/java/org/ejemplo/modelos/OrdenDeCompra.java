import javax.persistence.*;
import java.util.Date;

@Entity
public class OrdenDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    private int cantidad;

    @Enumerated(EnumType.STRING)
    private EstadoOrdenCompra estado;

    private Date fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "usuario_actualizo_id")
    private Usuario usuarioQueActualizo;


}

