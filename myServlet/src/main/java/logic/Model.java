package logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable{
	private static final Model instance = new Model();
	
	private final Map<Integer, User> model;
	
	public static Model getInstance() {
		return instance;
	}
	
	private Model() {
		model = new HashMap<>();
		
		model.put(1, new User("Artem", "Klevcov", 55555));
		model.put(2, new User("Anton", "Vinston", 22222));
		model.put(3, new User("Adom", "Sampler", 33222));
	}
	
	public void add(User user, int id) {
		model.put(id, user);
	}
	
	public void delete(int id) {
		model.remove(id);
	}
	
	public User get(int id) {
		return model.get(id);
	}
	
	public Map<Integer, User> getFromList() {
		return model;
	}
}
