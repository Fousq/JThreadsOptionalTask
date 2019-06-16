package kz.zhanbolat.jthreads.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.jthreads.action.DisembarkationAction;
import kz.zhanbolat.jthreads.action.LandingAction;
import kz.zhanbolat.jthreads.finder.PlaneFinder;

public class Ladder extends Thread {
	private static Logger logger = LogManager.getLogger(Ladder.class);
	private List<Passager> passagers;
	private Terminal terminal;
	private Plane plane;
	private static LandingAction landingAction = new LandingAction();
	private static DisembarkationAction disembarkationAction = new DisembarkationAction();
	
	public Ladder(Terminal terminal, Plane plane) {
		this.terminal = terminal;
		this.plane = plane;
		passagers = new ArrayList<>();
	}
	
	public Ladder(Terminal terminal) {
		this.terminal = terminal;
		passagers = new ArrayList<>();
	}
	
	public Ladder() {
		passagers = new ArrayList<>();
	}
	
	@Override
	public void run() {
		logger.info("Ladder " + Thread.currentThread().getId()
					+ " has started.");
		PlaneFinder finder = new PlaneFinder();
		while(true) {
			if (plane != null && terminal != null) {
				logger.info("Ladder"+ Thread.currentThread().getId() 
							+ " has an plane to performe actions.");
				long planeId = plane.getId();
				if (plane.size() == 0) {
					logger.info("Ladder " + Thread.currentThread().getId()
								+ " has started disembarkation on plane "
								+ planeId);
					disembarkationAction.disembark(Ladder.this, plane);
					logger.info("Ladder " + Thread.currentThread().getId()
							+ " has ended disembarkation on plane "
							+ planeId);
				} else {
					logger.info("Ladder " + Thread.currentThread().getId()
							+ " has started landing from plane "
							+ planeId + " to terminal " 
							+ terminal.getId());
					landingAction.land(plane, Ladder.this);
					landingAction.land(Ladder.this, terminal);
					logger.info("Ladder " + Thread.currentThread().getId()
							+ " has ended landing from plane "
							+ planeId + " to terminal " 
							+ terminal.getId());
				}
			} else {
				logger.info("No plane to perfome. Starting to find for one.");
				plane = finder.pop(terminal.getAirport());
				if (plane == null) {
					logger.info("No plane found.");
					break;
				}
				logger.info("Ladder " + Thread.currentThread().getId() 
							+ " found the plane.");
			}
		}
	}

	public List<Passager> getPassagers() {
		return Collections.unmodifiableList(passagers);
	}

	public void setPassagers(List<Passager> passagers) {
		this.passagers = passagers;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}
	
	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public void add(Passager passager) {
		passagers.add(passager);
	}
	
	public Passager get(int index) {
		return passagers.get(index);
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
	
}
