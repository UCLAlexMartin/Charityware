package systemHibernateEntities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

@Entity
@Audited
@XmlRootElement(name = "accesslog")
public class AccessLog {

	private Integer access_log_id;
	private User user;
	private Date access_start_time;
	private Date access_end_time;
	private String device;
	private String location;
	private Boolean isOnline;
	
	public AccessLog() {}
	public AccessLog(Date start, User user){
		this.access_start_time=start;
		this.user=user;
	}
	@XmlElement
	public Integer getAccess_log_id() {
		return access_log_id;
	}
	public void setAccess_log_id(Integer access_log_id) {
		this.access_log_id = access_log_id;
	}
	@XmlElement
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@XmlElement
	public Date getAccess_start_time() {
		return access_start_time;
	}
	public void setAccess_start_time(Date access_start_time) {
		this.access_start_time = access_start_time;
	}
	@XmlElement
	public Date getAccess_end_time() {
		return access_end_time;
	}
	public void setAccess_end_time(Date access_end_time) {
		this.access_end_time = access_end_time;
	}
	@XmlElement
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	@XmlElement
	public Boolean getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}
	@XmlElement
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
