package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
		@Id
    	@GeneratedValue(strategy=GenerationType.AUTO)
		private long userid;
		

		private String username;


		public long getUserid() {
			return userid;
		}


		public void setUserid(long userid) {
			this.userid = userid;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


}
