import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
//create the Insert class and inherit from the Jframe class
public class MenuPrestamos extends JFrame {
        //create the text box variables
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textid;
	private JTextField textId_libro;
	private JTextField textFecha_entrega;
	private JTextField textFecha_devolucion;
	private JTextField textCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                                        // call the insert interface
					MenuPrestamos frame = new MenuPrestamos();
					frame.setVisible(true);
				} catch (Exception e) {
                                    // send an exception in case of failure
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrestamos() {
            //we create the frame giving it the measurements and the color
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
                contentPane.setBackground(new Color(192,255,192)); 

                setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Create the form labels
		JLabel lblNewLabel = new JLabel("MENU PRESTAMOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(134, 11, 168, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("id:");
		lblNewLabel_1.setBounds(10, 63, 69, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Id_libro: ");
		lblNewLabel_1_1.setBounds(10, 87, 69, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha_entrega:");
		lblNewLabel_1_1_1.setBounds(10, 112, 89, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Fecha_devolucion: ");
		lblNewLabel_1_1_2.setBounds(10, 137, 89, 14);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Cantidad");
		lblNewLabel_1_1_3.setBounds(10, 162, 102, 14);
		contentPane.add(lblNewLabel_1_1_3);
		//create the text boxes
		textid = new JTextField();
		textid.setBounds(134, 60, 127, 20);
		contentPane.add(textid);
		textid.setColumns(10);
		
		textId_libro = new JTextField();
		textId_libro.setColumns(10);
		textId_libro.setBounds(134, 84, 127, 20);
		contentPane.add(textId_libro);
		
		textFecha_entrega = new JTextField();
		textFecha_entrega.setColumns(10);
		textFecha_entrega.setBounds(134, 109, 127, 20);
		contentPane.add(textFecha_entrega);
		
		textFecha_devolucion = new JTextField();
		textFecha_devolucion.setColumns(10);
		textFecha_devolucion.setBounds(134, 134, 127, 20);
		contentPane.add(textFecha_devolucion);
		
		textCantidad = new JTextField();
		textCantidad.setColumns(10);
		textCantidad.setBounds(134, 159, 127, 20);
		contentPane.add(textCantidad);
                ImageIcon image = new ImageIcon("image.png");
                JLabel imageLabel = new JLabel(image); 
                imageLabel.setBounds(400, 800, 150, 200);
                contentPane.add(imageLabel);
		//create the insert button in mySQL
		JButton btnSQL = new JButton("Insertar MySQL");
		btnSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                //create a dAo user of type Mysql
				Prestamo u1;
				PrestamoDAOMySQL daoSQL = new PrestamoDAOMySQL();
				//get the data
				int id = Integer.parseInt(textid.getText());
				String id_libros = textId_libro.getText();
				String Fecha_entrega = textFecha_entrega.getText();
				String Fecha_devolucion= textFecha_devolucion.getText();
				String can = textCantidad.getText();
				
				u1 = new Prestamo(id, id_libros, Fecha_entrega,Fecha_devolucion, can);
				try {
					daoSQL.create(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazListaPrestamos n = new InterfazListaPrestamos();
					n.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
                //we give you the measurements and the axes of the button
		btnSQL.setBounds(285, 83, 140, 23);
		contentPane.add(btnSQL);
		//create the update button in mySQL
		JButton btnSQLupd = new JButton("UPDATE MySQL");
		btnSQLupd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        PrestamoDAOMySQL daoSQL = new PrestamoDAOMySQL();
		        //get the data
		        int id = Integer.parseInt(textid.getText());
		        int id_libro =Integer.parseInt(textId_libro.getText());
		        String Fecha_entrega= textFecha_entrega.getText();
		        String Fecha_devolucion = textFecha_devolucion.getText();
		        String cantidad = textCantidad.getText();
		        
		        Prestamo u1 = new Prestamo(
                        Integer.parseInt(textid.getText()), 
                        textId_libro.getText(),
                        textFecha_entrega.getText(),
                        textFecha_devolucion.getText(),
                        textCantidad.getText()
                        );

		        try {
		            daoSQL.update(u1);
		        } catch (SQLException e1) {
		            System.out.print("No se pudo actualizar");
		            e1.printStackTrace();
		        }
		        
		        try {
		            InterfazListaPrestamos n = new InterfazListaPrestamos();
		            n.setVisible(true);
		            dispose();
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
                //we give you the measurements and the axes of the button
		btnSQLupd.setBounds(285, 103, 140, 23);
		contentPane.add(btnSQLupd);
                //create the delete button in mySQL
		JButton btnSQLdel= new JButton("delete MySQL");
		btnSQLdel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
                        //create a dAo user of type Mysql
		        PrestamoDAOMySQL daoSQL = new PrestamoDAOMySQL();
                    int id = Integer.parseInt(textid.getText());
                    try {
                        daoSQL.delete(id);
                    } catch (SQLException e1) {
                        System.out.print("No se pudo eliminar");
                        e1.printStackTrace();
                    }   
                    try {
		            InterfazListaPrestamos n = new InterfazListaPrestamos();
		            n.setVisible(true);
		            dispose();
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
                //we give you the measurements and the axes of the button
		btnSQLdel.setBounds(285, 125, 140, 23);
		contentPane.add(btnSQLdel);
		//create the insert button in TXT
		JButton btnTXT = new JButton("INSERTAR TXT");
		btnTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            //create a variable of type dao
				Prestamo u1;
				PrestamoDAOArchivo daoA = new PrestamoDAOArchivo("Prestamos.txt");
				//get the data
				int id = Integer.parseInt(textid.getText());
				String id_libro = textId_libro.getText();
				String fechaE = textFecha_entrega.getText();
				String fechaD= textFecha_devolucion.getText();
				String can = textCantidad.getText();
				
				u1 = new Prestamo(id, id_libro, fechaE, fechaD, can);
				try {
					daoA.create(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazPrestamoListTXT n = new InterfazPrestamoListTXT();
					n.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
                //we give you the measurements and the axes of the button
		btnTXT.setBounds(285, 150, 140, 23);
		contentPane.add(btnTXT);
                //create the update button in TXT
                JButton updTXT = new JButton("UPDATE TXT");
		updTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            //create a variable of type dao
			PrestamoDAOArchivo daoA = new PrestamoDAOArchivo("Prestamos.txt");
			int id = Integer.parseInt(textid.getText());
		    String  id_libro = textId_libro.getText();
		        String fecha_entrega = textFecha_entrega.getText();
		        String fecha_devolucion = textFecha_devolucion.getText();
		        String cantidad = textCantidad.getText();
		       Prestamo u1 = new Prestamo(
                        Integer.parseInt(textid.getText()), 
                        textId_libro.getText(),
                        textFecha_entrega.getText(),
                        textFecha_devolucion.getText(),
                        textCantidad.getText()
                        );

				try {
					daoA.update(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazPrestamoListTXT n = new InterfazPrestamoListTXT();
					n.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
                //we give you the measurements and the axes of the button
		updTXT.setBounds(285, 170, 140, 23);
		contentPane.add(updTXT);
                //create the delete button in TXT
                JButton delTXT = new JButton("Delete TXT");
		delTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            //create a variable of type dao
				PrestamoDAOArchivo daoA = new PrestamoDAOArchivo("Prestamos.txt");
                                int id = Integer.parseInt(textid.getText());
                    try {
                        daoA.delete(id);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazPrestamoListTXT n = new InterfazPrestamoListTXT();
					n.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
                //we give you the measurements and the axes of the button
		delTXT.setBounds(285, 195, 140, 23);
		contentPane.add(delTXT);
                
	}
}
