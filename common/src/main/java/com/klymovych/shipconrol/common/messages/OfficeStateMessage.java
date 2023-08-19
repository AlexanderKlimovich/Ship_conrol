package com.klymovych.shipconrol.common.messages;

import com.klymovych.shipconrol.common.bean.Route;
import com.klymovych.shipconrol.common.bean.Sourse;
import com.klymovych.shipconrol.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeStateMessage extends Message {

    private Route route;

    public OfficeStateMessage() {
        this.sourse = Sourse.OFFICE;
        this.type = Type.STATE;
    }

}
