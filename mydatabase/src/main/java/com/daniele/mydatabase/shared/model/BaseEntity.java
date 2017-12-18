package com.daniele.mydatabase.shared.model;

import com.daniele.mydatabase.DateUtils;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	@Transient
	private static final long serialVersionUID = 684452728857089137L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id = null;
	
	@Column(name = "is_active")
	private boolean active;
	
	@Column(name = "last_update")
	private LocalDate lastUpdate;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	public BaseEntity() {
	}
	
    @PrePersist
	public void setLastDeleted() {
		this.active = true;
	}
	
	@PreUpdate
	public void setLastUpdateAndUpdatedBy() {
		this.lastUpdate = DateUtils.now();
		this.updatedBy = "Dummy";
	}

	public Long getId() {
		return this.id;
	}
	
	public boolean isActive() {
		return active;
	}

	public LocalDate getLastUpdate() {
		return this.lastUpdate;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		BaseEntity other = (BaseEntity) obj;
		if (active != other.active)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}

	protected void copy(final BaseEntity source) {
		this.id = source.id;
		this.lastUpdate = source.lastUpdate;
	}
		
	protected static boolean getBooleanValue(final Boolean value) {
	     return Boolean.valueOf(String.valueOf(value));
	}
}