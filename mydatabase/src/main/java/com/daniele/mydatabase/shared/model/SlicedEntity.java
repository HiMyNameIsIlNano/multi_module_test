package com.daniele.mydatabase.shared.model;

import com.daniele.mydatabase.DateUtils;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class SlicedEntity extends BaseEntity {

	private static final long serialVersionUID = -5568938498033055357L;

	/*
	 * https://hibernate.atlassian.net/browse/HHH-11829
	 * 
	 * @Column(name = "valid_from", columnDefinition =
	 * "TIMESTAMP WITH TIME ZONE") protected ZonedDateTime validFrom;
	 * 
	 * @Column(name = "valid_to", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	 * protected ZonedDateTime validTo;
	 */

	@Column(name = "valid_from")
	protected LocalDate validFrom;

	@Column(name = "valid_to")
	protected LocalDate validTo;
	
	@Column(name = "updated_by")
	protected String updatedBy;

	@PreUpdate
	@PrePersist
	public void setValidFromAndValidTo() {
		this.validFrom = DateUtils.now();
		this.validTo = DateUtils.END_OF_DATE_TIME;
		this.updatedBy = "Dummy";
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result + ((validFrom == null) ? 0 : validFrom.hashCode());
		result = prime * result + ((validTo == null) ? 0 : validTo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SlicedEntity other = (SlicedEntity) obj;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (validFrom == null) {
			if (other.validFrom != null)
				return false;
		} else if (!validFrom.equals(other.validFrom))
			return false;
		if (validTo == null) {
			if (other.validTo != null)
				return false;
		} else if (!validTo.equals(other.validTo))
			return false;
		return true;
	}

}