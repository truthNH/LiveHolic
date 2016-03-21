package jp.ac.hal.tokyo.liveholic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * ファイルをZipに圧縮してダウンロードする
 * @author yusuke.koide
 *
 */
public class downloadZipFile {
	public static void dlZipFile(HttpServletResponse response,
	         String outputZipFileName, ArrayList<String> fileList)
	         throws ServletException {
	      try (ServletOutputStream outputStream = response.getOutputStream();
	            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);) {
	         response.setContentType("application/zip");
	         response.setHeader("Content-Disposition",
	               String.format("attachment; filename=\"%s\"", outputZipFileName));

	         for (String inputFilePath : fileList) {
	            File file = new File(inputFilePath);
	            downloadZipFile.zip(zipOutputStream, file, file);
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	         throw new ServletException("Download failed...", ex);
	      }
	   }

	   private static void zip(ZipOutputStream zipOutputStream, File rootFile,
	         File targetFile) {
	      if (targetFile.isDirectory()) {
	         // Directory
	         File[] files = targetFile.listFiles();
	         for (File file : files) {
	        	 downloadZipFile.zip(zipOutputStream, rootFile, file);
	         }
	      } else {
	         // File
	         String zipEntryPath = downloadZipFile.getZipEntryPath(rootFile, targetFile);
	         ZipEntry zipEntry = new ZipEntry(zipEntryPath);
	         try (BufferedInputStream inputStream = new BufferedInputStream(
	               new FileInputStream(targetFile))) {
	            zipOutputStream.putNextEntry(zipEntry);

	            downloadZipFile.writeStream(inputStream, zipOutputStream);

	            zipOutputStream.closeEntry();
	         } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	         } catch (IOException ex) {
	            ex.printStackTrace();
	         }
	      }
	   }

	   private static String getZipEntryPath(File rootFile, File targetFile) {
	      int lengthToExtractZipPath = rootFile.getPath().length()
	            - rootFile.getName().length();
	      return targetFile.getPath().replace("\\\\", "/")
	            .substring(lengthToExtractZipPath);
	   }

	   private static void writeStream(InputStream inputStream, OutputStream outputStream)
	         throws IOException {
	      int availableByteNumber;
	      while ((availableByteNumber = inputStream.available()) > 0) {
	         byte[] buffers = new byte[availableByteNumber];
	         int readByteNumber = inputStream.read(buffers);
	         if (readByteNumber < 0) {
	            break;
	         }
	         outputStream.write(buffers, 0, readByteNumber);
	      }
	   }
}
