import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Catch;

public class MockServiceApi extends HttpServlet {

	//http://localhost:1123/MockServerApp/services?api=doMobileLogin.json
	//http://localhost:1123/MockServerApp/services?api=tester.json
	//http://localhost:1123/MockServerApp/services
	
	private static final String ERROR_FILE_NAME = "errorStatusResponse.json";
	
	private static final String DO_LOGIN_URL = "http://localhost:1123/MockServerApp/services?api=doMobileLogin.json";

	public void init() throws ServletException {
		//mymsg = "List of mock json files";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		File fileDir = new File(MyServiceProgram.ASSET_DIR);
		String fileContent = "Pass the fileName in mock api as query string<br/>" +
				"<a href='"+DO_LOGIN_URL+"'>"+DO_LOGIN_URL+"</a>";
		if (request.getQueryString() != null) {
			String[] keyValues = request.getQueryString().split("=");
			if (keyValues != null && keyValues.length == 2) {
				String name = keyValues[1];
				File f = new File(fileDir, name);
				if (!f.exists()) {
					f = new File(fileDir, ERROR_FILE_NAME);
				}
				fileContent = getFileContent(f);
			}
		}

		// Setting up the content type of webpage
		response.setContentType("text/html");

		// Writing message to the web page
		PrintWriter out = response.getWriter();
		out.println( fileContent);
	}

	public void destroy() {
		/*
		 * leaving empty for now this can be used when we want to do something
		 * at the end of Servlet life cycle
		 */
	}

	public String getFileContent(File file) {
		if (!file.exists()) {
			return "File not existed :" + file.getAbsolutePath();
		}

		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			while ((st = br.readLine()) != null) {
				builder.append(st);
			}
			br.close();
		} catch (Exception e) {
			builder.append(e.getMessage());
		}
		return builder.toString();
	}

}