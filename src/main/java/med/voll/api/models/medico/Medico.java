package med.voll.api.models.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.models.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;
    private Boolean activo;

    public Medico(DatosMedicos datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.documento = datosRegistroMedico.documento();
        this.telefono = datosRegistroMedico.telefono();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
        this.activo = true;
    }

    public void actulizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if(datosActualizarMedico.nombre() != null)
            this.nombre = datosActualizarMedico.nombre();
        if(datosActualizarMedico.documento() != null)
            this.documento = datosActualizarMedico.documento();
        if(datosActualizarMedico.direccion() != null)
            this.direccion= direccion.actulizardatos(datosActualizarMedico.direccion());
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
