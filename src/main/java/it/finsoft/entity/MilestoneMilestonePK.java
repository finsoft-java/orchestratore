package it.finsoft.entity;

import java.io.Serializable;

public class MilestoneMilestonePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2788316622662564093L;

	private long milestone;

	private long milestoneChild;

	public MilestoneMilestonePK() {
	}

	public MilestoneMilestonePK(long milestone, long milestoneChild) {
		this.milestone = milestone;
		this.milestoneChild = milestoneChild;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (milestone ^ (milestone >>> 32));
		result = prime * result + (int) (milestoneChild ^ (milestoneChild >>> 32));
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
		if (milestoneChild != other.milestoneChild)
			return false;
		return true;
	}

}
