<?php
    function viewSource($filename){
        if(isset($_POST['source'])){
            header("Content-type: text/plain");
            $stream = fopen( strval($fileName),"r") or exit("Failed to display source");
            while(!feof($stream)){
                echo fgets($stream);
            }
            fclose($stream);
        }
    }
?>