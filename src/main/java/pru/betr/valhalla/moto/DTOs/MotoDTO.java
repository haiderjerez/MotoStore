package pru.betr.valhalla.moto.DTOs;

import java.text.NumberFormat;
import java.util.Locale;

public class MotoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String imagenUrl;
    private double precio;
    private int cilindrada;

    public MotoDTO(Long id, String marca, String modelo, String imagenUrl, double precio, int cilindrada) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.imagenUrl = imagenUrl;
        this.precio = precio;
        this.cilindrada = cilindrada;
    }

    // 🔥 Método adicional para devolver el precio con formato cuando se necesite
    public String getPrecioFormateado() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        return formatter.format(precio);
    }

    public Long getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getImagenUrl() { return imagenUrl; }
    public double getPrecio() { return precio; }
    public int getCilindrada() { return cilindrada; }

    public void setId(Long id) { this.id = id; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCilindrada(int cilindrada) { this.cilindrada = cilindrada; }
}
