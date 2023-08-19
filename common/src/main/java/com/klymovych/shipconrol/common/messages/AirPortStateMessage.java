package com.klymovych.shipconrol.common.messages;

import com.klymovych.shipconrol.common.bean.AirPort;
import com.klymovych.shipconrol.common.bean.Sourse;
import com.klymovych.shipconrol.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirPortStateMessage extends Message {

    private AirPort airPort;

    public AirPortStateMessage() {
        this.sourse = Sourse.AIRPORT;
        this.type = Type.STATE;
    }

    public AirPortStateMessage(AirPort airPort) {
        this();
        this.airPort = airPort;
    }
}
