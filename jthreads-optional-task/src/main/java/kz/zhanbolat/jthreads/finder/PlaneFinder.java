package kz.zhanbolat.jthreads.finder;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import kz.zhanbolat.jthreads.entity.Airport;
import kz.zhanbolat.jthreads.entity.Plane;

public class PlaneFinder {
	private static Lock lock = new ReentrantLock();
	
	public Plane pop(Airport airport) {
		lock.lock();
		try {
			Plane plane = null;
			if (airport.planesSize() == 0) {
				return plane;
			}
			plane = airport.getPlane(0);
			airport.removePlane(plane);
			return plane;
		} finally {
			lock.unlock();
		}
	}
	
}
