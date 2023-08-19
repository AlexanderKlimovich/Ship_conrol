package com.klymovych.shipconrol.common.messages;

import com.klymovych.shipconrol.common.bean.Board;
import com.klymovych.shipconrol.common.bean.Route;
import com.klymovych.shipconrol.common.bean.Sourse;
import com.klymovych.shipconrol.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeRouteMessage extends Message {

    private Route route;

    public OfficeRouteMessage() {
        this.sourse = Sourse.OFFICE;
        this.type = Type.ROTE;
    }

    public OfficeRouteMessage(Route route) {
        this();
        this.route = route;
    }
}
