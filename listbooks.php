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
                url: "cgi-bin/listbooks.cgi",
                success: function(data){
                    $(function(){
                        $('#table-body').append(data);
                    });
                },
                error: function(data){
                    $(function(){
                        $('#table-body').append(data);
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
        <form id="form">
            <table class="table table-striped table-dark col-sm-6 offset-sm-3" style="margin-top: 2%;">
                <thead>
                    <tr>
                        <th scope="col">Select</th>
                        <th scope="col">Book</th>
                        <th scope="col">Price</th>
                    </tr>
                </thead>
                <tbody id="table-body">
                    
                </tbody>
            </table>
            <button id="submit" type="submit" class="col-sm-2 offset-sm-5 btn btn-dark">Update</button>
        </form>
        <p id="success" style="color: green;"></p>
        <p id="error" style="color: red;"></p>
        <p><a id="source" href="#">View Source</a></p>
        <div id="display-source"></div>
        <?php
            include 'footer.php';
        ?>
        <script type="text/javascript">
            $('#submit').click(function(e){
                let isbn = "";
                let price = "";
                $("input:checkbox[name='check']:checked").each(function()
                {
                    isbn.push($(this).val());
                    price.push($(this).closest('tr').find("td:last").val());
                });
                alert(isbn.toString());
                alert(price.toString());

                let form = $('#form');
                $.ajax({
                type: "get",
                url: "cgi-bin/updateprice.cgi",
                data: {isbn: price}
                success: function(data){
                    $('#success').empty();
                    $('#success').append("Success!");
                },
                error: function(data){
                    $('#error').empty();
                    $('#error').append("Error: " + data);
                }
                });
            });*/
        </script>
    </body>
</html>