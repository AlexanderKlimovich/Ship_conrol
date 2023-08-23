package com.klymovych.shipconrol.listener.processor;

import com.klymovych.shipconrol.common.bean.Route;
import com.klymovych.shipconrol.common.messages.OfficeRouteMessage;
import com.klymovych.shipconrol.common.processor.MessageConverter;
import com.klymovych.shipconrol.common.processor.MessageProcessor;
import com.klymovych.shipconrol.provider.BoardProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_ROUTE")
@RequiredArgsConstructor
public class OfficeRouteProcessor implements MessageProcessor<OfficeRouteMessage> {

    private final BoardProvider boardProvider;

    private final MessageConverter messageConverter;

    @Override
    public void process(String jsonMessage) {
        OfficeRouteMessage message = messageConverter.extractMessage(jsonMessage, OfficeRouteMessage.class);
        Route route = message.getRoute();
        boardProvider.getBoards().stream()
                .filter(board -> board.noBusy() && route.getBoardName().equals(board.getName()))
                .findFirst()
                .ifPresent(board -> {
                    board.setRoute(route);
                    board.setBusy(true);
                    board.setLocation(null);
                });
    }
}
