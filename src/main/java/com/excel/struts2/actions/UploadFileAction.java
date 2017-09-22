package com.excel.struts2.actions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import com.excel.struts2.util.FilesUtil;
import com.innovative.runexcelreader.RunExcelReader;
import com.opensymphony.xwork2.ActionSupport;

public class UploadFileAction extends ActionSupport implements ServletContextAware {


	private String val;
	private String errMsg;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String execute() {


		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			FilesUtil.saveFile(getFile(), getFileFileName(), context.getRealPath("") + File.separator + filesPath);

			String[] args = null;
			String fileP = new File(context.getRealPath("") + File.separator + filesPath).getPath() + File.separator
					+ getFileFileName();
			System.out.println("file is " + fileP);

			if (null != getFileFileName() && getFileFileName().length() > 0) {
					args = new String[2];
					args[0] = "-f";
					args[1] = fileP;
				}else {
				System.out.println();
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			PrintStream old = System.out;
			System.setOut(ps);

			RunExcelReader.runExcelReader(args);

			System.out.flush();
			System.setOut(old);

			this.result = baos.toString().replaceAll("\n", "<br><br>");

		} catch (Exception e) {
			e.printStackTrace();
			this.result  = e.getMessage();
			return INPUT;
		}
		return SUCCESS;

	}

	private File file;
	private String fileContentType;
	private String fileFileName;
	private String filesPath;
	private ServletContext context;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}

	@Override
	public void setServletContext(ServletContext ctx) {
		this.context = ctx;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


}
