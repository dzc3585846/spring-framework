package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author dzc
 * @date 2019/7/11
 */
public class JmsCustomer {

    public static final  String ACTIVEMQ_ULR = "tcp://192.168.190.2:61616";
    public static final  String QUENE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        //1.获取activemq工场bean
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_ULR);
        //2.通过工场bean获取Connetion
        Connection connection = factory.createConnection();
        connection.start();

        //3.创建会话session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //4.创建目的地（具体是队列还是主题Topic）
        Queue queue = session.createQueue(QUENE_NAME);

        //5.创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        while (true){
            //接受消息
            TextMessage textMessage = (TextMessage) consumer.receive();
            if(null != textMessage)
                System.out.println("******消费者接收到消息："+textMessage.getText());
            else
                break;
        }
        consumer.close();
        session.close();
        connection.close();

    }
}
