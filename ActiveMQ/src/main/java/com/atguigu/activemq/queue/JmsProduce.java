package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author dzc
 * @date 2019/7/10
 */
public class JmsProduce {

    public static final  String ACTIVEMQ_ULR = "tcp://192.168.190.2:61616";
    public static final  String QUENE_NAME = "queue01";


    public static void main(String[] args) throws JMSException {
        //1.获取ActiveMQ工厂类
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_ULR);
        //2.通过连接工场，获取连接Connection
        Connection connection = factory.createConnection();
        connection.start();

        //3.创建会话Session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //4.创建目的地(具体是队列还是主题Topic)
        Queue queue = session.createQueue(QUENE_NAME);

        //5.创建消息生产者
        MessageProducer producer = session.createProducer(queue);
        //6.生产3条消息发动到MQ的队列里面
        for (int i = 1; i <=3 ; i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("msg---" + i);
            //8.通过 MessageProducer发送给mq
            producer.send(textMessage);
        }

        //9.关闭资源
        producer.close();
        session.close();
        connection.close();

        System.out.println("******消息发布到MQ完成");


    }
}
