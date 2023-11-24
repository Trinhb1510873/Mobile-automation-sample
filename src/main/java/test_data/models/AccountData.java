package test_data.models;

public class AccountData {
	private final String username;

	public AccountData(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "AccountData{" +
				"username='" + username + "'" +
				"}";
	}
}
