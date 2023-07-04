package com.example.thirdalgoproject;

public class Edge {
	Building adjacent;
	double distance;
	public Edge(Building adjacentCity, double distance) {
		this.adjacent = adjacentCity;
		this.distance = distance;
	}

	public Building getAdjacentBuilding() {
		return adjacent;
	}

	public double getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return "Edge [adjacent=" + adjacent + ", distance=" + distance + "]";
	}
	
}
