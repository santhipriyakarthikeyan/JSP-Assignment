

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,    // 10MB
                 maxRequestSize = 1024 * 1024 * 50) // 50MB


/**
 * Servlet implementation class FileUploadServlet
 */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIRECTORY = "D:\\sp\\ISTE\\Upload";
	
       
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
   		String appPath = request.getServletContext().getRealPath("");
		String uploadDirectoryPath = "D:\\sp\\ISTE\\Upload"; // Change this to your desired upload directory

		File uploadDirectory = new File(uploadDirectoryPath);
		if (!uploadDirectory.exists()) {
		    uploadDirectory.mkdirs(); // Create the directory if it doesn't exist
		}

		Part part = request.getPart("file");
		String fileName = extractFileName(part);

		if (fileName != null && !fileName.isEmpty()) {
		    String filePath = uploadDirectoryPath + File.separator + fileName; // Construct the file path
		    part.write(filePath);
		}

		response.sendRedirect("listfiles.jsp");   
    }
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return null;
	

	}

}
