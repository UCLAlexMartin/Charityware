package charityHibernateEntities;

import java.sql.Date; 
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

@Entity
@Audited
@XmlRootElement(name = "user")
public class User {

	private Integer user_id;
	private String userName;
	private UserType userType;
	private String userPassword;
	private String salt;

	private String userEmail;
	private Date dateCreated;
	private Boolean isActive;
	private Set<FilledForm> filledForms = new HashSet<FilledForm>();
	private Set<Event> events = new HashSet<Event>();
	private Set<AccessLog> accesslogs = new HashSet<AccessLog>();
	
	public User(){}
	public User(String name, String pass) {
		this.userName=name;
		this.userPassword=pass;
		this.isActive=true;
		this.salt="456";
		//this.dateCreated = new Date();
		this.userType = new UserType();
		this.userType.setUserType("Charity_Administrator");
		this.userType.setUserTypeId(2);
		this.userType.setIsActive(true);
	}
		
	@XmlElement
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@XmlElement
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	@XmlElement
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@XmlElement(name = "userType")
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	@XmlElement
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@XmlElement
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@XmlElement
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@XmlElement
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@XmlElement(name = "filledforms")
	public Set<FilledForm> getFilledForms() {
		return filledForms;
	}
	public void setFilledForms(Set<FilledForm> filledForms) {
		this.filledForms = filledForms;
	}
	@XmlElement(name = "events")
	public Set<Event> getEvents() {
		return events;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	public Set<AccessLog> getAccesslogs() {
		return accesslogs;
	}
	public void setAccesslogs(Set<AccessLog> accesslogs) {
		this.accesslogs = accesslogs;
	}

}

