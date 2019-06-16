package kz.zhanbolat.jthreads.generator;

public class IdGenerator {
	private long id = 0;
	private long maxId;
	
	public IdGenerator(long maxId) {
		this.maxId = maxId;
	}
	
	public IdGenerator() {
		maxId = Long.MAX_VALUE;
	}
	
	public long generate() {
		if (id >= maxId) {
			id = 0;
		}
		return ++id;
	}

	public long getMaxId() {
		return maxId;
	}

	public void setMaxId(long maxId) {
		this.maxId = maxId;
	}
	
}
