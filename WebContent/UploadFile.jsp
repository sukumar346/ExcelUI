<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Excel</title>
</head>
<body>
	<h3>Options</h3>
	<s:form action="UploadFile" method="post" enctype="multipart/form-data">
		<s:file label="File" name="file"></s:file>
		<s:submit value="Submit"></s:submit>
	</s:form>
	
	<s:property value="errMsg"/>
</body>
</html>