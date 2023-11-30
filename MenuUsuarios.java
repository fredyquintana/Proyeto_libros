import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MenuUsuarios extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textid;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCorreo;
	private JTextField textDireccion;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUsuarios frame = new MenuUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuUsuarios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 255, 192));
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("MENU USUARIOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(134, 11, 168, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("id:");
		lblNewLabel_1.setBounds(10, 63, 69, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Nombre: ");
		lblNewLabel_1_1.setBounds(10, 87, 69, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Apellido:");
		lblNewLabel_1_1_1.setBounds(10, 112, 89, 14);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Correo: ");
		lblNewLabel_1_1_2.setBounds(10, 137, 89, 14);
		contentPane.add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_3 = new JLabel("Direccion");
		lblNewLabel_1_1_3.setBounds(10, 162, 102, 14);
		contentPane.add(lblNewLabel_1_1_3);

		textid = new JTextField();
		textid.setBounds(134, 60, 127, 20);
		contentPane.add(textid);
		textid.setColumns(10);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(134, 84, 127, 20);
		contentPane.add(textNombre);

		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(134, 109, 127, 20);
		contentPane.add(textApellido);

		textCorreo = new JTextField();
		textCorreo.setColumns(10);
		textCorreo.setBounds(134, 134, 127, 20);
		contentPane.add(textCorreo);

		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(134, 159, 127, 20);
		contentPane.add(textDireccion);

		ImageIcon image = new ImageIcon("image.png");
		JLabel imageLabel = new JLabel(image);
		imageLabel.setBounds(400, 800, 150, 200);
		contentPane.add(imageLabel);

		JButton btnSQL = new JButton("Insertar MySQL");
		btnSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioBiblioteca u1;
				UsuarioDAOMySQLB daoSQL = new UsuarioDAOMySQLB();
				int id = Integer.parseInt(textid.getText());
				String nombre = textNombre.getText();
				String ap = textApellido.getText();
				String correo = textCorreo.getText();
				String can = textDireccion.getText();

				u1 = new UsuarioBiblioteca(id, nombre, ap, correo, can);
				try {
					daoSQL.create(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}

				try {
					InterfazListaB n = new InterfazListaB();
					n.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSQL.setBounds(285, 83, 140, 23);
		contentPane.add(btnSQL);

		JButton btnSQLupd = new JButton("UPDATE MySQL");
		btnSQLupd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDAOMySQLB daoSQL = new UsuarioDAOMySQLB();
				int id = Integer.parseInt(textid.getText());
				String nombre = textNombre.getText();
				String apellido = textApellido.getText();
				String correo = textCorreo.getText();
				String direccion = textDireccion.getText();

				UsuarioBiblioteca u1 = new UsuarioBiblioteca(
						Integer.parseInt(textid.getText()),
						textNombre.getText(),
						textApellido.getText(),
						textCorreo.getText(),
						textDireccion.getText()
				);

				try {
					daoSQL.update(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo actualizar");
					e1.printStackTrace();
				}

				try {
					InterfazListaB n = new InterfazListaB();
					n.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSQLupd.setBounds(285, 103, 140, 23);
		contentPane.add(btnSQLupd);

                //create the delete button in mySQL
		JButton btnSQLdel= new JButton("delete MySQL");
		btnSQLdel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
                        //create a dAo user of type Mysql
		        UsuarioDAOMySQLB daoSQL = new UsuarioDAOMySQLB();
                    int id = Integer.parseInt(textid.getText());
                    try {
                        daoSQL.delete(id);
                    } catch (SQLException e1) {
                        System.out.print("No se pudo eliminar");
                        e1.printStackTrace();
                    }   
                    try {
		            InterfazListaB n = new InterfazListaB();
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
				UsuarioBiblioteca u1;
				UsuarioBibliotecaDAOArchivo daoA = new UsuarioBibliotecaDAOArchivo("Registros.txt");
				//get the data
				int id = Integer.parseInt(textid.getText());
				String no  = textNombre.getText();
				String ap = textApellido.getText();
				String cor= textCorreo.getText();
				String can = textDireccion.getText();
				
				u1 = new UsuarioBiblioteca(id, no, ap, cor, can);
				try {
					daoA.create(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazListaTXTB n = new InterfazListaTXTB();
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
			UsuarioBibliotecaDAOArchivo daoA = new UsuarioBibliotecaDAOArchivo("Registros.txt");
			int id = Integer.parseInt(textid.getText());
		        String nombre  = textNombre.getText();
		        String apellido= textApellido.getText();
		        String correo = textCorreo.getText();
		        String direccion = textDireccion.getText();
		        UsuarioBiblioteca u1 = new UsuarioBiblioteca(
                        Integer.parseInt(textid.getText()), 
                        textNombre.getText(),
                        textApellido.getText(),
                        textCorreo.getText(),
                        textDireccion.getText()
                        );

				try {
					daoA.update(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazListaTXTB n = new InterfazListaTXTB();
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
				UsuarioBibliotecaDAOArchivo daoA = new UsuarioBibliotecaDAOArchivo("Registros.txt");
                                int id = Integer.parseInt(textid.getText());
                    try {
                        daoA.delete(id);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazListaTXTB n = new InterfazListaTXTB();
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