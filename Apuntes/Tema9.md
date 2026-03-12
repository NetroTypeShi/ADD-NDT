# Tema 9 – Bases de Datos NoSQL y MongoDB
**Acceso a Datos**

---

## Tema 9.1 – Introducción a bases de datos NoSQL

### 1. Concepto de NoSQL

Las bases de datos **NoSQL** son aquellas que **no utilizan el enfoque tradicional relacional** (no usan tablas con filas y columnas). La forma más común de almacenar datos es en **formato JSON**, aunque no es la única.

NoSQL no es una única tecnología, sino un grupo de productos y conceptos que comparten:

- Modelo de datos **no relacional**
- Funcionan bien en **clústeres**
- Normalmente son de **código abierto**
- **No tienen esquema de datos fijo**

---

### 1.1 Concepto de Clúster

Un **clúster** es un grupo de servidores que trabajan juntos para alojar y gestionar bases de datos en la nube de manera eficiente y escalable.

| Característica | Descripción |
|---|---|
| **Escalabilidad** | Horizontal o verticalmente |
| **Alta disponibilidad** | Funciona incluso ante fallos de red o hardware |
| **Seguridad integrada** | Autenticación, cifrado y redes seguras |
| **Rendimiento** | Baja latencia (tiempos de respuesta bajos) |
| **Monitoreo y mantenimiento** | Herramientas para métricas y copias de seguridad |
| **Flexibilidad y compatibilidad** | Nodos en varios países, compatible con múltiples tecnologías |
| **Consistencia** | Los datos deben ser iguales en todos los nodos |
| **Resiliencia** | Capacidad de recuperarse automáticamente ante fallos |

#### Load Balancer (Balanceador de Carga)

> Sistema que **distribuye el tráfico entrante** entre varios servidores para garantizar mayor disponibilidad, rendimiento y confiabilidad.

```
Cliente → Load Balancer → Servidor A
                       → Servidor B
```

---

### 1.2 Escalabilidad Horizontal vs Vertical

La **escalabilidad** es la capacidad de crecimiento de una aplicación para atender a un número creciente de solicitudes sin que la calidad de servicio se vea afectada.

#### Escalabilidad Vertical
- Consiste en **aumentar los recursos de un servidor existente** (CPU, RAM, almacenamiento, etc.)
- ✅ Fácil de implementar
- ❌ El beneficio es **limitado** (hay un techo de hardware)
- ❌ Suele ser **más cara** (hardware especializado)

#### Escalabilidad Horizontal
- Consiste en **aumentar la cantidad de servidores** (nodos) configurados en clúster con balanceo de carga
- ✅ Escalado **prácticamente ilimitado** (se añaden servidores bajo demanda)
- ✅ **Más barata** (no requiere hardware especializado)
- ❌ Más **compleja** de configurar

---

### 2. ¿Por qué surgieron las bases de datos NoSQL?

Surgieron en respuesta a las **limitaciones de las bases de datos relacionales (SQL)**:

| Limitación SQL | Solución NoSQL |
|---|---|
| Difícil escalar horizontalmente y manejar Big Data | Diseñadas para grandes volúmenes de datos distribuidos |
| Solo escalan verticalmente | Diseñadas para escalar horizontalmente en la nube |
| Esquema fijo (tablas con columnas) | Datos flexibles, sin esquema rígido |
| Solo manejan tablas | Soportan JSON, XML, Grafos, Clave-valor, etc. |
| Difícil mantener consistencia en múltiples servidores | Alta disponibilidad mediante clústeres |

---

### 3. Tipos de Bases de Datos NoSQL

#### 3.1 Clave-Valor
- Almacenan datos como **pares clave-valor**
- Ideales para **búsquedas rápidas por clave**
- **Caso de uso:** sesiones de usuario, caché, configuraciones

```
Clave: "user123"
Valor: {"nombre": "Juan", "email": "juan@email.com", "rol": "admin"}
```

#### 3.2 Basadas en Documentos
- Almacenan datos en **documentos JSON, BSON o XML**
- Cada documento puede tener una **estructura diferente** (flexible)
- **Caso de uso:** aplicaciones con datos no estructurados

