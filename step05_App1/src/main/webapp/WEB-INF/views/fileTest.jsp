<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload/Download</title>
</head>


<body>
	<h1>File</h1>
	<form action="/file-upload" method="POST" enctype="multipart/form-data">
	<!-- multiple파일 여러개 선택 -->
		<input type="file" name="files" multiple><br />
		<input type="submit" value="업로드">
	</form>
	<hr />
	<a href="/file-download" >File Download</a>
</body>
</html>	