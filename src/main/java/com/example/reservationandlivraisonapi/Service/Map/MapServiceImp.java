package com.example.reservationandlivraisonapi.Service.Map;

import org.springframework.stereotype.Component;

@Component
public class MapServiceImp implements IMapService{

    final int R = 6371;

    @Override
    public double calcDistanceMetre(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        double height = 0;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        distance = Math.sqrt(distance);
        System.out.println("----------------------------");
        System.out.println("FROM (" + lat1 + " , " + lon1 + ") TO (" + lat2 + " , " + lon2 +")");
        System.out.println("distance = " + distance);
        System.out.println("----------------------------");
        return distance;
    }

    @Override
    public double calcDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // convert to meters
        double height = 0;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        distance = Math.sqrt(distance);
        System.out.println("----------------------------");
        System.out.println("FROM (" + lat1 + " , " + lon1 + ") TO (" + lat2 + " , " + lon2 +")");
        System.out.println("distance = " + distance);
        System.out.println("----------------------------");
        return distance;
    }
}
