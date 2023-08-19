package com.klymovych.shipconrol.common.messages;

import com.klymovych.shipconrol.common.bean.AirPort;
import com.klymovych.shipconrol.common.bean.Board;
import com.klymovych.shipconrol.common.bean.Sourse;
import com.klymovych.shipconrol.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardStateMessage extends Message {

    private Board board;

    public BoardStateMessage() {
        this.sourse = Sourse.BOARD;
        this.type = Type.STATE;
    }

    public BoardStateMessage(Board board) {
        this();
        this.board = board;
    }
}
