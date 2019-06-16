package kz.zhanbolat.jthreads.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.jthreads.action.DisembarkationAction;
import kz.zhanbolat.jthreads.action.LandingAction;

public class Airport {
	private static final Logger logger = LogManager.getLogger(Airport.class);
	private static Lock lock = new ReentrantLock();
	private static Airport instance;
	private List<Passager> passagers;
	private List<Plane> planes;
	private static DisembarkationAction disembarkationAction 
												= new DisembarkationAction();
	
	private Airport() {
		passagers = new ArrayList<>();
		planes = new ArrayList<>();
	}
	
	public static Airport getInstance() {
		lock.lock();
		try {
			if (instance == null) {
				instance = new Airport();
			}
			return instance;
		} finally {
			lock.unlock();
		}
	}
	
	public void setPassagers(List<Passager> passagers) {
		this.passagers = passagers;
	}
	
	public List<Passager> getPassagers() {
		return Collections.unmodifiableList(passagers);
	}
	
	public List<Plane> getPlanes() {
		return planes;
	}

	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	public Passager getPassager(int index) {
		return passagers.get(index);
	}
	
	public void addPassager(Passager passager) {
		passagers.add(passager);
	}
	
	public void removePassager(Passager passager) {
		passagers.remove(passager);
	}
	
	public void removePassager(int index) {
		passagers.remove(index);
	}
	
	public int passagersSize() {
		return passagers.size();
	}
	
	public void addPlane(Plane plane) {
		planes.add(plane);
	}
	
	public Plane getPlane(int index) {
		return planes.get(index);
	}
	
	public void removePlane(int index) {
		planes.remove(index);
	}
	
	public void removePlane(Plane plane) {
		planes.remove(plane);
	}
	
	public int planesSize() {
		return planes.size();
	}
	
	public void disembark(Terminal terminal) {
		disembarkationAction.disembark(Airport.this, terminal);
	}
	
}
