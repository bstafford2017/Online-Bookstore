<!doctype html>
    <head>
        <?php 
            include 'required';
        ?>
        <title id="title">Search books</title>
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