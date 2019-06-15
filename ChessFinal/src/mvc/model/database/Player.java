package mvc.model.database;

import java.util.*;

public class Player {

	private int id;
	private String username;
	private String email;
	private String password;
	private String nickname;
	private boolean active;
	private ArrayList<Result> results;

	public Player(int id, String username, String email, String password, String nickname, boolean active) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.active = active;
		this.results = new ArrayList();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList<Result> getResults() {
		return this.results;
	}

	public void addResult(Result result) {
		this.results.add(result);
	}
}