/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import java.sql.Connection;

/**
 *
 * @author Usuario
 */
public interface IConexionBD {
    public Connection crearConexion() throws PersistenciaException;
}
