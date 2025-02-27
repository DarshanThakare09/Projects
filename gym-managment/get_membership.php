<?php
session_start();
require 'db.php';

if (!isset($_SESSION['user_id'])) {
    header("Location: index.php");
    exit;
}

// Handle Membership Selection
if (isset($_POST['select_membership'])) {
    $membership = $_POST['membership'];
    $usersCollection->updateOne(
        ['_id' => new MongoDB\BSON\ObjectId($_SESSION['user_id'])],
        ['$set' => ['membership' => $membership, 'status' => 'active']]
    );
    echo "<script>alert('Membership activated successfully!'); window.location='dashboard.php';</script>";
}

// Fetch Membership Plans
$membershipPlans = $membershipCollection->find()->toArray(); // ✅ Fix Here
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Choose Membership</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="text-center">Choose Your Membership Plan</h2>
        <form method="post" class="card p-4">
            <label for="membership">Select Membership:</label>
            <select name="membership" class="form-control" required>
                <?php foreach ($membershipPlans as $plan): ?>
                    <option value="<?= $plan['name'] ?>"><?= $plan['name'] ?> - ₹<?= $plan['price'] ?>/<?= $plan['duration'] ?></option>
                <?php endforeach; ?>
            </select>
            <button type="submit" name="select_membership" class="btn btn-success mt-3">Subscribe</button>
        </form>
        <a class="btn btn-secondary mt-3" href="dashboard.php">Back to Dashboard</a>
    </div>
</body>
</html>
