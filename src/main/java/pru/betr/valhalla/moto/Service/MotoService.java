package pru.betr.valhalla.moto.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pru.betr.valhalla.moto.DTOs.MotoDTO;
import pru.betr.valhalla.moto.Entity.Moto;
import pru.betr.valhalla.moto.Repository.MotoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotoService {
    @Autowired
    private MotoRepository motoRepository;

    public List<MotoDTO> obtenerTodas() {
        List<Moto> motos = motoRepository.findAll();
        return motos.stream().map(this::convertirAMotoDTO).collect(Collectors.toList());
    }

    public Optional<MotoDTO> obtenerPorId(Long id) {
        return motoRepository.findById(id).map(this::convertirAMotoDTO);
    }

    public List<MotoDTO> buscarPorMarca(String marca) {
        return motoRepository.findByMarcaContainingIgnoreCase(marca)
                .stream()
                .map(this::convertirAMotoDTO)
                .collect(Collectors.toList());
    }

    public List<MotoDTO> filtrarPorCilindrada(int min, int max) {
        return motoRepository.findByCilindradaBetween(min, max)
                .stream()
                .map(this::convertirAMotoDTO)
                .collect(Collectors.toList());
    }

    public Moto guardarMoto(Moto moto) {
        return motoRepository.save(moto);
    }

    public Moto actualizarMoto(Long id, Moto motoDetalles) {
        Moto moto = motoRepository.findById(id).orElseThrow(() -> new RuntimeException("Moto no encontrada"));
        moto.setMarca(motoDetalles.getMarca());
        moto.setModelo(motoDetalles.getModelo());
        moto.setCilindrada(motoDetalles.getCilindrada());
        moto.setPrecio(motoDetalles.getPrecio());
        moto.setImagenUrl(motoDetalles.getImagenUrl());
        return motoRepository.save(moto);
    }

    public void eliminarMoto(Long id) {
        motoRepository.deleteById(id);
    }

    private MotoDTO convertirAMotoDTO(Moto moto) {
        return new MotoDTO(moto.getId(), moto.getMarca(), moto.getModelo(), moto.getImagenUrl(), moto.getPrecio(), moto.getCilindrada());
    }
}
