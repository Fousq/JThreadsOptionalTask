package kz.zhanbolat.jthreads.action;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.jthreads.entity.Airport;
import kz.zhanbolat.jthreads.entity.Ladder;
import kz.zhanbolat.jthreads.entity.Passager;
import kz.zhanbolat.jthreads.entity.Plane;
import kz.zhanbolat.jthreads.entity.Terminal;

public class DisembarkationAction {
	private static Logger logger = LogManager.getLogger(DisembarkationAction.class);
	private static Lock lock = new ReentrantLock();
	
	public void disembark(Ladder ladder, Plane plane) {
		lock.lock();
		try {
			for (int i = 0; i < ladder.size() && !plane.isFull(); i++) {
				Passager passager = ladder.get(i);
				plane.add(passager);
			}
			logger.info("Plane " + plane.getId() + " is full");
			ladder.clear();
			ladder.setPlane(null);
		} finally {
			lock.unlock();
		}
	}
	
	public void disembark(Terminal terminal, Ladder ladder) {
		lock.lock();
		try {
			Plane plane = ladder.getPlane();
			if (plane == null) {
				return;
			}
			long planeId = plane.getId();
			for (int i = 0; i < terminal.size() && !ladder.getPlane().isFull(); i++) {
				Passager passager = terminal.get(i);
				if (passager.getPlaneId() == planeId) {
					ladder.add(passager);
					terminal.remove(i);
					i--;
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
	public void disembark(Airport airport, Terminal terminal) {
		lock.lock();
		try {
			for (int i = 0; i < airport.passagersSize(); i++) {
				Passager passager = airport.getPassager(i);
				if (passager.getTerminalId() == terminal.getId()) {
					terminal.add(passager);
					airport.removePassager(i);
					i--;
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
}
