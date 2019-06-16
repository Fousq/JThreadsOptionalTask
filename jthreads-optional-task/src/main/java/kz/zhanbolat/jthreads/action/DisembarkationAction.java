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
			logger.debug("Plane size: " + plane.size());
			logger.info("Plane " + plane.getId() + " is full");
			ladder.clear();
			ladder.setPlane(null);
		} finally {
			lock.unlock();
		}
	}
	
	public void disembark(Terminal terminal, Ladder ladder) {
		lock.lock();
		int count = 0;
		try {
			Plane plane = ladder.getPlane();
			if (plane == null) {
				return;
			}
			logger.debug("Terminal size: " + terminal.size());
			for (int i = 0; i < terminal.size() && !ladder.getPlane().isFull(); i++) {
				Passager passager = terminal.get(i);
				ladder.add(passager);
				logger.debug(passager + "has added to ladder " + ladder.getId());
				terminal.remove(i);
				i--;
				count++;
			}
			logger.debug(count + " passagers were added to ladder "
					+ ladder.getId());
		} finally {
			lock.unlock();
		}
	}
	
	public void disembark(Airport airport, Terminal terminal) {
		lock.lock();
		int count = 0;
		try {
			for (int i = 0; i < airport.passagersSize(); i++) {
				Passager passager = airport.getPassager(i);
				if (passager.getTerminalId() == terminal.getTerminalId()) {
					terminal.add(passager);
					airport.removePassager(i);
					i--;
					count++;
					logger.debug(passager + " added to terminal " 
								+ terminal.getTerminalId());
				}
			}
			logger.debug(count + " passagers were added to terminal "
						+ terminal.getTerminalId());
		} finally {
			lock.unlock();
		}
	}
	
}
