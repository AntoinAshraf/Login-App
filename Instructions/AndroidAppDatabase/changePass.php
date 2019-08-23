<?php
	require "connect.php";

	$user_name = $_POST['username'];
	$user_pass = $_POST['newpassword'];

	$mysql_qry = "UPDATE users SET Password = '$user_pass' WHERE Email LIKE '$user_name'";

	if($conn->query($mysql_qry) === TRUE){
        echo "success&" . $user_name;
    }else{
    	echo "Failed";
    }
?>