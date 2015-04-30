package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.db.DAO;

public class DocumentUploader {

	
	 private static boolean isMultipart;
//	 private static String filePath="E:/docs/";
	 private static String filePath="/users/imran/testdocs/";
	 private static int maxFileSize = 500000 * 1024;
	 private static int maxMemSize = 400 * 1024;
	 private static File file ;
	
	public static void processUploadRequest(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");

	    // Create path components to save the file
	    String path = request.getParameter("destination");
	    System.out.println("destination:"+path);
	    Part filePart = request.getPart("file");
	    System.out.println("filepart"+filePart);
	    String fileName = getFileName(filePart);

	    OutputStream out = null;
	    InputStream filecontent = null;
	    final PrintWriter writer = response.getWriter();

	    try {
	        out = new FileOutputStream(new File(path + File.separator
	                + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        writer.println("New file " + fileName + " created at " + path);
	        System.out.println("File{0}being uploaded to {1}"+ new Object[]{fileName+" path:"+path});
	    } catch (FileNotFoundException fne) {
	        writer.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        writer.println("<br/> ERROR: " + fne.getMessage());

	        System.out.println( "Problems during file upload. Error: {0}"+ new Object[]{fne.getMessage()});
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	        if (writer != null) {
	            writer.close();
	        }
	    }
	}

	private static String getFileName(Part part) {
	    String partHeader = part.getHeader("Content-Disposition");
	    System.out.println("Part Header = {0}"+partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	public static String uploadDoc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		// Check that we have a file upload request
		String resStr="false";
		String globalFileName=null;
		String username= (String)request.getSession().getAttribute("username");
		BigInteger bigInt = new BigInteger(username.getBytes());
		System.out.println("for username:"+username+" the filename:"+bigInt.toString());
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		if (!isMultipart) {
			return resStr;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("E://docs/"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						String fileNameWithExtention =fileName.substring(fileName.lastIndexOf("\\") ) ;
						System.out.println("with extension:"+fileNameWithExtention);
						int indexOfDot= fileNameWithExtention.indexOf(".");
						System.out.println("index:"+indexOfDot);
						String extension = fileNameWithExtention.substring(indexOfDot,fileNameWithExtention.length());
						System.out.println("extension:"+extension);
						//String fname= filePath+ fileName.substring(fileName.lastIndexOf("\\") + 1);
						String fname= filePath+ bigInt+extension;
						globalFileName= bigInt+extension;
						file = new File(fname);
						System.out.println(" In if:"+fname);
					} else {
						
						String fileNameWithExtention =fileName.substring(fileName.lastIndexOf("\\") + 1) ;
						System.out.println("with extension:"+fileNameWithExtention);
						int indexOfDot= fileNameWithExtention.indexOf(".");
						System.out.println("index:"+indexOfDot);
						String extension = fileNameWithExtention.substring(indexOfDot,fileNameWithExtention.length());
						System.out.println("extension:"+extension);
						//String fname= filePath+ fileName.substring(fileName.lastIndexOf("\\") + 1);
						String fname= filePath+ bigInt+extension;
						globalFileName= bigInt+extension;
						file = new File(fname);
						System.out.println(" In else:"+fname);
					}
					fi.write(file);
					System.out.println("file name:"+globalFileName);
					resStr=globalFileName;
					DAO dao = new DAO();
					boolean isPathInserted=dao.updateDocFileName(username,globalFileName);
					System.out.println("image inserted in db:"+isPathInserted);
					return resStr;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return resStr;
	}
	
	
	
	
	public static boolean uploadContacts(HttpServletRequest request,HttpServletResponse response) throws ServletException,java.io.IOException {
		// Check that we have a file upload request
		boolean isContactAdded= false;
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		if (!isMultipart) {
			return isContactAdded;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File("c:\\temp"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						String fname = filePath + fileName.substring(fileName.lastIndexOf("\\"));
						file = new File(fname);
						System.out.println(" In if:" + fname);
					} else {
						String fname = filePath+ fileName.substring(fileName.lastIndexOf("\\") + 1);
						file = new File(fname);
						System.out.println(" In else:" + fname);
					}
					fi.write(file);
				}
			}
			return isContactAdded;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return isContactAdded;
}
	
}
