<?php
session_start();
if (!isset($_SESSION['user_id'])) {
    header("Location: index.php");
    exit;
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="text-center">
            <h1 class="mb-4">Welcome to Gym Management System</h1>
        </div>

        <div class="row">
            <?php if ($_SESSION['role'] === 'admin'): ?>
                <div class="col-md-4">
                    <a class="btn btn-warning w-100 mb-3" href="manage_members.php">Manage Members</a>
                </div>
                <div class="col-md-4">
                    <a class="btn btn-secondary w-100 mb-3" href="manage_trainers.php">Manage Trainers</a>
                </div>
                <div class="col-md-4">
                    <a class="btn btn-info w-100 mb-3" href="manage_payments.php">Manage Payments</a>
                </div>
            <?php else: ?>
                <div class="col-md-4">
                    <a class="btn btn-primary w-100 mb-3" href="view_subscription.php">View Subscription</a>
                </div>
                <div class="col-md-4">
                    <a class="btn btn-success w-100 mb-3" href="book_class.php">Book a Class</a>
                </div>
                <div class="col-md-4">
                    <a class="btn btn-dark w-100 mb-3" href="track_progress.php">Track Progress</a>
                </div>
            <?php endif; ?>

            <?php if ($_SESSION['role'] === 'member'): ?>
                <div class="col-md-4">
                    <a href="get_membership.php" class="btn btn-warning w-100 mb-3">Get Membership</a>
                </div>
                <div class="col-md-4">
                    <a href="update_progress.php" class="btn btn-success w-100 mb-3">Update Progress</a>
                </div>
            <?php endif; ?>
        </div>

        <div class="text-center mt-4">
            <a class="btn btn-danger" href="logout.php">Logout</a>
        </div>
    </div>
</body>
</html>
