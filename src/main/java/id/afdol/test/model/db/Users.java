package id.afdol.test.model.db;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String social_security_number;

	private Date date_of_birth;
	private Timestamp date_created;
	private Timestamp date_updated;
	private String updated_by;
	private String created_by;
	private Boolean is_deleted;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSocial_security_number() {
		return social_security_number;
	}
	public void setSocial_security_number(String social_security_number) {
		this.social_security_number = social_security_number;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Boolean getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getDate_created() {
		return date_created;
	}
	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}
	public Timestamp getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(Timestamp date_updated) {
		this.date_updated = date_updated;
	}
}