```json
{
  "_id": "001",
  "nombre": "Laptop",
  "precio": 1200,
  "especificaciones": {
    "marca": "Dell",
    "procesador": "Intel i7",
    "memoria": "16GB"
  }
}
```

#### 3.3 Basadas en Columnas
- Almacenan datos **por columnas** en lugar de por filas
- Optimiza las consultas sobre **grandes volúmenes de datos**
- **Caso de uso:** análisis de grandes cantidades de datos (Big Data)

#### 3.4 Basadas en Grafos
- Almacenan **relaciones entre datos** como grafos
- Usan **nodos** (entidades) y **aristas** (relaciones)
- **Caso de uso:** redes sociales, recomendaciones, gestión de rutas

---

### 4. ¿Cuándo usar NoSQL?

- Big Data (grandes volúmenes de datos)
- Se necesita **escalabilidad horizontal**
- Datos **semi-estructurados o no estructurados**
- Aplicaciones en **tiempo real** (altas ops/seg y baja latencia)
- **Relaciones complejas** entre datos (grafos)
- **Alta disponibilidad** y tolerancia a fallos

---

## Tema 9.2 – MongoDB y el Teorema de CAP

### 1. MongoDB

MongoDB es una base de datos **NoSQL orientada a documentos**. Sus características principales:

- Los documentos se estructuran en **formato JSON**
- Internamente almacena en **BSON** (Binary JSON)
- **Modelo de datos flexible** – no requiere esquema rígido
- **Escalabilidad horizontal** – divide datos entre nodos
- Sistema de replicación llamado **"replica sets"** (los datos se duplican entre servidores)
- Compatible con múltiples plataformas (**local y en la nube**)

#### ¿Cuándo usar MongoDB?
- Aplicaciones con datos **no estructurados o semi-estructurados**
- Aplicaciones con datos que **cambian frecuentemente**
- Cuando la **escalabilidad y disponibilidad** son críticas
- Aplicaciones en tiempo real: logs, chats, e-commerce, IoT, etc.

---

### 2. Términos en MongoDB

| Término MongoDB | Equivalente SQL |
|---|---|
| **Base de datos** | Base de datos |
| **Colección** | Tabla |
| **Documento** | Fila / Registro |

- Una **colección** agrupa elementos relacionados conceptualmente, pero su estructura **no es fija**
- Un **documento** es la unidad fundamental de almacenamiento

---

### 3. El Teorema de CAP

> El teorema de CAP sostiene que en sistemas distribuidos **no es posible garantizar al completo** la consistencia, disponibilidad y tolerancia a particiones **simultáneamente**. Como máximo se pueden cumplir **2 de las 3**.

| Propiedad | Descripción |
|---|---|
| **C – Consistencia** | Todos los nodos tienen los mismos datos en un momento dado. Una lectura siempre devuelve el valor más reciente |
| **A – Disponibilidad** | El sistema siempre responde a las solicitudes |
| **P – Tolerancia a Particiones** | El sistema sigue funcionando aunque haya fallos de comunicación entre nodos |

#### Clasificación de bases de datos según CAP

| Tipo | Propiedades | Sacrifica | Ejemplos |
|---|---|---|---|
| **CA** | Consistencia + Disponibilidad | Tolerancia a particiones | MySQL, Oracle, SQL Server, Neo4J |
| **CP** | Consistencia + Tolerancia | Disponibilidad | **MongoDB**, HBase, Redis |
| **AP** | Disponibilidad + Tolerancia | Consistencia | DynamoDB, CouchDB, Cassandra |

#### Ejemplo práctico – Aplicación bancaria

- **(CP):** Si hay una partición de red, el sistema puede dejar de estar disponible para **evitar inconsistencias** en los saldos.
- **(AP):** Si hay una partición de red, el sistema sigue respondiendo pero puede mostrar **saldos desactualizados**.

#### Ejemplo práctico – Tienda online (AP)

1. Usuario A compra el último artículo → Servidor A actualiza inventario (cantidad = 0)
2. Partición de red: Servidor B no recibe la actualización
3. Usuario B también puede comprar el mismo artículo → **inconsistencia temporal**

