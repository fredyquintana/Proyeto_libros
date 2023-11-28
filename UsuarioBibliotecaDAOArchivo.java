//import the classes
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
//create the class UserDAOFile that inherits from the UserDao interface
public class UsuarioBibliotecaDAOArchivo implements UsuarioBibliotecaDAO {
    //create the arraylist of type user
    String nombreArchivo;
    ArrayList<UsuarioBiblioteca> listaUsuariosbiblioteca;
    //create constructor
    public UsuarioBibliotecaDAOArchivo(String nomAr) {
        this.nombreArchivo = nomAr;
    }
    //create the arraylist of type user
    public UsuarioBibliotecaDAOArchivo(ArrayList<UsuarioBiblioteca> listaUsuariosbiblioteca) {
        this.listaUsuariosbiblioteca = listaUsuariosbiblioteca;
    }
//create the deleteContent method
    public void borrarContenido() {
        try {
            //write to the text file
            FileWriter escribir = new FileWriter(nombreArchivo);
            PrintWriter linea = new PrintWriter(escribir);

            // Cerrar los flujos para garantizar que los cambios se guarden
            linea.close();
            escribir.close();

        } catch (IOException e) {
            System.out.println("Error al borrar el contenido: " + e);
        }
    }
//create the create method
    @Override
    public int create(UsuarioBiblioteca usuarioBiblioteca) throws SQLException {
        PrintWriter linea = null;
        FileWriter escribir = null;
        File archivo = new File(nombreArchivo);

        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            //write to the text file
            escribir = new FileWriter(archivo, true);
            linea = new PrintWriter(escribir);

            // Escribir en el archivo
            linea.println(usuarioBiblioteca.formatoArchivo());

        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            // Asegúrate de cerrar PrintWriter y FileWriter en el bloque finally
            if (linea != null) {
                linea.close();
            }
            if (escribir != null) {
                try {
                    escribir.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar FileWriter: " + e);
                }
            }
        }
        return 0;
    }

    @Override
    public UsuarioBiblioteca read(String id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public int update(UsuarioBiblioteca usuarioBiblioteca) throws SQLException {
        int result = -1;

        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                //read everything in the txt file and update it
                ArrayList<UsuarioBiblioteca> listaUsuariosbiblioteca = readAll();
                for (UsuarioBiblioteca usuarioAux : listaUsuariosbiblioteca) {
                    if (usuarioAux.getId() ==usuarioBiblioteca.getId()) {
                        usuarioAux.setNombre(usuarioBiblioteca.getNombre());
                        usuarioAux.setApellido(usuarioBiblioteca.getApellido());
                        usuarioAux.setCorreo(usuarioBiblioteca.getCorreo());
                        usuarioAux.setDireccion(usuarioBiblioteca.getDireccion());
                        break;
                    }
                }

                PrintWriter linea = null;
                FileWriter escribir = null;

                try {
                    //write to the text file
                    escribir = new FileWriter(archivo, false);
                    linea = new PrintWriter(escribir);

                    for (UsuarioBiblioteca usuarioAux : listaUsuariosbiblioteca) {
                        linea.println(usuarioAux.formatoArchivo());
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                } finally {
                    if (linea != null) {
                        linea.close();
                    }
                    if (escribir != null) {
                        try {
                            escribir.close();
                        } catch (IOException e) {
                            System.out.println("Error al cerrar FileWriter: " + e);
                        }
                    }
                }

                result = 0;
            } else {
                System.out.println("Lista no existente");
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return result;
    }

    //Delete user
   //we go through all the arraylist with the iterator and remove the user we want
    public ArrayList<UsuarioBiblioteca> borrar(int clave) throws SQLException {
        Iterator<UsuarioBiblioteca> peliculaIterator = listaUsuariosbiblioteca.iterator();
        while (peliculaIterator.hasNext()) {
            UsuarioBiblioteca usuario = peliculaIterator.next();
            if (usuario.getId() == clave) {
                peliculaIterator.remove();
            }
        }
        return listaUsuariosbiblioteca;
    }
//read all the users found in the txt
    @Override
    public ArrayList<UsuarioBiblioteca> readAll() throws SQLException {
        ArrayList<UsuarioBiblioteca> listaUsuariosbiblioteca = new ArrayList<>();

        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                FileReader leer = new FileReader(archivo);
                BufferedReader br = new BufferedReader(leer);
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] valores = linea.split("\\|");
                    int id = Integer.parseInt(valores[0]);
                    String nombre = valores[1];
                    String apellido = valores[2];
                    String correo = valores[3];
                    String direccion = valores[4];

                    UsuarioBiblioteca aux = new UsuarioBiblioteca(id, nombre, apellido, correo, direccion);
                    //add the user to the listUsers
                    listaUsuariosbiblioteca.add(aux);

                }
            } else {
                System.out.println("Lista no existente");
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return listaUsuariosbiblioteca;
    }

    @Override
    public int delete(int id) throws SQLException {
  
      ArrayList<UsuarioBiblioteca> listaUsuariosbiblioteca = readAll();
  
      Iterator<UsuarioBiblioteca> iterator = listaUsuariosbiblioteca.iterator();
  
      while(iterator.hasNext()) {
        UsuarioBiblioteca usuario = iterator.next();
  
        if(usuario.getId() == id) {
          iterator.remove();
        }
      }
  
      PrintWriter writer = null;
      FileWriter fileWriter = null;
  
      // Rest of the code...
  

  try {
    //write to the text file
    fileWriter = new FileWriter(nombreArchivo, false);
    writer = new PrintWriter(fileWriter);
    //we write in the txt with the format that we put in it
    for(UsuarioBiblioteca usuario : listaUsuariosbiblioteca) {
      writer.println(usuario.formatoArchivo());
    }

  } catch (IOException e) {
    e.printStackTrace();
  } finally {
    if(writer != null) writer.close();
    if(fileWriter != null) try {
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  return 0;
}


}
