document.addEventListener("DOMContentLoaded", () => {
    iniciarPantallaInicial();
    setupModals();
    fetchMotos();
});

function iniciarPantallaInicial() {
    const pantallaInicial = document.getElementById("pantallaInicial");
    const contenedorMotos = document.getElementById("contenedorMotos");

    if (pantallaInicial && contenedorMotos) {
        setTimeout(() => {
            pantallaInicial.style.display = "none";
            contenedorMotos.style.display = "grid";
        }, 1000);
    }
}

function setupModals() {
    const modalAgregarMoto = document.getElementById("modalAgregarMoto");
    const openAddMotoModal = document.getElementById("openAddMotoModal");
    const closeAddMotoModal = document.getElementById("closeAddMotoModal");

    if (openAddMotoModal && modalAgregarMoto) {
        openAddMotoModal.addEventListener("click", () => {
            modalAgregarMoto.style.display = "flex";
        });

        closeAddMotoModal?.addEventListener("click", () => {
            modalAgregarMoto.style.display = "none";
        });

        window.addEventListener("click", (event) => {
            if (event.target === modalAgregarMoto) {
                modalAgregarMoto.style.display = "none";
            }
        });
    }
}

function fetchMotos() {
    fetch("http://localhost:8080/api/motos/obtener")
        .then(response => {
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
            return response.json();
        })
        .then(motos => mostrarMotos(motos))
        .catch(error => console.error("Error cargando motos:", error));
}

function mostrarMotos(motos) {
    const container = document.getElementById("contenedorMotos");

    if (!container) {
        console.error("Error: No se encontró el contenedor de motos.");
        return;
    }

    container.innerHTML = "";

    if (!Array.isArray(motos) || motos.length === 0) {
        container.innerHTML = "<p>No hay motos disponibles</p>";
        return;
    }

    motos.forEach(moto => {
        const motoCard = document.createElement("div");
        motoCard.classList.add("moto-card");
        motoCard.innerHTML = `
            <img src="${moto.imagenUrl}" alt="${moto.modelo}" width="100%">
            <h2>${moto.marca} - ${moto.modelo}</h2>
            <p>Cilindrada: ${moto.cilindrada} cc</p>
            <p>Precio: ${moto.precio}</p>
        `;
        container.appendChild(motoCard);
    });
}

function agregarMoto() {
    const marca = document.getElementById("marca")?.value;
    const modelo = document.getElementById("modelo")?.value.trim();
    const cilindrada = parseInt(document.getElementById("cilindrada")?.value);
    const precio = parseFloat(document.getElementById("precio")?.value);
    const imagenUrl = document.getElementById("imagenUrl")?.value.trim();

    if (!modelo || isNaN(cilindrada) || isNaN(precio) || !imagenUrl) {
        alert("Completa todos los campos.");
        return;
    }

    const motoData = { marca, modelo, cilindrada, precio, imagenUrl };

    fetch("http://localhost:8080/api/motos/guardar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(motoData)
    })
    .then(response => {
        if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
        return response.json();
    })
    .then(() => {
        alert("Moto agregada correctamente.");
        document.getElementById("modalAgregarMoto").style.display = "none";
        fetchMotos();
    })
    .catch(error => console.error("Error al guardar moto:", error));
}
