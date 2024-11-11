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

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet{
	
	private AtomicInteger counter = new AtomicInteger(4);
	Model model = Model.getInstance();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
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
		
		int id = jobj.get("id").getAsInt();
		
		if (id < 0)
		{
			pw.print(gson.toJson("Отрицательные id отсутствуют"));
		}
		else if (id == 0) {
			pw.print(gson.toJson(model.getFromList()));
		}
		else {
			User user = model.get(id);
			pw.print(gson.toJson(user));
		}
	}
}
