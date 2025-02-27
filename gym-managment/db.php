<?php
require 'vendor/autoload.php'; 

$mongoClient = new MongoDB\Client("mongodb://localhost:27017");
$database = $mongoClient->gym_management;

$usersCollection = $database->users;
$membershipCollection = $database->membership;
// $paymentsCollection = $database->payments; 
// $trainersCollection = $database->trainers; 
?>
