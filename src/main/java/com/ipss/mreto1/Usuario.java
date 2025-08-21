/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipss.mreto1;

import java.util.Arrays;

/**
 *
 * @author TITAN 6
 */
public class Usuario {
    private String nombre;
    private String rut;
    private String password;
    private int[] notas = new int[10];

    public Usuario(String nombre, String rut, String password) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre.trim();
        
        if (rut == null || rut.isBlank()) {
            throw new IllegalArgumentException("El rut es obligatorio");
        }
        this.rut = rut.trim();
        
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La Contraseña es obligatoria");
        }
        this.password = password.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public int[] getNotas() {
        return Arrays.copyOf(notas, notas.length);
    }

    public void setNotas(int[] notas) {
        if (notas ==null){
            throw new IllegalArgumentException("El arreglo no puede ser null");
        }
        if (notas.length!=10){
            throw new IllegalArgumentException("El arreglo debe tener largo 10");
        }
        int i;
        for(i=0;i<notas.length;i++){
        if(notas[i]<10||notas[i]>70){
        throw new IllegalArgumentException("La nota "+ notas[i] + " debe ser mayor a 10 y menor 70");
        }
        }
        this.notas = Arrays.copyOf(notas, notas.length);
    }
    
    
    //Metodos
    public void generarNotasAleatorias(){
        int i;
        for(i=0;i<notas.length;i++){
        notas[i] = (int) (Math.random()*61+10);
        }
    }
    
    public double promedio(){
        double promedio;
        int i;
        int suma=0;
        int[] n1=notas;
        for(i=0;i<n1.length;i++){
            suma=suma+n1[i];
         }
        double s =(double) suma;
        promedio = s/n1.length;
        return promedio;
    }

    public int contarNotasMenores(int limite){
        int cantidad=0;
        int i;
        for(i=0;i<notas.length;i++){
        if(notas[i]<limite){
            cantidad+=1;
        }
        }
        return cantidad;
    }
    
    public boolean aprobo(){
        double prom=promedio();
        if(prom<40&&prom>39.6){
            prom = Math.round(prom);
            System.out.println("Salvado por secretaria");
        }
        return prom>=40;
    }
    
    
    
    
}
