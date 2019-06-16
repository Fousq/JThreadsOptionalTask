package kz.zhanbolat.jthreads.generator;

import kz.zhanbolat.jthreads.entity.Ladder;
import kz.zhanbolat.jthreads.entity.Plane;
import kz.zhanbolat.jthreads.entity.Terminal;

public class LadderGenerator {
	
	public Ladder generate() {
		return new Ladder();
	}
	
	public Ladder generate(Terminal terminal) {
		return new Ladder(terminal);
	}
	
	public Ladder generate(Terminal terminal, Plane plane) {
		return new Ladder(terminal, plane);
	}
	
}
