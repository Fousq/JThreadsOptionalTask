package kz.zhanbolat.jthreads.generator;

import java.util.Random;

import kz.zhanbolat.jthreads.entity.Passager;
import kz.zhanbolat.jthreads.entity.PassagerTarget;

public class PassagerGenerator {
	private static IdGenerator passagerIdGen = new IdGenerator();
	private static Random random = new Random();
	
	public Passager generate(int terminalsNumber) {
		return new Passager(passagerIdGen.generate(),
				random.nextInt(terminalsNumber));
	}
	
	public Passager generate(int terminalsNumber, long planeMaxId) {
		return new Passager(passagerIdGen.generate(),
				random.nextInt(terminalsNumber));
	}
	
	public Passager generate(int terminalsNumber, 
							PassagerTarget passagerTarget) {
		return new Passager(passagerIdGen.generate(),
							random.nextInt(terminalsNumber), 
							passagerTarget);
	}
	
	public Passager generate(int terminalsNumber, 
			PassagerTarget passagerTarget, long planeMaxId) {
		return new Passager(passagerIdGen.generate(),
			random.nextInt(terminalsNumber), passagerTarget);
	}
}
