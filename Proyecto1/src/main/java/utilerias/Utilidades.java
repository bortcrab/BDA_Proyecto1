/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package utilerias;

/**
 *
 * @author Usuario
 */
public class Utilidades {
    public int RegresarOFFSETMySQL(int limite, int pagina) {
        if (pagina <= 1) {
            return 0;
        }
        
        if (pagina == 2) {
            return limite;
        }
        
        return ((int) (limite * (pagina - 1)));
    }
}
