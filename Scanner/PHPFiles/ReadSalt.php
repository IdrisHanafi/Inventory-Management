<?php
$con=mysqli_connect("127.0.0.1","","","test");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL " . mysqli_connect_error();
}
$username = $_POST['username'];

$result = mysqli_query($con,"SELECT salt, pass, level FROM users WHERE uName='$username'");
$row = mysqli_fetch_array($result);
$arraylength = count($row);


$stuff =array();
for($x=0;$x<$arraylength/2;$x++)
  {
  echo $row[$x];
  echo "!!!";
  }


mysqli_close($con);
?>

