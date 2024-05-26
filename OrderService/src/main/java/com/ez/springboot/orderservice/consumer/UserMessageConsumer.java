package com.ez.springboot.orderservice.consumer;

import com.esotericsoftware.minlog.Log;
import com.ez.springboot.workservice.bean.User;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Classname UserMessageConsumer
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/26
 */
@Component
public class UserMessageConsumer {
    /**
     * 之前设置的交换机的name相同，然后type为fanout。
     * 虽然Queue不同，但是三个方式同时接收到相同消息
     * 所以不同的消息，需要绑定不同的交换机去处理消息。
     */

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "UserLoginMessageQueue"),
                    exchange = @Exchange(name = "UserSignIn", type = "fanout")
            )
    }, messageConverter = "messageConverter")
    public void receiveLoginMessage(String message) {
        Log.info("receive LoginMessage: " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "UserUpdateMessageQueue"),
                    exchange = @Exchange(name = "UserUpdate", type = "fanout")
            )
    }, messageConverter = "messageConverter")
    public void receiveUpdateMessage(String message) {
        Log.info("receive UpdateMessage: " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "UserSignInMessageQueue"),
                    exchange = @Exchange(name = "UserSignUp", type = "fanout")
            )
    }, messageConverter = "messageConverter")
    public void receiveSignUpMessage(User user) {
        System.out.println(user);
        Log.info("receive SignUpMessage: " + user);
    }
}
