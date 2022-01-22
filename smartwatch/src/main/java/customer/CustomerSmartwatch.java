package customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import smartwatchdata.SmartwatchData;
@WebListener
public class CustomerSmartwatch implements ServletContextListener{
	private final static String QUEUE_NAME = "montre";
	
	public static List<SmartwatchData> t = new ArrayList<SmartwatchData>();

	public CustomerSmartwatch() throws Exception {
		// Create a factory of connections to the MOM
		ConnectionFactory factory = new ConnectionFactory();
		// Specify the address of the MOM server
		factory.setHost("localhost");
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
			SmartwatchData s = new SmartwatchData(0, 0, 0, 0, 0, message, 0);
			t.add(s);
		};
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
		});
	}
}
