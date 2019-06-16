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
	private List<Terminal> terminals;
	private List<Ladder> ladder;
	private List<Passager> passagers;
	private boolean isPoolGenerated = false;
	private static LandingAction ladingAction = new LandingAction();
	private static DisembarkationAction disembarkationAction 
												= new DisembarkationAction();
	
	private Airport() {
		passagers = new ArrayList<>();
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
	
	public void generatePool(int size) {
		if (isPoolGenerated) {
			logger.error("Pool has been already generated.");
			return;
		}
		terminals = new ArrayList<>(size);
		ladder = new ArrayList<>(size);
		while(size != 0) {
			terminals.add(new Terminal(size));
			ladder.add(new Ladder());
			size--;
		}
		isPoolGenerated = true;
	}
	
	public void setPassagers(List<Passager> passagers) {
		this.passagers = passagers;
	}
	
	public List<Passager> getPassagers() {
		return Collections.unmodifiableList(passagers);
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
	
	public int size() {
		return passagers.size();
	}
	
}
