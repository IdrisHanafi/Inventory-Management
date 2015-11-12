<?php
$con=mysqli_connect("127.0.0.1","","","test");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL " . mysqli_connect_error();
}

$uName = $_POST['userName'];


$sql = "DELETE FROM users WHERE uName= '$uName'";

if ($con->query($sql) === TRUE) {
    echo "User Deleted successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

mysqli_close($con);
?>

