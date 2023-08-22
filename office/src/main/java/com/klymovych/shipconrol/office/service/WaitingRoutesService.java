package com.klymovych.shipconrol.office.service;

import com.klymovych.shipconrol.common.bean.Route;
import com.klymovych.shipconrol.common.bean.RoutePath;
import com.klymovych.shipconrol.common.bean.RoutePoint;
import com.klymovych.shipconrol.office.provider.AirPortsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class WaitingRoutesService {

    private final AirPortsProvider airPortsProvider;

    private final List<Route> list = new ArrayList<>();

    private final Lock lock = new ReentrantLock(true);

    public List<Route> list() {
        return list;
    }

    public void add(Route route) {
        try {
            lock.lock();
            list.add(route);
        } finally {
            lock.unlock();
        }
    }

    public void remove(Route route) {
        try {
            lock.lock();
            list.removeIf(route::equals);
        } finally {
            lock.unlock();
        }
    }

}
