        </table>
        <?php
            include 'footer.php';
        ?>
        <script type="text/javascript">
        function submit(){

            let isbn = $('#isbn').val();
            let title = $('#title').val();
            let price = $('#price').val();
            let subjects = $('#subjects').val();
            
            $.ajax({
                method: "post",
                url: "cgi-bin/info.cgi",
                data: isbn,
                success: function(data){
                    alert(data);
                }, 
                error: function(data){
                    alert(data)
                }
            });
        }
        </script>
   </body>
</html>