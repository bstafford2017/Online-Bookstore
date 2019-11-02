<!doctype html>
    <head>
        <?php 
            include 'required.php';
        ?>
        <title id="title">Search books</title>
        <script type="text/javascript">
            // Get all subjects on load
            $.ajax({
                type: "get",
                url: "cgi-bin/subject.cgi",
                success: function(data){
                    $(function(){
                        $('#subject').append(data);
                    });
                },
                error: function(data){
                    $(function(){
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
                    <h2 id="search-title" style="padding:10px;">Search for a book</h2>
                </div>
            </div>
        </div>
        <form id="form" action="cgi-bin/search.cgi">
            <div id="subject" class="form-text"></div>
            <small id="search-error" class="form-text text-muted" style="color: red;"></small>
            <input id="search-box" name="search-box" type="text" class="col-sm-6 offset-sm-2" placeholder="(i.e. ISBN, Title, Price or Subject)"/>
            <button type="submit" class="col-sm-2 btn btn-dark">Search</button>
        </form>
        <table class="table table-striped thead-dark col-sm-6 offset-sm-3">
            <thead>
                <tr>
                    <th scope="col">ISBN</th>
                    <th scope="col">Title</th>
                    <th scope="col">Price</th>
                    <th scope="col">Subject(s)</th>
                </tr>
            </thead>
            <tbody id="table-body">
                
            </tbody>
        </table>
        <button id="clear" type="submit" class="col-sm-2 offset-sm-5 btn btn-dark">Clear all</button>

        <p><a id="source" href="#">View Source</a></p>
        <div id="display-source"></div>
        <?php
            include 'footer.php';
        ?>
        <script type="text/javascript">
            $('#source').click(function(e){
                e.preventDefault();
                $.ajax({
                    type: "get",
                    url: "cgi-bin/source.cgi",
                    data: {filename: "search"},
                });
            });
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
                        $('#table-body').append(data);
                    },
                    error: function(data){
                        $('#table-body').append(data);
                    }
                });
            });

            $('#clear').click(function(e){
                e.preventDefault();
                var form = $(this);
                var url = form.attr('action');
                console.log(url);
                $.ajax({
                    type: "post",
                    url: url,
                    data: form.serialize(),
                    success: function(data){
                        $('body').append(data);
                    },
                    error: function(data){
                        $('body').append(data);
                    }
                });
            });
        </script>
    </body>
</html>