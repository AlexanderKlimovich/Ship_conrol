package com.klymovych.shipconrol.office.listener.processors;

import com.klymovych.shipconrol.common.bean.AirPort;
import com.klymovych.shipconrol.common.bean.Board;
import com.klymovych.shipconrol.common.bean.Route;
import com.klymovych.shipconrol.common.messages.AirPortStateMessage;
import com.klymovych.shipconrol.common.messages.BoardStateMessage;
import com.klymovych.shipconrol.common.processor.MessageConverter;
import com.klymovych.shipconrol.common.processor.MessageProcessor;
import com.klymovych.shipconrol.office.provider.AirPortsProvider;
import com.klymovych.shipconrol.office.provider.BoardsProvider;
import com.klymovych.shipconrol.office.service.WaitingRoutesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component("BOARD_STATE")
@RequiredArgsConstructor
public class BoardStateProcessor implements MessageProcessor<BoardStateMessage> {

    private final MessageConverter messageConverter;

    private final WaitingRoutesService waitingRoutesService;

    private final BoardsProvider boardsProvider;

    private final AirPortsProvider airPortsProvider;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void process(String jsonMessage) {
        BoardStateMessage message = messageConverter.extractMessage(jsonMessage, BoardStateMessage.class);
        Board board = message.getBoard();
        Optional<Board> previosOpt = boardsProvider.getBoard(board.getName());
        AirPort airPort = airPortsProvider.getAirPort(board.getLocation());

        boardsProvider.addBoard(board);
        if (previosOpt.isPresent() && board.hasRoute() && !previosOpt.get().hasRoute()) {
            Route route = board.getRoute();
            waitingRoutesService.remove(route);
        }

        if (previosOpt.isEmpty() || !board.isBusy() && previosOpt.get().isBusy()) {
            airPort.addBoard(board.getName());
            kafkaTemplate.sendDefault(messageConverter.toJson(new AirPortStateMessage(airPort)));
        }
    }
}
