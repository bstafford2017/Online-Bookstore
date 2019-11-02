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
            <button type="submit" class="col-sm-2 offset-sm-5 btn btn-dark">Update</button>
        </form>

        <p><a id="source" href="#">View Source</a></p>
        <div id="display-source"></div>
        <?php
            include 'footer.php';
        ?>
        <script type="text/javascript">
            $('#form').submit(function(){
                let form = $('#form');
                $.ajax({
                type: "get",
                url: "cgi-bin/subject.cgi",
                data: form.serialize(),
                success: function(data){
                    $('#table-body').append(data);
                },
                error: function(data){
                    $('#table-body').append(data);
                }
                });
            });
        </script>
    </body>
</html>