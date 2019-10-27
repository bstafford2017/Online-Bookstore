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
        <table>
            