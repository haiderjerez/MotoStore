package pru.betr.valhalla.moto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pru.betr.valhalla.moto.Entity.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long>{
    List<Moto> findByMarcaContainingIgnoreCase(String marca);
    List<Moto> findByCilindradaBetween(int min, int max);
}
