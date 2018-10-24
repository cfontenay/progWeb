package com.binary;
import java.util.ArrayList;
import java.util.List;

public class UsersManager{
	public static UsersManager instance;
	private List<User> users;
	
	public static UsersManager getInstance() {
		if (instance == null)
			instance = new UsersManager();
		return instance;
	}
	
	public void add(User u) {
		u.id = System.nanoTime();
		users.add(u);
	}
	public boolean remove(User u) {
		return users.remove(u);
	}
	public boolean remove(long id) {
		//parce que pas envie de faire tout bugué en passant en java 8
		//obligé de le faire sans predicate
		for(User u : users) {
			if(u.id == id) {
				users.remove(u);
				return true;
			}
		}
		return false;
			
	}
	public List<User> getAll(){
		return users;
	}
	private UsersManager() {
		users = new ArrayList<User>();
	}
	
}