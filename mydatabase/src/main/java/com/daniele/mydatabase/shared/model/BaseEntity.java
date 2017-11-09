package com.daniele.mydatabase.shared.model;

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
	
	@Column(name = "is_deleted")
	private boolean deleted;
	
	@Column(name = "last_update")
	private LocalDate lastUpdate;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	public BaseEntity() {
	}
	
    @PrePersist
	public void setLastDeleted() {
		this.deleted = false;
	}
	
	@PreUpdate
	public void setLastUpdateAndUpdatedBy() {
		this.lastUpdate = LocalDate.now();
		this.updatedBy = "Dummy";
	}

	public Long getId() {
		return this.id;
	}
	
	public boolean isDelete() {
		return deleted;
	}

	public LocalDate getLastUpdate() {
		return this.lastUpdate;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}

	protected void copy(final BaseEntity source) {
		this.id = source.id;
		this.lastUpdate = source.lastUpdate;
	}
	
	@Override
	public boolean equals(final Object obj){
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (!(obj instanceof BaseEntity)){
		    return false;
		}
		final BaseEntity other = (BaseEntity) obj;
		if (this.id != null && other.id != null){
		     if (this.id != other.id){
		         return false;
		     }
		}
		return true;
	}
	
	protected static boolean getBooleanValue(final Boolean value) {
	     return Boolean.valueOf(String.valueOf(value));
	}
}