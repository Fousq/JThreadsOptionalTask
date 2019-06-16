package kz.zhanbolat.jthreads.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.jthreads.action.DisembarkationAction;
import kz.zhanbolat.jthreads.action.LandingAction;

public class Terminal extends Thread {
	private static final Logger logger = LogManager.getLogger(Terminal.class);
	private Integer terminalId;
	private List<Passager> passagers;
	private Airport airport;
	private Ladder ladder;
	private static LandingAction landingAction = new LandingAction();
	private static DisembarkationAction disembarkationAction = new DisembarkationAction();
	
	public Terminal(Integer terminalId, Ladder ladder, Airport airport, List<Passager> passagers) {
		this.terminalId = terminalId;
		this.ladder = ladder;
		this.airport = airport;
		this.passagers = passagers;
	}
	
	public Terminal(Integer terminalId, Ladder ladder, Airport airport) {
		this.terminalId = terminalId;
		this.ladder = ladder;
		this.airport = airport;
		passagers = new ArrayList<>();
	}
	
	public Terminal(Integer terminalId) {
		this.terminalId = terminalId;
		passagers = new ArrayList<>();
	}
	
	@Override
	public void run() {
		logger.info("Terminal " + terminalId + " has started.");
		while(true) {
			if (passagers.size() != 0 && ladder != null &&
				ladder.getPlane() != null) {
				Passager passager = passagers.get(0);
				if (passager.getPassagerTarget() == PassagerTarget.TO_DISAMBARK) {
					logger.info("Terminal " + terminalId + " has started disembarkation,"
								+ "on ladder " + ladder.getId() + ".");
					disembarkationAction.disembark(Terminal.this, ladder);
					logger.info("Terminal " + terminalId + " has ended disembarkation.");
				} else {
					logger.info("Terminal " + terminalId + " has started landing,"
								+ "on airport.");
					landingAction.land(Terminal.this, airport);
					logger.info("Terminal " + terminalId + " has ended landing.");
				}
			}
		}
	}

	public long getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}

	public List<Passager> getPassagers() {
		return Collections.unmodifiableList(passagers);
	}

	public void setPassagers(List<Passager> passagers) {
		this.passagers = passagers;
	}

	public Ladder getLadder() {
		return ladder;
	}

	public void setLadder(Ladder ladder) {
		this.ladder = ladder;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}
	
	public Passager get(int index) {
		return passagers.get(index);
	}
	
	public void add(Passager passager) {
		passagers.add(passager);
	}
	
	public void remove(Passager passager) {
		passagers.remove(passager);
	}
	
	public void remove(int index) {
		passagers.remove(index);
	}
	
	public int size() {
		return passagers.size();
	}
	
}
