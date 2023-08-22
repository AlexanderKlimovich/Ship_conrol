package com.klymovych.shipconrol.office.controllers;

import com.klymovych.shipconrol.common.bean.Route;
import com.klymovych.shipconrol.office.service.PathService;
import com.klymovych.shipconrol.office.service.WaitingRoutesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/routes")
public class RouteRest {

    private final PathService pathService;

    private final WaitingRoutesService waitingRoutesService;

    @PostMapping(path="route")
    public void addRoute(@RequestBody List<String> routeLocations){
        Route route = pathService.convertLocationsToRoute(routeLocations);
        waitingRoutesService.add(route);
    }
}
