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
        <p><a id="source" href="#">View Source</a></p>
        <div id="display-source"></div>
        <?php
            include 'footer.php';
        ?>
        <script>
            $('#source').click(function(e){
                e.preventDefault();
                $.ajax({
                    method: "GET",
                    url: "cgi-bin/source.cgi",
                    data: {"filename": "search"},
                    success: function(data){
                        $('#display-source').empty();                
                        $('#display-source').append(data);
                    },
                    error: function(data){
                        $('#display-source').empty();    
                        $('#display-source').append(data);
                    }
                });
            });
        </script>
    </body>
</html>
