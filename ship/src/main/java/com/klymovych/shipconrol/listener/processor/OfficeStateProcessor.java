package com.klymovych.shipconrol.listener.processor;

import com.klymovych.shipconrol.common.messages.BoardStateMessage;
import com.klymovych.shipconrol.common.messages.OfficeStateMessage;
import com.klymovych.shipconrol.common.processor.MessageConverter;
import com.klymovych.shipconrol.common.processor.MessageProcessor;
import com.klymovych.shipconrol.provider.BoardProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_STATE")
@RequiredArgsConstructor
public class OfficeStateProcessor implements MessageProcessor<OfficeStateMessage> {

    private final BoardProvider boardProvider;

    private final MessageConverter messageConverter;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void process(String jsonMessage) {
        boardProvider.getBoards().forEach(board -> {
            kafkaTemplate.sendDefault(messageConverter.toJson(new BoardStateMessage(board)));
        });
    }
}
