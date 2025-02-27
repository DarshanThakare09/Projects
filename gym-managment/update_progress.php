<?php
session_start();
require 'db.php';

if (!isset($_SESSION['user_id'])) {
    header("Location: index.php");
    exit;
}

// Handle Progress Update
if (isset($_POST['update_progress'])) {
    $weight = $_POST['weight'];
    $bmi = $_POST['bmi'];
    
    $usersCollection->updateOne(
        ['_id' => new MongoDB\BSON\ObjectId($_SESSION['user_id'])],
        ['$set' => ['weight' => $weight, 'bmi' => $bmi]]
    );
    echo "<script>alert('Progress updated successfully!'); window.location='track_progress.php';</script>";
}

// Fetch Current User Data
$user = $usersCollection->findOne(['_id' => new MongoDB\BSON\ObjectId($_SESSION['user_id'])]);

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update Progress</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="text-center">Update Your Progress</h2>
        <form method="post" class="card p-4">
            <label>Weight (kg):</label>
            <input type="number" name="weight" class="form-control" value="<?= $user['weight'] ?? '' ?>" required>
            
            <label>BMI:</label>
            <input type="number" step="0.1" name="bmi" class="form-control" value="<?= $user['bmi'] ?? '' ?>" required>

            <button type="submit" name="update_progress" class="btn btn-primary mt-3">Update</button>
        </form>
        <a class="btn btn-secondary mt-3" href="dashboard.php">Back to Dashboard</a>
    </div>
</body>
</html>

