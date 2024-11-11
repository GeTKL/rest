package servletCalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/servletCalc")
public class servletCalc extends HttpServlet {
	private final Gson gson = new Gson();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
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
		
		JsonObject jsonObject = gson.fromJson(String.valueOf(jb), JsonObject.class);
		
		request.setCharacterEncoding("UTF-8");
		
		double number1 = jsonObject.get("number1").getAsDouble();
		double number2 = jsonObject.get("number2").getAsDouble();
		String operation = jsonObject.get("operation").getAsString();
		
		double result = 0;
		switch (operation) {
		case "+":
			result = number1 + number2;
			break;
		case "-":
			result = number1 - number2;
			break;
		case "*":
			result = number1 * number2;
			break;
		case "/":
			if (number2 == 0)
			{
				pw.print(gson.toJson("Значение равно 0, деление невозможно"));
			}
			else result = number1 / number2;
			break;
		}
		
		pw.print(gson.toJson("Результат = " + result));
	}

}
