/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

/**
 *
 * @author jorge
 */
public class Validadores {
        
    public static boolean validarNombre(String nombre) {
        // El nombre debe contener solo letras y espacios, y tener al menos una letra
        String regex = "^[a-zA-ZÁ-ÿ\\s]+";
        return nombre.matches(regex);
    }
    
    public static boolean validarApellido(String apellido) {
        // El apellido debe contener solo letras y espacios, y tener al menos una letra
        String regex = "^[a-zA-ZÁ-ÿ\\s]+";
        return apellido.matches(regex);
    }
    
    public static boolean validarCorreo(String correo) {
        // Expresión regular para validar un correo electrónico
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return correo.matches(regex);
    }
    
    public static boolean validarContraseña(String contraseña) {
        // Contraseña de mínimo 8 caracteres con al menos un dígito
        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";
        return contraseña.matches(regex);
    }
    
    public static boolean validarCodigoPostal(String codigoPostal) {
        // Código postal de máximo 5 números
        String regex = "^\\d{1,5}$";
        return codigoPostal.matches(regex);
    }
    
    public static boolean validarColonia(String colonia) {
        // Nombre de colonia puede contener letras, números, espacios y algunos caracteres especiales
        String regex = "^[a-zA-Z0-9Á-ÿ\\s.,()-]+$";
        return colonia.matches(regex);
    }
    
    public static boolean validarCalleNumero(String calleNumero) {
        // Calle y número exterior de máximo 5 números
        String regex = "^\\d{1,5}\\s[a-zA-ZÁ-ÿ0-9\\s.]+$";
        return calleNumero.matches(regex);
    }
}
