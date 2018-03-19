package br.com.ampq.v1.util;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import br.com.ampq.v1.model.Usuario;

public class ClientProduce{
	
	private String host = "tcp://localhost:61616";
	private ActiveMQConnectionFactory factory;
	private Connection connection;
	private Session session;
	private Destination queue;
	private MessageProducer producer, producerPaciente, producerHospital;
	private ObjectMessage objectMessage;
	private TextMessage message;
	
	//@Autowired
	//JmsTemplate jmsTemplate;
	
	public void createProduce(Usuario usuario){
		try {
			factory = new ActiveMQConnectionFactory(host);
			factory.setTrustAllPackages(true);
			
			// Create connection.
			connection = factory.createConnection();

			// Start the connection
			connection.start();

			// Create a session which is non transactional
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create Destination queue
			//Destination queue = session.createQueue("Arture");
			queue = session.createQueue(usuario.getDestinatario());
			
			// Create a producer
			producer = session.createProducer(queue);
			producerPaciente = session.createProducer(queue);
			producerHospital = session.createProducer(queue);

			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			//String msg = "Enviando mensagem com ActiveMQ.";
			String msg = usuario.getMessage();

			objectMessage = session.createObjectMessage(usuario);
			
			message = session.createTextMessage(msg);

			producerPaciente.send(message);
			producerHospital.send(objectMessage);
			//jmsTemplate.convertAndSend(objectMessage);
			session.close();
			connection.close();
			
			System.out.println("Mensagem enviada com sucesso: " + usuario.toString());
		} catch (Exception e) {
			System.out.println("Exception Occured");
		}
		
	}
	
}
