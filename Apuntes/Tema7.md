# Apuntes — Tema 7: Mapeo Objeto-Relacional (ORM)

**Fuente:** Presentaciones "Tema 7.1" y "Tema 7.2" (resumen y condensado).

## Contenido
1. Introducción al mapeo objeto-relacional (ORM)
2. Ventajas y desventajas de los ORMs
3. Herramientas ORM en Java
4. Hibernate: conceptos clave
5. Clases persistentes (anotaciones y reglas)
6. Configuración de proyecto (Spring Boot + PostgreSQL)
7. Ejemplo práctico: entidad, servicio y operaciones CRUD
8. Criteria API (CriteriaBuilder / CriteriaQuery)
9. Notas finales y referencias

---

## 1. Introducción al mapeo objeto-relacional (ORM)
El mapeo objeto-relacional (ORM) es una técnica y conjunto de herramientas que permiten trabajar con la base de datos mediante las clases y objetos del lenguaje (en este caso Java) en lugar de escribir SQL directo. El objetivo principal es reducir la fricción entre el modelo orientado a objetos y el modelo relacional (tablas/filas/columnas).

## 2. Ventajas y desventajas de los ORMs
**Ventajas**
- Desarrollo más rápido y orientado a objetos.
- Abstracción de sentencias SQL; el desarrollador manipula entidades.
- Mantenimiento más sencillo y coherente con POO.

**Desventajas**
- Coste y sobrecarga en rendimiento respecto a SQL optimizado.
- Curva de aprendizaje para entender su funcionamiento interno.

## 3. Herramientas ORM en Java
Principales ORMs mencionados:
- EBean
- iBATIS
- Hibernate

Hibernate es el más popular y completo: soporta JPQL/HQL además de consultas ORM y permite una gran versatilidad con distintos SGBD.

## 4. Hibernate: conceptos clave
- **Objetos de persistencia**: instancias que Hibernate traduce a operaciones SQL.
- **Archivo .properties** (o application.properties en Spring Boot): configuración de URL, usuario, contraseña y otras propiedades.
- **Mapeo**: históricamente se hacía en XML; hoy es más habitual usar anotaciones en las clases Java.
- **Session / SessionFactory**: `SessionFactory` contiene la configuración cargada; una `Session` representa una conexión/transacción con la BD y se usa para persistir, leer y eliminar entidades.

## 5. Clases persistentes (anotaciones y reglas)
Reglas comunes para que una clase sea persistente:
- Debe tener un constructor por defecto (sin parámetros).
- Debe tener un atributo `id` que actuará como clave primaria.
- Atributos privados con getters y setters.
- Anotaciones típicas:
  - `@Entity` — marca la clase como entidad persistente.
  - `@Table(name = "...")` — (opcional) nombre de la tabla.
  - `@Id` — identifica la clave primaria.
  - `@GeneratedValue(...)` — estrategia de generación automática de ids.

Ejemplo minimal de entidad en Java:

```java
import javax.persistence.*;

@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private int anyo;

    public Pelicula() { }

    public Pelicula(String titulo, int anyo) {
        this.titulo = titulo;
        this.anyo = anyo;
    }

    // getters y setters
}
```

## 6. Configuración de proyecto (Spring Boot + PostgreSQL)
Pasos básicos descritos:
- Crear proyecto con Spring Initializr (`start.spring.io`) incluyendo dependencias (Spring Data JPA/Hibernate, driver PostgreSQL).
- Crear base de datos en PostgreSQL y un usuario.
- Usar una herramienta cliente (por ejemplo DBeaver) para conectarse y comprobar tablas.
- Configurar `application.properties` con la URL de la BD, usuario y contraseña.
- Ejecutar la aplicación; Hibernate puede crear las tablas automáticamente si está configurado (`spring.jpa.hibernate.ddl-auto=update` o `create`).

## 7. Ejemplo práctico: servicio y operaciones CRUD
- **Session**: abrir sesión, iniciar transacción, persistir/merge/delete/get, commit o rollback, cerrar sesión.
- **Operaciones típicas**:
  - `session.persist(entity)` — insertar.
  - `session.get(Class.class, id)` — obtener por id.
  - `session.delete(entity)` — borrar.
  - `session.merge(entity)` — actualizar/merge.

Patrón común en un `Service` con Spring:
- Inyectar `SessionFactory` (o usar `EntityManager` con JPA).
- Abrir `Session`, comenzar transacción, realizar operación, commit/rollback, cerrar.

Fragmento esquemático (pseudocódigo):

```java
Session session = sessionFactory.openSession();
Transaction tx = null;
try {
    tx = session.beginTransaction();
    session.persist(pelicula);
    tx.commit();
} catch (Exception e) {
    if (tx != null) tx.rollback();
    throw e;
} finally {
    session.close();
}
```

## 8. Criteria API (CriteriaBuilder / CriteriaQuery)
Para consultas dinámicas/seguras se usa la Criteria API:
1. Obtener un `CriteriaBuilder` desde la `Session` o `EntityManager`.
2. Crear una `CriteriaQuery<T>` para la entidad deseada.
3. Definir la `Root<T>` (raíz) de la consulta.
4. Añadir condiciones (`where`, `orderBy`, etc.) con el `CriteriaBuilder`.
5. Ejecutar la consulta y obtener resultados.

Es útil para construir consultas complejas sin concatenar strings SQL.

## 9. Notas finales y referencias
- Material adaptado de las presentaciones del tema, que incluyen ejemplos paso a paso para crear el proyecto, configurar la BD y ejecutar operaciones básicas con Hibernate.

---

Si quieres, puedo:
- Generar una versión más extensa con fragmentos de `application.properties` y `pom.xml`.
- Añadir diagramas simples o ejemplos adicionales (relaciones `@OneToMany`, `@ManyToOne`, etc.).

```
Archivo: apuntes_tema7.md
Resumen basado en las presentaciones de clase.
```

