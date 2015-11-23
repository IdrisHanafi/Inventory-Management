<?php
 
/*
 * Following code will get single product details
 * A product is identified by product upcCode
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/DB_Connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
if (isset($_POST["upcCode"])) {
    $upcCode = $_POST['upcCode'];

    // get a product from products table
    $result = mysql_query("SELECT * FROM products WHERE upcCode = $upcCode");
 
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
            $result = mysql_fetch_array($result);
 
            $product = array();
            $product["productName"] = $result["productName"];
            $product["description"] = $result["description"];
            $product["upcCode"] = $result["upcCode"];
            $product["wholesalePrice"] = $result["wholesalePrice"];
            $product["retailPrice"] = $result["retailPrice"];
            $product["quantity"] = $result["quantity"];
            $product["location"] = $result["location"];
            // success
            $response["success"] = 1;
 
            // user node
            $response["product"] = array();
 
            array_push($response["product"], $product);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["failed"] = 0;
            $response["message"] = "No product found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["failed"] = 0;
        $response["message"] = "No product found";
 
        // echo no users JSON
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