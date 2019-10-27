<!doctype html>
    <head>
        <?php 
            include 'required.php';
        ?>
        <title id="title">Home</title>
    </head>
    <body>
        <?php
            include 'navbar.php';
        ?>
        <h2 style="text-align:center;">Welcome to the bookstore!<h2>
        <p><a name="source">View Source</a></p>
        <?php
            include 'source.php';
            viewSource('index.php');
            include 'footer.php';
        ?>
    </body>
</html>