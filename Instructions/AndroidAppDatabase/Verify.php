
<?php
    
    require "connect.php";

    $Email = $_GET['Email'];

    $mysql_qry = "UPDATE users SET Verified = 1 WHERE Email LIKE '$Email'";

    if($conn->query($mysql_qry) === TRUE){
        echo "Mail " . $Email ." Verified";
    }
?>