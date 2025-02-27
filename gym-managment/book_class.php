<?php
session_start();
require 'db.php';

if ($_SESSION['role'] !== 'member') {
    echo "Access Denied!";
    exit;
}

if (isset($_POST['book'])) {
    $class = $_POST['class_name'];
    $usersCollection->updateOne(
        ['_id' => new MongoDB\BSON\ObjectID($_SESSION['user_id'])],
        ['$push' => ['booked_classes' => $class]]
    );
    echo "<script>alert('Class booked successfully!');</script>";
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Book a Class</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-4">
        <h2 class="text-center">Book a Class</h2>
        <form method="post" class="mt-3">
            <select class="form-control my-2" name="class_name">
                <option value="Yoga">Yoga</option>
                <option value="CrossFit">CrossFit</option>
                <option value="Zumba">Zumba</option>
            </select>
            <button class="btn btn-primary w-100" type="submit" name="book">Book Now</button>
        </form>
        <a class="btn btn-secondary mt-3" href="dashboard.php">Back</a>
    </div>
</body>
</html>