---

### 4. MongoDB Atlas (Base de Datos en la Nube)

**MongoDB Atlas** es el servicio de base de datos en la nube de MongoDB. Permite usar MongoDB sin gestionar la infraestructura.

Características principales:
- **Alta disponibilidad** y backup automatizado
- **Escalabilidad automática** según la demanda
- Clúster global disponible en **115 países**
- Seguridad con autenticación y cifrado

#### Conexión con MongoDB Atlas
- No requiere instalación local
- La BD se gestiona en el sitio del proveedor
- Se obtiene un **string de conexión** para conectar aplicaciones

---

## Tema 9.3 – MongoDB Atlas y Java (SpringBoot)

### 1. Crear un proyecto SpringBoot + MongoDB

Usar Spring Initializr: [https://start.spring.io/](https://start.spring.io/)

- Añadir la dependencia: **Spring Data MongoDB (NoSQL)**

---

### 2. Configurar la conexión

El string de conexión tiene esta estructura:

```
mongodb+srv://<usuario>:<contraseña>@<cluster>.mongodb.net/<nombreBD>?retryWrites=true&w=majority
```

Añadir al archivo `application.properties`:

```properties
spring.application.name=miAplicacion
spring.data.mongodb.uri=mongodb+srv://<usuario>:<contraseña>@<cluster>.mongodb.net/<nombreBD>
```

Al lanzar la aplicación, SpringBoot se conectará automáticamente al clúster de MongoDB Atlas.

---

### 3. Anotaciones principales

#### `@Document`
Indica que la clase representa un **documento** a persistir en MongoDB (equivalente a `@Entity` en SQL).

```java
@Document
class User { }

// Con nombre de colección personalizado:
@Document(collection = "usuarios")
class User { }
```

#### `@Id`
Indica el campo que es **clave primaria**. En MongoDB Atlas se genera automáticamente como `ObjectId`.

```java
@Id
private String id;
```

#### `@Field`
Indica el **nombre del campo** en el documento MongoDB.

```java
@Document
class User {
    @Field("email")         // Se guarda como "email"
    String emailAddress;
}
```

#### Reglas de las clases documento

- Los atributos deben ser **privados**
- Deben tener **getters y setters**
- Debe existir un **constructor con parámetros**
- Debe existir un **constructor sin parámetros**
- El campo `@Id` es **opcional** (a diferencia de `@Entity`)

---

### 4. Repositorios: MongoRepository

Interfaz que proporciona métodos CRUD listos para usar con MongoDB.

```java
public interface UserRepository extends MongoRepository<User, String> { }
```

Para usarlo en cualquier clase:

```java
@Autowired
private UserRepository userRepository;
```

#### Métodos CRUD incluidos por defecto

```java
userRepository.count();                          // Cuenta documentos
userRepository.deleteAll();                      // Borra todos
userRepository.findById("67714fba07fef37...");   // Busca por ID
userRepository.save(user);                       // Guarda/actualiza
userRepository.findAll();                        // Obtiene todos
```

#### Métodos personalizados con Keywords

```java
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByAgeGreaterThan(int age);
    List<User> findByAgeBetween(int min, int max);
    void deleteByEmailAddressContaining(String keyword);
}
```

#### Keywords principales

| Operación | Keyword |
|---|---|
| Buscar | `findBy…` |
| Borrar | `deleteBy…` |
| Contar | `countBy…` |

> ⚠️ **No hay keyword para actualizar.** Para actualizar un documento se hace `.save()` sobre un documento existente (lo reemplaza).

#### Condiciones disponibles

`And`, `Or`, `Between`, `GreaterThan`, `LessThan`, `Containing`, `True`, `False`, `IsTrue`, `IsFalse`, `Null`, `NotNull`, `Empty`, `NotEmpty`, ...

Documentación oficial: [Spring Data MongoDB – Query Keywords](https://docs.spring.io/spring-data/mongodb/reference/repositories/query-keywords-reference.html)

---

*Material adaptado a partir del trabajo de Antonio Cuadros Lapresta y Guillermo Sandoval Schmidt.*
