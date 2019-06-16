package kz.zhanbolat.jthreads.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kz.zhanbolat.jthreads.action.DisembarkationAction;
import kz.zhanbolat.jthreads.action.LandingAction;

public class Ladder extends Thread {
	private List<Passager> passagers;
	private Terminal terminal;
	private Plane plane;
	private static LandingAction landingAction = new LandingAction();
	private static DisembarkationAction disembarkationAction = new DisembarkationAction();
	
	public Ladder() {
		passagers = new ArrayList<>();
	}
	
	@Override
	public void run() {
		
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
