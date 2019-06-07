package kz.zhanbolat.jthreads.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Airport {
	private static final Logger logger = LogManager.getLogger(Airport.class);
	private static Airport instance = new Airport();
	private List<Terminal> terminals;
	private List<Ladder> ladder;
	private boolean isPoolGenerated = false;
	
	private Airport() {
	}
	
	public static Airport getInstance() {
		return instance;
	}
	
	public void generatePool(int size) {
		if (isPoolGenerated) {
			logger.error("Pool has been already generated.");
			return;
		}
		terminals = new ArrayList<>(size);
		ladder = new ArrayList<>(size);
		while(size != 0) {
			terminals.add(new Terminal());
			ladder.add(new Ladder());
			size--;
		}
	}
	
	public void redirect(List<Passager> passagers, int terminalId) {
		Terminal terminal = terminals.get(terminalId);
		if (!terminal.isFree()) {
			logger.error("Terminal is already engaded.");
			return;
		}
		for (Passager passager : passagers) {
			if (passager.getId() == terminalId) {
				terminal.add(passager);
				passagers.remove(passager);
			}
		}
	}
	
}
