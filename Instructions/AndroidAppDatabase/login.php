<?php
	require "connect.php";

	//$user_name = 'pharmacymail579@gmail.com';
	//$user_pass = '5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5';
	$user_name = $_POST['username'];
	$user_pass = $_POST['password'];
	$mysql_qry = "SELECT * FROM users WHERE Email LIKE '$user_name' AND Password LIKE '$user_pass';";

	$result = mysqli_query($conn, $mysql_qry);
	$totalResult = "";
	if(mysqli_num_rows($result) > 0){
		$row = mysqli_fetch_assoc($result);
		$totalResult = "success&".$row['User_ID']."&".$row['Email'];
		$totalResult .= "&". $row['phoneNum'] . "&". $row['Address'] . "&" . $row['F_Name'];
		$totalResult .= "&" . $row['L_Name'] . "&" . $row['Add_Permition'] . "&" . $row['Sell_Permition'];
		$totalResult .= "&" . $row['Age'] . "&" . $row['Type_Number'];
		$totalResult .= "&" . $row['Verified'];
		echo $totalResult;
	}else{
		echo "failed";
	}
?>