package proyectoJuego;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Grupo3
 */
public class ClaseControlador {
    public void crearFichero(String nombreFichero){
        ObjectOutputStream oos;
         
        try{
            //Creamos el archivo 
            oos = new ObjectOutputStream(new FileOutputStream(nombreFichero));
            oos.close();
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    
    }
    
    public void escribirObjeto(String nombreFichero, ArrayList<Object> elementos){
        System.out.println("llega");
        try{
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(new FileOutputStream(nombreFichero));
            for(int i = 0; i < elementos.size() ; i++){
                Object elemento = new Object();
                elemento = elementos.get(i);
                oos.writeObject(elemento);
                System.out.println(elemento.toString());
            }
            oos.close();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println(ex);
        }
    
    }
    
    public ArrayList<Object> extraerObjetos(String nombreFichero){
        ArrayList<Object> elementos = new ArrayList<>();
        
        try{
            ObjectInputStream ois;  //Es una clase Va leer los datos de entrada. Flujo de entrada de objetos
            FileInputStream fi = new FileInputStream (nombreFichero);
            ois = new ObjectInputStream(fi); //creamos una nueva instancia 
            
            Object aux = ois.readObject();
            while(aux!= null){
                elementos.add(aux);
                aux = ois.readObject();
            }
            ois.close();
        
        }catch(FileNotFoundException ex){  //Detecta si existe el archivo estudiantes.dat
            System.out.println("El fichero no existe, lo voy a crear.");
            crearFichero(nombreFichero);
        }catch (EOFException e1)
        {
            //System.out.println ("Fin de fichero");
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
        return elementos;
    }
    
}
