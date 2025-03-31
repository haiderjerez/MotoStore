package pru.betr.valhalla.moto.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "motos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;

    @Column(nullable = false)
    private int cilindrada;

    @Column(nullable = false)
    private double precio;
    private String imagenUrl;
}


