<?php
	
	require "connect.php";

	$user_name = $_POST['username'];
	$user_pass = $_POST['password'];
	$mysql_qry = "SELECT * from users where Email like '$user_name' and Password like '$user_pass';";

	$result = mysqli_query($conn, $mysql_qry);
	$full_result = "";
	if(mysqli_num_rows($result) > 0){
		$row = mysqli_fetch_assoc($result);
		echo "success&".$row['F_Name']."&".$row['Email'];
	}else{
		echo "failed";
	}
?>