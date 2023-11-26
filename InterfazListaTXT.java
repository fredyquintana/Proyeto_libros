//we import all libraries
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
//create a class and inherit the Jframe
public class InterfazListaTXT extends JFrame {
    DefaultTableModel modelo = new DefaultTableModel();
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	InterfazListaTXT frame = new InterfazListaTXT();
                    frame.setVisible(true);
                    frame.setSize(1000, 600);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //create the interface and pass the exceptions to it
    public InterfazListaTXT() throws SQLException {
        //create the form and give it color and measurements
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192,255,192)); 
        setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        //we color the edges
        JScrollPane scrollPane = new JScrollPane();
        Color colorBorde = Color.decode("#8c8c8c");
        scrollPane.setBorder(new LineBorder(colorBorde, 2));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addGap(65)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                                .addGap(51)));
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addGap(105)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                                .addGap(29)));
        //create the table with the columns
        modelo.addColumn("id");
        modelo.addColumn("ISBN");
        modelo.addColumn("Autor");
        modelo.addColumn("Genero");
        modelo.addColumn("Cantidad");
        table = new JTable(modelo);
        table.setModel(new DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null},
                        { null, null, null, null, null},
                        { null, null, null, null, null},
                        { null, null, null, null, null},
                },
                new String[] {
                        "id", "ISBN", "Autor", "Genero", "Cantidad"
                }));
        //verify that the table is visible
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
        table.setEnabled(false);
        CargarUsuarios();
        //create the button that redirects us to the main interface
        JButton btnNewButton = new JButton("Regresar al menu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                // Add the code to open the main menu frame here
                try {
                    MenuLibros frame = new MenuLibros(); // Replace "Insertar" with the actual class name of your main menu frame
                    frame.setVisible(true);
                    frame.setSize(1000, 600);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(285, 10, 140, 23);
	contentPane.add(btnNewButton, BorderLayout.NORTH);
    }

    public void CargarUsuarios() throws SQLException {
        // Get the records but now in ArrayList
        UsuarioDAOArchivo dao = new UsuarioDAOArchivo("Usuarios.txt");
        ArrayList<Usuario> usuarios = new ArrayList<>();
    
        usuarios = dao.readAll();
        Object datos[] = new Object[5];
        //we go through all the records that contain the text document
        if (usuarios != null) {
            for (Usuario u : usuarios) {
                datos[0] = u.getId();
                datos[1] = u.getISBN();
                datos[2] = u.getAutor();
                datos[3] = u.getGenero();
                datos[4] = u.getCantidad();
                modelo.addRow(datos);
            }
        }
        //we format the table
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getTableHeader().setBackground(Color.BLUE);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setModel(modelo);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(30);
    }
    
}
