<?php
 
/*
 * Following code will update a product information
 * A product is identified by product upcCode
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['upcCode']) && isset($_POST['description']) && isset($_POST['wholesalePrice']) && isset($_POST['retailPrice']) && isset($_POST['location'])) {
 
    $upcCode = $_POST['upcCode'];
    $description = $_POST['description'];
    $wholesalePrice = $_POST['wholesalePrice'];
    $retailPrice = $_POST['retailPrice'];
    $location = $_POST['location'];

    // include db connect class
    require_once __DIR__ . '/DB_Connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched upcCode
    $result = mysql_query("UPDATE products SET description='$description', wholesalePrice='$wholesalePrice', retailPrice='$retailPrice', location='$location' WHERE upcCode='$upcCode'");
 
    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
 
        $response["failed"] = 0;
        $response["message"] = "Oops! An error occurred.";

        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["failed"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>