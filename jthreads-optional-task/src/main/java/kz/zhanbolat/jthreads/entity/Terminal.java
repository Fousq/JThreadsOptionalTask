package kz.zhanbolat.jthreads.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Terminal extends Thread {
	private Integer id;
	private boolean isFree = true;
	private List<Passager> passagers;
	private Ladder ladder;
	
	public Terminal() {
		passagers = new ArrayList<>();
	}
	
	public Terminal(Integer id) {
		this.id = id;
	}
	
	public Terminal(Ladder ladder) {
		this.ladder = ladder;
	}
	
	public Terminal(boolean isFree) {
		this.isFree = isFree;
	}
	
	@Override
	public void run() {
		
	}
	
	public void setIsFree(boolean isFree) {
		this.isFree = isFree;
	}
	
	public boolean isFree() {
		return isFree;
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Passager> getPassagers() {
		return Collections.unmodifiableList(passagers);
	}

	public void setPassagers(List<Passager> passagers) {
		this.passagers = passagers;
	}
	
	public void add(Passager passager) {
		passagers.add(passager);
	}
	
	public void remove(Passager passager) {
		passagers.remove(passager);
	}

	public Ladder getLadder() {
		return ladder;
	}

	public void setLadder(Ladder ladder) {
		this.ladder = ladder;
	}
	
}
