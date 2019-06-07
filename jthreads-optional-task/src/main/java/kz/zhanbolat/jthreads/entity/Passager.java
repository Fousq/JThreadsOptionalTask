package kz.zhanbolat.jthreads.entity;

public class Passager {
	private Long id;
	private Integer terminalId;
	
	public Passager(Long id, Integer terminalId) {
		this.id = id;
		this.terminalId = terminalId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}
	
}
