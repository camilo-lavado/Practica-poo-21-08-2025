/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ipss.mreto1;

import java.util.Arrays;

/**
 *
 * @author TITAN 6
 */
public class Mreto1 {

    public static void main(String[] args) {
                /*Micro Reto 1
                Usuario con arreglo de 10 notas
                Objetivo: crear una clase que maneje internamente un arreglo fijo (int[10]) sin exponerlo.
                Requisitos (lo que debe existir)
                Campos privados:
                String nombre, String rut, String password
                int[] notas de largo 10 (todas parten en 0)
                Constructor que valide: nombre, rut, password no nulos/ni vacíos.
                Si algo está mal, throw new IllegalArgumentException("mensaje_claro").

                Métodos (solo las firmas, tú implementas):
                public void generarNotasAleatorias() → llena las 10 posiciones con números entre 10 y 70 (incluidos).
                public int[] obtenerNotas() → devuelve una copia del arreglo interno (no el mismo).
                public double promedio() → calcula el promedio con división en double.
                public boolean aprobo() → usa promedio(); define tú si “aprueba con ≥ 40”.

                Pistas (sin código)
                Arreglo fijo: notas = new int[10];
                Aleatorio: ThreadLocalRandom.current().nextInt(10, 71) (71 es exclusivo)
                Copia segura: Arrays.copyOf(notas, notas.length)
                Promedio: castea la suma a double antes de dividir
                Prueba manual mínima (hazla desde un main sencillo)
                Crea un Usuario con nombre/rut/password válidos.
                Llama generarNotasAleatorias().
                Llama obtenerNotas() y verifica:
                largo = 10
                cada nota ∈ [10, 70]
                Imprime promedio() y aprobo().
        
                Criterios de aprobación del reto
                No hay IndexOutOfBoundsException.
                Ninguna nota fuera de rango.
                Si modificas el arreglo que te devuelve obtenerNotas(), no cambia el interno.
        
                Pequeño ejercicio extra (para asentar arreglos)
                Agrega método privado int contarNotasMenoresA(int limite) y úsalo en una impresión tipo:
                “tienes X notas bajo 40”.
                    */
                
                
                /*Micro-reto 2: Sistema con arreglo de 5 usuarios y login. Yo te doy el plano; tú lo construyes.
                    Objetivo
                    Practicar arreglos de objetos y búsqueda lineal (sin colecciones).

                    Especificación mínima
                    Clase Sistema

                    Campos
                    private final Usuario[] usuarios = new Usuario[5];
                    private int size = 0; // cuántos hay cargados
                
                    Métodos (firmas sugeridas)
                    public void registrarUsuario(Usuario u)

                    Validaciones:
                    u != null
                    capacidad: size < usuarios.length, si no → throw new IllegalStateException("sistema_lleno")
                    duplicado por RUT: si ya existe un Usuario con el mismo rut (usa equalsIgnoreCase sobre trim()), → throw new IllegalArgumentException("rut_duplicado")
                    Inserta en usuarios[size] y luego size++.
                    public Usuario login(String rut, String password)
                    Si rut o password nulos/blank → retorna null (o lanza, tú decide y sé consistente).
                    Busca por RUT (lineal sobre 0..size-1).
                    Si encuentra, compara password con equals (no ==).
                    Devuelve el Usuario cuando coincide; si no, null.
                    (Opcional, ayuda interna)
                    private int indexOfRut(String rutNormalizado)
                    Devuelve índice 0..size-1 o -1 si no existe.

                    Notas de implementación
                    Recorre solo hasta size, no todo usuarios.length.
                    Normaliza RUT con rut.trim() y quizás toUpperCase() para comparar igualando formato.
                
                    Pruebas manuales (en tu main)
                    Crea Sistema s = new Sistema();
                    Registra 5 usuarios (incluye el que ya tenías).
                    Prueba registrar un sexto → debe lanzar "sistema_lleno".
                    Prueba registrar RUT duplicado → debe lanzar "rut_duplicado".
                    login:
                    Caso feliz (RUT y pass correctos) → retorna Usuario y muestras getNombre().
                    RUT correcto, pass incorrecta → null.
                    RUT inexistente → null.
                    Tras un login exitoso:
                    generarNotasAleatorias()
                    imprimir Arrays.toString(usuario.getNotas())
                    promedio() y aprobo().
                    Pistas (sin código)
                    Búsqueda lineal típica:
                    for (int i = 0; i < size; i++) { if (usuarios[i].getRut().equalsIgnoreCase(rutNorm)) return i; }

                    Normaliza entradas:
                    String rutNorm = rut == null ? "" : rut.trim();

                    No mezcles presentación con lógica (no println en Sistema).
                    
                    Tu turno
                    Implementa Sistema con esos métodos. Si te sirve, escribe primero solo las firmas y los comentarios dentro con los pasos; luego completa los cuerpos.*/
        
        Usuario user = new Usuario("Pepito","19231273-2","abcd1234");
        user.generarNotasAleatorias();
        System.out.println(Arrays.toString(user.getNotas()));
        System.out.println("Promedio: " + user.promedio());
        if(!user.aprobo()){
            System.out.println(user.getNombre() + " Reprobado con nota: "+ user.promedio());
        }else {
            System.out.println(user.getNombre() + " Aprobado con nota: "+ user.promedio());
        }
        System.out.println("Tienes " + user.contarNotasMenores(40)+" notas bajo 40");
    }
}
