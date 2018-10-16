package com.tian.test;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;

/**
 * Created by tom on 2018/10/12.
 */
public class KafkaProductTest {

    public static void main(String[] args){
        Properties properties = new Properties();
        // bootstrap.servers是Kafka集群的IP地址，如果Broker数量超过1个，则使用逗号分隔，
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        /*
        producer需要server接收到数据之后发出的确认接收的信号，此项配置就是指procuder需要多少个这样的确认信号。此配置实际上代表了数据备份的可用性。以下设置为常用选项：
（1）acks=0： 设置为0表示producer不需要等待任何确认收到的信息。副本将立即加到socket  buffer并认为已经发送。没有任何保障可以保证此种情况下server已经成功接收数据，同时重试配置不会发生作用（因为客户端不知道是否失败）回馈的offset会总是设置为-1；
（2）acks=1： 这意味着至少要等待leader已经成功将数据写入本地log，但是并没有等待所有follower是否成功写入。这种情况下，如果follower没有成功备份数据，而此时leader又挂掉，则消息会丢失。
（3）acks=all： 这意味着leader需要等待所有备份都成功写入日志，这种策略会保证只要有一个备份存活就不会丢失数据。这是最强的保证。
（4）其他的设置，例如acks=2也是可以的，这将需要给定的acks数量，但是这种策略一般很少用。
         */
        properties.put("acks", "all");
        /*
        设置大于0的值将使客户端重新发送任何数据，一旦这些数据发送失败。注意，这些重试与客户端接收到发送错误时的重试没有什么不同。
        允许重试将潜在的改变数据的顺序，如果这两个消息记录都是发送到同一个partition，则第一个消息失败第二个发送成功，则第二条消息会比第一条消息出现要早。
         */
        properties.put("retries", 0);
        /*
        producer将试图批处理消息记录，以减少请求次数。这将改善client与server之间的性能。这项配置控制默认的批量处理消息字节数。
        不会试图处理大于这个字节数的消息字节数。
        发送到brokers的请求将包含多个批量处理，其中会包含对每个partition的一个请求。
        较小的批量处理数值比较少用，并且可能降低吞吐量（0则会仅用批量处理）。较大的批量处理数值将会浪费更多内存空间，这样就需要分配特定批量处理数值的内存大小。
         */
        properties.put("batch.size", 16384);
        // 逗留时间, 也就是一个消息如果没有达到批量大小,则最多等待这么久发送
        properties.put("linger.ms", 1);
        /*
        producer可以用来缓存数据的内存大小。如果数据产生速度大于向broker发送的速度，producer会阻塞或者抛出异常，以“block.on.buffer.full”来表明。
        这项设置将和producer能够使用的总内存相关，但并不是一个硬性的限制，因为不是producer使用的所有内存都是用于缓存。一些额外的内存会用于压缩（如果引入压缩机制），同样还有一些用于维护请求。
         */
        properties.put("buffer.memory", 33554432);
        //  序列化类型。 Kafka消息是以键值对的形式发送到Kafka集群的，其中Key是可选的，Value可以是任意类型。但是在Message被发送到Kafka集群之前，Producer需要把不同类型的消
        //  息序列化为二进制类型。本例是发送文本消息到Kafka集群，所以使用的是StringSerializer
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            for (int i = 0; i < 100; i++) {
                String msg = "Message " + i;
                producer.send(new ProducerRecord<String, String>("HelloWorld", msg));
                System.out.println("Sent:" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }

    }
}
