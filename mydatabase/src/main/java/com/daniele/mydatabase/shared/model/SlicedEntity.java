package com.daniele.mydatabase.shared.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.daniele.mydatabase.shared.DateUtils;

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
		this.validFrom = LocalDate.now();
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
	
}