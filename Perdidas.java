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
//create the Insert class and inherit from the Jframe class
public class Perdidas extends JFrame {
        //create the text box variables
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textid;
	private JTextField textISBN;
	private JTextField textAu;
	private JTextField textGenero;
	private JTextField textCan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                                        // call the insert interface
					Insertar frame = new Insertar();
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
	public Insertar() {
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
		JLabel lblNewLabel = new JLabel("MENU PERDIDAS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(134, 11, 168, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("id_Libro:");
		lblNewLabel_1.setBounds(10, 63, 69, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ISBN: ");
		lblNewLabel_1_1.setBounds(10, 87, 69, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Autor:");
		lblNewLabel_1_1_1.setBounds(10, 112, 89, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Genero: ");
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
		
		textNom = new JTextField();
		textNom.setColumns(10);
		textNom.setBounds(134, 84, 127, 20);
		contentPane.add(textid);
		
		textAp = new JTextField();
		textAp.setColumns(10);
		textAp.setBounds(134, 109, 127, 20);
		contentPane.add(textISBN);
		
		textCorreo = new JTextField();
		textCorreo.setColumns(10);
		textCorreo.setBounds(134, 134, 127, 20);
		contentPane.add(textGenero);
		textCon = new JTextField();
		textCon.setColumns(10);
		textCon.setBounds(134, 159, 127, 20);
		contentPane.add(textCan);
                ImageIcon image = new ImageIcon("image.png");
                JLabel imageLabel = new JLabel(image); 
                imageLabel.setBounds(400, 800, 150, 200);
                contentPane.add(imageLabel);
		//create the insert button in mySQL
		JButton btnSQL = new JButton("Insertar MySQL");
		btnSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                //create a dAo user of type Mysql
				Usuario u1;
				UsuarioDAOMySQL daoSQL = new UsuarioDAOMySQL();
				//get the data
				int id = Integer.parseInt(textid.getText());
				String ISBN = textISBN.getText();
				String autor = textAu.getText();
				String genero = textGenero.getText();
				String can = textCan.getText();
				
				u1 = new Usuario(id, ISBN, autor, genero, can);
				try {
					daoSQL.create(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazListaUsuarios n = new InterfazListaUsuarios();
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
		        UsuarioDAOMySQL daoSQL = new UsuarioDAOMySQL();
		        //get the data
		        int id = Integer.parseInt(textid.getText());
		        String ISBN  = textISBN.getText();
		        String autor = textAu.getText();
		        String genero = textGenero.getText();
		        String can= textCan.getText();
		        
		        Usuario u1 = new Usuario(
                        Integer.parseInt(textid.getText()), 
                        textISBN.getText(),
                        textAu.getText(),
                        textGenero.getText(),
                        textCan.getText()
                        );

		        try {
		            daoSQL.update(u1);
		        } catch (SQLException e1) {
		            System.out.print("No se pudo actualizar");
		            e1.printStackTrace();
		        }
		        
		        try {
		            InterfazListaUsuarios n = new InterfazListaUsuarios();
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
		        UsuarioDAOMySQL daoSQL = new UsuarioDAOMySQL();
                    int id = Integer.parseInt(textid.getText());
                    try {
                        daoSQL.delete(id);
                    } catch (SQLException e1) {
                        System.out.print("No se pudo eliminar");
                        e1.printStackTrace();
                    }   
                    try {
		            InterfazListaUsuarios n = new InterfazListaUsuarios();
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
				Usuario u1;
				UsuarioDAOArchivo daoA = new UsuarioDAOArchivo("Usuarios.txt");
				//get the data
				int id = Integer.parseInt(textid.getText());
				String ISBN  = textISBN.getText();
				String autor= textAu.getText();
				String genero = textGenero.getText();
				String can = textCan.getText();
				
				u1 = new Usuario(id, ISBN, autor, genero, can);
				try {
					daoA.create(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazListaTXT n = new InterfazListaTXT();
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
			UsuarioDAOArchivo daoA = new UsuarioDAOArchivo("Usuarios.txt");
			int id = Integer.parseInt(textid.getText());
		        String ISBN = textISBN.getText();
		        String autor= textAu.getText();
		        String genero = textGenero.getText();
		        String pass = textCan.getText();
		        Usuario u1 = new Usuario(
                        Integer.parseInt(textid.getText()), 
                        textISBN.getText(),
                        textAu.getText(),
                        textGenero.getText(),
                        textCan.getText()
                        );

				try {
					daoA.update(u1);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazListaTXT n = new InterfazListaTXT();
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
				UsuarioDAOArchivo daoA = new UsuarioDAOArchivo("Usuarios.txt");
                                int id = Integer.parseInt(textid.getText());
                    try {
                        daoA.delete(id);
				} catch (SQLException e1) {
					System.out.print("No se pudo agregar");
					e1.printStackTrace();
				}
				
				try {
					InterfazListaTXT n = new InterfazListaTXT();
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