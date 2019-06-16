package kz.zhanbolat.jthreads.action;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import kz.zhanbolat.jthreads.entity.Airport;
import kz.zhanbolat.jthreads.entity.Ladder;
import kz.zhanbolat.jthreads.entity.Passager;
import kz.zhanbolat.jthreads.entity.PassagerTarget;
import kz.zhanbolat.jthreads.entity.Plane;
import kz.zhanbolat.jthreads.entity.Terminal;

public class LandingAction {
	private static Lock lock = new ReentrantLock();
	
	public void land(Plane plane, Ladder ladder) {
		lock.lock();
		try {
			for (int i = 0; i < plane.size(); i++) {
				Passager passager = plane.get(i);
				ladder.add(passager);
			}
			plane.clear();
			ladder.setPlane(null);
		} finally {
			lock.unlock();
		}
	}
	
	public void land(Ladder ladder, Terminal terminal) {
		lock.lock();
		try {
			for (int i = 0; i < ladder.size(); i++) {
				terminal.add(ladder.get(i));
			}
			ladder.clear();
		} finally {
			lock.unlock();
		}
	}
	
	public void land(Terminal terminal, Airport airport) {
		lock.lock();
		try {
			for (int i = 0; i < terminal.size(); i++) {
				Passager passager = terminal.get(i);
				if (passager.getPassagerTarget() == PassagerTarget.TO_LANT) {
					passager.setPassagerTarget(PassagerTarget.NO_TARGET);
					airport.addPassager(passager);
					terminal.remove(i);
					i--;
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
}
