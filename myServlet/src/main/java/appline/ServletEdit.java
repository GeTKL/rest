package appline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import logic.Model;
import logic.User;

@WebServlet(urlPatterns = "/put")
public class ServletEdit extends HttpServlet{

	private AtomicInteger counter = new AtomicInteger(4);
	Model model = Model.getInstance();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer jb = new StringBuffer();
		String line;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
		
		JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
		
		request.setCharacterEncoding("UTF-8");
		
		int id = jobj.get("id").getAsInt();
		String name = jobj.get("name").getAsString();
		String surname = jobj.get("surname").getAsString();
		double salary = jobj.get("salary").getAsDouble();
		
		User user = new User(name, surname, salary);
		model.add(user, id);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		pw.print(gson.toJson(model.getFromList()));
	}
}