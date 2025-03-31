package pru.betr.valhalla.moto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pru.betr.valhalla.moto.DTOs.MotoDTO;
import pru.betr.valhalla.moto.Entity.Moto;
import pru.betr.valhalla.moto.Service.MotoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/motos")
@CrossOrigin(origins = "*")
public class MotoController {
    @Autowired
    private MotoService motoService;

    @GetMapping("/obtener")
    public List<MotoDTO> obtenerTodas() {
        return motoService.obtenerTodas();
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<MotoDTO> obtenerPorId(@PathVariable Long id) {
        Optional<MotoDTO> moto = motoService.obtenerPorId(id);
        return moto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<MotoDTO> buscarPorMarca(@RequestParam String marca) {
        return motoService.buscarPorMarca(marca);
    }

    @GetMapping("/filtro")
    public List<MotoDTO> filtrarPorCilindrada(@RequestParam int min, @RequestParam int max) {
        return motoService.filtrarPorCilindrada(min, max);
    }

    @PostMapping("/guardar")
    public Moto guardarMoto(@RequestBody Moto moto) {
        return motoService.guardarMoto(moto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Moto> actualizarMoto(@PathVariable Long id, @RequestBody Moto motoDetalles) {
        Moto motoActualizada = motoService.actualizarMoto(id, motoDetalles);
        return ResponseEntity.ok(motoActualizada);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarMoto(@PathVariable Long id) {
        motoService.eliminarMoto(id);
    }
}
