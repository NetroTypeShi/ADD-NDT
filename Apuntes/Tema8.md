# Implementación de APIs en Java – Tema 8.3
## Acceso a datos

Apuntes resumidos a partir de las diapositivas.

---

## 1. Controllers

- En Spring Boot, un controlador es la clase que gestiona las peticiones HTTP.
- Se identifica mediante la anotación:
  - `@RestController`

---

## 2. @RequestMapping

- Permite definir el recurso que va a gestionar un controlador.
- Se coloca sobre la clase:
  - `@RequestMapping("nombreRecurso")`

### Buenas prácticas al nombrar recursos

- Usar sustantivos.
- Usar letras en minúscula.
- Evitar caracteres especiales.
- Separar palabras con `-`.

---

## 3. Servicios en los controladores

- Los controladores utilizan servicios para:
  - insertar datos,
  - borrar datos,
  - actualizar datos,
  - obtener datos.

---

## 4. ResponseEntity

- Toda petición a una API devuelve una respuesta con:
  - estado HTTP,
  - datos (opcional).

- En Spring Boot se utiliza `ResponseEntity` para devolver:
  - solo estado,
  - o estado más datos.

- Todos los métodos del controlador deben devolver un `ResponseEntity`.

### Estados HTTP habituales

- `NO_CONTENT` → no se envía contenido.
- `CREATED` → se ha creado un nuevo recurso.
- `NOT_FOUND` → no se encuentra el recurso.
- `OK` → la operación se ha realizado correctamente.

Los estados se encuentran en el enumerado `HttpStatus`.

---

## 5. Envío de información a la API

Las peticiones pueden enviar información de dos formas:

### A través de la URL
- Para uno o pocos datos simples.
- Se utiliza:
  - `@PathVariable`

### A través del cuerpo de la petición (body)
- Para datos complejos.
- Se utiliza:
  - `@RequestBody`

---

## 6. @PathVariable

- Se usa cuando se trabaja con un único recurso.
- Ejemplos de operaciones:
  - borrar un recurso por id,
  - obtener un recurso por id,
  - actualizar un recurso por id.
- El identificador se indica en la ruta.

---

## 7. Operación POST

Para insertar un recurso:

- Crear un método en el controlador.
- Usar la anotación:
  - `@PostMapping`
- Normalmente se recibe un parámetro con:
  - `@RequestBody`
- Llamar al método del servicio que inserta el dato.

---

## 8. Operación DELETE

Para borrar un recurso:

- Crear un método en el controlador.
- Usar:
  - `@DeleteMapping("/{id}")`
- Usar un parámetro con:
  - `@PathVariable`
- Llamar al método del servicio que elimina el dato.

---

## 9. Operación GET sin parámetros

Para obtener varios recursos:

- Crear un método en el controlador.
- Usar:
  - `@GetMapping`
- Llamar al método del servicio que obtiene todos los datos.

---

## 10. Operación GET con parámetros

Para obtener un recurso concreto o datos filtrados:

- Crear un método en el controlador.
- Usar:
  - `@GetMapping("/{id}")`
- Usar un parámetro:
  - `@PathVariable`
- Llamar al método del servicio correspondiente.

También se pueden usar otros atributos:

- Ejemplo de ruta:
  - `/duracionMenorA/{minutos}`
- Parámetro:
  - `@PathVariable int minutos`

---

## 11. Operación PUT

Para actualizar un recurso:

- Crear un método en el controlador.
- Usar:
  - `@PutMapping("/{id}")`
- Usar normalmente:
  - un parámetro `@PathVariable` (id),
  - un parámetro `@RequestParam` (atributo a modificar).
- Llamar al método del servicio que actualiza el dato.

---

## 12. Postman

- Herramienta para:
  - desarrollar,
  - probar,
  - documentar APIs.
- Permite enviar peticiones HTTP a la API y ver sus respuestas.

### Uso básico

- Para probar un GET por id se introduce la URL de la ruta.
- Si se usa `@RequestParam`, se añaden los parámetros desde la sección de parámetros y se reflejan en la URL.
- Si se usa `@RequestBody`, la información se introduce en el cuerpo de la petición.

---

Material adaptado a partir del trabajo de Antonio Cuadros Lapresa.
