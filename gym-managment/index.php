<?php
session_start();
require 'db.php';

// Handle login
if (isset($_POST['login'])) {
    $email = $_POST['email'];
    $password = $_POST['password'];
    $user = $usersCollection->findOne(['email' => $email]);
    if ($user && password_verify($password, $user['password'])) {
        $_SESSION['user_id'] = (string) $user['_id'];
        $_SESSION['role'] = $user['role'];
        header("Location: dashboard.php");
        exit;
    } else {
        echo "<script>alert('Invalid login!');</script>";
    }
}

// Handle registration
if (isset($_POST['register'])) {
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = password_hash($_POST['password'], PASSWORD_BCRYPT);
    $role = $_POST['role'];
    $usersCollection->insertOne(['name' => $name, 'email' => $email, 'password' => $password, 'role' => $role]);
    echo "<script>alert('Registration successful! Please login.');</script>";
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Gym Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card mx-auto p-4" style="max-width: 400px;">
            <h2 class="text-center">Login</h2>
            <form method="post">
                <input class="form-control my-2" type="email" name="email" placeholder="Email" required>
                <input class="form-control my-2" type="password" name="password" placeholder="Password" required>
                <button class="btn btn-primary w-100" type="submit" name="login">Login</button>
            </form>
            <hr>
            <h2 class="text-center">Register</h2>
            <form method="post">
                <input class="form-control my-2" type="text" name="name" placeholder="Full Name" required>
                <input class="form-control my-2" type="email" name="email" placeholder="Email" required>
                <input class="form-control my-2" type="password" name="password" placeholder="Password" required>
                <select class="form-control my-2" name="role">
                    <option value="member">Member</option>
                    <option value="admin">Admin</option>
                    <option value="trainer">Trainer</option>
                </select>
                <button class="btn btn-success w-100" type="submit" name="register">Register</button>
            </form>
        </div>
    </div>
</body>
</html>
