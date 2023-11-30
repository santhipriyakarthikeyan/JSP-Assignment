

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.InputStream;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
@WebServlet("/DownloadServlet")
/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String fileName = request.getParameter("fileName");
	        if (fileName != null && !fileName.isEmpty()) {
	            String appPath = request.getServletContext().getRealPath("");
	            String filePath = appPath + File.separator + "uploads" + File.separator + fileName;

	            File file = new File(filePath);
	            if (file.exists()) {
	                response.setContentType("application/octet-stream");
	                response.setContentLengthLong(file.length());
	                String headerKey = "Content-Disposition";
	                String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
	                response.setHeader(headerKey, headerValue);

	                try (InputStream inputStream = new FileInputStream(file);
	                     OutputStream outputStream = response.getOutputStream()) {
	                    byte[] buffer = new byte[4096];
	                    int bytesRead = -1;
	                    while ((bytesRead = inputStream.read(buffer)) != -1) {
	                        outputStream.write(buffer, 0, bytesRead);
	                    }
	                }
	            }
	        }
	}

	}
