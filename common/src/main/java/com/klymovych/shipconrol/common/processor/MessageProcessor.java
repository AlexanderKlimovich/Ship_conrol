package com.klymovych.shipconrol.common.processor;

import com.klymovych.shipconrol.common.messages.Message;

public interface MessageProcessor <T extends Message> {

    void process(String jsonMessage);
}
