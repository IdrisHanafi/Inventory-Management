<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();


// check for required fields
if (isset($_POST['productName']) && isset($_POST['description']) && isset($_POST['upcCode']) 
   && isset($_POST['wholesalePrice']) && isset($_POST['retailPrice']) && isset($_POST['quantity']) 
   && isset($_POST['location'])) {
 
    $productName = $_POST['productName'];
    $description = $_POST['description'];
    $upcCode = $_POST['upcCode'];
    $wholesalePrice = $_POST['wholesalePrice'];
    $retailPrice = $_POST['retailPrice'];
    $quantity = $_POST['quantity'];
    $location = $_POST['location'];
 
    $floatWP = floatval($wholesalePrice);
    $floatRP = floatval($retailPrice);
    $intQuantity = intval($quantity);

    // include db connect class
    require_once __DIR__ . '/DB_Connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO products (productName, description, upcCode, wholesalePrice, retailPrice, quantity, location) VALUES ('$productName','$description','$upcCode','$floatWP', '$floatRP', '$intQuantity', '$location')");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
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