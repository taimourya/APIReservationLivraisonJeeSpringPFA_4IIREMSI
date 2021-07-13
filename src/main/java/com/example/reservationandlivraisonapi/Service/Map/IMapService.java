package com.example.reservationandlivraisonapi.Service.Map;

public interface IMapService {
    public double calcDistanceMetre(double lat1, double lon1, double lat2, double lon2);
    public double calcDistanceKm(double lat1, double lon1, double lat2, double lon2);
}
