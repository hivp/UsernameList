package com.company.usercheck.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A Restricted Word.
 */
@Entity
@Table(name = "RESTRICTED_WORD")
public class RestrictedWord implements Serializable{

	private static final long serialVersionUID = -1921148540465475602L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	
	@NotNull
    @Size(min = 6, max = 50)
    @Column(length = 50, unique = true, nullable = false)
	private String word;

	public RestrictedWord(){
		
	}
		
	public RestrictedWord(String word) {
		super();
		this.word = word;
	}

	public RestrictedWord(Long id, String word) {
		super();
		this.id = id;
		this.word = word;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		RestrictedWord other = (RestrictedWord) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RestrictedWord [id=" + id + ", word=" + word + "]";
	}
	
	
	
}
