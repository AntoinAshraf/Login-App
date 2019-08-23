<?php
	require "connect.php";

	$user_name = $_POST['username'];

	$mysql_qryCheck = "SELECT * FROM users WHERE Email LIKE '$user_name' ;";

	$result = mysqli_query($conn, $mysql_qryCheck);

	$RandNumber = rand(10000, 99999);

	if(mysqli_num_rows($result) > 0){
		
		$mailto = $user_name;
	    $mailSub = "Pharmacy Application";
	    $mailMsg = "<h3>Use This Code to verify your request</h3><br>Use this Code<h5>$RandNumber</h5>";

	    require 'PHPMailer-master/PHPMailerAutoload.php';
		$mail = new PHPMailer();
		$mail ->IsSmtp();
		$mail ->SMTPDebug = 0;
		$mail ->SMTPAuth = true;
		$mail ->SMTPSecure = 'ssl';
		$mail ->Host = "smtp.gmail.com";
		$mail ->Port = 465; // or 587
		$mail ->IsHTML(true);
		$mail ->Username = "Your Mail";
		$mail ->Password = "Your Password";
		$mail ->SetFrom("From name");
		$mail ->Subject = $mailSub;
		$mail ->Body = $mailMsg;
		$mail ->AddAddress($mailto);

		if($mail->Send()) {
			echo "success&" . $user_name . "&" . $RandNumber;
		} else {
		   echo "Failed";
		}
	}else{
		echo "MailNotFound";
	}
?>
