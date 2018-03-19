package br.com.ampq.v1.util;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import br.com.ampq.v1.model.Usuario;

public class ClientConsumer {

	public void userConsume(Usuario usuario){
		try {
			ActiveMQConnectionFactory factory = 
					new ActiveMQConnectionFactory("tcp://localhost:61616");
			factory.setTrustAllPackages(true);
			
			//Cria a conexão com ActiveMQ
			Connection connection = factory.createConnection();
			// Inicia a conexão
			connection.start();
			// Cria a sessão
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Crea a fila e informa qual o destinatário.
            Destination queue = session.createQueue(usuario.getDestinatario());            
            MessageConsumer consumer = session.createConsumer(queue);
            Message message = consumer.receive();
            
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Consumer Received 1: " + text);
            }
            
            if(message instanceof ObjectMessage){
            	ObjectMessage objectMessage = (ObjectMessage) message;
            	Usuario paciente = (Usuario) objectMessage.getObject();
            	System.out.println("Consumer Received 2: " + paciente);
            }
            
            session.close();
            connection.close();
		} catch (Exception e) {
			System.out.println("Exception Occured");
		}
	}
		
}
