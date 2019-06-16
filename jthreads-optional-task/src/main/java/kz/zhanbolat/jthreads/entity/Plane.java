package kz.zhanbolat.jthreads.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kz.zhanbolat.jthreads.action.LandingAction;

public class Plane {
	private Long id;
	private int capacity;
	private int flyDistance;
	private List<Passager> passagers;
	private static LandingAction landingAction = new LandingAction();
	
	public Plane(int capacity, int flyDistance) {
		this.capacity = capacity;
		this.flyDistance = flyDistance;
		this.passagers = new ArrayList<>(capacity);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getFlyDistance() {
		return flyDistance;
	}

	public void setFlyDistance(int flyDistance) {
		this.flyDistance = flyDistance;
	}

	public List<Passager> getPassagers() {
		return Collections.unmodifiableList(passagers);
	}

	public void setPassagers(List<Passager> passagers) {
		this.passagers = passagers;
	}
	
	public Passager get(int index) {
		return passagers.get(index);
	}
	
	public void add(Passager passager) {
		passagers.add(passager);
	}
	
	public int size() {
		return passagers.size();
	}
	
	public void remove(int index) {
		passagers.remove(index);
	}
	
	public void remove(Passager passager) {
		passagers.remove(passager);
	}
	
	public void clear() {
		passagers.clear();
	}
	
	public boolean isFull() {
		return (capacity - passagers.size()) == 0;
	}
	
}
