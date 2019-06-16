package kz.zhanbolat.jthreads.entity;

import java.util.Objects;

public class Passager {
	private Long id;
	private Integer terminalId;
	private PassagerTarget passagerTarget;
	
	public Passager(Long id, Integer terminalId) {
		this.id = id;
		this.terminalId = terminalId;
		this.passagerTarget = PassagerTarget.NO_TARGET;
	}
	
	public Passager(Long id, Integer terminalId,
					PassagerTarget passagerTarget) {
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
		builder.append(", passagerTarget=");
		builder.append(passagerTarget);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, passagerTarget, terminalId);
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
				&& Objects.equals(terminalId, other.terminalId);
	}
	
}
