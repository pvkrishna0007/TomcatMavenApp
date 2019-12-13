import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServiceProgram extends HttpServlet {

	private String mymsg;
	public static final String ASSET_DIR = "D:\\Apps\\MOCK_API_SERVICES\\mockData";
	private static final String BASE_SERVICE_URL = "http://localhost:1123/MockServerApp/services?api=";

	public void init() throws ServletException {
		mymsg = "List of mock json files";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filePath = System.getProperty("user.dir");

		File file = new File(ASSET_DIR);
		String fileContent = "";
		File[] listOfFiles = file.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			File f = listOfFiles[i];
			String content = "<br/><a href='" + BASE_SERVICE_URL+ f.getName() + "'>"
					+ f.getName() + " </a>";
			fileContent += content;
		}

		// Setting up the content type of webpage
		response.setContentType("text/html");

		// Writing message to the web page
		PrintWriter out = response.getWriter();
		out.println("<h1>" + mymsg + " " + fileContent + "</h1>");
	}

	public void destroy() {
		/*
		 * leaving empty for now this can be used when we want to do something
		 * at the end of Servlet life cycle
		 */
	}
}