package kz.zhanbolat.jthreads.entity;

public class Passager {
	private Long id;
	private Integer terminalId;
	private Long planeId;
	private PassagerTarget passagerTarget;
	
	public Passager(Long id, Integer terminalId) {
		this.id = id;
		this.terminalId = terminalId;
		this.passagerTarget = PassagerTarget.NO_TARGET;
	}
	
	public Passager(Long id, Integer terminalId, PassagerTarget passagerTarget) {
		this.id = id;
		this.terminalId = terminalId;
		this.passagerTarget = passagerTarget;
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
	
	public Long getPlaneId() {
		return planeId;
	}

	public void setPlaneId(Long planeId) {
		this.planeId = planeId;
	}

	public PassagerTarget getPassagerTarget() {
		return passagerTarget;
	}

	public void setPassagerTarget(PassagerTarget passagerTarget) {
		this.passagerTarget = passagerTarget;
	}
	
}
