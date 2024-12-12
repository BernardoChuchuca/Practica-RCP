package ec.edu.ups.appdis.ejbclient;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import org.jboss.tools.examples.model.Member;
import org.jboss.tools.examples.service.MemberRegistrationRemote;

public class Main {

	private MemberRegistrationRemote memberRegistration;

	public void intanciarMemberRegistration() throws Exception {
		try {
			final Hashtable<String, Comparable> jndiProperties = new Hashtable<String, Comparable>();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
			jndiProperties.put("jboss.naming.client.ejb.context", true);

			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			jndiProperties.put(Context.SECURITY_PRINCIPAL, "bernardo");
			jndiProperties.put(Context.SECURITY_CREDENTIALS, "nacho1234");

			final Context context = new InitialContext(jndiProperties);

			final String lookupName = "ejb:/jboss-javaee-webapp/MemberRegistration!org.jboss.tools.examples.service.MemberRegistrationRemote";

			this.memberRegistration = (MemberRegistrationRemote) context.lookup(lookupName);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void registrar(String email, String name, String phone) throws Exception {
		Member m = new Member();
		m.setEmail(email);
		m.setName(name);
		m.setPhoneNumber(phone);

		memberRegistration.register(m);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					main.intanciarMemberRegistration();
					main.createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crear y mostrar la interfaz gráfica
	private void createAndShowGUI() {
		JFrame frame = new JFrame("Registro de Miembro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

		frame.setVisible(true);
	}

	// Colocar los componentes del formulario en el panel
	private void placeComponents(JPanel panel) {
		panel.setLayout(new GridLayout(4, 2));

		JLabel emailLabel = new JLabel("Correo Electronico:");
		panel.add(emailLabel);

		JTextField emailField = new JTextField(20);
		panel.add(emailField);

		JLabel nameLabel = new JLabel("Nombre:");
		panel.add(nameLabel);

		JTextField nameField = new JTextField(20);
		panel.add(nameField);

		JLabel phoneLabel = new JLabel("Telefono:");
		panel.add(phoneLabel);

		JTextField phoneField = new JTextField(20);
		panel.add(phoneField);

		JButton registerButton = new JButton("Registrar");
		panel.add(registerButton);

		// Acción cuando el usuario hace clic en "Registrar"
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String email = emailField.getText();
					String name = nameField.getText();
					String phone = phoneField.getText();

					// Llamada al método de registro con los datos del formulario
					registrar(email, name, phone);
					JOptionPane.showMessageDialog(null, "Miembro registrado con éxito!");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al registrar miembro.");
				}
			}
		});
	}
}
