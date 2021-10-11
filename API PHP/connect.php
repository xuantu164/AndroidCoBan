<?php
	$hostname = "localhost";
	$usename = "id13966755_xuantu164";
	$password = "}kYm565B9dv#}qUQ";
	$databasename = "id13966755_mp3zing";

	$con = mysqli_connect($hostname,$usename,$password,$databasename);

	mysqli_query($con,"SET NAME 'utf8'");

	/*if($con){
		echo "Thành Công !";
	}
	else {
		echo "Lỗi !";
	}*/
?>