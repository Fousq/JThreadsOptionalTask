package kz.zhanbolat.jthreads.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import kz.zhanbolat.jthreads.action.LandingAction;
import kz.zhanbolat.jthreads.exception.PlaneException;

public class Plane {
	private Long id;
	private int capacity;
	private List<Passager> passagers;
	private static LandingAction landingAction = new LandingAction();
	
	public Plane(long id, int capacity, List<Passager> passagers)
												throws PlaneException {
		this.id = id;
		if (passagers.size() > capacity) {
			throw new PlaneException("Passagers is more than the capacity of"
									+ " the plane");
		}
		this.capacity = capacity;
		this.passagers = passagers;
	}
	
	public Plane(long id, int capacity) {
		this.id = id;
		this.capacity = capacity;
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
	
	public void land(Ladder ladder) {
		landingAction.land(Plane.this, ladder);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Plane [id=");
		builder.append(id);
		builder.append(", capacity=");
		builder.append(capacity);
		builder.append(", passagers=");
		builder.append(passagers);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(capacity, id, passagers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plane other = (Plane) obj;
		return capacity == other.capacity && Objects.equals(id, other.id) && Objects.equals(passagers, other.passagers);
	}
	
}
