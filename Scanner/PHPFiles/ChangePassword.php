<?php
$con=mysqli_connect("127.0.0.1","","","test");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL " . mysqli_connect_error();
}

$uName = $_POST['userName'];
$salt = $_POST['newSalt'];
$pass = $_POST['hashWord'];

$sql = "UPDATE users SET salt='$salt', pass = '$pass' WHERE uName = '$uName'";

if ($con->query($sql) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

mysqli_close($con);
?>

