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
			dataType: "html",
			contentType: "application/json; charset=utf-8",
                    success: function(data){
                        $('#display-source').append("<p>" + JSON.stringify(data) + "</p>");
                    },
                    error: function(data){
                        $('#display-source').append("<p>" + JSON.stringify(data) + "</p>");
                    }
                });
            });
        </script>
    </body>
</html>
