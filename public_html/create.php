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
                    <h2 id="create-title" style="padding:10px; color:white; ">Create a book submission</h2>
                </div>
            </div>
        </div>
        <form id="form" class="col-sm-4 offset-sm-4" style="color:white;">
            <div class="form-group" >
                <label>Book Title</label>
                <input id="book-title" name="book-title" type="text" class="form-control" placeholder="i.e. Web Programming"/>
                <small id="title-error" class="form-text text-muted" style="color: red;"></small>
            </div>
            <div class="form-group">
                <label>Price</label>
                <input id="price" name="author" type="text" class="form-control" placeholder="i.e. 12.99"/>
                <small id="price-error" class="form-text text-muted" style="color: red;"></small>
            </div>
            <div class="form-group">
                <label>ISBN</label>
                <input id="isbn" name="isbn" type="text" class="form-control" placeholder="i.e. 0123456789"/>
                <small id="isbn-error" class="form-text text-muted" style="color: red;"></small>
            </div>
            <div class="form-group">
                <label>Subjects</label>
                <input id="subjects" name="subjects" type="text" class="form-control" placeholder="i.e. Software Development, Computer Science, etc."/>
                <small id="subjects-error" class="form-text text-muted" style="color: red;"></small>
            </div>
            <button id="submit" type="submit" class="col-sm-2 offset-sm-5 btn btn-outline-primary">Submit</button>
        </form>
        <script>
            $('#form').submit(function (e){
                e.preventDefault();
                if(!$('book-title').val){
                    $('title-error').append("Please enter a valid book title");
                } else if(!$('price').val){
                    $('price-error').append("Please enter a valid book price");
                } else if(!$('isbn').val){
                    $('isbn-error').append("Please enter a valid book isbn");
                } else if(!$('subjects').val){
                    $('subjects-error').append("Please enter a valid book subject");
                } else {
                    $.ajax({
                        type: "post",
                        url: "cgi-bin/create.cgi",
                        success: function(data){
                            alert("Success in AJAX");
                        },
                        error: function(){
                            alert("Error in AJAX");
                        }
                    });
                }
            });
        </script>
    </body>
</html>