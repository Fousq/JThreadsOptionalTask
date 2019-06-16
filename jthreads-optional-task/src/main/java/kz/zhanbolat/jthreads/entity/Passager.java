package kz.zhanbolat.jthreads.entity;

import java.util.Objects;

public class Passager {
	private Long id;
	private Integer terminalId;
	private Long planeId;
	private PassagerTarget passagerTarget;
	
	public Passager(Long id, Integer terminalId, Long planeId) {
		this.id = id;
		this.terminalId = terminalId;
		this.planeId = planeId;
		this.passagerTarget = PassagerTarget.NO_TARGET;
	}
	
	public Passager(Long id, Integer terminalId, Long planeId,
					PassagerTarget passagerTarget) {
		this.id = id;
		this.terminalId = terminalId;
		this.planeId = planeId;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Passager [id=");
		builder.append(id);
		builder.append(", terminalId=");
		builder.append(terminalId);
		builder.append(", planeId=");
		builder.append(planeId);
		builder.append(", passagerTarget=");
		builder.append(passagerTarget);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, passagerTarget, planeId, terminalId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passager other = (Passager) obj;
		return Objects.equals(id, other.id) && passagerTarget == other.passagerTarget
				&& Objects.equals(planeId, other.planeId) && Objects.equals(terminalId, other.terminalId);
	}
	
}
