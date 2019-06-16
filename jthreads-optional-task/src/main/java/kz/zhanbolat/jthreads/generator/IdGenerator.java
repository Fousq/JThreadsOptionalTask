package kz.zhanbolat.jthreads.generator;

public class IdGenerator {
	private static long id = 0;
	public long generate() {
		return ++id;
	}
}
