# 🏍️ MotoStore Valhalla

¡Bienvenido a **MotoStore Valhalla**! Un proyecto web para exhibir y administrar motocicletas de mediano y alto cilindraje con un toque vikingo. ⚡🔥

---

## 🚀 Tecnologías Utilizadas

- **Frontend:** HTML, CSS (diseño elegante y oscuro), JavaScript.
- **Backend:** Spring Boot con PostgreSQL.
- **Base de Datos:** PostgreSQL.
- **API:** Comunicación con endpoints para obtener, filtrar, agregar y eliminar motos.

---

## 🎯 Funcionalidades

✅ **Catálogo de Motos:** Se muestran en tarjetas con imagen, marca, modelo, cilindrada y precio.

✅ **Filtros:** Permite buscar por marca o rango de cilindraje.

✅ **Agregar Motos:** Formulario con marcas predefinidas y validaciones.

✅ **Eliminar Motos:** Cada tarjeta tiene un botón de eliminar (con confirmación).

✅ **Efectos Visuales:** Aparición de elementos con animaciones sutiles y tipografía vikinga.

✅ **Pantalla Inicial:** Desaparece después de 1 segundo para dar paso a las motos.

✅ **Modales Funcionales:** Para agregar motos y aplicar filtros sin recargar la página.

---

## 📡 Endpoints del Backend

🔹 **Obtener motos:** `GET http://localhost:8080/api/motos/obtener`

🔹 **Buscar por marca:** `GET http://localhost:8080/api/motos/buscar?marca=BMW`

🔹 **Filtrar por cilindrada:** `GET http://localhost:8080/api/motos/filtro?min=400&max=996`

🔹 **Agregar moto:** `POST http://localhost:8080/api/motos/guardar`

🔹 **Eliminar moto:** `DELETE http://localhost:8080/api/motos/eliminar/{id}`

---

## 🎭 Estilo Visual

🟣 **Paleta de colores:** Negro y morado.

🔠 **Tipografía:** Estilo vikinga para los títulos.

🎨 **Diseño:** Tarjetas con efectos al pasar el mouse, modal con inputs elegantes.

---

## 🛠️ Instalación y Ejecución

1️⃣ Clona el repositorio:
```bash
 git clone https://github.com/tu_usuario/motostore-valhalla.git
```

2️⃣ Abre el proyecto y ejecuta el backend con Spring Boot.

3️⃣ Inicia el frontend abriendo `index.html` en un navegador.

4️⃣ Disfruta la experiencia **MotoStore Valhalla**. 🏍️🔥

---

## 🤘 Contribuciones

Si quieres aportar mejoras, envía un **Pull Request** o sugiere cambios en **Issues**.

---

## ⚡ Autor

👑 **Jefe** – Proyecto diseñado y desarrollado con toda la actitud malandra. 

📩 Contáctame si tienes sugerencias o quieres más mejoras. 

---

¡Acelera con **MotoStore Valhalla** y siente el poder de las dos ruedas! 🏍️🔥
