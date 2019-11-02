<!doctype html>
    <head>
        <?php 
            include 'required.php';
        ?>
        <title id="title">Create</title>
    </head>
    <body>
        <?php
            include 'navbar.php';
        ?>
        
        <div id="header"></div>
        <div class="container">
            <div class="row">
                <div class="col-sm" style="text-align: center;">
                    <h2 id="create-title" style="padding:10px;">Create a book submission</h2>
                </div>
            </div>
        </div>
        <form class="col-sm-4 offset-sm-4" method="get" action="cgi-bin/create.cgi">
            <div class="form-group" >
                <label>Book Title</label>
                <input id="book-title" name="book-title" type="text" class="form-control" placeholder="i.e. Web Programming" required/>
                <p id="title-error" class="form-text text-muted" style="color: red;"></p>
            </div>
            <div class="form-group">
                <label>Price</label>
                <input id="price" name="price" type="text" class="form-control" placeholder="i.e. 12.99" required/>
                <p id="price-error" class="form-text text-muted" style="color: red;"></p>
            </div>
            <div class="form-group">
                <label>ISBN</label>
                <input id="isbn" name="isbn" type="text" class="form-control" placeholder="i.e. 0123456789" required/>
                <p id="isbn-error" class="form-text text-muted" style="color: red;"></p>
            </div>
            <div class="form-group">
                <label>Subjects</label>
                <input id="subjects" name="subjects" type="text" class="form-control" placeholder="i.e. Engineering" required/>
                <p id="subjects-error" class="form-text text-muted" style="color: red;"></p>
            </div>
            <input id="submit" type="submit" class="col-sm-2 offset-sm-5 btn btn-dark" value="Search">
        </form>
        <p id="success" style="color: green;"></p>
        <p id="error" style="color: red;"></p>
        <p><a id="source" href="#">View Source</a></p>
        <div id="display-source"></div>
        <?php
            include 'footer.php';
        ?>
        <script>
            $('#source').click(function(e){
                e.preventDefault();
                $.ajax({
                    type: "get",
                    url: "cgi-bin/source.cgi",
                    data: {filename: "search"},
                });
            });
            /*$('#submit').click(function(e){
                $.ajax({
                    type: "post",
                    url: "cgi-bin/create.cgi",
                    success: function(data){
                        $('#success').append("Successfully created book!");                    
                    },
                    error: function(data){
                        $('#error').append("Error when creating book!");                    
                    }
                });
            });*/
        </script>
    </body>
</html>