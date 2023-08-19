package com.klymovych.shipconrol.common.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class AirPort {

    private String name;

    private List<String> boards = new ArrayList<String>();

    private int x;

    private int y;

    private void addBoard(String boardName){
        int ind = boards.indexOf(boardName);

        if(ind >= 0){
            boards.set(ind, boardName);
        } else {
            boards.add(boardName);
        }
    }

    private void removwBoard(String boardName){
        boards.remove(boardName);
    }
}
