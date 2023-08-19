package com.klymovych.shipconrol.common.messages;

import com.klymovych.shipconrol.common.bean.Sourse;
import com.klymovych.shipconrol.common.bean.Type;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Message {

    protected Type type;

    protected Sourse sourse;

    public String getCode() {
        return sourse.name() + "_" + type.name();
    }
}
