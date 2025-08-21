# Sistema de Login con Notas — README

## 1) Resumen

Pequeña app de consola en Java que:

1) Permite **loguearse** con *RUT + contraseña*.
2) Una vez autenticado, el sistema **genera 10 notas** (enteros entre **10 y 70**).
3) El usuario puede **ver sus notas**, **ver su promedio** y saber si **aprueba** (regla por defecto: **≥ 40**).

## 2) Objetivos de aprendizaje

- Practicar **POO**: clases, encapsulación, responsabilidades.
- Dominar **arreglos** (creación, copias defensivas, recorridos, validaciones).
- Manejar **flujo de consola** robusto (entradas válidas, menús, reintentos).
- Diseñar **contratos de métodos** y casos de prueba.

## 3) Requisitos

### Funcionales

- **Registro fijo** de 5 usuarios en el sistema (o por código).
- **Login** por RUT y password.
- Al autenticar: generar **exactamente 10 notas** (enteros en [10..70]).
- Menú con opciones:
  1. Ver notas
  2. Ver promedio y estado (Aprobado/Reprobado)
  3. Regenerar notas
  0. Salir

### No funcionales / calidad

- Encapsulación correcta (no exponer el arreglo interno).
- Validaciones y mensajes de error claros.
- Código simple, métodos cortos, nombres legibles.

## 4) Estructura sugerida del proyecto

```
src/
└─ com/ipss/mreto1/
   ├─ Usuario.java
   ├─ Sistema.java
   └─ Main.java   // o Mreto1.java (punto de entrada con menú)
```

## 5) Diseño de clases y contratos

### Clase `Usuario`

**Campos (privados):**

- `String nombre`, `String rut`, `String password`
- `int[] notas`  // siempre de largo 10

**Constructor y validaciones mínimas:**

- `Usuario(String nombre, String rut, String password)`
  - `nombre`, `rut`, `password` no nulos/ni vacíos (trim).

**Métodos públicos (contratos):**

- `void generarNotasAleatorias()`
  - Llena las 10 posiciones con enteros **[10..70]**.
- `int[] getNotas()`
  - Devuelve **copia** del arreglo interno.
- `void setNotas(int[] nuevasNotas)`
  - Rechaza `null`, exige `length == 10`, valida rango [10..70], guarda **copia**.
- `double promedio()`
  - Retorna promedio **double** (evitar división entera).
- `boolean aprobo()`
  - Por defecto `promedio() >= 40`.
- **Extras recomendados (PU2):**
  - `int notaMax()`, `int notaMin()`, `int[] notasOrdenadas()`, `double mediana()`, `int contarAprobadas(int corte)`.

**Notas de diseño:**

- No imprimir dentro de la lógica (deja `println` para el `main`).
- No exponer `password` en producción (para este ejercicio, evitar `getPassword()`).

---

### Clase `Sistema`

**Campos:**

- `private final Usuario[] usuarios = new Usuario[5];`
- `private int size = 0;` // cuántos registrados

**Métodos públicos (contratos):**

- `void registrarUsuario(Usuario u)`
  - Rechaza `null`.
  - Si `size == usuarios.length` → `IllegalStateException("sistema_lleno")`.
  - Si RUT ya existe (comparación por `trim()` y `equalsIgnoreCase`) → `IllegalArgumentException("rut_duplicado")`.
  - Inserta y `size++`.
- `Usuario login(String rut, String password)`
  - Si `rut/password` nulos o vacíos → retorna `null`.
  - Busca por RUT en `0..size-1`.
  - Si RUT coincide y `password.equals(...)` → retorna `Usuario`; si no, `null`.

**Notas de diseño:**

- Recorre hasta `size` (no hasta `usuarios.length`).
- Normaliza entradas (`trim`, opcional `toUpperCase` para comparar).

---

### Clase `Main` (o `Mreto1`)

**Responsabilidad:** orquestar flujo de consola y menú.  
**Sugerido:**

- Hasta **3 intentos** de login.
- Tras login: bucle `do-while` con menú (0,1,2,3).
- Entradas robustas (ver PU1).

## 6) Flujo del usuario (experiencia)

1) App se inicia → pide RUT y password.
2) Si autenticación correcta → muestra “Hola, {nombre}”.
3) Genera notas (si no existen aún) y muestra menú.
4) El usuario elige opciones hasta salir.

## 7) Casos de prueba (manuales)

### Registro y capacidad

- Registrar 5 usuarios → OK.
- Registrar un 6º → **lanza** `"sistema_lleno"`.
- Registrar uno con RUT ya usado (distintas mayúsculas/espacios) → **lanza** `"rut_duplicado"`.

### Login

- RUT + pass correctos → retorna `Usuario`.
- RUT correcto + pass incorrecta → `null`.
- RUT inexistente → `null`.
- `rut/password` vacíos o `null` → `null`.

### Notas

- Tras `generarNotasAleatorias()` → **10** notas, cada una **[10..70]**.
- `getNotas()` **no** permite modificar las internas (probar: cambiar la copia y volver a pedir).
- `promedio()` correcto en casos:
  - todas 10 → 10.0
  - todas 70 → 70.0
  - mezcla conocida → calcula a mano y compara.

### Aprobación

- Promedio 39.9 → `false`
- Promedio 40.0 → `true`

### Menú

- Opción 1 → imprime 10 notas.
- Opción 2 → imprime promedio y “Aprobado/Reprobado”.
- Opción 3 → cambia realmente las notas (comparar antes/después).
- Opción 0 → sale sin error.

## 8) Power-Ups (extensiones opcionales)

### PU1 — Entradas robustas (Scanner)

Crear `InputHelper` con:

- `String readNonEmpty(Scanner sc, String prompt)`
- `int readIntInRange(Scanner sc, String prompt, int min, int max)`
- `void pressEnterToContinue(Scanner sc)`

Manejar `InputMismatchException` y limpiar buffer (`nextLine()`).

### PU2 — Utilidades de notas

- `notaMax()`, `notaMin()`, `notasOrdenadas()` (copia), `mediana()` (en copia ordenada), `contarAprobadas(40)`  
- Añadir al menú:
  4. Ver nota máxima y mínima
  5. Ver notas ordenadas
  6. Ver mediana

### PU3 — Tests de borde + refactor

- Mini “runner” que ejecute casos borde y reporte “OK/ERROR”.
- Quitar impresiones de lógica.
- Revisar nombres y comentarios.

## 9) Errores comunes y cómo evitarlos

- **Comparar Strings con `==`** → usar `equals` / `equalsIgnoreCase`.
- **Exponer arreglo interno** → `getNotas()` debe retornar **copia** (`Arrays.copyOf`).
- **División entera en promedio** → castear suma a `double` antes de dividir.
- **Recorrer más allá de `size`** en `Sistema` → limita el for a `i < size`.
- **Rango aleatorio mal** → `nextInt(10, 71)` o `(int)(Math.random()*61) + 10`.

## 10) Cómo ejecutar

### NetBeans

- Crear proyecto Java con paquete `com.ipss.mreto1`.
- Añadir `Usuario.java`, `Sistema.java`, `Main.java`.
- Ejecutar con `Run`.

### CLI

```bash
# desde la carpeta src
javac com/ipss/mreto1/*.java
java com.ipss.mreto1.Main
```

## 11) Criterios de evaluación (rúbrica breve)

- **POO y encapsulación**: 30%
- **Arreglos y validaciones**: 30%
- **Flujo y UX de consola**: 20%
- **Pruebas y casos borde**: 15%
- **Limpieza y claridad**: 5%
