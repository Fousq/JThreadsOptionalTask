package kz.zhanbolat.jthreads.generator;

import java.util.List;
import java.util.Random;

import kz.zhanbolat.jthreads.entity.Passager;
import kz.zhanbolat.jthreads.entity.Plane;
import kz.zhanbolat.jthreads.exception.PlaneException;

public class PlaneGenerator {
	private static IdGenerator idGen = new IdGenerator();
	private static Random random = new Random();
	
	public Plane generateWithRandomCapacity(int bound) {
		return new Plane(idGen.generate(), random.nextInt(bound));
	}
	
	public Plane generateWithRandomCapacity(int bound, List<Passager> passagers) throws PlaneException {
		return new Plane(idGen.generate(), random.nextInt(bound), passagers);
	}
	
	public Plane generate(int capacity) {
		return new Plane(idGen.generate(), capacity);
	}
	
	public Plane generate(int capacity, List<Passager> passagers) throws PlaneException {
		return new Plane(idGen.generate(), capacity, passagers);
	}
	
}
