package kz.zhanbolat.jthreads.entity;

import java.util.ArrayList;
import java.util.List;

public class Ladder extends Thread {
	private List<Passager> passagers;
	
	public Ladder() {
		passagers = new ArrayList<>();
	}
	
	@Override
	public void run() {
		
	}
	
}
