<?php
session_start();
require 'db.php';

if (!isset($_SESSION['user_id']) || $_SESSION['role'] !== 'admin') {
    header("Location: index.php");
    exit;
}

// Fetch all members from the `users` collection where role = "member"
$members = $usersCollection->find(['role' => 'member'])->toArray();

// Handle Member Deletion
if (isset($_POST['delete_member'])) {
    $memberId = $_POST['member_id'];
    $usersCollection->deleteOne(['_id' => new MongoDB\BSON\ObjectId($memberId)]);
    echo "<script>alert('Member deleted successfully!'); window.location='manage_members.php';</script>";
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Manage Members</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="text-center">Manage Members</h2>
        <table class="table table-bordered table-striped mt-3">
            <thead class="table-dark">
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Membership</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <?php if (!empty($members)): ?>
                    <?php foreach ($members as $member): ?>
                        <tr>
                            <td><?= $member['name'] ?></td>
                            <td><?= $member['email'] ?></td>
                            <td><?= $member['membership'] ?? 'None' ?></td>
                            <td>
                                <form method="post">
                                    <input type="hidden" name="member_id" value="<?= $member['_id'] ?>">
                                    <button type="submit" name="delete_member" class="btn btn-danger btn-sm">Delete</button>
                                </form>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                <?php else: ?>
                    <tr><td colspan="4" class="text-center">No members found.</td></tr>
                <?php endif; ?>
            </tbody>
        </table>
        <a class="btn btn-secondary mt-3" href="dashboard.php">Back to Dashboard</a>
    </div>
</body>
</html>
