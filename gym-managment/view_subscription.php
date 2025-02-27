<?php
session_start();
require 'db.php';

if (!isset($_SESSION['user_id'])) {
    header("Location: index.php");
    exit;
}

// Fetch user details
$user = $usersCollection->findOne(['_id' => new MongoDB\BSON\ObjectId($_SESSION['user_id'])]);

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>View Subscription</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="text-center">Your Subscription Details</h2>
        <div class="card p-4">
            <p><strong>Name:</strong> <?= $user['name'] ?? 'N/A' ?></p>
            <p><strong>Email:</strong> <?= $user['email'] ?? 'N/A' ?></p>
            <p><strong>Membership Plan:</strong> <?= isset($user['membership']) ? $user['membership'] : 'No active membership' ?></p>
        </div>
        <a class="btn btn-secondary mt-3" href="dashboard.php">Back to Dashboard</a>
    </div>
</body>
</html>
