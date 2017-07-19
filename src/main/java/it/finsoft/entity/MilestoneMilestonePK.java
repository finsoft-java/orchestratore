package it.finsoft.entity;

import java.io.Serializable;

/**
 * Chiave primaria della MilestoneMilestone
 *
 */
public class MilestoneMilestonePK implements Serializable {

	private static final long serialVersionUID = -2788316622662564093L;

	private long milestone;

	private long milestoneComponente;

	public MilestoneMilestonePK() {
	}

	public MilestoneMilestonePK(long milestone, long milestoneComponente) {
		this.milestone = milestone;
		this.milestoneComponente = milestoneComponente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (milestone ^ (milestone >>> 32));
		result = prime * result + (int) (milestoneComponente ^ (milestoneComponente >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MilestoneMilestonePK other = (MilestoneMilestonePK) obj;
		if (milestone != other.milestone)
			return false;
		if (milestoneComponente != other.milestoneComponente)
			return false;
		return true;
	}

}
