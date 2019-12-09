package com.trilogyed.leveluppointsqueueconsumer;

import com.trilogyed.leveluppointsqueueconsumer.util.messages.LevelUpPointsEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @RabbitListener(queues = LevelupPointsQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(LevelUpPointsEntry msg) {
        System.out.println(msg.toString());
    }
}
