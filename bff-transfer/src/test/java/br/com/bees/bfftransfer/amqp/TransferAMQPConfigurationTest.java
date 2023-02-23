package br.com.bees.bfftransfer.amqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TransferAMQPConfigurationTest {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private RabbitAdmin admin;
	
	@Test
    public void testSimpleSends() {
		
	}
}
