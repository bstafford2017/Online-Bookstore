<!doctype html>
    <head>
        <meta charset="UTF-8">
        <link rel="icon" href="./favicon.png"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title id="title">Search books</title>
        <link rel="stylesheet" href="style.css">
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            // Get all subjects on load
            $.ajax({
                type: "get",
                url: "cgi-bin/subject.cgi",
                success: function(data){
                    $(function(){
                        $('html').append(data);
                    });
                },
                error: function(data){
                    $(function(){
                        alert(JSON.stringify(data) + "Error with loading subjects");
                        $('#subject').append(data);
                    });
                }
            });
        </script>
    </head>
    <body>
        <?php
            include 'navbar.php';
        ?>
    
        <!-- Content-->
        <div id="header"></div>
        <div class="container">
            <div class="row">
                <div class="col-sm" style="text-align: center;">
                    <h2 id="search-title" style="padding:10px; color:white;">Search for a book</h2>
                </div>
            </div>
        </div>
        <form id="form" action="cgi-bin/search.cgi">
            <div id="subject" class="form-text"></div>
            <small id="search-error" class="form-text text-muted" style="color: red;"></small>
            <input id="search-box" name="search-box" type="text" class="col-sm-7 offset-sm-1 form-control" placeholder="(i.e. ISBN, Title, Price or Subject)"/>
            <button type="submit" class="col-sm-2 btn btn-outline-primary">Search</button>
        </form>
        <div id="table"></div>
        <form method="post" action="cgi-bin/clear.cgi">
            <button type="submit" class="col-sm-2 btn btn-outline-primary">Clear all</button>
        </form>

        <script type="text/javascript">
            $("#form").submit(function(e){
                e.preventDefault();
                var form = $(this);
                var url = form.attr('action');
                console.log(url);
                $.ajax({
                    type: "post",
                    url: url,
                    data: form.serialize(),
                    success: function(data){
                        $('html').append(data);
                    },
                    error: function(data){
                        alert(data +"Error of AJAX");
                    }
                });
            });
        </script>
    </body>
</html>