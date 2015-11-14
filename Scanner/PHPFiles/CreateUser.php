<?php
$con=mysqli_connect("127.0.0.1","","","test");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL " . mysqli_connect_error();
}
$fName = $_POST['firstName'];
$lName = $_POST['lastName'];
$uName = $_POST['userName'];
$salt = $_POST['newSalt'];
$pass = $_POST['hashWord'];
$level = $_POST['level'];

$sql = "INSERT INTO users (fName, lName, uName, salt, pass, level)
VALUES ('$fName','$lName','$uName','$salt', '$pass', '$level')";

if ($con->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

mysqli_close($con);
?>

