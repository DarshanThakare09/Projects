<?php
session_start();
require 'db.php';

if (!isset($_SESSION['user_id']) || $_SESSION['role'] !== 'admin') {
    header("Location: index.php");
    exit;
}

// Fetch all trainers from the `users` collection where role = "trainer"
$trainers = $usersCollection->find(['role' => 'trainer'])->toArray();

// Handle Trainer Deletion
if (isset($_POST['delete_trainer'])) {
    $trainerId = $_POST['trainer_id'];
    $usersCollection->deleteOne(['_id' => new MongoDB\BSON\ObjectId($trainerId)]);
    echo "<script>alert('Trainer deleted successfully!'); window.location='manage_trainers.php';</script>";
}

// Handle Adding a New Trainer
if (isset($_POST['add_trainer'])) {
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = password_hash($_POST['password'], PASSWORD_DEFAULT); // Hash password for security

    $usersCollection->insertOne([
        'name' => $name,
        'email' => $email,
        'password' => $password,
        'role' => 'trainer'
    ]);

    echo "<script>alert('Trainer added successfully!'); window.location='manage_trainers.php';</script>";
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Manage Trainers</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="text-center">Manage Trainers</h2>

        <!-- Add Trainer Form -->
        <div class="card p-4 mb-4">
            <h4>Add New Trainer</h4>
            <form method="post">
                <div class="mb-3">
                    <label class="form-label">Name:</label>
                    <input type="text" name="name" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email:</label>
                    <input type="email" name="email" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password:</label>
                    <input type="password" name="password" class="form-control" required>
                </div>
                <button type="submit" name="add_trainer" class="btn btn-primary">Add Trainer</button>
            </form>
        </div>

        <!-- Trainers Table -->
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <?php if (!empty($trainers)): ?>
                    <?php foreach ($trainers as $trainer): ?>
                        <tr>
                            <td><?= $trainer['name'] ?></td>
                            <td><?= $trainer['email'] ?></td>
                            <td>
                                <form method="post">
                                    <input type="hidden" name="trainer_id" value="<?= $trainer['_id'] ?>">
                                    <button type="submit" name="delete_trainer" class="btn btn-danger btn-sm">Delete</button>
                                </form>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                <?php else: ?>
                    <tr><td colspan="3" class="text-center">No trainers found.</td></tr>
                <?php endif; ?>
            </tbody>
        </table>

        <a class="btn btn-secondary mt-3" href="dashboard.php">Back to Dashboard</a>
    </div>
</body>
</html>
