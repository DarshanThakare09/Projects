<?php
session_start();
require 'db.php';

if (!isset($_SESSION['user_id']) || $_SESSION['role'] !== 'admin') {
    header("Location: index.php");
    exit;
}

// Update Payment Status
if (isset($_POST['update_payment'])) {
    $user_id = new MongoDB\BSON\ObjectId($_POST['user_id']);
    $new_status = $_POST['status'];

    $usersCollection->updateOne(
        ['_id' => $user_id],
        ['$set' => ['payment_status' => $new_status]]
    );

    echo "<script>alert('Payment status updated successfully!'); window.location='manage_payments.php';</script>";
}

// Fetch Users Who Have Memberships
$users = $usersCollection->find(
    ['membership' => ['$exists' => true]]
)->toArray();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Manage Payments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="text-center">Manage Payments</h2>
        <table class="table table-bordered">
            <tr>
                <th>User Name</th>
                <th>Membership</th>
                <th>Amount</th>
                <th>Payment Status</th>
                <th>Update Status</th>
            </tr>
            <?php foreach ($users as $user): ?>
                <tr>
                    <td><?= $user['name'] ?></td>
                    <td><?= $user['membership'] ?></td>
                    <td>₹<?= isset($user['membership_price']) ? $user['membership_price'] : 'N/A' ?></td>
                    <td><?= isset($user['payment_status']) ? $user['payment_status'] : 'Pending' ?></td>
                    <td>
                        <form method="post">
                            <input type="hidden" name="user_id" value="<?= $user['_id'] ?>">
                            <select name="status" class="form-control">
                                <option value="Pending" <?= isset($user['payment_status']) && $user['payment_status'] == 'Pending' ? 'selected' : '' ?>>Pending</option>
                                <option value="Done" <?= isset($user['payment_status']) && $user['payment_status'] == 'Done' ? 'selected' : '' ?>>Done</option>
                            </select>
                            <button type="submit" name="update_payment" class="btn btn-primary mt-2">Update</button>
                        </form>
                    </td>
                </tr>
            <?php endforeach; ?>
        </table>
        <a class="btn btn-secondary mt-3" href="dashboard.php">Back to Dashboard</a>
    </div>
</body>
</html>
