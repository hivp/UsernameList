package com.company.usercheck.domain;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.io.Serializable;


/**
 * A user.
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 6361248271058375801L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

	@NotNull
    @Size(min = 6, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String userName;	
	
	public User(){
		
	}
	
	public User(String userName) {
		super();
		this.userName = userName;
	}

	public User(Long id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + "]";
	}


}
