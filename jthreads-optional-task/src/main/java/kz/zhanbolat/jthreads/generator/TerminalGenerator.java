package kz.zhanbolat.jthreads.generator;

import java.util.List;

import kz.zhanbolat.jthreads.entity.Airport;
import kz.zhanbolat.jthreads.entity.Ladder;
import kz.zhanbolat.jthreads.entity.Passager;
import kz.zhanbolat.jthreads.entity.Terminal;

public class TerminalGenerator {
	private static IdGenerator idGen = new IdGenerator();
	
	public Terminal generate() {
		return new Terminal((int)idGen.generate());
	}
	
	public Terminal generate(Ladder ladder, Airport airport) {
		return new Terminal((int)idGen.generate(), ladder, airport);
	}
	
	public Terminal generate(Ladder ladder, Airport airport, 
							List<Passager> passagers) {
		return new Terminal((int)idGen.generate(), ladder, airport, passagers);
	}
	
}
