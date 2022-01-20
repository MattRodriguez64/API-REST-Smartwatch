package customer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class CustomerSmartwatch {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		// Create a factory of connections to the MOM
		ConnectionFactory factory = new ConnectionFactory();
		// Specify the address of the MOM server
		factory.setHost("rabbit.begon.dev");
		// Create a new connection to the MOM
		Connection connection = factory.newConnection();
		// Create a new channel within this connection
		// to consume and to produce messages
		Channel channel = connection.createChannel();
		// Declare the queue to be used with the parameters:
		// durable:false, exclusive: false, autodelete:false,
		// additional-arguments:null
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// now we will be ready to receive messages
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.println(" [x] Received '" + message + "'");
		};
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
		});
	}
}
