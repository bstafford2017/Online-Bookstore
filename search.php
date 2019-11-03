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
        <div class="container">
            <div class="row">
                <div class="col-sm" style="text-align: center;">
                    <h2 id="search-title" style="padding:10px;">Search for a book</h2>
                </div>
            </div>
        </div>
        <form id="form">
            <div id="subject" class="col-sm-8 offset-sm-2 bg-dark text-white" style="margin-bottom: 2%;"><h3 style="text-align:center;">Subjects:</h3></div>
            <small id="search-error" class="form-text text-muted" style="color: red;"></small>
            <input id="search-box" name="search-box" type="text" class="col-sm-6 offset-sm-2" placeholder="(i.e. ISBN, Title, Price or Subject)"/>
            <button id="search" type="submit" class="col-sm-2 btn btn-dark">Search</button>
        </form>
        <table class="table table-striped table-dark col-sm-6 offset-sm-3" style="margin-top: 2%;">
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
                    success: function(data){
                        $('html').empty();    
                        $('html').html(data);
                    },
                    error: function(data){
                        $('html').empty();    
                        $('html').html(data);
                    }
                });
            });
            
            $("#search").click(function(e){
                e.preventDefault();
                let subject = [];
                $("input:checkbox:checked").each(function(){
                    subject.push($(this).val());
                });
                let json = "";
                if(subject.toString() != undefined || subject.toString() == ""){
                    json = "subject" + "=" + subject.toString();
                    json = json.replace(",", "+");
                }
                if($('#search-box').val() != ""){
                    json = json + "&search=" + $('#search-box').val();
                }
                console.log(json);
                $.ajax({
                    type: "get",
                    url: "cgi-bin/search.cgi",
                    data: json,
                    success: function(data){
                        $('#table-body').empty();                
                        $('#table-body').append(data);
                    },
                    error: function(data){
                        $('#table-body').empty();    
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