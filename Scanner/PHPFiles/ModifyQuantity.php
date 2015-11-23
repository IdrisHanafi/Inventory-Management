<?php
 
/*
 * Following code will update a product information
 * A product is identified by product upcCode
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['upcCode']) && isset($_POST['quantity']) && isset($_POST['quantitySold'])) {
 
    $upcCode = $_POST['upcCode'];
    $quantity = $_POST['quantity'];
    $quantitySold = $_POST['quantitySold'];

    $intQuantity = intval($quantity);
    $intQuantitySold = intval($quantitySold);

    // include db connect class
    require_once __DIR__ . '/DB_Connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched upcCode
    $result = mysql_query("UPDATE products SET quantity='$intQuantity', quantitySold='$intQuantitySold' WHERE upcCode='$upcCode'");
 
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