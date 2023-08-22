package com.klymovych.shipconrol.office.service;

import com.klymovych.shipconrol.common.bean.Route;
import com.klymovych.shipconrol.common.bean.RoutePath;
import com.klymovych.shipconrol.common.bean.RoutePoint;
import com.klymovych.shipconrol.office.provider.AirPortsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PathService {

    private final AirPortsProvider airPortsProvider;

    public RoutePath makePath(String from, String to) {
        return new RoutePath(airPortsProvider.getRoutePoint(from), airPortsProvider.getRoutePoint(to), 0);
    }

    public Route convertLocationsToRoute(List<String> locations) {
        Route route = new Route();
        List<RoutePath> routePaths = new ArrayList<>();
        List<RoutePoint> points = new ArrayList<>();

        locations.forEach(location -> {
            airPortsProvider.getPorts().stream()
                    .filter(airPort -> airPort.getName().equals(location))
                    .findFirst()
                    .ifPresent(airPort -> {
                        points.add(new RoutePoint(airPort));
                    });
        });

        for (int i = 0; i < points.size() - 1; i++) {
            routePaths.add(new RoutePath());
        }

        routePaths.forEach(row -> {
            int index = routePaths.indexOf(row);
            if (row.getFrom() == null) {
                row.setFrom(points.get(index));
                if (points.size() > index + 1) {
                    row.setTo(points.get(index + 1));
                } else {
                    row.setTo(points.get(index));
                }
            }
        });

        route.setPath(routePaths);

        return route;
    }
}
