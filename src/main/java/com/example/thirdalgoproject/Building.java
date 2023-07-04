package com.example.thirdalgoproject;

import java.util.LinkedList;

import javafx.scene.control.Button;

public class Building implements Comparable<Building> {
	float x;
	float y;
	String name;
	LinkedList<Edge> edges = new LinkedList<>();
	private double max = Double.MAX_VALUE;
	private Building tempPrev;
	Building prevousBuilding;
	Button test = new Button();

	public Building(String name, float x, float y) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public Building() {

	}

	public void addAdjacentBuilding(Building Building, double distance) {
		edges.add(new Edge(Building, distance));
	}

	public void resetTemps() {
		max = Double.MAX_VALUE;
		tempPrev = null;
	}
	
	

	public Button getTest() {
		return test;
	}

	public void setTest(Button test) {
		this.test = test;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public LinkedList<Edge> getAdjacentsList() {
		return edges;
	}

	public void setTempCost(double tempCost) {
		this.max = tempCost;
	}

	public double getTempCost() {
		return max;
	}

	public void setTempPrev(Building tempPrev) {
		this.tempPrev = tempPrev;
		
		
	} 

	public String getFullName() {
		return name;
	}

	public Building getTempPrev() {
		return tempPrev;
	}

	@Override
	public String toString() {
		return "Building [x=" + x + ", y=" + y + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Building o) {
		if (this.max > o.max)
			return 1;
		else if (this.max < o.max)
			return -1;
		return 0;
	}

}
