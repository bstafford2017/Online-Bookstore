#!/usr/bin/perl
use strict;
use warnings;
use CGI; 
my $query = new CGI;
my $filename = $query->param('name');
print("Content-type: text/plain\n\n");

if(! defined $filename){
    exit(0);
}
if($filename eq 'search'){
    system("/bin/cat search.cgi search.pl Search.java");
} elsif($filename eq 'create'){
    system("/bin/cat create.cgi create.pl Create.java");
} elsif($filename eq 'index'){
    system("/bin/cat index.php");
}