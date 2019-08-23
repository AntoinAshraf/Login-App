<?php

	require "connect.php";

	$email = $_POST['email'];
	$phoneNum = $_POST['phoneNum'];
	$user_pass = $_POST['password'];
	$Address = $_POST['address'];
	$F_name = $_POST['f_name'];
	$L_name = $_POST['l_name'];
	$Add_Permition = $_POST['add_perm'];
	$Sell_Permition = $_POST['sell_perm'];
	$Age = $_POST['age'];
	$Type_Number = $_POST['type_number'];

	if($Type_Number == 'Manager'){
		$Type_Number = 1;
	} else if($Type_Number == 'Pharmacist'){
		$Type_Number = 2;
	} else if($Type_Number == 'Delivery Boy'){
		$Type_Number = 3;
	} else if($Type_Number == 'patient'){
		$Type_Number = 4;
	}

	$AgeInt = (int)$Age;
	$Add_PermitionInt = (int)$Add_Permition;
	$Sell_PermitionInt = (int)$Sell_Permition;
	
	$mysql_qry = "INSERT INTO users (Email, phoneNum, Password, Address, F_Name, L_Name, Add_Permition, Sell_Permition, Age, Type_Number) VALUES ('$email', '$phoneNum', '$user_pass', '$Address', '$F_name', '$L_name', $Add_PermitionInt, $Sell_PermitionInt, $AgeInt, $Type_Number);";

	$mysql_qryCheck = "SELECT * FROM users WHERE Email LIKE '$email' ;";
	
	$result = mysqli_query($conn, $mysql_qryCheck);

	if(mysqli_num_rows($result) > 0){
		echo "User Exists";
	}else{
		if($conn->query($mysql_qry) === TRUE){
			$mailto = $email;
		    $mailSub = "Pharmacy Application";
		    $mailMsg = "<h3>Click on the link Below to Verify your mail</h3><br><a href='http://192.168.1.7/AndroidAppDatabase/Verify.php?Email=$email'>Verify Your Email</a>";

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
			$mail ->Password = "your Password";
			$mail ->SetFrom("from name");
			$mail ->Subject = $mailSub;
			$mail ->Body = $mailMsg;
			$mail ->AddAddress($mailto);

			if(!$mail->Send()) {
				
				$mysql_qryDelete = "DELETE FROM users WHERE Email like '$email';";
				if ($conn->query($mysql_qryDelete) === TRUE){
					echo "Failed";
				}
			} else {
			   echo "Success";
			}

		}else{
			echo "Error: ". $mysql_qry . "<br>" . $conn->error;
		}
	}
	$conn->close();
?>
